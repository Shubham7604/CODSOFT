import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<QuizQuestion> questions;
    private int score;
    private List<String> results;
    private Scanner scanner;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        results = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new QuizQuestion("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 0));
        questions.add(new QuizQuestion("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        questions.add(new QuizQuestion("Which company developed Java?", new String[]{"Microsoft", "Apple", "Sun Microsystems", "Google"}, 2));
        questions.add(new QuizQuestion("What is the size of an int in Java?", new String[]{"4 bytes", "2 bytes", "8 bytes", "1 byte"}, 0));
        questions.add(new QuizQuestion("Which of these is not a Java keyword?", new String[]{"class", "public", "goto", "integer"}, 3));
        questions.add(new QuizQuestion("Which method is used to start a thread execution?", new String[]{"init()", "start()", "run()", "resume()"}, 1));
        questions.add(new QuizQuestion("What is the default value of a boolean variable in Java?", new String[]{"true", "false", "1", "0"}, 1));
        questions.add(new QuizQuestion("Which package contains the Random class?", new String[]{"java.util", "java.lang", "java.io", "java.net"}, 0));
        questions.add(new QuizQuestion("Which of the following is a marker interface?", new String[]{"Serializable", "Cloneable", "Remote", "All of the above"}, 3));
        questions.add(new QuizQuestion("What is the return type of the hashCode() method in the Object class?", new String[]{"int", "long", "void", "Object"}, 0));
        
    }

    public void start() {
        for (QuizQuestion question : questions) {
            askQuestion(question);
        }
        showResult();
    }

    private void askQuestion(QuizQuestion question) {
        System.out.println(question.getQuestion());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ": " + options[i]);
        }
        System.out.print("Your answer (1-4): ");
        long startTime = System.currentTimeMillis();
        int userAnswer = scanner.nextInt() - 1;
        long endTime = System.currentTimeMillis();

        if ((endTime - startTime) > 10000) {
            System.out.println("Time's up! Moving to the next question.");
            results.add("Question: " + question.getQuestion() + "\nYour answer: Time's up! \nCorrect answer: " + question.getCorrectAnswerText());
        } else {
            boolean isCorrect = userAnswer == question.getCorrectAnswer();
            if (isCorrect) {
                score++;
            }
            results.add("Question: " + question.getQuestion() + " \nYour answer: " + options[userAnswer] + (isCorrect ? " (Correct)" : " (Incorrect)") + " \nCorrect answer: " + question.getCorrectAnswerText());
        }
    }

    private void showResult() {
        System.out.println("\nYour final score is: " + score + "/" + questions.size());
        System.out.println("Detailed Results:");
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.start();
    }
}

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctAnswer;

    public QuizQuestion(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }
    public String getCorrectAnswerText() {
        return options[correctAnswer];
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

