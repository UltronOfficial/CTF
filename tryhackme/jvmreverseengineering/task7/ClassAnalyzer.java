import java.lang.reflect.*;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassAnalyzer {
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
            Class<?> classC = classLoader.loadClass("c");
            
            // Print info about class 0
            System.out.println("Class 0 methods:");
            Method[] methods0 = class0.getDeclaredMethods();
            for (Method method : methods0) {
                System.out.println("  " + method.getName() + " - Return Type: " + method.getReturnType().getName());
                System.out.println("    Parameters: " + method.getParameterCount());
                for (Class<?> paramType : method.getParameterTypes()) {
                    System.out.println("      " + paramType.getName());
                }
            }
            
            // Print info about class 1
            System.out.println("\nClass 1 methods:");
            Method[] methods1 = class1.getDeclaredMethods();
            for (Method method : methods1) {
                System.out.println("  " + method.getName() + " - Return Type: " + method.getReturnType().getName());
                System.out.println("    Parameters: " + method.getParameterCount());
                for (Class<?> paramType : method.getParameterTypes()) {
                    System.out.println("      " + paramType.getName());
                }
            }
            
            // Print info about class c
            System.out.println("\nClass c methods:");
            Method[] methodsC = classC.getDeclaredMethods();
            for (Method method : methodsC) {
                System.out.println("  " + method.getName() + " - Return Type: " + method.getReturnType().getName());
                System.out.println("    Parameters: " + method.getParameterCount());
                for (Class<?> paramType : method.getParameterTypes()) {
                    System.out.println("      " + paramType.getName());
                }
            }
            
            classLoader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 