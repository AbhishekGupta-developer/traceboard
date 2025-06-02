package com.myorganisation.traceboard.exceptions;

public class UserDoesNotExist extends Exception {
    public UserDoesNotExist() {
        super("User doesn't exist.");
    }

    public UserDoesNotExist(String m) {
        super(m);
    }
}
