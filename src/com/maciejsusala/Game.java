package com.maciejsusala;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Game {
    private int score;

    private static final Scanner scanner = new Scanner(System.in);

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private boolean isCharABCD(char c) {
        return c >= 'A' && c <= 'D';
    }
    List<List<String>> quizQuestions = csvToArrayList();

    public void runGame() {
        for (int i = 1; i < quizQuestions.size(); i++) {
            displayQuestion(i);
            char readAnswer = getAnswerFromUser();
            char correctAnswer = quizQuestions.get(i).get(5).charAt(0);
            processAnswer(readAnswer, correctAnswer, i);
        }
        displayFinalScore();
    }

    private void displayQuestion(int questionIndex) {
        System.out.println(quizQuestions.get(questionIndex).get(0));
        System.out.println("A: " + quizQuestions.get(questionIndex).get(1));
        System.out.println("B: " + quizQuestions.get(questionIndex).get(2));
        System.out.println("C: " + quizQuestions.get(questionIndex).get(3));
        System.out.println("D: " + quizQuestions.get(questionIndex).get(4));
    }

    private void processAnswer(char userAnswer, char correctAnswer, int questionIndex) {
        if (userAnswer == correctAnswer) {
            System.out.println("Gratulacje, poprawna odpowiedź!");
            setScore(getScore() + 1);
        } else {
            System.out.println("Niestety, błędna odpowiedź.");
        }

        String answerValue = getAnswerValue(correctAnswer, questionIndex);

        System.out.println("Prawidłowa odpowiedź to: " + answerValue + " - " + quizQuestions.get(questionIndex).get(6));
        System.out.println();
    }

    private String getAnswerValue(char correctAnswer, int questionIndex) {
        return switch (correctAnswer) {
            case 'A' -> "A " + quizQuestions.get(questionIndex).get(1);
            case 'B' -> "B " + quizQuestions.get(questionIndex).get(2);
            case 'C' -> "C " + quizQuestions.get(questionIndex).get(3);
            case 'D' -> "D " + quizQuestions.get(questionIndex).get(4);
            default -> "";
        };
    }

    private void displayFinalScore() {
        System.out.println("Twój wynik to: " + getScore());
    }
    public List<List<String>> csvToArrayList() {
        String separator = File.separator;
        String filePath = "src" + separator + "quiz.csv";
        return HandleCSV.putCSVtoList(filePath);
    }

    public char getAnswerFromUser() {
        char result;
        while (true) {
            String input = scanner.nextLine();
            if (input.length() == 1) {
                char c = Character.toUpperCase(input.charAt(0));
                if (isCharABCD(c)) {
                    result = c;
                    break;
                }
            }
            System.out.println("Wpisz poprawną literę (A - D)");
        }
        return result;
    }
}
