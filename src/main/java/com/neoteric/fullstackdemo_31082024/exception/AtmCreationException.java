package com.neoteric.fullstackdemo_31082024.exception;

public class AtmCreationException extends Exception{
    public String message;
    public AtmCreationException(String msg){
        this.message=msg;
    }
}
