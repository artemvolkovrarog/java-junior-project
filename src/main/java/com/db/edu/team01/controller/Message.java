package com.db.edu.team01.controller;

public class Message {
    private final String command;
    private final String payload;

    public Message(String command, String payload) {
        this.command = command;
        this.payload = payload;
    }

    public String getCommand() {
        return command;
    }

    public String getPayload() {
        return payload;
    }

    public boolean payloadIsValid() {
        return this.payload.length() <= 150;
    }
}
