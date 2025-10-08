import java.util.*;

class Student {
    String name;
    int age;
    double marks;

    Student(String name, int age, double marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " (" + marks + ")";
    }
}

public class PartB {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Riya", 20, 78.5),
                new Student("Karan", 21, 55.0),
                new Student("Simran", 19, 92.0),
                new Student("Vijay", 22, 61.5)
        );

        System.out.println("Students with marks > 60 sorted by marks:");
        students.stream()
                .filter(s -> s.marks > 60)
                .sorted((s1, s2) -> Double.compare(s2.marks, s1.marks))
                .map(s -> s.name + " - " + s.marks)
                .forEach(System.out::println);
    }
}
