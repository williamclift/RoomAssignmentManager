/*
======================================================
    Room Assignment Project
    @author William Clift
 
    This application takes in preference data on where students would like to live for the upcoming year and provides a comprehensive room assignment suggestion to administrators of Residence Life.
 =====================================================
 */

import java.util.Date;
import java.util.Scanner;

public class RoomAssignment{

    
    public static void main(String[] args){
        // Declare Scanner
        Scanner in = new Scanner(System.in);
        
        String username, password;
        username = "residencelife";
        password = "bears";
        
        RoomDatabase rd = new RoomDatabase();
        rd.autoLogin(username, password);

    }
   
}
