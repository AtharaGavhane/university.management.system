package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class About extends JFrame {

    private static final int WIDTH = 750;
    private static final int HEIGHT = 550;
    private final Color ACCENT_BLUE = new Color(30, 144, 255);
    private final Color BASE_BG = new Color(245, 245, 245);

    // --- Inner Class for Advanced Background Animation (3D Facade Simulation) ---
    private class AnimatedFacade extends JPanel {
        private Timer timer;
        private int animationOffset = 0;
        private final Color GLASS_COLOR = new Color(150, 200, 255, 100);
        private final Color WALL_COLOR = new Color(180, 180, 220);

        public AnimatedFacade() {
            setOpaque(false);

            // Timer for continuous redraw
            timer = new Timer(100, e -> {
                animationOffset = (animationOffset + 1) % 100;
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            // 1. Dark Gradient Background (Simulates depth/night sky)
            GradientPaint gpBg = new GradientPaint(0, 0, new Color(5, 5, 25), w, h, new Color(10, 10, 50));
            g2d.setPaint(gpBg);
            g2d.fillRect(0, 0, w, h);

            // 2. Draw Stylized Building Facade
            g2d.setColor(WALL_COLOR);
            g2d.fillRoundRect(w / 4, h / 4, w / 2, h / 2, 20, 20);

            // 3. Animated Windows (Simulating internal lights/reflections)
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    int x = w / 4 + 40 + i * 100;
                    int y = h / 4 + 40 + j * 60;

                    // Subtle light shift for "animation"
                    Color windowColor = new Color(
                            (int) (50 + Math.sin((animationOffset + i * 10) * 0.1) * 50),
                            (int) (150 + Math.sin((animationOffset + j * 10) * 0.1) * 50),
                            255, 200
                    );
                    g2d.setColor(windowColor);
                    g2d.fill(new Rectangle2D.Double(x, y, 60, 40));
                }
            }

            // 4. Floating Data Nodes (For "innovative/3D" feel)
            Random rand = new Random();
            for(int i = 0; i < 20; i++) {
                int x = (i * 35 + animationOffset) % w;
                int y = (i * 25 + animationOffset * 2) % h;

                g2d.setColor(ACCENT_BLUE);
                g2d.fillOval(x, y, 5, 5);

                // Draw trailing line
                g2d.setColor(new Color(ACCENT_BLUE.getRed(), ACCENT_BLUE.getGreen(), ACCENT_BLUE.getBlue(), 100));
                g2d.drawLine(x, y, x - 10, y - 10);
            }

            g2d.dispose();
        }
    }

    // -------------------------------------------------------------------------

    public About(){
        // Set Look and Feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("About the University Management System");
        setSize(WIDTH, HEIGHT);
        setLocation(400, 150);
        setLayout(new BorderLayout());

        // --- 1. Animated Background Panel ---
        AnimatedFacade background = new AnimatedFacade();
        background.setLayout(null);
        add(background, BorderLayout.CENTER);

        // --- 2. Main Content Panel (Semi-Transparent) ---
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(255, 255, 255, 230)); // Slightly transparent white
        contentPanel.setBounds(50, 40, WIDTH - 100, HEIGHT - 100);
        background.add(contentPanel);

        // --- University Heading ---
        JLabel uniHeading = new JLabel("Global Academy of Technology (GAT)");
        uniHeading.setBounds(20, 20, 550, 35);
        uniHeading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        uniHeading.setForeground(ACCENT_BLUE);
        contentPanel.add(uniHeading);

        // --- Purpose Info ---
        JTextArea purposeArea = new JTextArea(
                "Purpose:\n" +
                        "The Global Academy of Technology (GAT) is dedicated to providing world-class education " +
                        "and technical training. This UMS acts as the central digital hub for faculty, students, " +
                        "and administration to manage enrollment, records, examinations, leave, and financials.\n\n" +
                        "Our Vision: To empower the next generation of engineers, leaders, and innovators through structured " +
                        "education and efficient management systems."
        );
        purposeArea.setBounds(20, 70, 600, 170);
        purposeArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        purposeArea.setForeground(Color.DARK_GRAY);
        purposeArea.setBackground(new Color(255, 255, 255, 0));
        purposeArea.setEditable(false);
        contentPanel.add(purposeArea);

        // --- Developer Section ---
        JLabel devHeading = new JLabel("System Developer");
        devHeading.setBounds(20, 260, 300, 30);
        devHeading.setFont(new Font("Tahoma", Font.BOLD, 22));
        devHeading.setForeground(ACCENT_BLUE.darker());
        contentPanel.add(devHeading);

        // --- Your Name Highlighted ---
        JLabel nameLabel = createStyledLabel("Atharav Gavhane", 20, 300, 400, 30, new Font("Tahoma", Font.BOLD, 24));
        addInteractiveEffect(nameLabel);
        contentPanel.add(nameLabel);

        // --- Contact Info ---
        JLabel contactLabel = createStyledLabel("Contact: atharav.dev@ums.org | vpkbiet.org", 20, 340, 500, 25, new Font("Tahoma", Font.PLAIN, 16));
        contentPanel.add(contactLabel);


        // --- Close Button ---
        JButton closeButton = new JButton("Close System Info");
        closeButton.setBounds(200, 400, 200, 35);
        closeButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        closeButton.setBackground(new Color(200, 50, 50));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> setVisible(false));
        addHoverEffect(closeButton);
        contentPanel.add(closeButton);

        setVisible(true);
    }

    // --- Helper for styling JLabels ---
    private JLabel createStyledLabel(String text, int x, int y, int w, int h, Font font) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setFont(font);
        return label;
    }

    // --- Helper for hover effect on Labels ---
    private void addInteractiveEffect(JLabel label) {
        label.setOpaque(false);
        label.addMouseListener(new MouseAdapter() {
            private final Font originalFont = label.getFont();
            private final Font hoverFont = originalFont.deriveFont(originalFont.getSize() * 1.1f);

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(ACCENT_BLUE.brighter());
                label.setFont(hoverFont);
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Color.DARK_GRAY);
                label.setFont(originalFont);
                label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    // --- Helper for button animation/interactivity ---
    private void addHoverEffect(JButton button) {
        Color original = button.getBackground();
        Color hover = button.getBackground().brighter();

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(original);
            }
        });
    }

    public static void main(String[] args) {
        new About();
    }
}