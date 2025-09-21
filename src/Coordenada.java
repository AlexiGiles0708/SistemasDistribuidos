public class Coordenada {
    private double x, y;
    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    @Override
    public String toString() {
        return "Coordenada{" + "x=" + x + ", y=" + y + '}';
    }
}
