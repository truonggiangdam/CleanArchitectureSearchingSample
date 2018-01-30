package com.example.giangdam.data.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by cpu11326-local on 30/01/2018.
 */
@Singleton
public class JsonConverter {
    private final Gson gson = new Gson();

    @Inject
    JsonConverter() {}
    public String listObjectToJson(Object listObject) {
        final Type listObjectType = new TypeToken<Object>() {}.getType();
        return gson.toJson(listObject, listObjectType);
    }

    public <T> List<T> jsonToListObject(String jsonString) {
        final Type listObjectType = new TypeToken<List<T>>() {}.getType();
        return gson.fromJson(jsonString, listObjectType);
    }
}
