package proyecto2;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;

public class SimpleGui2 extends JFrame {
    public static void main(String[] args) {
        int n = 8; // Número de asteroides
        SimpleGui2 gui = new SimpleGui2(n);
        gui.setVisible(true);
    }

    public SimpleGui2(int n) {
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel p = new Panel(n);
        add(p);
    }

    private static class Asteroide {
        int cx, cy;
        int minRadius, maxRadius;
        int numVertices;
        double vx, vy;
        Polygon shape;

        public Asteroide(int cx, int cy, int minRadius, int maxRadius, int numVertices) {
            this.cx = cx;
            this.cy = cy;
            this.minRadius = minRadius;
            this.maxRadius = maxRadius;
            this.numVertices = numVertices;
            this.vx = Math.random() * 4 - 2;
            this.vy = Math.random() * 4 - 2;
            generateShape();
        }

        private void generateShape() {
            shape = new Polygon();
            for (int i = 0; i < numVertices; i++) {
                double angle = 2 * Math.PI * i / numVertices;
                int radius = minRadius + (int)(Math.random() * (maxRadius - minRadius));
                int x = cx + (int)(Math.cos(angle) * radius);
                int y = cy + (int)(Math.sin(angle) * radius);
                shape.addPoint(x, y);
            }
        }

        public void move(java.util.List<Asteroide> others, int width, int height) {
            cx += vx;
            cy += vy;
            if (cx < 0) cx = width;
            if (cx > width) cx = 0;
            if (cy < 0) cy = height;
            if (cy > height) cy = 0;
            generateShape();
        }

        public void draw(Graphics g) {
            g.setColor(Color.black);
            g.drawPolygon(shape);
        }
    }

    private class Panel extends JPanel {
        private final java.util.List<Asteroide> asteroides;
        private int n;

        private int naveX = 0;
        private final int naveY = 0;
        private final double scale = 0.3;
        private final int velocidad = 4;
        private final int naveWidth = (int)(0.3 * 200); // aprox nave rotada

        public Panel(int n) {
            this.n = n;
            asteroides = new ArrayList<>();
            Random rand = new Random(12345);
            for (int i = 0; i < n; i++) {
                int centerX = 100 + rand.nextInt(1000);
                int centerY = 100 + rand.nextInt(600);
                int minRadius = 20, maxRadius = 50;
                int numVertices = 10 + rand.nextInt(6);
                asteroides.add(new Asteroide(centerX, centerY, minRadius, maxRadius, numVertices));
            }

            Timer timer = new Timer(30, e -> {
                naveX += velocidad;
                if (naveX > getWidth()) naveX = -naveWidth;
                for (Asteroide a : asteroides) {
                    a.move(asteroides, getWidth(), getHeight());
                }
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Dibuja la nave animada
            g.setColor(Color.blue);
            int offsetX = naveX;
            int offsetY = naveY;

            int[][] pts = {
                    {0, 100}, {30, 60}, {60, 45}, {80, 20}, {100, 0},
                    {120, 20}, {140, 45}, {170, 60}, {200, 100}, {100, 120}
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

            // Dibuja los asteroides
            for (Asteroide a : asteroides) {
                a.draw(g);
            }

            // Barra de progreso (abajo)
            int barWidth = getWidth() - 40;
            int barHeight = 30;
            int barX = 20;
            int barY = getHeight() - barHeight - 20;
            int totalDistancia = getWidth() + naveWidth;
            double progreso = (double)(naveX + naveWidth) / totalDistancia;
            int fillWidth = (int)(barWidth * progreso);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(barX, barY, barWidth, barHeight);
            g.setColor(Color.GREEN.darker());
            g.fillRect(barX, barY, fillWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(barX, barY, barWidth, barHeight);
            g.drawString("Progreso: " + (int)(progreso * 100) + "%", barX + barWidth / 2 - 40, barY + barHeight - 8);
        }
    }
}