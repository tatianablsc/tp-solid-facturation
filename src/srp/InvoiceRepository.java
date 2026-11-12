package srp;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository {
    private final List<Invoice> invoices = new ArrayList<>();

    public void save(Invoice invoice) { invoices.add(invoice); }
    public int count() { return invoices.size(); }
}