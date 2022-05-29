import com.sun.jmx.remote.internal.ArrayQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Passenger {
    public String passengerName;
    public long passengerNumber;
    public String passengerClass;
    public double balance;
    public String activities;

    public void getPassenger(String passgrName) {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency", "sumit", "sumit");
            String sqlstmt = "select * from passenger where PassengerName = ?";
            PreparedStatement prepStmt = con.prepareStatement(sqlstmt);
            prepStmt.setString(1, passgrName);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            String passName = rs.getString("PassengerName");
            String passClass = rs.getString("PassengerClass");
            String act = rs.getString("Activities");
            long passNumber = rs.getInt("PassengerNumber");
            double bala = rs.getFloat("Balance");
            this.passengerName = passName;
            this.passengerNumber = passNumber;
            this.passengerClass = passClass;
            this.balance = bala;
            this.activities = act;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void printAllPassengers()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency", "sumit", "sumit");
            String sqlstmt = "select * from passenger";
            PreparedStatement prepStmt = con.prepareStatement(sqlstmt);
            ResultSet rs = prepStmt.executeQuery();
            List<Passenger> passengers = new ArrayList<>();
            List<String> activities = new ArrayList<>();
            while(rs.next())
            {
                Passenger p = new Passenger();
                p.passengerNumber = rs.getInt("PassengerNumber");
                p.passengerName = rs.getString("PassengerName");
                p.balance = rs.getFloat("Balance");
                p.activities = rs.getString("Activities");
                p.passengerClass = rs.getString("PassengerClass");
                passengers.add(p);
                activities = Arrays.asList(rs.getString("Activities").split(","));
            }
            for (Passenger passenger : passengers)
            {
                System.out.println("Name : " + passenger.passengerName);
                System.out.println("Number : " + passenger.passengerNumber);
                System.out.println("Balance : " + passenger.balance);
                System.out.println("List of each activity");
                //Load each activity based on activity name
                for (String activity : activities) {
                    float amt = 0;
                    String sqlstmt2 = "select * from activity where ActivityName = ?";
                    PreparedStatement prepStmt2 = con.prepareStatement(sqlstmt2);
                    prepStmt2.setString(1, activity);
                    ResultSet rs2 = prepStmt2.executeQuery();
                    while(rs2.next()) {
                        System.out.println("Activity: " + rs2.getString("ActivityName"));
                        System.out.println("Destination: " + rs2.getString("DestinationName"));
                        if (Objects.equals(passenger.passengerClass, "Standard"))
                            amt = rs2.getInt("Cost");
                        else if (Objects.equals(passenger.passengerClass, "Gold")) ;
                        amt = (float) (0.9 * rs2.getInt("Cost"));
                        System.out.println("Amount paid by passenger is: " + amt);
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}


