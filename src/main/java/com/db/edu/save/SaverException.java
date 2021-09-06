package com.db.edu.save;

import java.io.IOException;

public class SaverException extends IOException{

    public SaverException() {
    }

    public SaverException(String message) {
        super(message);
    }

    public SaverException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaverException(Throwable cause) {
        super(cause);
    }
}