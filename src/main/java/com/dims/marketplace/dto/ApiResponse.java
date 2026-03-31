package com.dims.marketplace.dto;

import lombok.Getter;

@Getter
public class ApiResponse <T>{
    private int code;
    private String message;
    private T data;
}
