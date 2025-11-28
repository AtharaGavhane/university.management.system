package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class main_class extends JFrame implements ActionListener {

    // --- Colors and Fonts for Professional Theme ---
    private final Color ACCENT_COLOR = new Color(30, 144, 255); // Dodger Blue
    private final Color HOVER_COLOR = new Color(220, 240, 255); // Very Light Blue
    private final Font MENU_FONT = new Font("Segoe UI", Font.BOLD, 15);
    private final Font ITEM_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    main_class() {
        // 1. Set Look and Feel for a modern, flat appearance
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("University Management System Dashboard");

        // Ensure the layout is set to BorderLayout (the default for JFrame)
        // or a similar manager so the JMenuBar sits at the top (NORTH)

        // --- Background Image Setup ---
        // This JLabel will cover the entire frame below the MenuBar.
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/3rd.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1540, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        add(img); // Adds the image to the center of the frame

        // --- Menu Bar Setup ---
        JMenuBar mb = new JMenuBar();
        mb.setBackground(Color.WHITE);

        // =========================================================================
        // 1. Enrollment (New Information)
        // =========================================================================
        JMenu newInfo = createMenu("1. Enrollment", mb, ACCENT_COLOR, MENU_FONT);
        createMenuItem("New Faculty Information", newInfo, HOVER_COLOR, ITEM_FONT);
        createMenuItem("New Student Information", newInfo, HOVER_COLOR, ITEM_FONT);

        // =========================================================================
        // 2. View Records (Details)
        // =========================================================================
        JMenu details = createMenu("2. View Records", mb, ACCENT_COLOR, MENU_FONT);
        createMenuItem("View Faculty Details", details, HOVER_COLOR, ITEM_FONT);
        createMenuItem("View Student Details", details, HOVER_COLOR, ITEM_FONT);

        // =========================================================================
        // 3. Leave Management
        // =========================================================================
        JMenu leave = createMenu("3. Leave", mb, ACCENT_COLOR, MENU_FONT);
        createMenuItem("Faculty Leave", leave, HOVER_COLOR, ITEM_FONT);
        createMenuItem("Student Leave", leave, HOVER_COLOR, ITEM_FONT);
        leave.addSeparator();

        // Sub-menu for Leave Details
        JMenu leaveDetails = createSubMenu("Leave Details", leave, Color.DARK_GRAY, ITEM_FONT);
        createMenuItem("Faculty Leave Details", leaveDetails, HOVER_COLOR, ITEM_FONT);
        createMenuItem("Student Leave Details", leaveDetails, HOVER_COLOR, ITEM_FONT);

        // =========================================================================
        // 4. Examination & Results
        // =========================================================================
        JMenu exam = createMenu("4. Examination", mb, ACCENT_COLOR, MENU_FONT);
        createMenuItem("Enter Marks", exam, HOVER_COLOR, ITEM_FONT);
        createMenuItem("Examination Results", exam, HOVER_COLOR, ITEM_FONT);

        // =========================================================================
        // 5. Administration (Update Details)
        // =========================================================================
        JMenu updateInfo = createMenu("5. Administration", mb, ACCENT_COLOR, MENU_FONT);
        createMenuItem("Update Faculty Details", updateInfo, HOVER_COLOR, ITEM_FONT);
        createMenuItem("Update Student Details", updateInfo, HOVER_COLOR, ITEM_FONT);

        // =========================================================================
        // 6. Financials (Fee Details)
        // =========================================================================
        JMenu fee = createMenu("6. Financials", mb, ACCENT_COLOR, MENU_FONT);
        createMenuItem("Fee Structure", fee, HOVER_COLOR, ITEM_FONT);
        createMenuItem("Student Fee Form", fee, HOVER_COLOR, ITEM_FONT);

        // =========================================================================
        // 7. Utility, About, Exit (Aligned Right)
        // =========================================================================

        mb.add(Box.createHorizontalGlue()); // Pushes final items to the right

        JMenu utility = createMenu("Utility", mb, ACCENT_COLOR, MENU_FONT);
        createMenuItem("Calculator", utility, HOVER_COLOR, ITEM_FONT);
        createMenuItem("Notepad", utility, HOVER_COLOR, ITEM_FONT);

        JMenu about = createMenu("About", mb, ACCENT_COLOR, MENU_FONT);
        createMenuItem("About", about, HOVER_COLOR, ITEM_FONT);

        JMenu exit = createMenu("Exit", mb, ACCENT_COLOR, MENU_FONT);
        createMenuItem("Exit", exit, HOVER_COLOR, ITEM_FONT);

        // *** THIS is the step that places the menu bar on the frame ***
        setJMenuBar(mb);

        setSize(1540, 850);
        setVisible(true);
    }

    // -----------------------------------------------------------------------------
    // HELPER METHODS (Defined outside the constructor)
    // -----------------------------------------------------------------------------

    private JMenu createMenu(String text, JMenuBar mb, Color color, Font font) {
        JMenu menu = new JMenu(text);
        menu.setFont(font);
        menu.setForeground(color);
        mb.add(menu);
        return menu;
    }

    private JMenu createSubMenu(String text, JMenu parentMenu, Color color, Font font) {
        JMenu subMenu = new JMenu(text);
        subMenu.setFont(font);
        subMenu.setForeground(color);
        parentMenu.add(subMenu);
        return subMenu;
    }

    private JMenuItem createMenuItem(String text, JMenu parentMenu, Color hoverColor, Font font) {
        JMenuItem item = new JMenuItem(text);
        item.setBackground(Color.WHITE);
        item.setFont(font);
        item.addActionListener(this);

        // --- Interactive Animation (Hover Effect) ---
        item.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                item.setBackground(hoverColor);
                item.setForeground(ACCENT_COLOR); // Use ACCENT_COLOR for text on hover
            }
            public void mouseExited(MouseEvent me) {
                item.setBackground(Color.WHITE);
                item.setForeground(Color.BLACK);
            }
        });

        parentMenu.add(item);
        return item;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sm = e.getActionCommand();

        if (sm.equals("Exit")) {
            System.exit(0);
        } else if (sm.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (sm.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (sm.equals("New Faculty Information")) {
            new AddFaculty();
        } else if (sm.equals("New Student Information")) {
            new AddStudent();
        } else if (sm.equals("View Faculty Details")) {
            new TeacherDetails();
        } else if (sm.equals("View Student Details")) {
            new StudentDetails();
        } else if (sm.equals("Faculty Leave")) {
            new TeacherLeave();
        } else if (sm.equals("Student Leave")) {
            new StudentLeave();
        } else if (sm.equals("Faculty Leave Details")) {
            new TeacherLeaveDetails();
        } else if (sm.equals("Student Leave Details")) {
            new StudentLeaveDetails();
        } else if (sm.equals("Update Faculty Details")) {
            new UpdateTeacher();
        } else if (sm.equals("Update Student Details")) {
            new updateStudent();
        } else if (sm.equals("Enter Marks")) {
            new EnterMarks();
        } else if (sm.equals("Examination Results")) {
            new ExaminationDetails();
        } else if (sm.equals("Fee Structure")) {
            new FreeStructure();
        } else if (sm.equals("Student Fee Form")) {
            new StudentFeeForm();
        } else if (sm.equals("About")) {
            new About();
        }
    }

    public static void main(String[] args) {
        new main_class();
    }
}