package com.sidh.hobby.core.dto;

import lombok.Data;

@Data
public class BooleanResult implements EvaluationResult {
    protected boolean result;
}
