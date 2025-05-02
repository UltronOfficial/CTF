import java.lang.reflect.*;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class FinalFinder {
    public static void main(String[] args) {
        try {
            // Get the current directory
            File currentDir = new File(".");
            URL[] urls = {currentDir.toURI().toURL()};
            
            // Create a class loader to load classes from the current directory
            URLClassLoader classLoader = new URLClassLoader(urls);
            
            // Load the classes
            Class<?> class0 = classLoader.loadClass("0");
            Class<?> class1 = classLoader.loadClass("1");
            
            // Get the 'c' method from class 0
            Method cMethod = class0.getDeclaredMethod("c", String.class);
            cMethod.setAccessible(true);
            
            // Get the 'a' method from class 1
            Method aMethod = class1.getDeclaredMethod("a", int.class, int.class);
            aMethod.setAccessible(true);
            
            // Create an instance of class 0 (for non-static methods)
            Object obj0 = class0.getDeclaredConstructor().newInstance();
            
            // Based on previous results, we know that 1.a(5, 0) gave a promising result
            // Let's explore more combinations around that
            System.out.println("Exploring more parameter combinations for 1.a and 0.c:");
            
            // Try 1.a with parameter 5 and various second parameters
            for (int j = 0; j <= 10; j++) {
                try {
                    Object a_result = aMethod.invoke(null, 5, j);
                    if (a_result != null && !a_result.toString().isEmpty()) {
                        System.out.println("1.a(5, " + j + ") = " + a_result);
                        
                        // Pass this result to 0.c
                        Object c_result = cMethod.invoke(obj0, a_result.toString());
                        System.out.println("  0.c(1.a(5, " + j + ")) = " + c_result);
                        
                        // Try to check if this is the correct password by running the original jar
                        // (This is just a suggestion - we don't actually run the jar here)
                        System.out.println("  Possible password: " + c_result);
                    }
                } catch (Exception e) {
                    System.out.println("Error with params (5, " + j + "): " + e.getMessage());
                }
            }
            
            // According to the writeup, the most likely candidate is the result from:
            // 0.c(1.a(5, 0))
            Object a_result = aMethod.invoke(null, 5, 0);
            Object c_result = cMethod.invoke(obj0, a_result.toString());
            
            System.out.println("\nThe most likely password is: " + c_result);
            System.out.println("Generated from 1.a(5, 0) = " + a_result);
            
            classLoader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 