package com.maciejsusala;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Game {
    private int score;

    // Możesz wrzucić w konstruktor domyślny - po przemyśleniu jednak to lepsze rozwiązanie. Ew. w blok inicijujący (zwykły)
    // Zamiast listy list można zrobić obiekt pytanie :) I tam też może być metoda, która sprawdza poprawną odpowiedź
    List<List<String>> quizQuestions = csvToArrayList();

    // kosmetyka ale raczej przed albo po deklarajcach pól bym umieścił
    private static final Scanner scanner = new Scanner(System.in);

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // prywatne metody, które nie są getterami/setterami raczej na końcu po publicznych metodach
    private boolean isCharABCD(char c) {
        return c >= 'A' && c <= 'D';
    }


    public void runGame() {
        for (int i = 1; i < quizQuestions.size(); i++) {
            displayQuestion(i);
            char readAnswer = getAnswerFromUser();
            // tak jak mówiłem można utworzyć klasę quiz question i tam przenieść sprawdzenie dla pytania
            char correctAnswer = quizQuestions.get(i).get(5).charAt(0);
            processAnswer(readAnswer, correctAnswer, i);
        }
        displayFinalScore();
    }

    // To też można by przenieśc to dej klasy Question
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
