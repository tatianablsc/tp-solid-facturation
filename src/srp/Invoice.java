package srp;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private final String number;
    private final String client;
    private final List<InvoiceLine> lines = new ArrayList<>();

    public Invoice(String number, String client) {
        this.number = number;
        this.client = client;
    }

    public void addLine(InvoiceLine line) { lines.add(line); }
    public String getNumber() { return number; }
    public String getClient() { return client; }
    public List<InvoiceLine> getLines() { return lines; }
}