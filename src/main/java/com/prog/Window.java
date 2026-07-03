package com.prog;

import java.awt.*;
import javax.swing.*;

public class Window extends JFrame {
    private BookFile bookFile;
    private JPanel principal;

    // HEADER
    private JPanel header, headerWest, headerEast;
    private JLabel title, subtitle;
    private JButton addBookBtn;

    // MAIN
    private JPanel main;

    // Statistics
    private JPanel stats, read, pending, wishlist, avgRating;
    private JLabel numRead, txtRead, numPending, txtPending, numWishlist, txtWishlist, numAvg, txtAvg;

    // Filters
    private JPanel filtersPanel;
    private JButton btnRead, btnPending, btnWishlist, btnAll, btnReading;
    private JComboBox<String> combo;
    private JTextField txtSearch;

    // Cards
    private JPanel cardsPanel, centerContent;
    private JScrollPane scroll;

    // Needs
    private String currentFilter = "ALL";
    private int cardWidth = 269;
    private boolean isMaximized = false;

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
        addBookBtn = new JButton("➕ Añadir libro");

        // MAIN
        main = new JPanel(new BorderLayout());

        // Statistics
        stats = new JPanel(new GridLayout(1, 4, 10, 0));
        numRead = new JLabel();
        numAvg = new JLabel();
        numPending = new JLabel();
        numWishlist = new JLabel();
        txtAvg = new JLabel("☆  Puntuación media");
        txtPending = new JLabel("🕒  Proximamente");
        txtRead = new JLabel("✓  Leídos");
        txtWishlist = new JLabel("♡  Quiero leer");
        read = new JPanel();
        pending = new JPanel();
        wishlist = new JPanel();
        avgRating = new JPanel();

        // Filters
        filtersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnRead = new JButton("Leídos");
        btnPending = new JButton("Próximamente");
        btnWishlist = new JButton("Quiero leer");
        btnAll = new JButton("Todos");
        btnReading = new JButton("Leyendo");
        combo = new JComboBox<>(new String[] { "Fecha", "Título" });
        txtSearch = new JTextField();

