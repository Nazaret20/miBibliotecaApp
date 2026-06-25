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
    private JButton btnRead, btnPending, btnWishlist, btnAll;

    // Cards
    private JPanel cardsPanel, centerContent;
    private JScrollPane scroll;

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
        numRead = new JLabel("8");
        numAvg = new JLabel("4.2");
        numPending = new JLabel("3");
        numWishlist = new JLabel("6");
        txtAvg = new JLabel("☆  Puntuación media");
        txtPending = new JLabel("🕒  Proximamente");
        txtRead = new JLabel("✓  Leídos");
        txtWishlist = new JLabel("♡  Quiero leer");
        read = createRoundedCard(new Color(224, 245, 211));
        pending = createRoundedCard(new Color(250, 237, 212));
        wishlist = createRoundedCard(new Color(255, 224, 240));
        avgRating = createRoundedCard(new Color(223, 220, 252));

        // Filters
        filtersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnRead = new JButton("Leídos");
        btnPending = new JButton("Próximamente");
        btnWishlist = new JButton("Quiero leer");
        btnAll = new JButton("Todos");

        // Cards
        cardsPanel = new JPanel(new WrapLayout(FlowLayout.CENTER, 15, 15));
        scroll = new JScrollPane(cardsPanel);
        centerContent = new JPanel(new BorderLayout());
    }

    /*----------------------------COMPONENTS----------------------------- */
    private void initComponents() {
        setTitle("Mi biblioteca");
        setSize(800, 600);
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
        addBookBtn.putClientProperty("JButton.buttonType", null);
        addBookBtn.putClientProperty("FlatLaf.style",
                "arc: 8; borderColor: #85B7EB; hoverBorderColor: #85B7EB; pressedBackground: #B8D4F0");

        addBookBtn.setMargin(new Insets(8, 16, 8, 16));

        // MAIN
        principal.add(main, BorderLayout.CENTER);

        // Statistics
        main.add(stats, BorderLayout.NORTH);
        stats.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 16));
        stats.setBackground(new Color(245, 245, 245));
        stats.setPreferredSize(new Dimension(0, 95));

        read.setLayout(new BoxLayout(read, BoxLayout.Y_AXIS));
        read.setBorder(BorderFactory.createEmptyBorder(18, 14, 18, 14));
        read.add(txtRead);
        read.add(numRead);
        stats.add(read);
        numRead.setFont(new Font("Nunito", Font.BOLD, 22));
        numRead.setForeground(new Color(8, 80, 65));
        txtRead.setFont(new Font("Nunito", Font.PLAIN, 14));
        txtRead.setForeground(new Color(15, 110, 86));

        pending.setLayout(new BoxLayout(pending, BoxLayout.Y_AXIS));
        pending.setBorder(BorderFactory.createEmptyBorder(18, 14, 18, 14));
        pending.add(txtPending);
        pending.add(numPending);
        stats.add(pending);
        numPending.setFont(new Font("Nunito", Font.BOLD, 22));
        numPending.setForeground(new Color(99, 56, 6));
        txtPending.setFont(new Font("Nunito", Font.PLAIN, 14));
        txtPending.setForeground(new Color(133, 79, 11));

        wishlist.setLayout(new BoxLayout(wishlist, BoxLayout.Y_AXIS));
        wishlist.setBorder(BorderFactory.createEmptyBorder(18, 14, 18, 14));
        wishlist.add(txtWishlist);
        wishlist.add(numWishlist);
        stats.add(wishlist);
        numWishlist.setFont(new Font("Nunito", Font.BOLD, 22));
        numWishlist.setForeground(new Color(114, 36, 62));
        txtWishlist.setFont(new Font("Nunito", Font.PLAIN, 14));
        txtWishlist.setForeground(new Color(153, 53, 86));

        avgRating.setLayout(new BoxLayout(avgRating, BoxLayout.Y_AXIS));
        avgRating.setBorder(BorderFactory.createEmptyBorder(18, 14, 18, 14));
        avgRating.add(txtAvg);
        avgRating.add(numAvg);
        stats.add(avgRating);
        numAvg.setFont(new Font("Nunito", Font.BOLD, 22));
        numAvg.setForeground(new Color(60, 52, 137));
        txtAvg.setFont(new Font("Nunito", Font.PLAIN, 14));
        txtAvg.setForeground(new Color(83, 74, 183));

        // Filters
        filtersPanel.setBackground(new Color(245, 245, 245));
        filtersPanel.setBorder(BorderFactory.createEmptyBorder(16, 12, 8, 16));
        filtersPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        filtersPanel.add(btnRead);
        btnRead.setFocusPainted(false);
        btnRead.setMargin(new Insets(6, 16, 6, 16));
        btnRead.putClientProperty("JButton.buttonType", "roundRect");
        btnRead.setBackground(new Color(240, 240, 240));
        btnRead.setForeground(new Color(80, 80, 80));
        btnRead.putClientProperty("FlatLaf.style",
                "borderColor: #CCCCCC; hoverBorderColor: #7EC87A; hoverBackground: #E1F5DA; pressedBackground: #cee9c2");

        filtersPanel.add(btnPending);
        btnPending.setFocusPainted(false);
        btnPending.setMargin(new Insets(6, 16, 6, 16));
        btnPending.putClientProperty("JButton.buttonType", "roundRect");
        btnPending.setBackground(new Color(240, 240, 240));
        btnPending.setForeground(new Color(80, 80, 80));
        btnPending.putClientProperty("FlatLaf.style",
                "borderColor: #CCCCCC; hoverBorderColor: #F5A623; hoverBackground: #FEF3DC; pressedBackground: #f7e5bd");

        filtersPanel.add(btnWishlist);
        btnWishlist.setFocusPainted(false);
        btnWishlist.setMargin(new Insets(6, 16, 6, 16));
        btnWishlist.putClientProperty("JButton.buttonType", "roundRect");
        btnWishlist.setBackground(new Color(240, 240, 240));
        btnWishlist.setForeground(new Color(80, 80, 80));
        btnWishlist.putClientProperty("FlatLaf.style",
                "borderColor: #CCCCCC; hoverBorderColor: #ED93B1; hoverBackground: #FBEAF0; pressedBackground: #f7d8e8");

        filtersPanel.add(btnAll);
        btnAll.setFocusPainted(false);
        btnAll.setMargin(new Insets(6, 16, 6, 16));
        btnAll.putClientProperty("JButton.buttonType", "roundRect");
        btnAll.setBackground(new Color(240, 240, 240));
        btnAll.setForeground(new Color(80, 80, 80));
        btnAll.putClientProperty("FlatLaf.style",
                "borderColor: #CCCCCC; hoverBorderColor: #85B7EB; hoverBackground: #E6F1FB; pressedBackground: #c8dff3");

        btnRead.setFocusable(false);
        btnPending.setFocusable(false);
        btnWishlist.setFocusable(false);
        btnAll.setFocusable(false);

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
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(0, 8, 16, 16));

        loadBooks("ALL");

        setLocationRelativeTo(null);
    }

    /*----------------------------LISTENERS----------------------------- */
    private void setUpListeners() {
        btnRead.addActionListener(e -> {
            setActiveFilter(btnRead);
            loadBooks("LEIDO");
        });
        btnPending.addActionListener(e -> {
            setActiveFilter(btnPending);
            loadBooks("PROXIMO");
        });
        btnWishlist.addActionListener(e -> {
            setActiveFilter(btnWishlist);
            loadBooks("QUIERO_LEER");
        });
        btnAll.addActionListener(e -> {
            setActiveFilter(btnAll);
            loadBooks("ALL");
        });

        addBookBtn.addActionListener(e -> new AddBookDialog(this, bookFile));
    }

    public void refreshBooks() {
        loadBooks("ALL");
    }

    private void loadBooks(String filter) {
        bookFile.sortByDate();
        cardsPanel.removeAll();
        cardsPanel.removeAll();
        for (Book book : bookFile.getBookList()) {
            if (filter.equals("ALL") || book.getStatus().equals(filter)) {
                cardsPanel.add(createBookCard(book));
            }
        }
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private void setActiveFilter(JButton activeBtn) {
        JButton[] filterBtns = { btnRead, btnPending, btnWishlist, btnAll };
        Color[] bgColors = {
                new Color(225, 245, 218),
                new Color(254, 243, 220),
                new Color(251, 234, 240),
                new Color(230, 241, 251)
        };
        String[] borderColors = { "#7EC87A", "#F5A623", "#ED93B1", "#85B7EB" };
        String[] hoverColors = { "#7EC87A", "#F5A623", "#ED93B1", "#85B7EB" };
        String[] hoverBgColors = { "#E0F5D3", "#FEF3DC", "#FBEAF0", "#E6F1FB" };
        String[] pressedColors = { "#cee9c2", "#f7e5bd", "#f7d8e8", "#c8dff3" };

        for (int i = 0; i < filterBtns.length; i++) {
            if (filterBtns[i] == activeBtn) {
                filterBtns[i].setBackground(bgColors[i]);
                filterBtns[i].setForeground(new Color(44, 44, 42));
                filterBtns[i].putClientProperty("FlatLaf.style",
                        "borderColor: " + borderColors[i]);
                filterBtns[i].putClientProperty("FlatLaf.style",
                        "borderColor: " + borderColors[i] + "; pressedBackground: " + pressedColors[i]);
                filterBtns[i].putClientProperty("FlatLaf.style",
                        "borderColor: " + borderColors[i] + "; hoverBorderColor: " + borderColors[i]
                                + "; pressedBackground: " + pressedColors[i]);
            } else {
                filterBtns[i].setBackground(new Color(240, 240, 240));
                filterBtns[i].setForeground(new Color(80, 80, 80));
                filterBtns[i].putClientProperty("FlatLaf.style",
                        "borderColor: #CCCCCC; hoverBorderColor: " + hoverColors[i] + "; hoverBackground: "
                                + hoverBgColors[i]);
                filterBtns[i].putClientProperty("FlatLaf.style",
                        "borderColor: #CCCCCC; hoverBorderColor: " + hoverColors[i] + "; hoverBackground: "
                                + hoverBgColors[i] + "; pressedBackground: " + pressedColors[i]);
            }
            filterBtns[i].repaint();
        }
    }

    /*----------------------------EXTRAS----------------------------- */
    private JPanel createRoundedCard(Color backgroundColor) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(backgroundColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        card.setOpaque(false);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
        return card;
    }

    private JPanel createBookCard(Book book) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(220, 220, 220));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(getBackground());
                g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        card.setOpaque(false);
        card.setBackground(new Color(250, 250, 250));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(220, 250));
        card.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));

        JLabel titleLabel = new JLabel(book.getTitle());
        titleLabel.setFont(new Font("Nunito", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel authorLabel = new JLabel(book.getAuthor());
        authorLabel.setFont(new Font("Nunito", Font.PLAIN, 14));
        authorLabel.setForeground(new Color(136, 135, 128));
        authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        String stars = "★".repeat(book.getRating()) + "☆".repeat(5 - book.getRating());
        JLabel starsLabel = new JLabel(stars);
        starsLabel.setFont(new Font("Nunito", Font.PLAIN, 15));
        starsLabel.setForeground(new Color(186, 117, 23));
        starsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel notesLabel = new JLabel(
                "<html><i>" + (book.getNotes().isEmpty() ? "Sin comentarios" : book.getNotes()) + "</i></html>");
        notesLabel.setFont(new Font("Nunito", Font.PLAIN, 13));
        notesLabel.setForeground(new Color(136, 135, 128));
        notesLabel.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 0));

        JPanel notesWrapper = new JPanel(new BorderLayout());
        notesWrapper.setOpaque(false);
        notesWrapper.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(200, 200, 200)));
        notesWrapper.add(notesLabel);
        notesWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel dateLabel = new JLabel("○ " + (book.getDate().isEmpty() ? "Sin fecha" : book.getDate()));
        dateLabel.setFont(new Font("Nunito", Font.PLAIN, 13));
        dateLabel.setForeground(new Color(136, 135, 128));
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Color bar
        JPanel colorBar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
            }
        };
        colorBar.setPreferredSize(new Dimension(100, 6));
        colorBar.setMinimumSize(new Dimension(0, 6));
        colorBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 6));
        colorBar.setBackground(switch (book.getStatus()) {
            case "LEIDO" -> new Color(95, 202, 165);
            case "LEYENDO" -> new Color(133, 183, 235);
            case "PROXIMO" -> new Color(250, 199, 117);
            default -> new Color(237, 147, 177);
        });

        // Status pill
        Color statusBg = switch (book.getStatus()) {
            case "LEIDO" -> new Color(225, 245, 238);
            case "LEYENDO" -> new Color(230, 241, 251);
            case "PROXIMO" -> new Color(250, 238, 218);
            default -> new Color(251, 234, 240);
        };
        Color statusFg = switch (book.getStatus()) {
            case "LEIDO" -> new Color(8, 80, 65);
            case "LEYENDO" -> new Color(12, 68, 124);
            case "PROXIMO" -> new Color(99, 56, 6);
            default -> new Color(114, 36, 62);
        };
        String statusText = switch (book.getStatus()) {
            case "LEIDO" -> "✓ Leído";
            case "LEYENDO" -> "📖 Leyendo";
            case "PROXIMO" -> "🕒 Próximamente";
            default -> "♡ Quiero leer";
        };

        JPanel statusPill = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        statusPill.setBackground(statusBg);
        statusPill.setOpaque(false);
        statusPill.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 3));
        JLabel statusLabel = new JLabel(statusText);
        statusLabel.setFont(new Font("Nunito", Font.BOLD, 10));
        statusLabel.setForeground(statusFg);
        statusPill.add(statusLabel);
        statusPill.setMaximumSize(new Dimension(statusPill.getPreferredSize().width + 16, 24));

        JPanel pillWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pillWrapper.setOpaque(false);
        pillWrapper.add(statusPill);
        pillWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Action buttons
        JButton editBtn = createSmallButton("Editar", new Color(245, 245, 245), new Color(80, 80, 80),
                new Color(200, 200, 200));
        JButton deleteBtn = createSmallButton("Eliminar", new Color(251, 234, 240), new Color(114, 36, 62),
                new Color(237, 147, 177));

        JPanel actionsPanel = new JPanel();
        actionsPanel.setBackground(new Color(250, 250, 250));
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.X_AXIS));
        actionsPanel.setOpaque(false);
        actionsPanel.add(editBtn);
        actionsPanel.add(Box.createHorizontalStrut(6));
        actionsPanel.add(deleteBtn);
        actionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Assemble card
        card.add(colorBar, 0);
        card.add(Box.createVerticalStrut(8), 1);
        card.add(pillWrapper, 2);
        card.add(Box.createVerticalStrut(8));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(2));
        card.add(authorLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(starsLabel);
        card.add(Box.createVerticalStrut(4));
        card.add(notesWrapper);
        card.add(Box.createVerticalStrut(2));
        card.add(dateLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(actionsPanel);

        deleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar este libro?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    bookFile.deleteBook(book.getId());
                    loadBooks("ALL");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el libro");
                }
            }
        });

        editBtn.addActionListener(e -> new AddBookDialog(this, bookFile, book));

        return card;
    }

    private JButton createSmallButton(String text, Color bg, Color fg, Color border) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(border);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.setColor(bg);
                g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        return btn;
    }
}