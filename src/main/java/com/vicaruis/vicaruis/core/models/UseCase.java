package com.vicaruis.vicaruis.core.models;

public interface UseCase<RequestDto, ResponseDto> {
    ResponseDto execute(RequestDto requestDto);
}
