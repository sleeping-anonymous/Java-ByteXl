import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class PartB {
    public static void main(String[] args) {
        student st1 = new student(10, "Monaal Sharma");
        String filename = "byteData.txt";

        // Serialize the object (write it to file)
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(st1);
            oos.close();
            System.out.println("Data added");
            st1.display();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Deserialize the object (read it back)
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            student st2 = (student) ois.readObject();
            ois.close();
            st2.display();  // Use display() to print student details
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class student implements Serializable {
    int id;
    String name;

    student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    void display() {
        System.out.println("id :" + id + " Name: " + name);
    }
}
