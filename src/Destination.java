import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;


public class Destination {
    public String destinationName;
    public List<Activity> activities;

    public void getDestination(String destinationName)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency", "sumit", "sumit");
            String sqlstmt = "select ActivityName from activity where DestinationName = ?";
            PreparedStatement prepStmt = con.prepareStatement(sqlstmt);
            prepStmt.setString(1, destinationName);
            ResultSet rs = prepStmt.executeQuery();
            List<String> ac = new ArrayList<>();
            while(rs.next())
            {
                ac.add(rs.getString("ActivityName"));
            }
            List<Activity> activities1 = new ArrayList<>();
            this.destinationName = destinationName;
            for (String s : ac) {
                Activity a = new Activity();
                a.getActivity(s);
                activities1.add(a);
            }
            this.activities = activities1;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
