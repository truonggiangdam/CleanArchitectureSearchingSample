package com.example.giangdam.data.file;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by cpu11326-local on 30/01/2018.
 */
@Singleton
public class FileManager {

    @Inject
    FileManager() {}

    public void writeToFile(File file, String content) {
        if(file.exists()) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(content);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readFileContent(File file) {
        final StringBuilder contentBuilder = new StringBuilder();
        if(file.exists()) {
            String line;

            try {
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);

                while ((line = bufferedReader.readLine()) != null) {
                    contentBuilder.append(line).append("\n");
                }

                bufferedReader.close();
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return contentBuilder.toString();
    }

    public boolean exists(File file) {
        return file.exists();
    }

    public boolean clearDirectory(File directory) {
        boolean result = false;
        if(directory.exists()) {
            for(File file: directory.listFiles()) {
                result = file.delete();
                if(!result){
                    break;
                }
            }
        }

        return result;
    }

    public void writeToPreferences(Context context, String preferenceFileName, String key, long value) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName,
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getFromPreferences(Context context, String preferenceFileName, String key) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName,
                Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }
}
