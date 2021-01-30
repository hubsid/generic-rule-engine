package com.sidh.hobby.core.service;

import com.sidh.hobby.core.dto.EvaluationResult;
import com.sidh.hobby.core.dto.NumericalResult;
import com.sidh.hobby.core.enums.EvaluationType;
import com.sidh.hobby.core.enums.Operator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class Evaluators {
    private static Map<Operator, BinaryOperator<Integer>> INT_IN_INT_OUT;

    static {
        HashMap<Operator, BinaryOperator<Integer>> map = new HashMap<>();
        map.put(Operator.ADD, Integer::sum);
        map.put(Operator.SUB, (i, j) -> i - j);
        map.put(Operator.MUL, (i, j) -> i * j);
        map.put(Operator.DIV, (i, j) -> i / j);
        map.put(Operator.MOD, (i, j) -> i % j);

        INT_IN_INT_OUT = Collections.unmodifiableMap(map);
    }

    private static Map<Operator, BiFunction<Integer, Integer, Boolean>> INT_IN_BOOL_OUT;

    static {
        HashMap<Operator, BiFunction<Integer, Integer, Boolean>> map = new HashMap<>();
        map.put(Operator.LT, (i, j) -> i < j);
        map.put(Operator.LTE, (i, j) -> i <= j);
        map.put(Operator.GT, (i, j) -> i > j);
        map.put(Operator.GTE, (i, j) -> i >= j);
        map.put(Operator.EQ, Objects::equals);

        INT_IN_BOOL_OUT = Collections.unmodifiableMap(map);
    }

    private static Map<Operator, BinaryOperator<Double>> DOUBLE_IN_DOUBLE_OUT;

    static {
        HashMap<Operator, BinaryOperator<Double>> map = new HashMap<>();
        map.put(Operator.ADD, Double::sum);
        map.put(Operator.SUB, (i, j) -> i - j);
        map.put(Operator.MUL, (i, j) -> i * j);
        map.put(Operator.DIV, (i, j) -> i / j);
        map.put(Operator.MOD, (i, j) -> i % j);

        DOUBLE_IN_DOUBLE_OUT = Collections.unmodifiableMap(map);
    }

    private static Map<Operator, BiFunction<Double, Double, Boolean>> DOUBLE_IN_BOOL_OUT;

    static {
        HashMap<Operator, BiFunction<Double, Double, Boolean>> map = new HashMap<>();
        map.put(Operator.LT, (i, j) -> i < j);
        map.put(Operator.LTE, (i, j) -> i <= j);
        map.put(Operator.GT, (i, j) -> i > j);
        map.put(Operator.GTE, (i, j) -> i >= j);
        map.put(Operator.EQ, Objects::equals);

        DOUBLE_IN_BOOL_OUT = Collections.unmodifiableMap(map);
    }

    private static Map<Operator, BinaryOperator<Boolean>> BOOL_IN_BOOL_OUT;

    static {
        HashMap<Operator, BinaryOperator<Boolean>> map = new HashMap<>();
        map.put(Operator.AND, (i, j) -> i && j);
        map.put(Operator.OR, (i, j) -> i || j);
        map.put(Operator.XOR, (i, j) -> i ^ j);
        map.put(Operator.NE, (i, j) -> i != j);
        map.put(Operator.EQ, (i, j) -> i == j);

        BOOL_IN_BOOL_OUT = Collections.unmodifiableMap(map);
    }


    public static BiFunction getEvaluator(EvaluationResult input, Operator operator) {
        EvaluationType evaluationType = OperatorEvaluationType.mapping.get(operator);

        if (input instanceof NumericalResult) {
            NumericalResult numericalInput = ((NumericalResult) input);

            if (numericalInput.getResult() instanceof Integer) {
                if(evaluationType == EvaluationType.NUMERICAL)
                    return INT_IN_INT_OUT.get(operator);

                return INT_IN_BOOL_OUT.get(operator);
            }

            if(evaluationType == EvaluationType.NUMERICAL)
                return DOUBLE_IN_DOUBLE_OUT.get(operator);

            return DOUBLE_IN_BOOL_OUT.get(operator);
        }

        return BOOL_IN_BOOL_OUT.get(operator);
    }

}
