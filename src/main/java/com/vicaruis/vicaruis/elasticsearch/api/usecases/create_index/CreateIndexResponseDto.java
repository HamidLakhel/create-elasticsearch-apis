package com.vicaruis.vicaruis.elasticsearch.api.usecases.create_index;

public class CreateIndexResponseDto {
    String id;

    String indexName;

    public CreateIndexResponseDto(String id, String indexName) {
        this.id = id;
        this.indexName = indexName;
    }

    public String getId() {
        return id;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    @Override
    public String toString() {
        return "CreateIndexResponseDto{" +
                "id='" + id + '\'' +
                ", indexName='" + indexName + '\'' +
                '}';
    }
}
