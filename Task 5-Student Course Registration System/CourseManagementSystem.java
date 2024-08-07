package student_Course_Registration_System;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int enrolledStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        }
        return false;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents;
    }
}

class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            return true;
        }
        return false;
    }
}



public class CourseManagementSystem {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        
        courses.add(new Course("CS101", "Intro to Computer Science", "Basics of computer science", 30, "MWF 9-10AM"));
        courses.add(new Course("MA101", "Calculus I", "Introduction to Calculus", 25, "TTh 10-11:30AM"));

        students.add(new Student("1010", "Shubham"));
        students.add(new Student("1020", "Anand"));

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. List Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerForCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void listCourses() {
        for (Course course : courses) {
            System.out.println(course.getCode() + ": " + course.getTitle() + " - " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule() + " | Available Slots: " + course.getAvailableSlots());
        }
    }

    private static void registerForCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.next();
        Student student = findStudentByID(studentID);

        if (student != null) {
            System.out.print("Enter course code: ");
            String courseCode = scanner.next();
            Course course = findCourseByCode(courseCode);

            if (course != null && student.registerCourse(course)) {
                System.out.println("Successfully registered for the course.");
            } else {
                System.out.println("Registration failed. The course may be full or already registered.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.next();
        Student student = findStudentByID(studentID);

        if (student != null) {
            System.out.print("Enter course code: ");
            String courseCode = scanner.next();
            Course course = findCourseByCode(courseCode);

            if (course != null && student.dropCourse(course)) {
                System.out.println("Successfully dropped the course.");
            } else {
                System.out.println("Dropping failed. The course may not be registered.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student findStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

