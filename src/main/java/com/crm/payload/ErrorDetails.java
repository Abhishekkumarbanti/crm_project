package com.crm.payload;

import org.apache.logging.log4j.message.Message;

import java.util.Date;

public class ErrorDetails {
    private Date date;
    private  String Message;
    private String request;
    public ErrorDetails(String Message , Date date ,String request){
        this.Message=Message;
        this.date=date;
    }





    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return Message;
    }

    public String getRequest() {
        return request;
    }
}
