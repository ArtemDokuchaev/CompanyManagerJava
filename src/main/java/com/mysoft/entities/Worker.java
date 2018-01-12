package com.mysoft.entities;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Worker {
    private int id;
    private String firstName;
    private String lastName;
    private String status;
    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayList<Task> pendingTasks = new ArrayList<>();

    public Worker(){
    }

    public Worker( int id, String firstName, String lastName, String status ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public void ShowTasks() {
        if ( tasks.size() != 0 ) {
            tasks.forEach(
                    item -> System.out.println( "\t" + item )
            );
        } else {
            System.out.println( "\tNo tasks yet." );
        }
    }

    public void addTask( ArrayList<Task> Tasks ) {
        System.out.print( "\nEnter task id: " );
        Scanner in = new Scanner( System.in );
        int taskID = in.nextInt();
        boolean hasID = false;
        for ( Task task : tasks ) {
            if ( task.getId() == taskID ) {
                hasID = true;
            }
        }
        for ( Task task : pendingTasks ) {
            if ( task.getId() == taskID ) {
                hasID = true;
            }
        }
        if ( !hasID ) {
            for ( Task task : Tasks ) {
                if ( task.getId() == taskID ) {
                    pendingTasks.add( task );
                }
            }
        } else {
            System.out.println( "\nTask already given" );
        }
    }

    public void handlePendingTask() {
        if ( pendingTasks.size() != 0 ) {
            Scanner in = new Scanner( System.in );
            for ( Task task : pendingTasks ) {
                System.out.println( task + "\nAccept?[1-y/2-n]" );
                int option = in.nextInt();
                if ( option == 1 ) {
                    tasks.add( task );
                }
            }
            pendingTasks.clear();
        }
    }

    public void removeTask() {
        System.out.print( "\nEnter task id you want to delete: " );
        Scanner in = new Scanner( System.in );
        int taskID = in.nextInt();
        int i = 0;
        for ( Task task : tasks ) {
            if ( task.getId() == taskID ) {
                tasks.remove( i );
                break;
            }
            i++;
        }
    }

    public void ToVacation() {
        if ( status.equals( "On vacation" ) ) {
            status = "Working";
        } else {
            status = "On vacation";
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks( ArrayList<Task> tasks ) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public ArrayList<Task> getPendingTasks() {
        return pendingTasks;
    }

    public void setPendingTasks( ArrayList<Task> pendingTasks ) {
        this.pendingTasks = pendingTasks;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id = " + id +
                ", firstName = '" + firstName + '\'' +
                ", last_name = '" + lastName + '\'' +
                ", status = '" + status + '\'' +
                '}';
    }


}
