import srp.*;
import ocp.*;
import lsp.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test SRP
        Invoice invoice = new Invoice("F001", "Tatiana");
        invoice.addLine(new InvoiceLine("Clavier", 50, 2));
        invoice.addLine(new InvoiceLine("Souris", 20, 1));
        InvoiceCalculator calculator = new InvoiceCalculator();
        new InvoicePrinter(calculator).print(invoice);   // Total attendu : 120
        new InvoiceRepository().save(invoice);

        // Test OCP
        System.out.println(new DiscountService(new StandardDiscount()).applyDiscount(100));    // 95
        System.out.println(new DiscountService(new VipDiscount()).applyDiscount(100));         // 80
        System.out.println(new DiscountService(new StudentDiscount()).applyDiscount(100));     // 90
        System.out.println(new DiscountService(new BlackFridayDiscount()).applyDiscount(100)); // 70

        // Test LSP
        List<Shape> shapes = List.of(new Rectangle(5, 4), new Square(4));
        int total = 0;
        for (Shape s : shapes) {
            total += s.area();
        }
        System.out.println("Aire totale : " + total); // 36
    }
}