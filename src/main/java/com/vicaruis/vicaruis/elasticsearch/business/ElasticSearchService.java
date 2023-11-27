package com.vicaruis.vicaruis.elasticsearch.business;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.GetResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vicaruis.vicaruis.core.configurations.AppElasticSearchClientConfiguration;
import com.vicaruis.vicaruis.core.exceptions.InternalErrorException;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_document.CreateDocumentRequestDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_document.CreateDocumentResponseDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_index.CreateIndexRequestDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_index.CreateIndexResponseDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.get_document_by_id.GetDocumentByIdResponseDto;
import com.vicaruis.vicaruis.elasticsearch.business.exceptions.DocumentIdNotFoundException;
import com.vicaruis.vicaruis.elasticsearch.business.exceptions.IndexNameAlreadyExistsException;
import com.vicaruis.vicaruis.elasticsearch.business.exceptions.IndexNameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class ElasticSearchService {

    ElasticsearchClient esClient;

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchService.class);

    public ElasticSearchService(AppElasticSearchClientConfiguration appElasticSearchClient) {
        this.esClient = appElasticSearchClient.getClient();
    }

    public CreateIndexResponseDto createIndex(CreateIndexRequestDto properties) {
        try {
            String indexName = properties.getName();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> a = new HashMap<>();

            if (properties.getAliases() != null) {
                a.put("aliases", properties.getAliases());
            }

            if (properties.getSettings() != null) {
                a.put("settings", properties.getSettings());
            }

            if (properties.getMappings() != null) {
                a.put("mappings", properties.getMappings());
            }

            String jsonSettings = objectMapper.writeValueAsString(a);

            if (this.indexExists(indexName)) {
                throw new IndexNameAlreadyExistsException("Index already exists: " + indexName);
            }

            String id = esClient.indices().create(c -> c
                    .index(indexName)
                    .withJson(new StringReader(jsonSettings))).index();

            return new CreateIndexResponseDto(id, indexName);

        }
        catch (IndexNameAlreadyExistsException e) {
            LOG.debug("Index already exists: " + e);
            throw new IndexNameAlreadyExistsException(e.getMessage());
        }
        catch (JsonProcessingException e) {
            LOG.debug("Error writing properties to JSON: " + e);
        } catch (ElasticsearchException e) {
            LOG.debug("Error creating index in Elasticsearch: " + e);
            throw new ElasticsearchException(e.getMessage(),e.response());
        } catch (Exception e) {
            LOG.debug("Unexpected error: " + e);
            throw new InternalErrorException("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    private boolean indexExists(String indexName) throws IOException {
        return esClient.indices().exists(c -> c.index(indexName)).value();
    }
    public CreateDocumentResponseDto createDocument(CreateDocumentRequestDto documentIndexReq) {
        try {

            String indexName = documentIndexReq.getIndexName();
            Object document = documentIndexReq.getDocument();
            if (!this.indexExists(indexName)) {
                throw new IndexNameNotFoundException("Index Not found " + indexName);
            }

            JSONObject jsonDocument = new JSONObject(document.toString());
            Reader input = new StringReader(
                    jsonDocument.toString()
                            .replace('\'', '"'));

            String id = esClient.index(i -> i
                    .index(indexName).withJson(input)

            ).id();

            System.out.println("ID: " + id);
            return new CreateDocumentResponseDto(id);

        } 
        catch (IndexNameNotFoundException e) {
            LOG.debug("Index not found: " + e);
            throw new IndexNameNotFoundException(e.getMessage());
        }
        catch (ElasticsearchException e) {
            LOG.debug("Error creating document in Elasticsearch: " + e);
            throw new ElasticsearchException(e.getMessage(),e.response());
        }
        catch (Exception e) {
            LOG.debug("Error creating document in Elasticsearch: " + e);
            throw new InternalErrorException("Error creating document in Elasticsearch: " + e.getMessage());
        }
    }


    public GetDocumentByIdResponseDto getDocumentById(String indexName, String id) {
        try {
            if (!this.indexExists(indexName)) {
                throw new IndexNameNotFoundException("Index Not found " + indexName);
            }

            GetResponse<ObjectNode> response = esClient.get(g -> g
                            .index(indexName)
                            .id(id),
                    ObjectNode.class
            );
            if (!response.found())  {
                LOG.debug("Product not found");
                throw new DocumentIdNotFoundException("Document Id Invalid " + id);
            }

            return new GetDocumentByIdResponseDto(response.source());
        }
        catch (IndexNameNotFoundException e) {
            LOG.debug("Index not found: " + e);
            throw new IndexNameNotFoundException(e.getMessage());
        }
        catch (DocumentIdNotFoundException e) {
            LOG.debug("Document Id Invalid " + e);
            throw new DocumentIdNotFoundException(e.getMessage());
        }
        catch (ElasticsearchException e) {
            LOG.debug("Error getting document in Elasticsearch: " + e);
            throw new ElasticsearchException(e.getMessage(),e.response());
        }
        catch (Exception e) {
            LOG.debug("Error getting document in Elasticsearch: " + e);
            throw new InternalErrorException("Error getting document in Elasticsearch: " + e.getMessage());
        }
    }
}
