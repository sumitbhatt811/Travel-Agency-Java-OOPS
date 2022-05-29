import java.util.*;
import java.sql.*;


public class Travel_package implements Package {
    public String packageName;
    public int passengerCapacity;
    public int passengerCount;
    List<String> destinations;
    public List<Passenger> passengerList;

    @Override
    public void getTravelPackage(String name)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency", "sumit", "sumit");
            String sqlstmt = "select * from travel_package where PackageName = ?";
            PreparedStatement prepStmt = con.prepareStatement(sqlstmt);
            prepStmt.setString(1, name);
            ResultSet rs1 = prepStmt.executeQuery();
            rs1.next();
            int capacity = rs1.getInt("Capacity");
            int pCount = rs1.getInt("PassengerCount");
            List<String> dests = Arrays.asList(rs1.getString("Destinations").split(","));
            String sqlstmt2 = "select PassengerName from passenger where PackageName = ?";
            PreparedStatement prepStmt2 = con.prepareStatement(sqlstmt2);
            prepStmt2.setString(1, name);
            ResultSet rs2 = prepStmt2.executeQuery();
            List<String> pNames = new ArrayList<>();
            while(rs2.next())
            {
                pNames.add(rs2.getString("PassengerName"));
            }
            List<Passenger> passengerList1 = new ArrayList<>();
            this.packageName = name;
            this.passengerCapacity = capacity;
            this.passengerCount = pCount;
            this.destinations = dests;
            for (String pName : pNames) {
                Passenger p = new Passenger();
                p.getPassenger(pName);
                passengerList1.add(p);
            }
            this.passengerList = passengerList1;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    @Override
    public void printTravelPackage(String name)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency","sumit","sumit");
            String sqlstmt = "select Destinations from travel_package where PackageName = ?";
            PreparedStatement prepStmt = con.prepareStatement(sqlstmt);
            prepStmt.setString(1,name);
            ResultSet rs=prepStmt.executeQuery();
            rs.next();
            String destinations = rs.getString("Destinations");
            List<String> dests;
            dests = Arrays.asList(destinations.split(","));
            System.out.println("Travel Package - " + name);
            System.out.println("Destinations and activities at each destination");
            for (String dest : dests) {
                Destination d = new Destination();
                d.getDestination(dest);
                System.out.println(d.destinationName);
                for (int j = 0; j < d.activities.size(); j++) {
                    System.out.println("Activity : " + d.activities.get(j).activityName);
                    System.out.println("Description : " + d.activities.get(j).description);
                    System.out.println("Cost : " + d.activities.get(j).cost);
                    System.out.println("Capacity : " + d.activities.get(j).capacity);
                    System.out.println();
                }
                System.out.println();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


    @Override
    public void printTravelPackagePassengers(String _packageName)
    {
        getTravelPackage(_packageName);
        System.out.println("Travel Package - "+ packageName);
        System.out.println("Passenger Capacity: "+ passengerCapacity);
        System.out.println("Passenger Enrolled: "+ passengerCount);

        for (Passenger passenger : passengerList) {
            System.out.println("Name: " + passenger.passengerName + " Passenger Number: " + passenger.passengerNumber);
        }
    }
}
