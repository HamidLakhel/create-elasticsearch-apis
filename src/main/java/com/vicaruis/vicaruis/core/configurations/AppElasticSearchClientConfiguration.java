package com.vicaruis.vicaruis.core.configurations;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import jakarta.annotation.PostConstruct;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppElasticSearchClientConfiguration {

        @Value("${elasticsearch.serverUrl}")
        private String serverUrl;

        @Value("${elasticsearch.apiKey}")
        private String apiKey;

        private RestClient restClient;
        private ElasticsearchTransport transport;
        private ElasticsearchClient esClient;

        private static final Logger LOG = LoggerFactory.getLogger(AppElasticSearchClientConfiguration.class);


        @Bean()
        public ElasticsearchClient getClient() {
                return esClient;
        }

        @PostConstruct
        public void init() {
                restClient = RestClient
                                .builder(HttpHost.create(serverUrl))
                                .setDefaultHeaders(new Header[] {
                                                new BasicHeader("Authorization", "ApiKey " + apiKey)
                                })
                                .build();

                transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
                esClient = new ElasticsearchClient(transport);
                LOG.info("Elasticsearch client initialized");
        }
}
