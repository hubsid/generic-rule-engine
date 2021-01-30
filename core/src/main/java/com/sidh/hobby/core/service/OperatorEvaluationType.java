package com.sidh.hobby.core.service;

import com.sidh.hobby.core.enums.EvaluationType;
import com.sidh.hobby.core.enums.Operator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OperatorEvaluationType {

    public static final Map<Operator, EvaluationType> mapping;

    static {
        HashMap<Operator, EvaluationType> mp = new HashMap<>();

        mp.put(Operator.ADD, EvaluationType.NUMERICAL);
        mp.put(Operator.SUB, EvaluationType.NUMERICAL);
        mp.put(Operator.MUL, EvaluationType.NUMERICAL);
        mp.put(Operator.DIV, EvaluationType.NUMERICAL);
        mp.put(Operator.MOD, EvaluationType.NUMERICAL);

        mp.put(Operator.AND, EvaluationType.BOOLEAN);
        mp.put(Operator.OR, EvaluationType.BOOLEAN);
        mp.put(Operator.XOR, EvaluationType.BOOLEAN);
        mp.put(Operator.NE, EvaluationType.BOOLEAN);
        mp.put(Operator.LT, EvaluationType.BOOLEAN);
        mp.put(Operator.LTE, EvaluationType.BOOLEAN);
        mp.put(Operator.GT, EvaluationType.BOOLEAN);
        mp.put(Operator.GTE, EvaluationType.BOOLEAN);
        mp.put(Operator.EQ, EvaluationType.BOOLEAN);

        mapping = Collections.unmodifiableMap(mp);
    }
}
