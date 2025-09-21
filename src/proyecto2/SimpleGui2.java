package proyecto2;

import javax.swing.*;
import java.awt.*;

public class SimpleGui2 extends JFrame {
    public static void main(String[] args) {
        SimpleGui2 gui = new SimpleGui2();
        gui.setVisible(true);
    }

    public SimpleGui2() {
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel p = new Panel();
        add(p);
    }

    private class Panel extends JPanel {
        private int naveX = 0; // posición horizontal de la nave
        private final int naveY = 0; // vertical fija
        private final double scale = 0.3; // nave pequeña
        private final int velocidad = 4; // pixeles por frame

        public Panel() {
            Timer timer = new Timer(30, e -> {
                naveX += velocidad;
                // Si la nave sale del panel, vuelve a empezar
                if (naveX > getWidth()) {
                    naveX = -80; // ajusta según tamaño nave
                }
                repaint();
            });
            timer.start();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            
            g.setColor(Color.blue);
            int offsetX = naveX;
            int offsetY = naveY;

            int[][] pts = {
                    {0, 100},   // ala izquierda
                    {30, 60},   // base ala izquierda
                    {60, 45},   // cuerpo izquierdo
                    {80, 20},   // base cohete izquierda
                    {100, 0},   // punta superior
                    {120, 20},  // base cohete derecha
                    {140, 45},  // cuerpo derecho
                    {170, 60},  // base ala derecha
                    {200, 100}, // ala derecha
                    {100, 120}  // punta inferior central (motor)
            };

            Polygon nave = new Polygon();

            double theta = Math.PI / 2;
            for (int[] p : pts) {
                int x0 = p[0], y0 = p[1];
                int xRot = (int)(x0 * Math.cos(theta) - y0 * Math.sin(theta));
                int yRot = (int)(x0 * Math.sin(theta) + y0 * Math.cos(theta));
                nave.addPoint(offsetX + (int)(scale * xRot), offsetY + (int)(scale * yRot));
            }

            g.drawPolygon(nave);


            int n = 5;
            for (int i = 0; i < n; i++) {
                int centerX = (int)(Math.random() * getWidth());
                int centerY = (int)(Math.random() * getHeight());
                drawAsteroid(g, centerX, centerY, 20, 50);
            }
        }

        private void drawAsteroid(Graphics g, int cx, int cy, int minRadius, int maxRadius) {
            int numVertices = 10 + (int)(Math.random() * 6);
            Polygon asteroid = new Polygon();
            for (int i = 0; i < numVertices; i++) {
                double angle = 2 * Math.PI * i / numVertices;
                int radius = minRadius + (int)(Math.random() * (maxRadius - minRadius));
                int x = cx + (int)(Math.cos(angle) * radius);
                int y = cy + (int)(Math.sin(angle) * radius);
                asteroid.addPoint(x, y);
            }
            g.setColor(Color.black);
            g.drawPolygon(asteroid);
        }
    }
}