package com.sidh.hobby.core.service;

import com.sidh.hobby.core.dto.BooleanResult;
import com.sidh.hobby.core.dto.EvaluationResult;
import com.sidh.hobby.core.dto.NumericalResult;
import com.sidh.hobby.core.dto.RuleNode;
import com.sidh.hobby.core.enums.EvaluationType;
import com.sidh.hobby.core.enums.Operator;

import java.util.Map;
import java.util.function.BiFunction;

public class EvaluationResultFactory {

    public static EvaluationResult make(Map<String, Object> data, RuleNode ruleNode) {
        Object value = data.get(ruleNode.getAttribute());

        EvaluationType evaluationType = OperatorEvaluationType.mapping.get(ruleNode.getPeerOperator());

        return newEvaluationResult(value, evaluationType);
    }

    public static EvaluationResult calculate(EvaluationResult lhs, Operator operator, EvaluationResult rhs) {
        BiFunction evaluator = Evaluators.getEvaluator(lhs, operator);

        Object result = evaluator.apply(lhs, rhs);

        EvaluationType evaluationType = OperatorEvaluationType.mapping.get(operator);

        return newEvaluationResult(result, evaluationType);
    }

    private static EvaluationResult newEvaluationResult(Object value, EvaluationType evaluationType) {
        EvaluationResult evaluationResult = null;
        if (evaluationType == EvaluationType.BOOLEAN) {
            BooleanResult booleanResult = new BooleanResult();
            booleanResult.setResult((Boolean) value);
            evaluationResult = booleanResult;
        } else if (evaluationType == EvaluationType.NUMERICAL) {
            NumericalResult result = new NumericalResult();
            result.setResult((Number) value);
            evaluationResult = result;
        }

        return evaluationResult;
    }
}
