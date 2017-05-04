package com.app.learningtoeic.entity;

/**
 * Created by dell on 4/11/2017.
 */

public class Topic {
    public int id;
    public String name;
    public String topicImageName;
    public String translateVie;
    public boolean isChecked;

    public Topic() {
    }

    public Topic(int id, String name, String topicImageName, String translateVie) {
        this.id = id;
        this.name = name;
        this.topicImageName = topicImageName;
        this.translateVie = translateVie;
    }

    public Topic(int id, String name, String topicImageName) {
        this.id = id;
        this.name = name;
        this.topicImageName = topicImageName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
