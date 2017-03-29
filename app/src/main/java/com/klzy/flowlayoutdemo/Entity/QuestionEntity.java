package com.klzy.flowlayoutdemo.Entity;

import java.util.List;

/**
 * Created by KLZY on .
 */

public class QuestionEntity {

    private String title;
    private int count;
    private String questiontype;

    private List<QuestionItemEntity> question;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(String questiontype) {
        this.questiontype = questiontype;
    }

    public List<QuestionItemEntity> getQuestion() {
        return question;
    }

    public void setQuestion(List<QuestionItemEntity> question) {
        this.question = question;
    }

}
