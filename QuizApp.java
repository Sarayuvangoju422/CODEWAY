import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    String question;
    List<String> options;
    int correctOptionIndex;

    public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
}

public class QuizApp {
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static List<QuizQuestion> questions = new ArrayList<>();
    private static Timer timer;

    public static void main(String[] args) {
        initializeQuestions();
        startQuiz();
    }

    private static void initializeQuestions() {
        // Add your quiz questions, options, and correct answers here
        questions.add(new QuizQuestion("What is the capital of France?",
                List.of("Berlin", "Paris", "Madrid", "Rome"), 1));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?",
                List.of("Earth", "Mars", "Venus", "Jupiter"), 1));
        // Add more questions as needed
    }

    private static void startQuiz() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                showNextQuestion();
            }
        }, 15000); // Set the timer duration in milliseconds (e.g., 15000 milliseconds = 15 seconds)

        showNextQuestion();
    }

    private static void showNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("\nQuestion: " + currentQuestion.question);
            for (int i = 0; i < currentQuestion.options.size(); i++) {
                System.out.println((i + 1) + ". " + currentQuestion.options.get(i));
            }

            int userAnswer = getUserAnswer();
            checkAnswer(userAnswer, currentQuestion.correctOptionIndex);
        } else {
            endQuiz();
        }
    }

    private static int getUserAnswer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter your answer (1-" + questions.get(currentQuestionIndex).options.size() + "): ");
        int userAnswer = scanner.nextInt();
        return userAnswer;
    }

    private static void checkAnswer(int userAnswer, int correctAnswer) {
        if (userAnswer == correctAnswer + 1) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is: " +
                    questions.get(currentQuestionIndex).options.get(correctAnswer));
        }

        currentQuestionIndex++;
        timer.cancel(); // Cancel the current timer
        startQuiz(); // Move to the next question
    }

    private static void endQuiz() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + " out of " + questions.size());

        // Display summary of correct and incorrect answers if needed

        // Additional cleanup or summary logic can be added here

        System.exit(0); // Exit the program
    }
}
