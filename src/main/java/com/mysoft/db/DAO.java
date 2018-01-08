package com.mysoft.db;

import com.mysoft.entities.Employment;
import com.mysoft.entities.Task;
import com.mysoft.entities.Worker;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DAO {
	static DBConnection dbConnection = new DBConnection();
	//Adding new worker to DB, updating ArrayList
	public static ArrayList<Worker> AddWorker() throws SQLException, ClassNotFoundException {
		DBConnection dbConnection = new DBConnection();
		Scanner in = new Scanner( System.in );
		Worker worker = new Worker();

		System.out.print( "Enter worker's name and surname:\nName: " );
		worker.setFirstName( in.nextLine() );
		System.out.print( "Surname: " );
		worker.setLastName( in.nextLine() );

		String addQuery = "INSERT into workers(first_name, last_name) VALUES (?,?)";
		try ( Connection c = dbConnection.getConnection();
			  PreparedStatement ps = c.prepareStatement( addQuery ) ) {
			ps.setString( 1, worker.getFirstName() );
			ps.setString( 2, worker.getLastName() );
			ps.executeUpdate();
		}
		ArrayList<Worker> updatedWorkers = new ArrayList<>( getWorkers() );
		System.out.println( updatedWorkers.get( updatedWorkers.size() - 1 ) + "\nAdded!" );
		return updatedWorkers;
	}
	//Deleting worker from DB, updating ArrayLlist
	public static ArrayList<Worker> DeleteWorker() throws SQLException {
		DBConnection dbConnection = new DBConnection();
		Scanner in = new Scanner( System.in );
		Worker worker = new Worker();
		System.out.print( "Enter worker id:" );
		worker.setId( in.nextInt() );
		String deleteQuery = "DELETE FROM workers WHERE id_worker = ?";
		try ( Connection c = dbConnection.getConnection();
			  PreparedStatement ps = c.prepareStatement( deleteQuery ) ) {
			ps.setInt( 1, worker.getId() );
			ps.executeUpdate();
		}
		ArrayList<Worker> updatedWorkers = new ArrayList<>( getWorkers() );
		return updatedWorkers;
	}
	//Selecting all Workers from DB and adding them to ArrayList
	public static List<Worker> getWorkers() throws SQLException {
		ArrayList<Worker> workers = new ArrayList<>();
		String SelectQuery = "SELECT * FROM workers";
		Statement statement = dbConnection.getConnection().createStatement();
		try {
			ResultSet resultSet = statement.executeQuery( SelectQuery );

			while ( resultSet.next() ) {
				Worker worker = new Worker();
				worker.setId( resultSet.getInt( "id_worker" ) );
				worker.setFirstName( resultSet.getString( "first_name" ) );
				worker.setLastName( resultSet.getString( "last_name" ) );
				worker.setStatus( resultSet.getString( "status" ) );
				workers.add( worker );
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return workers;
	}
	//Adding new task to db, updating ArrayList
	public static ArrayList<Task> AddTask() throws SQLException {
		DBConnection dbConnection = new DBConnection();
		Scanner in = new Scanner( System.in );
		Task task = new Task();
		System.out.print( "Enter task's name and description:\nName: " );
		task.setName( in.nextLine() );
		System.out.print( "Description: " );
		task.setDescription( in.nextLine() );

		String addQuery = "INSERT into tasks(name, description) VALUES (?,?)";
		try ( Connection c = dbConnection.getConnection();
			  PreparedStatement ps = c.prepareStatement( addQuery ) ) {
			ps.setString( 1, task.getName() );
			ps.setString( 2, task.getDescription() );
			ps.executeUpdate();
		}
		ArrayList<Task> updatedTask = new ArrayList<>( getTasks() );
		System.out.println( updatedTask.get( updatedTask.size() - 1 ) + "\nAdded!" );
		return updatedTask;
	}
	//Deleting task from DB, updating ArrayList
	public static ArrayList<Task> DeleteTask() throws SQLException {
		DBConnection dbConnection = new DBConnection();
		Scanner in = new Scanner( System.in );
		Task task = new Task();
		System.out.print( "Enter task id:" );
		task.setId( in.nextInt() );
		String deleteQuery = "DELETE FROM tasks WHERE id_task = ?";
		try ( Connection c = dbConnection.getConnection();
			  PreparedStatement ps = c.prepareStatement( deleteQuery ) ) {
			ps.setInt( 1, task.getId() );
			ps.executeUpdate();
		}
		ArrayList<Task> updatedTasks = new ArrayList<>( getTasks() );
		return updatedTasks;
	}
	//Selecting all tasks from DB and adding them to ArrayList
	public static List<Task> getTasks() throws SQLException {
		ArrayList<Task> tasks = new ArrayList<>();
		String SelectQuery = "SELECT * FROM tasks";
		Statement statement = dbConnection.getConnection().createStatement();
		try {
			ResultSet resultSet = statement.executeQuery( SelectQuery );

			while ( resultSet.next() ) {
				Task task = new Task();
				task.setId( resultSet.getInt( "id_task" ) );
				task.setName( resultSet.getString( "name" ) );
				task.setDescription( resultSet.getString( "description" ) );
				tasks.add( task );
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return tasks;
	}
	//Showing ArrayList of Workers or Tasks
	public static <T> void Show( ArrayList<T> items ) throws SQLException {
		items.forEach( System.out::println );
	}
	//Select currentWorker for WorkerMenu
	public static Worker SelectWorker() throws SQLException {
		DBConnection dbConnection = new DBConnection();
		Scanner in = new Scanner( System.in );
		String query = "SELECT * FROM workers WHERE id_worker = ?";

		try ( Connection c = dbConnection.getConnection();
			  PreparedStatement ps = c.prepareStatement( query ) ) {
			while ( true ) {
				System.out.print( "Enter worker id: " );
				int workerID = in.nextInt();
				ps.setInt( 1, workerID );
				ResultSet resultSet = ps.executeQuery();
				if ( resultSet.next() ) {
					Worker worker = new Worker();
					worker.setId( resultSet.getInt( "id_worker" ) );
					worker.setFirstName( resultSet.getString( "first_name" ) );
					worker.setLastName( resultSet.getString( "last_name" ) );
					worker.setStatus( resultSet.getString( "status" ) );
					return worker;
				}
				System.out.println( "\nWorker with ID " + workerID + " doesn't exist. Try again.\n" );
			}
		}
	}

	public static void giveTask() throws SQLException {
		DBConnection dbConnection = new DBConnection();
		Employment employment = new Employment();
//        Task task = new Task();
//        Worker worker = new Worker();
		Scanner in = new Scanner( System.in );
		System.out.print( "Enter worker id and task id:\nWorker id: " );
		employment.setId_worker( in.nextInt() );
		System.out.print( "Task id: " );
		employment.setId_task( in.nextInt() );
		String addQuery = "INSERT INTO employment(id_worker, id_task) VALUES (?,?)";
		String selectQuery = "SELECT * FROM employment WHERE id_task = ? AND id_worker = ?";
		String checkSelect = "SELECT tasks.id_task, workers.id_worker FROM workers " +
				"INNER JOIN tasks ON tasks.id_task = ? AND workers.id_worker = ?;";
		try ( Connection c = dbConnection.getConnection();
			  PreparedStatement psCheckSelect = c.prepareStatement(checkSelect);
			  PreparedStatement psAdd = c.prepareStatement(addQuery);
			  PreparedStatement psSelect = c.prepareStatement(selectQuery) ) {
			psCheckSelect.setInt(1, employment.getId_task());
			psCheckSelect.setInt(2, employment.getId_worker());
			ResultSet resultSet = psCheckSelect.executeQuery();
			//Checking if those IDs are exist
			if( resultSet.next() ){
				psSelect.setInt(1, employment.getId_task());
				psSelect.setInt(2, employment.getId_worker());
				resultSet = psSelect.executeQuery();
				//Checking if this tasks already has a relation
				if( !resultSet.next()){
					psAdd.setInt(2, employment.getId_task());
					psAdd.setInt(1, employment.getId_worker());
					psAdd.executeUpdate();
				}//select tasks.id_task, workers.id_worker from workers INNER JOIN tasks ON tasks.id_task = 3 AND workers.id_worker = 6;
				else {
					System.out.println("\nTask already given to this worker\n");
				}
			}
			else {
				System.out.println("\nTask with ID " + employment.getId_task() + " or worker with ID " + employment.getId_worker() + " doesn't exist.");
			}
		}
	}
	//Removing worker from task
	public static void removeFromTask() throws SQLException {
		DBConnection dbConnection = new DBConnection();
		Employment employment = new Employment();
//        Task task = new Task();
//        Worker worker = new Worker();
		Scanner in = new Scanner( System.in );
		System.out.print( "Enter worker id and task id:\nWorker id: " );
		employment.setId_worker( in.nextInt() );
		System.out.print( "Task id: " );
		employment.setId_task( in.nextInt() );
		String deleteQuery = "DELETE FROM employment WHERE id_worker = ? AND id_task = ?";
		String selectQuery = "SELECT * FROM employment WHERE id_worker = ? AND id_task = ?";
		try ( Connection c = dbConnection.getConnection();
			  PreparedStatement selectStatement = c.prepareStatement( selectQuery );
			  PreparedStatement deleteStatement = c.prepareStatement( deleteQuery ) ) {
			selectStatement.setInt( 1, employment.getId_worker() );
			selectStatement.setInt( 2, employment.getId_task() );
			ResultSet resultSet = selectStatement.executeQuery();
			//Checking if this relation exist
			if ( resultSet.next() ) {
				deleteStatement.setInt( 1, employment.getId_worker() );
				deleteStatement.setInt( 2, employment.getId_task() );
				deleteStatement.executeUpdate();
			} else {
				System.out.println( "Task with ID " + employment.getId_task() +
						" wasn't given/already removed from worker with ID " + employment.getId_worker() );
			}
		}
	}

	//	SELECT tasks.id_task, tasks.name, workers.id_worker, workers.first_name, workers.last_name
//	FROM employment INNER JOIN workers ON workers.id_worker = employment.id_worker
//	INNER JOIN tasks ON employment.id_task = tasks.id_task;
	public static void ShowEmployment() throws SQLException {
		ArrayList<Task> tasks = new ArrayList<>();
		ArrayList<Worker> workers = new ArrayList<>();
		ArrayList<Employment> employments = new ArrayList<>();
		String joinQuery = "SELECT workers.id_worker, workers.first_name, workers.last_name, employment.id_employment,employment.status " +
				"FROM employment INNER JOIN workers ON workers.id_worker = employment.id_worker\n" +
				"INNER JOIN  tasks ON employment.id_task = tasks.id_task ORDER BY employment.id_task;";
		try ( Statement statement = dbConnection.getConnection().createStatement() ) {
			ResultSet resultSet = statement.executeQuery( joinQuery );
			//Filling the ArrayList of Workers and Employment
			while ( resultSet.next() ) {
				Worker worker = new Worker();
				Employment employment = new Employment();
				employment.setId( resultSet.getInt( "id_employment" ) );
				employment.setId_worker( resultSet.getInt( "id_worker" ) );
				employment.setStatus( resultSet.getString( "status" ) );
				employments.add( employment );
				worker.setId( resultSet.getInt( "id_worker" ) );
				worker.setFirstName( resultSet.getString( "first_name" ) );
				worker.setLastName( resultSet.getString( "last_name" ) );
				workers.add( worker );
			}
			joinQuery = "SELECT tasks.id_task, tasks.name " +
					"FROM employment INNER JOIN tasks ON employment.id_task = tasks.id_task " +
					"ORDER BY employment.id_task;";
			resultSet = statement.executeQuery( joinQuery );
			int i = 0;
			//Filling the ArrayList of Tasks and Employment
			while ( resultSet.next() ) {
				employments.get( i ).setId_task( resultSet.getInt( "id_task" ) );
				i++;
				Task task = new Task();
				task.setId( resultSet.getInt( "id_task" ) );
				task.setName( resultSet.getString( "name" ) );
				tasks.add( task );
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		//Showing the tasks and all the workers assigned to it bellow
		for ( int i = 0; i < tasks.size(); ) {
			int currentTask = i;
			System.out.println( tasks.get( i ) );
			while ( i < tasks.size() &&
					//employments.get(i).getId_worker() == workers.get(i).getId() &&
					employments.get( i ).getId_task() == tasks.get( currentTask ).getId() ) {
				System.out.println( "\t" + workers.get( i ) + '\t' + employments.get( i ).getStatus() );
				i++;
			}
		}
	}

	//Show all pending tasks to currentWorker; Changing status of the task in employment to 'accepted' or 'declined'
	public static void handlePendingTasks( Worker currentWorker ) throws SQLException, IOException {
		DBConnection dbConnection = new DBConnection();
		ArrayList<Task> tasks = new ArrayList<>();
		String query = "SELECT employment.status, tasks.id_task, tasks.name,tasks.description " +
				"FROM employment INNER JOIN tasks ON employment.id_task = tasks.id_task WHERE employment.id_worker = ?";
		try ( Connection c = dbConnection.getConnection();
			  PreparedStatement ps = c.prepareStatement( query ) ) {
			ps.setInt( 1, currentWorker.getId() );
			ResultSet resultSet = ps.executeQuery();
			while ( resultSet.next() ) {
				if ( Objects.equals( resultSet.getString( "status" ), "pending" ) ) {
					Task task = new Task();
					task.setId( resultSet.getInt( "id_task" ) );
					task.setName( resultSet.getString( "name" ) );
					task.setDescription( resultSet.getString( "description" ) );
					tasks.add( task );
					System.out.println("Pending: " + task);
				}
			}
			int option;
			Scanner in = new Scanner( System.in );
			for ( int i = 0; i < tasks.size(); i++ ) {
				if ( tasks.size() != 0 ) {
					System.out.println( "Pending: " + tasks.get( i ) + "\nAccept?[1-yes/2-no]" );
					option = in.nextInt();
					if ( option == 1 ) {
						try (PreparedStatement ps1 = c.prepareStatement( "UPDATE employment SET status = 'accepted' " +
								" WHERE id_worker = ? and id_task = ?;" )) {

							ps1.setInt( 1, currentWorker.getId() );
							ps1.setInt( 2, tasks.get( i ).getId() );
							ps1.executeUpdate();
						}
					} else {
						try(PreparedStatement ps1 = c.prepareStatement( "UPDATE employment SET status = 'declined' " +
								" WHERE id_worker = ? and id_task = ?;" )) {
							ps1.setInt( 1, currentWorker.getId() );
							ps1.setInt( 2, tasks.get( i ).getId() );
							ps1.executeUpdate();
						}
					}
				} else {
					System.out.println( "No pending tasks." );
				}
			}
		}//end of try
	}//end of method
	//Switching worker's status to 'vacation' or 'working'
	public static Worker ToVacation( Worker currentWorker ) throws SQLException {
		DBConnection dbConnection = new DBConnection();
		ArrayList<Task> tasks = new ArrayList<>();
		String vacationQuery = "UPDATE workers SET status = 'on vacation' WHERE id_worker = ?;";
		String workingQuery = "UPDATE workers SET status = 'working' WHERE id_worker = ?;";
		String updTasksQuery = "UPDATE employment SET status = 'accepted' WHERE id_worker = ? AND id_task = ?;";
		String selectQuery = "SELECT status FROM workers WHERE id_worker = ?";
		//beginning for changing status in employment
//		String taskSelectQuery = "SELECT id_task, status " +
//				"FROM employment WHERE id_worker = ? AND employment.status = 'accepted';";
		try (Connection c = dbConnection.getConnection();
			 PreparedStatement psSelectQuery = c.prepareStatement( selectQuery );
			 PreparedStatement psWorkingQuery = c.prepareStatement( workingQuery );
			// PreparedStatement psTaskSelectQuery = c.prepareStatement( taskSelectQuery );
			 PreparedStatement psUpdTasksQuery = c.prepareStatement( updTasksQuery );
			 PreparedStatement psVacationQuery = c.prepareStatement( vacationQuery )){

			psSelectQuery.setInt( 1, currentWorker.getId() );
			ResultSet resultSet = psSelectQuery.executeQuery();
			resultSet.next();
			if ( Objects.equals( resultSet.getString( "status" ), "on vacation" ) ){
				psWorkingQuery.setInt( 1, currentWorker.getId() );
				psWorkingQuery.executeUpdate();
				currentWorker.setStatus( "working" );
//				psTaskSelectQuery.setInt( 1, currentWorker.getId() );
//				resultSet = psTaskSelectQuery.executeQuery();
//				while (resultSet.next()){
//					psUpdTasksQuery.setInt( 1, currentWorker.getId() );
//					psUpdTasksQuery.setInt( 2, resultSet.getInt( "id_task" ) );
//					psUpdTasksQuery.executeUpdate();
//				}
			}
			else {
				psVacationQuery.setInt( 1, currentWorker.getId() );
				psVacationQuery.executeUpdate();
				currentWorker.setStatus( "on vacation" );
			}
		}//end of try
		return currentWorker;
	}//end of method
}//end of class
