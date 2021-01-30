package com.sidh.hobby.core.service;

import com.sidh.hobby.core.dto.RuleNode;
import com.sidh.hobby.core.dto.EvaluationResult;

import java.util.Map;

public class RuleNodeEvaluatorFactory {

    EvaluationResult evaluate(Map<String, Object> data, RuleNode ruleNode) {
        EvaluationResult result;

        if (ruleNode.getChild() != null) {
            result = evaluate(data, ruleNode.getChild());
        }
        else {
            result = EvaluationResultFactory.make(data, ruleNode);
        }

        if(ruleNode.getPeer() == null)
            return result;

        return EvaluationResultFactory.calculate(result, ruleNode.getPeerOperator(), evaluate(data, ruleNode.getPeer()));
    }
}
