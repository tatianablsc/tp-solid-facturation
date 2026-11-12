package ocp;

public class StandardDiscount implements DiscountStrategy {
    public double apply(double amount) { return amount * 0.95; }
}