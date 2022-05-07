package com.kiwi.ApiServer.DTO.Evaluation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EvaluationList {
    String category;
    String title;
    int type;
    List<EvaluationQuestion> questions;
}
