package com.sidh.hobby.core.dto;

import lombok.Data;

@Data
public class NumericalResult implements EvaluationResult {
    protected Number result;
}
