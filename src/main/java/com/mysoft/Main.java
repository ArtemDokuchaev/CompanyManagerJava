package com.mysoft;


import com.mysoft.entities.Company;
import com.mysoft.entities.Task;
import com.mysoft.entities.Worker;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main( String[] args ) throws IOException, SQLException, ClassNotFoundException {
		Company company = new Company();
		Scanner in = new Scanner( System.in );
		int choice = -1;
		while ( choice != 0 ) {
			System.out.println( "\n1-Show workers and their tasks;\n2-Show tasks;\n3-Give a task to the worker;" +
					"\n4-Remove task from worker;\n5-Handle tasks\n6-Go to vacation;\n0-Exit.\n" );
			choice = in.nextInt();
			switch ( choice ) {
				case 1: {
					company.showWorkersTasks();
					break;
				}
				case 2: {
					company.showTasks();
					break;
				}
				case 3: {
					company.giveTask();
					break;
				}
				case 4: {
					company.takeTask();
					break;
				}
				case 5: {
					company.handleTasks();
					break;
				}
				case 6: {
					company.ToVacation();
					break;
				}
			}
		}
	}
}


