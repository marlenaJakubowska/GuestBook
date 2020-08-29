package com.codecool.guestbook;

public class EntryNote {

    private final String name;
    private final String message;
    private final String date;

    public EntryNote(String name, String message, String date) {
        this.name = name;
        this.message = message;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Entry Note{" +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}
