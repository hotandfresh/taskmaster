package com.bomibear.taskmaster.Model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import java.util.Date;

@DynamoDBDocument
public class History {

    private String timestamp;
    private String action;

    public History(){}

    public History(String action){
        this.timestamp = new Date().toString();
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getAction() {
        return action;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
