package studentGrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TotalMarksTask implements Runnable {
    private List<Double> marks;

    public TotalMarksTask(List<Double> marks) {
        this.marks = marks;
    }

    @Override
    public void run() {
        double totalMarks = marks.stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("Total Marks: " + totalMarks);
    }
}

class AveragePercentageTask implements Runnable {
    private List<Double> marks;

    public AveragePercentageTask(List<Double> marks) {
        this.marks = marks;
    }

    @Override
    public void run() {
        double totalMarks = marks.stream().mapToDouble(Double::doubleValue).sum();
        double averagePercentage = totalMarks / marks.size();
        System.out.println("Average Percentage: " + averagePercentage);
    }
}

class GradeTask implements Runnable {
    private List<Double> marks;

    public GradeTask(List<Double> marks) {
        this.marks = marks;
    }

    @Override
    public void run() {
        double totalMarks = marks.stream().mapToDouble(Double::doubleValue).sum();
        double averagePercentage = totalMarks / marks.size();
        char grade = calculateGrade(averagePercentage);
        System.out.println("Grade: " + grade);
    }

    private char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}

public class StudentGrades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> marks = new ArrayList<>();

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        for (int i = 0; i < numSubjects; i++) {
            try {
                System.out.print("Enter the marks for subject " + (i + 1) + ": ");
                double mark = scanner.nextDouble();
                if (mark < 0 || mark > 100) {
                    throw new IllegalArgumentException("Marks should be between 0 and 100.");
                }
                marks.add(mark);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                i--; 
            }
        }

        
        Thread totalMarksThread = new Thread(new TotalMarksTask(marks));
        Thread averagePercentageThread = new Thread(new AveragePercentageTask(marks));
        Thread gradeThread = new Thread(new GradeTask(marks));

        
        totalMarksThread.start();
        averagePercentageThread.start();
        gradeThread.start();

        try {
           
            totalMarksThread.join();
            averagePercentageThread.join();
            gradeThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }

        scanner.close();
    }
}
