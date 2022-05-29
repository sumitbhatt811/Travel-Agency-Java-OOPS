import java.util.*;

public class Main {
    public static void main(String[] args) {
        int op = 0;
        while(op!=5) {
            System.out.println("Welcome to New Travel Agency");
            System.out.println("1. Print itinerary of the travel package");
            System.out.println("2. Print the passenger list of the travel package");
            System.out.println("3. Print the details of an individual passenger");
            System.out.println("4. Print the details of available activities");
            System.out.println("5. Exit");
            System.out.println("Select an option");

            Scanner sc = new Scanner(System.in);
            op = sc.nextInt();
            String packageName;
            switch (op) {
                case 1:
                    System.out.println("Type a package name");
                    sc.nextLine();
                    packageName = sc.nextLine();
                    Travel_package t1 = new Travel_package();
                    t1.printTravelPackage(packageName);
                    break;
                case 2:
                    System.out.println("Type a package name");
                    sc.nextLine();
                    String packageName2 = sc.nextLine();
                    Travel_package t2 = new Travel_package();
                    System.out.println("Printing passenger List of the package " + packageName2);
                    t2.printTravelPackagePassengers(packageName2);
                    break;
                case 3:
                    System.out.println("Printing passenger details of each passenger");
                    Passenger P = new Passenger();
                    P.printAllPassengers();
                    break;
                case 4:
                    System.out.println("Details of available activities");
                    Activity A = new Activity();
                    A.availableActivities();
                    break;
            }
        }
    }
}