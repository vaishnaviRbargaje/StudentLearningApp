package com.tka.StudentLearningApp.dto;

import java.util.ArrayList;
import java.util.List;

public class TestDto {

    private Long id;
    private String title;
    private String description;
    private List<QuestionDto> questions = new ArrayList<>();

  
    public TestDto() {
    }

    public TestDto(Long id, String title, String description, List<QuestionDto> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
