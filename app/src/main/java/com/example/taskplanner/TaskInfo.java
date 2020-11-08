package com.example.taskplanner;

class TaskInfo {

    String title;
    String date;
    String note;

    public TaskInfo() {
    }


    public TaskInfo(String title, String date, String note) {
        this.title = title;
        this.date = date;
        this.note = note;
    }

    public TaskInfo(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
