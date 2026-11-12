package ocp;

public class VipDiscount implements DiscountStrategy {
    public double apply(double amount) { return amount * 0.80; }
}