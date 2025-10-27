import java.io.*;
import java.util.Scanner;

public class PartC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Only one Scanner

        System.out.println("How many employee entries would you like to add?");
        int numEntries = sc.nextInt();

        for (int i = 0; i < numEntries; i++) {
            addEmployee(sc);  // pass scanner
        }

        display();

        sc.close();  // Close scanner here, once at the end
    }

    static void addEmployee(Scanner sc) {
        System.out.println("Enter the ID:");
        int id = sc.nextInt();
        sc.nextLine();  // Consume newline

        System.out.println("Enter the Name:");
        String name = sc.nextLine();

        System.out.println("Enter the Salary:");
        double salary = sc.nextDouble();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Employee.txt", true))) {
            writer.write("ID: " + id + ", Name: " + name + ", Salary: " + salary);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void display() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Employee.txt"))) {
            String line;
            System.out.println("\nEmployee Records:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
