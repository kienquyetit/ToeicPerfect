package com.app.learningtoeic.entity;

public class Word {
    private int id;
    private int topic;
    private int id_temp;
    private String vocabulary;
    private String vocalization;
    private String explanation;
    private String translate;
    private String example;
    private String exampleTranslate;
    private int favourite;

    public Word() {

    }

    public Word(int id, int topic, int id_temp, String vocabulary, String vocalization, String explanation, String translate, String example, String exampleTranslate, int favourite) {
        this.id = id;
        this.topic = topic;
        this.id_temp = id_temp;
        this.vocalization = vocalization;
        this.vocabulary = vocabulary;
        this.translate = translate;
        this.example = example;
        this.exampleTranslate = exampleTranslate;
        this.favourite = favourite;
        this.explanation = explanation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public int getId_temp() {
        return id_temp;
    }

    public void setId_temp(int id_temp) {
        this.id_temp = id_temp;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getVocalization() {
        return vocalization;
    }

    public void setVocalization(String vocalization) {
        this.vocalization = vocalization;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExampleTranslate() {
        return exampleTranslate;
    }

    public void setExampleTranslate(String exampleTranslate) {
        this.exampleTranslate = exampleTranslate;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }
}
