import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Activity {
    public String activityName;
    public String description;
    public int cost;
    public int capacity;
    public int currentCount;
    public String destinationName;

    public void getActivity(String activityName)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency", "sumit", "sumit");
            String sqlstmt = "select * from activity where ActivityName = ?";
            PreparedStatement prepStmt = con.prepareStatement(sqlstmt);
            prepStmt.setString(1, activityName);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            String description = rs.getString("Description");
            String destName = rs.getString("DestinationName");
            int cost = rs.getInt("Cost");
            int capacity = rs.getInt("Capacity");
            int currentCount = rs.getInt("CurrentCount");
            this.activityName = activityName;
            this.description = description;
            this.cost = cost;
            this.capacity = capacity;
            this.destinationName = destName;
            this.currentCount = currentCount;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void availableActivities()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency", "sumit", "sumit");
            String sqlstmt = "select * from activity where Capacity != CurrentCount";
            PreparedStatement prepStmt = con.prepareStatement(sqlstmt);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            List<Activity> activities = new ArrayList<>();
            while (rs.next()) {
                Activity a = new Activity();
                //Load activities which are not full
                a.activityName = rs.getString("ActivityName");
                a.cost = rs.getInt("Cost");
                a.capacity = rs.getInt("Capacity");
                a.description = rs.getString("Description");
                a.currentCount = rs.getInt("CurrentCount");
                a.destinationName = rs.getString("DestinationName");
                activities.add(a);
            }
            for (Activity activity : activities) {
                System.out.println("Activity Name :" + activity.activityName);
                System.out.println("Destination :" + activity.destinationName);
                System.out.println("Description :" + activity.description);
                System.out.println("Cost :" + activity.cost);
                System.out.println("Capacity :" + activity.capacity);
                System.out.println("Current count :" + activity.currentCount);
                System.out.println("Spaces available :" + (activity.capacity - activity.currentCount));
                System.out.println();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
