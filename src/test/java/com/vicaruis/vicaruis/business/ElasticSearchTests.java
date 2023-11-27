package com.vicaruis.vicaruis.business;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vicaruis.vicaruis.core.configurations.AppElasticSearchClientConfiguration;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_document.CreateDocumentRequestDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_document.CreateDocumentResponseDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_index.CreateIndexRequestDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_index.CreateIndexResponseDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.get_document_by_id.GetDocumentByIdResponseDto;
import com.vicaruis.vicaruis.elasticsearch.business.ElasticSearchService;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ElasticSearchServiceTest {
   private ElasticSearchService elasticSearchService;
   private AppElasticSearchClientConfiguration appElasticSearchClientConfiguration;
   private ElasticsearchClient esClient;

   @BeforeEach
   void setUp() {
       esClient = mock(ElasticsearchClient.class);
       appElasticSearchClientConfiguration = mock(AppElasticSearchClientConfiguration.class);
       when(appElasticSearchClientConfiguration.getClient()).thenReturn(esClient);
       elasticSearchService = new ElasticSearchService(appElasticSearchClientConfiguration);
   }

   @Test
   void testCreateIndex() {
       // Prepare test data
       CreateIndexRequestDto properties = new CreateIndexRequestDto();
       properties.setName("testIndex");

       // Call the method you want to test
       CreateIndexResponseDto response = elasticSearchService.createIndex(properties);

       // Use assertions to verify the behavior
       assertNotNull(response);
       assertEquals("testIndex", response.getIndexName());
   }

   @Test
   void testCreateDocument() throws JSONException {
       // Prepare test data
       CreateDocumentRequestDto documentIndexReq = new CreateDocumentRequestDto("testIndex", new JSONObject("{ \"name\": \"John\" }"));
       documentIndexReq.setIndexName("testIndex");

       // Call the method you want to test
       CreateDocumentResponseDto response = elasticSearchService.createDocument(documentIndexReq);

       // Use assertions to verify the behavior
       assertNotNull(response);
   }

   @Test
   void testGetDocumentById() {
       // Prepare test data
       String indexName = "testIndex";
       String id = "testId";

       // Call the method you want to test
       GetDocumentByIdResponseDto response = elasticSearchService.getDocumentById(indexName, id);

       System.out.println("response " + response);
       // Use assertions to verify the behavior
       assertNotNull(response);
   }

}