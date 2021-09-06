package com.db.edu.team01.controller;

public enum Command {
    CMD_SEND("/snd"),
    CMD_HISTORY("/hist"),
    CMD_IDENTIFY("/chid");

    public final String body;

    Command(String body) {
        this.body = body;
    }


}
