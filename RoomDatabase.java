/*
======================================================
    Room Database
    @author William Clift
 
    This class provides the connection to the Room Assignment Database that stores all of the room information.
 =====================================================
 */

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class RoomDatabase{
    
    private static Connection conn;
    private static Statement stmt;
    
    /**
     Room Database Constructor
     */
    public RoomDatabase(){}
    
    /**
     Prompts the user to input the username and password information; No parameters needed
     */
    public void userLogin(){
        Scanner in = new Scanner(System.in);
        boolean loggedIn = false;
        boolean exit = false;
        
        //Loops until the user enters correct login or chooses to exit
        while(!loggedIn || !exit){
            String username, password, databaseName;
            System.out.print("--Connect to Database--\nUsername: ");
            username = in.nextLine();
            System.out.print("Password: ");
            password = in.nextLine();
            
            loggedIn = login(username, password);
            
            //Failed Login Procedure - Reprompt the user for Identification
            if(!loggedIn){
                //Prompts the User to retry the Username/Password Combination
                System.out.print("Try again (y/n)?: ");
                String retry = in.nextLine();
                    
                //Exit only if user inputs "n"
                if(retry == "n"){
                    exit = true;
                }
            }
        }
    }
    
    /**
     Auto Login using hard coded username and password to access the database
     @param username
     @param password
     */
    public void autoLogin(String username, String password){
        boolean exit = false;

        while(!exit){
            System.out.println("--Connect to Database--\nProcessing...");
            boolean loggedIn = login(username, password);
            
            //Failed Login Procedure
            if(!loggedIn)
                exit = true;
        }
    }
    
    
    /**
     Provides the User an opprotunity to manually login, using the command line.
     */
    private boolean login(String username, String password){
        boolean loggedIn = false;

        //Checks the login information to ensure validity -- Accesses the database if it is correct
        if(checkLogin(username, password)){
            establishConnectionToDatabase("RoomAssignment", "sa", "reallyStrongPwd123");
            loggedIn = true;
        }

        //Once Login is successful, inform the user.
        if(loggedIn)
            System.out.println("--Login Successful--");
        else
            System.out.println("Username or Password not recognized.");

        return loggedIn;
    }
    
    /**
     Checks to see if the login is valid and logs in using the proper identification if it is correct
     @param username
     @param password
     @return boolean checkLogin
     */
    private boolean checkLogin(String username, String password){
        boolean valid = false;
        if(username.equals("residencelife") && password.equals("bears")){
            valid = true;
        }
        return valid;
    }
    
    /**
     Establish the Connection to the database, catches errors
     @param databaseName
     @param username
     @param password
     */
    private void establishConnectionToDatabase(String databaseName, String username, String password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost;databaseName="+databaseName+";user="+username+";password="+password;
            conn = DriverManager.getConnection(dbURL);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     Executes the query statement for the String s
     @param s -- the intended statement to execute
     @return rs -- the Result set of the sql query
     */
    private ResultSet executeQueryStatement(String s) throws SQLException {
        ResultSet rs;
        rs = stmt.executeQuery(s);
        return rs;
    }

    /**
     Display the Query Results
     @param rs -- the result set of the query results
     */
    private void displayQueryResults(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();

        int numOfCols = rsmd.getColumnCount();
        
        while (rs.next()) {
            for (int i = 1; i < numOfCols + 1; i++) {
                System.out.print((rsmd.getColumnLabel(i) + ": " + rs.getString(i)
                        + " "));
            }
            System.out.println();
        }
    }
}
