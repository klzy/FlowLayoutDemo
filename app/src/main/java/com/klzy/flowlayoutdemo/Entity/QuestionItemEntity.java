package com.klzy.flowlayoutdemo.Entity;

import java.util.List;
import java.util.Set;

/**
 * Created by KLZY on .
 */

public class QuestionItemEntity {

    private String id;
    private String questiontype;
    private String title;
    private int count;

    public List<String> selector;   //选项
    public Set<Integer> selected;   //选中项
    private int layoutID;
    private int layoutItem;
    private int maxSelect;
    private String itmeID;
    private String answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(String questiontype) {
        this.questiontype = questiontype;
    }

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

    public QuestionItemEntity(String title, int count) {
        this.title = title;
        this.count = count;
        this.questiontype = "normal";
    }

    public List<String> getSelector() {
        return selector;
    }

    public void setSelector(List<String> selector) {
        this.selector = selector;
    }

    public Set<Integer> getSelected() {
        return selected;
    }

    public void setSelected(Set<Integer> selected) {
        this.selected = selected;
    }

    public int getLayoutID() {
        return layoutID;
    }

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }

    public int getLayoutItem() {
        return layoutItem;
    }

    public void setLayoutItem(int layoutItem) {
        this.layoutItem = layoutItem;
    }

    public int getMaxSelect() {
        return maxSelect;
    }

    public void setMaxSelect(int maxSelect) {
        this.maxSelect = maxSelect;
    }

    public String getItmeID() {
        return itmeID;
    }

    public void setItmeID(String itmeID) {
        this.itmeID = itmeID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
