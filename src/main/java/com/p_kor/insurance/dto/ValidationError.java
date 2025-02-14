package com.p_kor.insurance.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class ValidationError {

    private String field;
    private String message;
}
