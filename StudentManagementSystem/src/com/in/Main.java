package com.in;

import java.util.Scanner;

public class Main {
    private static StudentManager studentManager = new StudentManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Student Record Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Name: ");
        scanner.nextLine(); // consume newline
        String name = scanner.nextLine();
        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        System.out.print("Enter Grade: ");
        scanner.nextLine(); // consume newline
        String grade = scanner.nextLine();
        
        Student student = new Student(name, rollNumber, grade);
        studentManager.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void viewAllStudents() {
        for (Student student : studentManager.getAllStudents()) {
            System.out.println(student);
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Roll Number of the Student to Update: ");
        int rollNumber = scanner.nextInt();
        Student student = studentManager.getStudentByRollNumber(rollNumber);
        if (student != null) {
            System.out.print("Enter New Name: ");
            scanner.nextLine(); // consume newline
            String name = scanner.nextLine();
            System.out.print("Enter New Grade: ");
            String grade = scanner.nextLine();
            studentManager.updateStudent(rollNumber, name, grade);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter Roll Number of the Student to Delete: ");
        int rollNumber = scanner.nextInt();
        studentManager.deleteStudent(rollNumber);
        System.out.println("Student deleted successfully.");
    }
}
