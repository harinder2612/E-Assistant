package com.harinder.e_assistant;


import java.sql.Time;

public class scheduleItem {

    String subject,room,klass,time;

    public scheduleItem(String subject, String room, String klass, String time) {
        this.subject = subject;
        this.room = room;
        this.klass = klass;
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
