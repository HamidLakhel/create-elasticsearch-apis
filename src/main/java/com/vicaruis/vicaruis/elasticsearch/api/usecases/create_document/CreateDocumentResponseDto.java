package com.vicaruis.vicaruis.elasticsearch.api.usecases.create_document;

public class CreateDocumentResponseDto {
    String id;

    public CreateDocumentResponseDto(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
