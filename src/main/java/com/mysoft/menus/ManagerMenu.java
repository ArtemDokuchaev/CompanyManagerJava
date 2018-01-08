package com.mysoft.menus;

import com.mysoft.db.*;
import com.mysoft.entities.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerMenu {
	public static void main() throws IOException, SQLException, ClassNotFoundException {
		InputStreamReader in = new InputStreamReader( System.in );
		ArrayList<Worker> workers = new ArrayList<>( DAO.getWorkers() );
		ArrayList<Task> tasks = new ArrayList<>( DAO.getTasks() );
		char option = ' ';
		while ( option != '9' ) {
			System.out.println( "\n0-Show all tasks and workers assigned to them\n1-Hire worker\n2-Fire worker\n3-Show workers" +
					"\n4-Give a task\n5-Remove from task\n6-Show tasks\n7-Add task\n8-Delete task\n9-Exit\n" );
			option = ( char ) in.read();
			if ( option != '\n' ) {
				in.read();
			}
			switch ( option ) {
				case '0': {
					DAO.ShowEmployment();
					break;
				}
				case '1': {
					workers = DAO.AddWorker();
					break;
				}
				case '2': {
					workers = DAO.DeleteWorker();
					break;
				}
				case '3': {
					DAO.Show( workers );
					break;
				}
				case '4': {
					DAO.giveTask();
					break;
				}
				case '5': {
					DAO.removeFromTask();
					break;
				}
				case '6': {
					DAO.Show( tasks );
					break;
				}
				case '7': {
					tasks = DAO.AddTask();
					break;
				}
				case '8': {
					tasks = DAO.DeleteTask();
					break;
				}
			}//end of switch()
		}//end of while()
	}
}
