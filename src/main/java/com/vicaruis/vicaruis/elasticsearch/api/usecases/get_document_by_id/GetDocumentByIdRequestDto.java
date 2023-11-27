package com.vicaruis.vicaruis.elasticsearch.api.usecases.get_document_by_id;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;



public class GetDocumentByIdRequestDto {

    @NotNull(message = "IndexName cannot be null")
    @NotEmpty(message = "Index name cannot be empty")
    String indexName;

    @NotNull(message = "Document cannot be null")
    @NotEmpty(message = "id name cannot be empty")
    String id;

    public GetDocumentByIdRequestDto(@NotNull String indexName, @NotNull String id) {
        this.indexName = indexName;
        this.id = id;
    }

    public @NotNull String getIndexName() {
        return indexName;
    }

    public @NotNull String getId() {
        return id;
    }
}
