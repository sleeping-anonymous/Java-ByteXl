import java.util.*;
import java.util.stream.*;

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " (₹" + price + ")";
    }
}

public class PartC {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product(101, "Laptop", 55000),
                new Product(102, "Mouse", 700),
                new Product(103, "Keyboard", 1200),
                new Product(104, "Monitor", 9000),
                new Product(105, "SSD", 3500)
        );

        // Filter, sort, collect names
        List<String> expensiveProducts = products.stream()
                .filter(p -> p.price > 1000)
                .sorted(Comparator.comparingDouble(p -> p.price))
                .map(p -> p.name)
                .collect(Collectors.toList());

        System.out.println("Products priced > 1000 sorted by price:");
        System.out.println(expensiveProducts);

        // Average price
        double avgPrice = products.stream()
                .mapToDouble(p -> p.price)
                .average()
                .orElse(0.0);
        System.out.println("\nAverage Price of all products: ₹" + avgPrice);
    }
}
