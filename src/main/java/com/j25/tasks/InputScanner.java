package com.j25.tasks;

import java.util.Scanner;

public class InputScanner {
    private Scanner scanner = new Scanner(System.in);

    public String askAction() {
        System.out.println("Co chcesz zrobić? [c, r, u, d]");
        return scanner.nextLine();
    }


    public Task askNewTask() {
        Task newTask = new Task();

        System.out.println("Podaj nazwę zadania:");
        newTask.setName(scanner.nextLine());

        System.out.println("Czy zadanie jest wykonane?");
        try {
            newTask.setDone(Boolean.parseBoolean(scanner.nextLine()));
        } catch (IllegalArgumentException iae) {
            System.err.println("Niepoprawne wejście, oznaczam zadanie jako niewykonane.");
        }

        return newTask;
    }

    public Long askIdToDelete() {
        return askId("Podaj id niechcianego zadania:");
    }

    public Long askIdToGet() {
        return askId("Podaj id zadania do modyfikacji");
    }

    private Long askId(String message) {
        Long id = null;
        do {
            try {
                System.out.println(message);
                id = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException nfe) {
                System.err.println("Niepoprawne wejście.");
            }
        } while (id == null);
        return id;
    }

    public void editTask(Task taskToEdit) {
        System.out.println("Podaj nową nazwę:");
        taskToEdit.setName(scanner.nextLine());

        System.out.println("Czy zadanie jest wykonane?");
        try {
            taskToEdit.setDone(Boolean.parseBoolean(scanner.nextLine()));
        } catch (IllegalArgumentException iae) {
            System.err.println("Niepoprawne wejście, oznaczam zadanie jako niewykonane.");
        }
    }
}
