package com.coolers.housekeep.housekeep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class BussinessException extends RuntimeException {
    private String errorType;
    private String errorMessage;
}
