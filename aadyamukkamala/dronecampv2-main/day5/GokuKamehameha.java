import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GokuKamehameha extends JPanel implements ActionListener {

    Timer timer;
    int charge = 0;
    boolean firing = false;
    int beamLength = 0;

    public GokuKamehameha() {
        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!firing) {
            charge++;

            if (charge > 100) {
                firing = true;
                beamLength = 0;
            }
        } else {
            beamLength += 35;

            if (beamLength > 800) {
                firing = false;
                charge = 0;
                beamLength = 0;
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Background
        g2.setColor(new Color(10, 10, 30));
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Ground
        g2.setColor(new Color(40, 80, 40));
        g2.fillRect(0, 430, getWidth(), 120);

        // =========================
        // GOKU
        // =========================

        int x = 230;
        int y = 280;

        // Hair
        g2.setColor(Color.BLACK);

        Polygon hair = new Polygon();
        hair.addPoint(x - 45, y - 110);
        hair.addPoint(x - 20, y - 155);
        hair.addPoint(x - 5, y - 120);
        hair.addPoint(x + 15, y - 170);
        hair.addPoint(x + 25, y - 120);
        hair.addPoint(x + 60, y - 150);
        hair.addPoint(x + 50, y - 90);
        hair.addPoint(x - 50, y - 90);

        g2.fillPolygon(hair);

        // Face
        g2.setColor(new Color(255, 205, 160));
        g2.fillOval(x - 42, y - 105, 85, 90);

        // Eyes
        g2.setColor(Color.BLACK);
        g2.fillOval(x - 22, y - 70, 10, 15);
        g2.fillOval(x + 15, y - 70, 10, 15);

        // Nose
        g2.drawLine(x, y - 55, x - 5, y - 45);

        // Mouth
        g2.drawArc(x - 12, y - 48, 25, 15, 180, 180);

        // Neck
        g2.setColor(new Color(255, 205, 160));
        g2.fillRect(x - 18, y - 20, 36, 30);

        // Orange shirt
        g2.setColor(new Color(240, 120, 20));
        g2.fillOval(x - 65, y, 130, 150);

        // Blue undershirt
        g2.setColor(new Color(30, 80, 200));
        g2.fillOval(x - 25, y + 5, 50, 80);

        // Legs
        g2.setColor(new Color(30, 70, 180));
        g2.fillRect(x - 55, y + 120, 45, 130);
        g2.fillRect(x + 10, y + 120, 45, 130);

        // Boots
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(x - 65, y + 240, 55, 25);
        g2.fillRect(x + 10, y + 240, 55, 25);

        // =========================
        // ARMS CHARGING KAMEHAMEHA
        // =========================

        g2.setStroke(new BasicStroke(22));
        g2.setColor(new Color(255, 205, 160));

        // Upper arms
        g2.drawLine(x - 45, y + 35, x - 90, y + 80);
        g2.drawLine(x + 45, y + 35, x + 90, y + 80);

        // Hands
        g2.fillOval(x - 110, y + 65, 40, 40);
        g2.fillOval(x + 70, y + 65, 40, 40);

        // =========================
        // ENERGY BALL
        // =========================

        int ballSize = 25 + charge / 2;

        int ballX = x - ballSize / 2;
        int ballY = y + 65;

        // Glow
        g2.setColor(new Color(50, 100, 255, 60));
        g2.fillOval(
                ballX - 20,
                ballY - 20,
                ballSize + 40,
                ballSize + 40
        );

        // Energy
        g2.setColor(new Color(50, 150, 255));
        g2.fillOval(ballX, ballY, ballSize, ballSize);

        g2.setColor(Color.WHITE);
        g2.fillOval(
                ballX + ballSize / 4,
                ballY + ballSize / 4,
                ballSize / 2,
                ballSize / 2
        );

        // =========================
        // KAMEHAMEHA BEAM
        // =========================

        if (firing) {

            int startX = x + 100;
            int startY = y + 85;

            // Outer glow
            g2.setStroke(new BasicStroke(70));
            g2.setColor(new Color(40, 80, 255, 70));
            g2.drawLine(
                    startX,
                    startY,
                    startX + beamLength,
                    startY
            );

            // Blue beam
            g2.setStroke(new BasicStroke(40));
            g2.setColor(new Color(30, 100, 255));
            g2.drawLine(
                    startX,
                    startY,
                    startX + beamLength,
                    startY
            );

            // White center
            g2.setStroke(new BasicStroke(15));
            g2.setColor(Color.WHITE);
            g2.drawLine(
                    startX,
                    startY,
                    startX + beamLength,
                    startY
            );

            // Beam sparks
            g2.setStroke(new BasicStroke(3));

            for (int i = 0; i < 15; i++) {
                int sparkX =
                        startX + (int)(Math.random() * beamLength);

                int sparkY =
                        startY + (int)(Math.random() * 80) - 40;

                g2.setColor(new Color(100, 180, 255));
                g2.drawLine(
                        sparkX,
                        sparkY,
                        sparkX + 10,
                        sparkY
                );
            }
        }

        // =========================
        // TEXT
        // =========================

        g2.setFont(new Font("Arial", Font.BOLD, 32));
        g2.setColor(Color.WHITE);

        if (!firing) {
            g2.drawString("KAMEHAMEHA!!!", 420, 100);
        } else {
            g2.drawString("KAMEHAMEHAAAA!!!", 390, 100);
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Goku Kamehameha");

        GokuKamehameha game = new GokuKamehameha();

        frame.add(game);
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}