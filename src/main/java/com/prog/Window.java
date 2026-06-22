package com.prog;

import java.awt.*;

import javax.swing.*;

public class Window extends JFrame {
    private BookFile bookFile;
    private JPanel principal;
    // HEADER
    private JPanel header;
    private JPanel headerWest, headerEast;
    private JLabel title, subtitle;
    private JButton addBookBtn;

    // STATISTICS
    private JPanel main, stats, read, pending, wishlist, avgRating;
    private JLabel numRead, txtRead, numPending, txtPending, numWishlist, txtWishlist, numAvg, txtAvg;

    public Window() {
        try {
            bookFile = new BookFile();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar libros");
        }

        setUpLayout();
        initComponents();
        setUpListeners();
    }

    /*----------------------------LAYOUT----------------------------- */
    private void setUpLayout() {
        principal = new JPanel(new BorderLayout());

        // HEADER
        header = new JPanel(new BorderLayout());
        headerWest = new JPanel(new GridLayout(2, 1, 5, 5));
        title = new JLabel("Mi biblioteca personal");
        subtitle = new JLabel("Mis lecturas, mi mundo.");
        headerEast = new JPanel();
        addBookBtn = new JButton("➕  Añadir libro");

        // STATISTICS
        main = new JPanel(new BorderLayout());
        stats = new JPanel(new GridLayout(1, 4, 10, 0));
        read = new JPanel();
        wishlist = new JPanel();
        pending = new JPanel();
        avgRating = new JPanel();
        numRead = new JLabel("8");
        numAvg = new JLabel();
        numPending = new JLabel();
        numWishlist = new JLabel();
        txtAvg = new JLabel("💯 Puntuación media");
        txtPending = new JLabel("🕑 Pendientes ");
        txtRead = new JLabel("✔ Leídos");
        txtWishlist = new JLabel("❤ Quiero leer");

    }

    /*----------------------------COMPONENTS----------------------------- */
    private void initComponents() {
        setTitle("Mi biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        principal.setBackground(new Color(250, 250, 250));
        add(principal);

        // HEADER--------
        principal.add(header, BorderLayout.NORTH);
        header.add(headerWest, BorderLayout.WEST);
        header.add(headerEast, BorderLayout.EAST);
        header.setBackground(new Color(250, 250, 250));
        header.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        headerWest.setBackground(new Color(250, 250, 250));
        headerEast.setBackground(new Color(250, 250, 250));
        headerEast.setLayout(new BoxLayout(headerEast, BoxLayout.Y_AXIS));

        headerWest.add(title);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(44, 44, 42));

        headerWest.add(subtitle);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(new Color(136, 135, 128));

        // addBookBtn appearance
        headerEast.add(Box.createVerticalGlue());
        headerEast.add(addBookBtn);
        headerEast.add(Box.createVerticalGlue());
        addBookBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        addBookBtn.setBackground(new Color(230, 241, 251));
        addBookBtn.setForeground(new Color(12, 68, 124));
        addBookBtn.setMargin(new Insets(8, 16, 8, 16));

        // MAIN--------
        principal.add(main, BorderLayout.CENTER);

        // Statistics
        main.add(stats, BorderLayout.NORTH);
        read.setLayout(new BoxLayout(read, BoxLayout.Y_AXIS));
        read.add(txtRead);
        read.add(numRead);
        stats.add(read);

        // Cards

        // pack();
        setLocationRelativeTo(null);
    }

    /*----------------------------LISTENERS----------------------------- */
    private void setUpListeners() {

    }
}
