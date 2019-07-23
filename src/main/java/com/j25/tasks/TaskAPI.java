package com.j25.tasks;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskAPI {
    private final static String BASE_URL = "http://194.181.116.187:16666/task";
    private final static Gson GSON = new Gson();

    private HttpClient client = HttpClient.newBuilder().build();


    public Optional<Task> getById(Long idToGet){
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(BASE_URL + "/" + idToGet))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return Optional.ofNullable(GSON.fromJson(response.body(), Task.class));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public boolean delete(Long taskToDeleteId) {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(BASE_URL + "/" + taskToDeleteId))
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return GSON.fromJson(response.body(), Boolean.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List getAll() {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(BASE_URL))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return GSON.fromJson(response.body(), List.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ArrayList();
    }

    public void sendOrPut(Task taskToSend) {

        String jsonTaskToSend = GSON.toJson(taskToSend);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(BASE_URL))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonTaskToSend))
                .header("Content-Type", "application/json")
                .build();

        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Task taskToUpdate) {

        String jsonTaskToSend = GSON.toJson(taskToUpdate);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(BASE_URL))
                .POST(HttpRequest.BodyPublishers.ofString(jsonTaskToSend))
                .header("Content-Type", "application/json")
                .build();

        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
