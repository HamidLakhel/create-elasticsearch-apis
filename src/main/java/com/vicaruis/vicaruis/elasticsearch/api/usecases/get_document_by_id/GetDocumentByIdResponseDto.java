package com.vicaruis.vicaruis.elasticsearch.api.usecases.get_document_by_id;

public class GetDocumentByIdResponseDto{
    Object document;

    public GetDocumentByIdResponseDto(Object document) {
        this.document = document;
    }

    public Object getDocument() {
        return document;
    }

    public void setDocument(Object document) {
        this.document = document;
    }
}
