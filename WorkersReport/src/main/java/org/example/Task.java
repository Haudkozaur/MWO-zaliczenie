package org.example;

public class Task {
        private String date;
        private String quest;
        private int time;
        private String workerName;

    public Task(String date, String quest, int time, String workerName) {
        this.date = date;
        this.quest = quest;
        this.time = time;
        this.workerName = workerName;
    }


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

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    @Override
    public String toString() {
        return "Task{" +
                "date='" + date + '\'' +
                ", quest='" + quest + '\'' +
                ", time=" + time +
                ", workerName='" + workerName + '\'' + // Include new field
                '}';
    }
}
