package com.in;

import java.io.*;
import java.util.*;

public class StudentManager {
    private List<Student> students;
    private final String FILE_NAME = "students.txt";

    public StudentManager() {
        students = new ArrayList<>();
        loadFromFile();
    }

    // Load data from file
    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int rollNumber = Integer.parseInt(parts[1]);
                String grade = parts[2];
                students.add(new Student(name, rollNumber, grade));
            }
        } catch (FileNotFoundException e) {
            // File not found, start with an empty list
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save data to file
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                bw.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // CRUD Operations
    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentByRollNumber(int rollNumber) {
        return students.stream().filter(s -> s.getRollNumber() == rollNumber).findFirst().orElse(null);
    }

    public void updateStudent(int rollNumber, String name, String grade) {
        Student student = getStudentByRollNumber(rollNumber);
        if (student != null) {
            student.setName(name);
            student.setGrade(grade);
            saveToFile();
        }
    }

    public void deleteStudent(int rollNumber) {
        students.removeIf(s -> s.getRollNumber() == rollNumber);
        saveToFile();
    }
}
