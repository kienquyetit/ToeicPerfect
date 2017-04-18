package com.app.learningtoeic.entity;

/**
 * Created by dell on 4/11/2017.
 */

public class Topic {
    public int id;
    public String name;
    public String topicImageName;

    public Topic(){

    }

    public Topic(int id, String name, String topicImageName){
        this.id = id;
        this.name = name;
        this.topicImageName = topicImageName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
