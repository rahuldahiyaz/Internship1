// Task 2

import java.util.ArrayList;
import java.util.Scanner;

// Student class to store student details
class Student {
    int id;
    String name;
    String grade;

    // Constructor to initialize student data
    public Student(int id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // toString() method to display student details in a readable format
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Grade: " + grade;
    }
}

// Main class to manage students
public class StudentManagement {
    // List to store all students
    static ArrayList<Student> students = new ArrayList<>();

    // Method to add a new student
    public static void addStudent(Scanner sc) {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Grade: ");
        String grade = sc.nextLine();

        // Create new Student object and add to list
        students.add(new Student(id, name, grade));
        System.out.println("Student added successfully!\n");
    }

    // Method to remove a student by ID
    public static void removeStudent(Scanner sc) {
        System.out.print("Enter Student ID to remove: ");
        int id = sc.nextInt();

        // Find and remove student
        boolean removed = false;
        for (Student s : students) {
            if (s.id == id) {
                students.remove(s);
                System.out.println("Student removed successfully!\n");
                removed = true;
                break;
            }
        }

        if (!removed) {
            System.out.println("No student found with ID: " + id + "\n");
        }
    }

    // Method to display all students
    public static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.\n");
            return;
        }
        System.out.println("List of Students:");
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        // Loop until user chooses to exit
        do {
            // Menu
            System.out.println("===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Display Students");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    removeStudent(sc);
                    break;
                case 3:
                    displayStudents();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.\n");
            }
        } while (choice != 4);

        sc.close();
    }
}
