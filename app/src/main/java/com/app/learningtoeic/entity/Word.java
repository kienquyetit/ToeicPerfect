package com.app.learningtoeic.entity;

import android.os.Parcel;
import android.os.Parcelable;


public class Word implements Parcelable {
    private int id;
    private int topic;
    private int id_temp;
    private String vocabulary;
    private String vocalization;
    private String explanation;
    private String translate;
    private String example;
    private String example_translate;
    private int favourite;

    protected Word(Parcel in) {
        id = in.readInt();
        topic = in.readInt();
        id_temp = in.readInt();
        vocabulary = in.readString();
        vocalization = in.readString();
        explanation = in.readString();
        translate = in.readString();
        example = in.readString();
        example_translate = in.readString();
        favourite = in.readInt();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

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

    public String getExample_translate() {
        return example_translate;
    }

    public void setExample_translate(String example_translate) {
        this.example_translate = example_translate;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(topic);
        parcel.writeInt(id_temp);
        parcel.writeString(vocabulary);
        parcel.writeString(vocalization);
        parcel.writeString(explanation);
        parcel.writeString(translate);
        parcel.writeString(example);
        parcel.writeString(example_translate);
        parcel.writeInt(favourite);
    }

    public Word(int id, int topic, int id_temp, String vocabulary, String vocalization, String explanation, String translate, String example, String example_translate, int favourite) {
        this.id = id;
        this.topic = topic;
        this.id_temp = id_temp;
        this.vocalization = vocalization;
        this.vocabulary = vocabulary;
        this.translate = translate;
        this.example = example;
        this.example_translate = example_translate;
        this.favourite = favourite;
        this.explanation = explanation;
    }

    public Word() {

    }
}
