package com.example;

import encrypter.ListEncrypter;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class App 
{
    public static void main( String[] args )
    {
        long startTime = System.nanoTime();
        URL resourceUrl = App.class.getClassLoader().getResource("sample_file_1.txt");
        if ( resourceUrl == null) {
            throw new RuntimeException("Resource url is null, cannot proceed further");
        }
        File file = new File(resourceUrl.getFile());
        try (BufferedReader reader = new BufferedReader(new FileReader( file))) {
            String line;
            List<String> content = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }
            System.out.println("File content:");
            System.out.println(content);
            // Create ForkJoinPool
            ForkJoinPool pool = new ForkJoinPool();
            // Create and submit the main task
            ListEncrypter task = new ListEncrypter(content);
            List<String> result = pool.invoke(task);
            System.out.println("Encrypted content:");
            System.out.println(result);
        } catch (IOException e) {
            // Handle the exception
            System.err.println("Error reading the file: " + e.getMessage());
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime; // duration in nanoseconds
        System.out.println("Task execution time: " + duration /1_000_000  + " milliseconds");
    }
}
