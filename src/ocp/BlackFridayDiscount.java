package ocp;

public class BlackFridayDiscount implements DiscountStrategy {
    public double apply(double amount) { return amount * 0.70; }
}