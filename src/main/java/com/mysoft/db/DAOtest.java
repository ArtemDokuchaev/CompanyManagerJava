package com.mysoft.db;

import com.mysoft.entities.Task;
import com.mysoft.entities.Worker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOtest {
	static DBConnection dbConnection = new DBConnection();

	public static List<Worker> getWorkers() throws SQLException {
		ArrayList<Worker> workers = new ArrayList<>();
		String SelectQuery = "SELECT * FROM workers";
		Statement statement = dbConnection.getConnection().createStatement();
		try {
			ResultSet resultSet = statement.executeQuery( SelectQuery );

			while ( resultSet.next() ) {
				Worker worker = new Worker();
				worker.setId(resultSet.getInt("id_worker"));
				worker.setFirstName(resultSet.getString("first_name"));
				worker.setLastName(resultSet.getString("last_name"));
				worker.setStatus(resultSet.getString("status"));
				workers.add(worker);
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return workers;
	}
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
				tasks.add(task);
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return tasks;
	}

	public static <T> void Show( ArrayList<T> items) throws SQLException {
		items.forEach( System.out::println);
	}

	public static void main(String[] args) throws SQLException {
		ArrayList<Worker> workers = new ArrayList<>( getWorkers() );
		ArrayList<Task> tasks = new ArrayList<>( getTasks() );
		Show(tasks);
		Show(workers);
	}
}
