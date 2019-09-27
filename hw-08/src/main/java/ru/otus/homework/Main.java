package ru.otus.homework;


import com.google.gson.Gson;
import ru.otus.homework.examples.JsonObjectUser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        Gson gson=new Gson();

        JsonObjectUser object = new JsonObjectUser();
        String jsonUser=new JsonUser().json(object);
        JsonObjectUser object2 = gson.fromJson(jsonUser, JsonObjectUser.class);
        System.out.println(object2);

    }





}


