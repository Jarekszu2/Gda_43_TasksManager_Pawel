package com.j25.tasks;


import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//     uri
// (url + urn)


public class Main {
    public static void main(String[] args) {
        TaskAPI api = new TaskAPI();
        InputScanner scanner = new InputScanner();

        String action;
        do {
            action = scanner.askAction();
            if (action.equalsIgnoreCase("C")) {
                Task newTaskToSend = scanner.askNewTask();
                api.sendOrPut(newTaskToSend);
            } else if (action.equalsIgnoreCase("R")) {
                List tasks = api.getAll();

                for (Object o : tasks) {
                    System.out.println(o);
                }
            } else if (action.equalsIgnoreCase("U")) {
                Optional<Task> taskToEditOptional = api.getById(scanner.askIdToGet());
                if (taskToEditOptional.isPresent()) {
                    Task taskToEdit = taskToEditOptional.get();

                    scanner.editTask(taskToEdit);

                    api.update(taskToEdit);
                } else {
                    System.err.println("Zadanie nie istnieje.");
                }
            } else if (action.equalsIgnoreCase("D")) {
                api.getAll().forEach(System.out::println);

                Long idToDelete = scanner.askIdToDelete();
                api.delete(idToDelete);
            }
        } while (!action.equalsIgnoreCase("exit"));
    }
}
