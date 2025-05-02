import java.lang.reflect.*;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class PasswordFinder {
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
            
            // Try to find constructors for class 0
            System.out.println("Constructors for class 0:");
            for (Constructor<?> constructor : class0.getDeclaredConstructors()) {
                System.out.println("  " + constructor);
            }
            
            // Try to find constructors for class 1
            System.out.println("\nConstructors for class 1:");
            for (Constructor<?> constructor : class1.getDeclaredConstructors()) {
                System.out.println("  " + constructor);
            }
            
            // Create an instance of class 0 (for non-static methods)
            Object obj0 = null;
            try {
                obj0 = class0.getDeclaredConstructor().newInstance();
                System.out.println("\nSuccessfully created instance of class 0");
            } catch (Exception e) {
                System.out.println("\nCould not instantiate class 0: " + e.getMessage());
                // Try calling methods statically
                System.out.println("Attempting to call methods statically...");
            }
            
            // Try calling method 'a' statically first to get values
            System.out.println("\nTesting class 1.a with parameters 0-5 (static call):");
            for (int i = 0; i <= 5; i++) {
                Object a_result = aMethod.invoke(null, i, 0);
                if (a_result != null && !a_result.toString().isEmpty()) {
                    System.out.println("1.a(" + i + ", 0) = " + a_result);
                    
                    // Try calling method 'c' with the result from 'a'
                    if (obj0 != null) {
                        System.out.println("  Calling 0.c with result from 1.a(" + i + ", 0):");
                        Object c_result = cMethod.invoke(obj0, a_result.toString());
                        System.out.println("  Result: " + c_result);
                    } else {
                        // Try calling 'c' statically
                        System.out.println("  Calling 0.c statically with result from 1.a(" + i + ", 0):");
                        Object c_result = cMethod.invoke(null, a_result.toString());
                        System.out.println("  Result: " + c_result);
                    }
                }
            }
            
            // Also try calling method 'c' directly with some common strings
            System.out.println("\nTrying common inputs for method 'c':");
            String[] commonInputs = {"", "password", "flag", "key", "secret"};
            for (String input : commonInputs) {
                try {
                    Object result;
                    if (obj0 != null) {
                        result = cMethod.invoke(obj0, input);
                    } else {
                        result = cMethod.invoke(null, input);
                    }
                    System.out.println("0.c(\"" + input + "\") = " + result);
                } catch (Exception e) {
                    System.out.println("Error calling 0.c(\"" + input + "\"): " + e.getMessage());
                }
            }
            
            classLoader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 