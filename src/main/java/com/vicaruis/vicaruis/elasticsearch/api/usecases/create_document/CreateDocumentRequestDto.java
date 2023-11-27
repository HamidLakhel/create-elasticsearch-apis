package com.vicaruis.vicaruis.elasticsearch.api.usecases.create_document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateDocumentRequestDto {
    @NotEmpty(message = "Index name cannot be empty")
    String indexName;

    @NotNull(message = "Document cannot be null")
    Object document;

    public CreateDocumentRequestDto(String indexName, @NotNull Object document) {
        this.indexName = indexName;
        this.document = document;
    }

    public String getIndexName() {
        return indexName;
    }

    public @NotNull Object getDocument() {
        System.out.println("getDocument: " + document);
        return document;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
}
