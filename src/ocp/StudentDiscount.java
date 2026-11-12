package ocp;

public class StudentDiscount implements DiscountStrategy {
    public double apply(double amount) { return amount * 0.90; }
}