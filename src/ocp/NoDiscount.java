package ocp;

public class NoDiscount implements DiscountStrategy {
    public double apply(double amount) { return amount; }
}