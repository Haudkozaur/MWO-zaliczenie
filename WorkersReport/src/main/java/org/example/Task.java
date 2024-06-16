package org.example;

public class Task {
    private String date;
    private String quest;
    private int time;

    public Task(String date, String quest, int time) {
        this.date = date;
        this.quest = quest;
        this.time = time;
    }

    // Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Task{" +
                "date='" + date + '\'' +
                ", quest='" + quest + '\'' +
                ", time=" + time +
                '}';
    }
}
