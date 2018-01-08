package com.mysoft;


import com.mysoft.db.DAO;
import com.mysoft.entities.Worker;
import com.mysoft.menus.ManagerMenu;
import com.mysoft.menus.WorkerMenu;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Main {
    public static void main( String[] args ) throws IOException, SQLException, ClassNotFoundException {
        InputStreamReader in = new InputStreamReader( System.in );
        char option = ' ';
        while (option!='5') {
            System.out.println( "1-Manager menu\n2-Worker menu\n5-exit\n" );
            option = ( char ) in.read();
            if ( option != '\n' ) {
                in.read();
            }
            switch ( option )
            {
                case '1':
                    ManagerMenu.main();
                    break;
                case '2':
                    WorkerMenu.main();
                    break;
                default:
                    break;
            }

        }
    }
}