        // Cards
        cardsPanel = new JPanel(new WrapLayout(FlowLayout.CENTER, 15, 15));
        scroll = new JScrollPane(cardsPanel);
        centerContent = new JPanel(new BorderLayout());
    }

    /*----------------------------COMPONENTS----------------------------- */
    private void initComponents() {
        setTitle("Mi biblioteca");
        setSize(900, 700);

        ImageIcon appIcon = new ImageIcon(getClass().getResource("/icons/bibliotecaIcon.png"));
        setIconImage(appIcon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        principal.setBackground(new Color(245, 245, 245));
        add(principal);

        // HEADER
        principal.add(header, BorderLayout.NORTH);
        header.add(headerWest, BorderLayout.WEST);
        header.add(headerEast, BorderLayout.EAST);
        header.setBackground(new Color(245, 245, 245));
        header.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        headerWest.setBackground(new Color(245, 245, 245));
        headerEast.setBackground(new Color(245, 245, 245));
        headerEast.setLayout(new BoxLayout(headerEast, BoxLayout.Y_AXIS));

        headerWest.add(title);
        title.setFont(new Font("Nunito", Font.BOLD, 22));
        title.setForeground(new Color(44, 44, 42));

        headerWest.add(subtitle);
        subtitle.setFont(new Font("Nunito", Font.PLAIN, 13));
        subtitle.setForeground(new Color(136, 135, 128));

        headerEast.add(Box.createVerticalGlue());
        headerEast.add(addBookBtn);
        headerEast.add(Box.createVerticalGlue());
        addBookBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBookBtn.setBackground(new Color(230, 241, 251));
        addBookBtn.setForeground(new Color(12, 68, 124));
        addBookBtn.setMargin(new Insets(8, 16, 8, 16));
        addBookBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addBookBtn.putClientProperty("JButton.buttonType", null);
        addBookBtn.putClientProperty("FlatLaf.style",
                "arc: 8; borderColor: #85B7EB; hoverBorderColor: #85B7EB; pressedBackground: #B8D4F0");

        // MAIN
        principal.add(main, BorderLayout.CENTER);

        // Statistics
        main.add(stats, BorderLayout.NORTH);
        stats.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 16));
        stats.setBackground(new Color(245, 245, 245));
        stats.setPreferredSize(new Dimension(0, 95));

        read = UIUtils.createStatPanel(txtRead, numRead, new Color(224, 245, 211));
        pending = UIUtils.createStatPanel(txtPending, numPending, new Color(250, 237, 212));
        wishlist = UIUtils.createStatPanel(txtWishlist, numWishlist, new Color(255, 224, 240));
        avgRating = UIUtils.createStatPanel(txtAvg, numAvg, new Color(223, 220, 252));

        numRead.setFont(new Font("Nunito", Font.BOLD, 22));
        numRead.setForeground(new Color(8, 80, 65));
        txtRead.setForeground(new Color(15, 110, 86));

        numPending.setFont(new Font("Nunito", Font.BOLD, 22));
        numPending.setForeground(new Color(99, 56, 6));
        txtPending.setForeground(new Color(133, 79, 11));

        numWishlist.setFont(new Font("Nunito", Font.BOLD, 22));
        numWishlist.setForeground(new Color(114, 36, 62));
        txtWishlist.setForeground(new Color(153, 53, 86));

        numAvg.setFont(new Font("Nunito", Font.BOLD, 22));
        numAvg.setForeground(new Color(60, 52, 137));
        txtAvg.setForeground(new Color(83, 74, 183));

        stats.add(read);
        stats.add(pending);
        stats.add(wishlist);
        stats.add(avgRating);

        // Filters
        filtersPanel.setBackground(new Color(245, 245, 245));
        filtersPanel.setBorder(BorderFactory.createEmptyBorder(16, 12, 8, 16));
        filtersPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        UIUtils.styleComboBox(combo);
        UIUtils.styleSearchField(txtSearch);

        setupFilterButton(btnRead, "#7EC87A", "#E1F5DA", "#cee9c2");
        setupFilterButton(btnPending, "#F5A623", "#FEF3DC", "#f7e5bd");
        setupFilterButton(btnWishlist, "#ED93B1", "#FBEAF0", "#f7d8e8");
        setupFilterButton(btnReading, "#A89AE8", "#e8e6fd", "#d8d3ff");
        setupFilterButton(btnAll, "#85B7EB", "#E6F1FB", "#c8dff3");

        filtersPanel.add(btnRead);
        filtersPanel.add(btnPending);
        filtersPanel.add(btnWishlist);
        filtersPanel.add(btnReading);
        filtersPanel.add(btnAll);
        filtersPanel.add(combo);
        filtersPanel.add(txtSearch);

        // Cards
        centerContent.add(filtersPanel, BorderLayout.NORTH);
        centerContent.add(scroll, BorderLayout.CENTER);
        centerContent.setAlignmentX(Component.LEFT_ALIGNMENT);
        main.add(centerContent, BorderLayout.CENTER);

        scroll.setBorder(null);
        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        scroll.getViewport().setBackground(new Color(245, 245, 245));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getViewport().addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                cardsPanel.revalidate();
            }
        });

        cardsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardsPanel.setBackground(new Color(245, 245, 245));
        cardsPanel.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        loadBooks("ALL");
        setLocationRelativeTo(null);
    }

    private void setupFilterButton(JButton btn, String hoverBorder, String hoverBg, String pressed) {
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setMargin(new Insets(6, 16, 6, 16));
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.setBackground(new Color(240, 240, 240));
        btn.setForeground(new Color(80, 80, 80));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.putClientProperty("FlatLaf.style",
                "borderColor: #CCCCCC; hoverBorderColor: " + hoverBorder +
                        "; hoverBackground: " + hoverBg + "; pressedBackground: " + pressed);
    }

    /*----------------------------LISTENERS----------------------------- */
    private void setUpListeners() {
        addBookBtn.addActionListener(e -> new AddBookDialog(this, bookFile));

        btnRead.addActionListener(e -> {
            setActiveFilter(btnRead);
            currentFilter = "LEIDO";
            loadBooks("LEIDO");
        });
        btnPending.addActionListener(e -> {
            setActiveFilter(btnPending);
            currentFilter = "PROXIMO";
            loadBooks("PROXIMO");
        });
        btnWishlist.addActionListener(e -> {
            setActiveFilter(btnWishlist);
            currentFilter = "QUIERO_LEER";
            loadBooks("QUIERO_LEER");
        });

        btnReading.addActionListener(e -> {
            setActiveFilter(btnReading);
            currentFilter = "LEYENDO";
            loadBooks("LEYENDO");
        });
        btnAll.addActionListener(e -> {
            setActiveFilter(btnAll);
            currentFilter = "ALL";
            loadBooks("ALL");
        });
        combo.addActionListener(e -> {
            if (combo.getSelectedItem().equals("Título")) {
                bookFile.sortByTitle();
            } else {
                bookFile.sortByDate();
            }
            loadBooks(currentFilter);
        });

        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                searchBooks();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                searchBooks();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
            }
        });

        addWindowStateListener(e -> {
            if ((e.getNewState() & java.awt.Frame.MAXIMIZED_BOTH) != 0) {
                isMaximized = true;
                SwingUtilities.invokeLater(() -> {
                    int width = (scroll.getViewport().getWidth() - 65) / 4;
                    updateCardSize(width);
                    loadBooks(currentFilter);
                });
            } else {
                isMaximized = false;
                SwingUtilities.invokeLater(() -> {
                    cardWidth = 269;
                    loadBooks(currentFilter);
                });
            }
        });

        java.awt.event.MouseAdapter focusAway = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                principal.requestFocusInWindow();
            }
        };

        principal.addMouseListener(focusAway);
        main.addMouseListener(focusAway);
        stats.addMouseListener(focusAway);
        cardsPanel.addMouseListener(focusAway);
    }

    private void searchBooks() {
        String query = txtSearch.getText().trim().toLowerCase();
        cardsPanel.removeAll();
        for (Book book : bookFile.getBookList()) {
            if (book.getTitle().toLowerCase().contains(query) ||
                    book.getAuthor().toLowerCase().contains(query)) {
                cardsPanel.add(new BookCard(book, bookFile, this, cardWidth, isMaximized));
            }
        }
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private void updateCardSize(int width) {
        cardWidth = width;
        loadBooks(currentFilter);
    }

    public void refreshBooks() {
        loadBooks("ALL");
    }

    private void loadBooks(String filter) {
        updateStats();
        cardsPanel.removeAll();
        for (Book book : bookFile.getBookList()) {
            if (filter.equals("ALL") || book.getStatus().equals(filter)) {
                cardsPanel.add(new BookCard(book, bookFile, this, cardWidth, isMaximized));
            }
        }

        if (cardsPanel.getComponentCount() == 0) {
            String emptyMsg = bookFile.getBookList().isEmpty()
                    ? "Tu biblioteca está vacía"
                    : "No tienes libros en esta categoría";
            String emptySub = bookFile.getBookList().isEmpty()
                    ? "Añade tu primer libro para empezar"
                    : "Prueba con otro filtro";
            JPanel emptyPanel = new JPanel();
            emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
            emptyPanel.setOpaque(false);
            emptyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel icon = new JLabel("📚");
            icon.setFont(icon.getFont().deriveFont(60f));
            icon.setForeground(new Color(110, 110, 110));
            icon.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel msg = new JLabel(emptyMsg);
            msg.setFont(new Font("Nunito", Font.BOLD, 18));
            msg.setForeground(new Color(100, 100, 100));
            msg.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel sub = new JLabel(emptySub);
            sub.setForeground(new Color(100, 100, 100));
            sub.setAlignmentX(Component.CENTER_ALIGNMENT);

            emptyPanel.add(Box.createVerticalGlue());
            emptyPanel.add(icon);
            emptyPanel.add(Box.createVerticalStrut(12));
            emptyPanel.add(msg);
            emptyPanel.add(Box.createVerticalStrut(6));
            emptyPanel.add(sub);
            emptyPanel.add(Box.createVerticalGlue());

            cardsPanel.setLayout(new BorderLayout());
            cardsPanel.add(emptyPanel, BorderLayout.CENTER);
        } else {
            cardsPanel.setLayout(new WrapLayout(FlowLayout.CENTER, 10, 10));
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private void setActiveFilter(JButton activeBtn) {
        JButton[] filterBtns = { btnRead, btnPending, btnWishlist, btnReading, btnAll };
        Color[] bgColors = {
                new Color(225, 245, 218),
                new Color(254, 243, 220),
                new Color(251, 234, 240),
                new Color(232, 230, 253),
                new Color(230, 241, 251)
        };
        String[] borderColors = { "#7EC87A", "#F5A623", "#ED93B1", "#A89AE8", "#85B7EB" };
        String[] hoverColors = { "#7EC87A", "#F5A623", "#ED93B1", "#A89AE8", "#85B7EB" };
        String[] hoverBgColors = { "#E0F5D3", "#FEF3DC", "#FBEAF0", "#e8e6fd", "#E6F1FB" };
        String[] pressedColors = { "#cee9c2", "#f7e5bd", "#f7d8e8", "#d8d3ff", "#c8dff3" };

        for (int i = 0; i < filterBtns.length; i++) {
            if (filterBtns[i] == activeBtn) {
                filterBtns[i].setBackground(bgColors[i]);
                filterBtns[i].setForeground(new Color(44, 44, 42));
                filterBtns[i].putClientProperty("FlatLaf.style",
                        "borderColor: " + borderColors[i] + "; hoverBorderColor: " + borderColors[i]
                                + "; pressedBackground: " + pressedColors[i]);
            } else {
                filterBtns[i].setBackground(new Color(240, 240, 240));
                filterBtns[i].setForeground(new Color(80, 80, 80));
                filterBtns[i].putClientProperty("FlatLaf.style",
                        "borderColor: #CCCCCC; hoverBorderColor: " + hoverColors[i] + "; hoverBackground: "
                                + hoverBgColors[i] + "; pressedBackground: " + pressedColors[i]);
            }
            filterBtns[i].repaint();
        }
    }

    private void updateStats() {
        int read = 0, pending = 0, wishlist = 0;
        int totalRating = 0, ratedBooks = 0;

        for (Book book : bookFile.getBookList()) {
            switch (book.getStatus()) {
                case "LEIDO" -> read++;
                case "PROXIMO" -> pending++;
                case "QUIERO_LEER" -> wishlist++;
            }
            if (book.getRating() > 0) {
                totalRating += book.getRating();
                ratedBooks++;
            }
        }

        numRead.setText(String.valueOf(read));
        numPending.setText(String.valueOf(pending));
        numWishlist.setText(String.valueOf(wishlist));
        numAvg.setText(ratedBooks > 0 ? String.format("%.1f", (double) totalRating / ratedBooks) : "-");
    }

}