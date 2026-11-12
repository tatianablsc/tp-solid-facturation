package srp;

public class InvoicePrinter {
    private final InvoiceCalculator calculator;

    public InvoicePrinter(InvoiceCalculator calculator) {
        this.calculator = calculator;
    }

    public void print(Invoice invoice) {
        System.out.println("Facture " + invoice.getNumber() + " - " + invoice.getClient());
        for (InvoiceLine line : invoice.getLines()) {
            System.out.println(line.getLabel() + " x" + line.getQuantity() + " : " + line.getUnitPrice());
        }
        System.out.println("Total : " + calculator.calculateTotal(invoice));
    }
}