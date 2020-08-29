package com.codecool.guestbook;

public class EntryNote {

    private final int id;
    private final String name;
    private final String message;
    private final String date;

    public EntryNote(int id, String name, String message, String date) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}
