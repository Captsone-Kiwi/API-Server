package com.kiwi.ApiServer.DTO.Evaluation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EvaluationListString {
    String category;
    String title;
    int type;
    String data;
    List<String> questions;
}
