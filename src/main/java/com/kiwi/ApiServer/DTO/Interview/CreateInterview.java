package com.kiwi.ApiServer.DTO.Interview;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateInterview {
    private String interviewName;
    private String startTime;
    private int template;
    private List<String> interviewee;
    private List<String> interviewer;
}
