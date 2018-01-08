package com.mysoft.menus;

import com.mysoft.db.DAO;
import com.mysoft.entities.Worker;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
public class WorkerMenu {
	public static void main() throws SQLException, ClassNotFoundException, IOException {
		InputStreamReader in = new InputStreamReader(System.in);
		System.out.println("Select worker first!");
		Worker currentWorker = new Worker();
		currentWorker = DAO.SelectWorker();
		char option = ' ';
		while (option != '8') {
			System.out.println("\nCurrent worker: " + currentWorker + '\n');
			System.out.println("1-Select current worker\n2-Handle pending tasks\n3-Go to vacation/return from it\n8-Exit\n");
			option = (char) in.read();
			if (option != '\n') {
				in.read();
			}
			switch (option) {
				case '1': {
					currentWorker = DAO.SelectWorker();
					break;
				}
				case '2': {
					DAO.handlePendingTasks(currentWorker);
					break;
				}
				case '3': {
					currentWorker = DAO.ToVacation(currentWorker);
					break;
				}
			}
		}
	}
}
