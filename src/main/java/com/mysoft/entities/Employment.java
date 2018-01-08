package com.mysoft.entities;

public class Employment {
    private int id;
    private int id_worker;
    private int id_task;
    private String status;

    public Employment(){
    }
    public Employment(int id, int id_worker, int id_task, String status) {
        this.id = id;
        this.id_worker = id_worker;
        this.id_task = id_task;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_worker() {
        return id_worker;
    }

    public void setId_worker(int id_worker) {
        this.id_worker = id_worker;
    }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employment{" +
                "id=" + id +
                ", id_worker=" + id_worker +
                ", id_task=" + id_task +
                ", status='" + status + '\'' +
                '}';
    }
}
