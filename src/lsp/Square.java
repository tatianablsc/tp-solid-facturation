package lsp;

public final class Square implements Shape {
    private final int side;

    public Square(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Côté invalide");
        }
        this.side = side;
    }

    public int area() { return side * side; }
}