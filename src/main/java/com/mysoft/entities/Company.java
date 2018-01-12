package com.mysoft.entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Company {
	private ArrayList<Worker> workers = new ArrayList<>();
	private ArrayList<Task> tasks = new ArrayList<>();

	public Company() {
		Worker worker = new Worker( 5, "John", "Hanks", "idle" );
		workers.add( worker );
		worker = new Worker( 3, "Oliver", "Scott", "idle" );
		workers.add( worker );
		worker = new Worker( 4, "Bob", "Thompson", "idle" );
		workers.add( worker );
		worker = new Worker( 2, "Peter", "Jackson", "idle" );
		workers.add( worker );
		worker = new Worker( 1, "Nick", "William", "idle" );
		workers.add( worker );

		Task task = new Task( 1, "Task 1", "Description of the Task 1" );
		tasks.add( task );
		task = new Task( 2, "Task 2", "Description of the Task 2" );
		tasks.add( task );
		task = new Task( 3, "Task 3", "Description of the Task 3" );
		tasks.add( task );
	}

	public void showWorkersTasks() {
		for ( int i = 0; i < workers.size(); i++ ) {
			System.out.println( workers.get( i ) );
			workers.get( i ).ShowTasks();
			System.out.println();
		}
	}

	public void showTasks() {
		for ( Task task : tasks ) {
			System.out.println( task );
		}
	}

	public int getWorkerId() {
		Scanner in = new Scanner( System.in );
		System.out.print( "\nEnter worker id: " );
		int workerID = in.nextInt();
		int i = 0;
		boolean hasID = false;
		for ( Worker item : workers ) {
			if ( item.getId() == workerID ) {
				return i;
			}
			i++;
		}
		if ( !hasID ) {
			System.out.println( "Worker with ID " + workerID + " doesn't exist." );
		}
		return -1;
	}

	public void giveTask() {
		workers.get( getWorkerId() ).addTask( tasks );
	}

	public void takeTask() {
		workers.get( getWorkerId() ).removeTask();
	}


	public void handleTasks() {
		workers.get( getWorkerId() ).handlePendingTask();
	}

	public void ToVacation() {
		workers.get( getWorkerId() ).ToVacation();
	}
}
