package srp;

public class InvoiceLine {
    private final String label;
    private final double unitPrice;
    private final int quantity;

    public InvoiceLine(String label, double unitPrice, int quantity) {
        this.label = label;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getLabel() { return label; }
    public double getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }
}