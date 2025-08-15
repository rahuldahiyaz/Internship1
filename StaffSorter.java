import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Staff class represents an employee's basic details
class Staff {
    String fullName;
    int yearsOld;
    double monthlyPay;

    // Constructor to set staff details
    public Staff(String fullName, int yearsOld, double monthlyPay) {
        this.fullName = fullName;
        this.yearsOld = yearsOld;
        this.monthlyPay = monthlyPay;
    }

    // For displaying staff info in a readable format
    @Override
    public String toString() {
        return "Name: " + fullName + ", Age: " + yearsOld + ", Salary: " + monthlyPay;
    }
}

public class StaffSorter {
    public static void main(String[] args) {
        // Creating a list to store multiple staff members
        ArrayList<Staff> team = new ArrayList<>();
        team.add(new Staff("Rahul", 28, 55000));
        team.add(new Staff("Aman", 25, 65000));
        team.add(new Staff("Neha", 30, 75000));
        team.add(new Staff("Priya", 26, 60000));

        System.out.println("Initial Staff List:");
        for (Staff s : team) {
            System.out.println(s);
        }

        // Sorting staff by salary in descending order
        Collections.sort(team, new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2) {
                return Double.compare(s2.monthlyPay, s1.monthlyPay); // High to low
            }
        });

        System.out.println("\nSorted by Salary (High to Low):");
        for (Staff s : team) {
            System.out.println(s);
        }

        // Sorting staff by name in alphabetical order
        Collections.sort(team, new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2) {
                return s1.fullName.compareTo(s2.fullName); // A to Z
            }
        });

        System.out.println("\nSorted by Name (A to Z):");
        for (Staff s : team) {
            System.out.println(s);
        }
    }
}
