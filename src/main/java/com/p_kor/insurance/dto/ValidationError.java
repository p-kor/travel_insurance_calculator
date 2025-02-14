package com.p_kor.insurance.dto;

public record ValidationError(
        String field,
        String message) {
}
