package srp;

public class InvoiceCalculator {
    public double calculateTotal(Invoice invoice) {
        double total = 0;
        for (InvoiceLine line : invoice.getLines()) {
            total += line.getUnitPrice() * line.getQuantity();
        }
        return total;
    }
}