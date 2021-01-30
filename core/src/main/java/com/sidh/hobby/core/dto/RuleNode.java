package com.sidh.hobby.core.dto;

import com.sidh.hobby.core.enums.Operator;
import lombok.Value;

@Value
public class RuleNode {
    RuleNode child;
    RuleNode peer;
    Operator peerOperator;
    String attribute;
}
