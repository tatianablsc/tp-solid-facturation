package lsp;

public final class Rectangle implements Shape {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions invalides");
        }
        this.width = width;
        this.height = height;
    }

    public int area() { return width * height; }
}