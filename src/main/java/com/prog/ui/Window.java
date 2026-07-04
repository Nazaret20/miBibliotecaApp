package com.prog.ui;

import java.awt.*;
import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.prog.data.BookFile;
import com.prog.model.Book;
import com.prog.utils.ThemeColors;
import com.prog.utils.UIUtils;
import com.prog.utils.WrapLayout;

/**
 * Main application window for the personal library manager.
 * Displays book cards in a grid layout with filtering, sorting and search
 * capabilities.
 * Connects to {@link BookFile} for data persistence and {@link BookCard} for
 * card rendering.
 */
public class Window extends JFrame {
    private BookFile bookFile;
    private JPanel principal;

    // HEADER
    private JPanel header, headerWest, headerEast, btnsPanel;
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
    private JScrollPane filterScroll;

    // Cards
    private JPanel cardsPanel, centerContent;
    private JScrollPane scroll;

    // State
    private String currentFilter = "ALL";
    private int cardWidth = 269;
    private boolean isMaximized = false;
    private JButton themeBtn;

    public Window(boolean darkMode) {
        try {
            bookFile = new BookFile();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar libros");
        }
        setUpLayout();
        initComponents();
        setUpListeners();
        themeBtn.setText(darkMode ? "☀" : "🌙");
        themeBtn.setForeground(ThemeColors.themeButtonForeground());
        themeBtn.setBackground(ThemeColors.themeButtonBackground());
    }

    /*----------------------------LAYOUT----------------------------- */
    private void setUpLayout() {
        principal = new JPanel(new BorderLayout());

        header = new JPanel(new BorderLayout());
        headerWest = new JPanel(new GridLayout(2, 1, 5, 5));
        btnsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        title = new JLabel("Mi biblioteca personal");
        subtitle = new JLabel("Mis lecturas, mi mundo.");
        headerEast = new JPanel();
        themeBtn = new JButton("🌙");
        addBookBtn = new JButton("➕ Añadir libro");

        main = new JPanel(new BorderLayout());

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

        filtersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterScroll = new JScrollPane(filtersPanel);
        btnRead = new JButton("Leídos");
        btnPending = new JButton("Próximamente");
        btnWishlist = new JButton("Quiero leer");
        btnAll = new JButton("Todos");
        btnReading = new JButton("Leyendo");
        combo = new JComboBox<>(new String[] { "Fecha", "Título" });
        txtSearch = new JTextField();

        cardsPanel = new JPanel(new WrapLayout(FlowLayout.CENTER, 15, 15));
        scroll = new JScrollPane(cardsPanel);
        centerContent = new JPanel(new BorderLayout());
    }

    /*----------------------------COMPONENTS----------------------------- */
    private void initComponents() {
        setTitle("Mi biblioteca");
        setSize(900, 700);
        setIconImage(new ImageIcon(getClass().getResource("/icons/bibliotecaIcon.png")).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        principal.setBackground(ThemeColors.background());
        add(principal);

        // HEADER
        principal.add(header, BorderLayout.NORTH);
        header.add(headerWest, BorderLayout.WEST);
        header.add(headerEast, BorderLayout.EAST);
        header.setBackground(ThemeColors.background());
        header.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        headerWest.setBackground(ThemeColors.background());
        headerWest.add(title);
        title.setFont(new Font("Nunito", Font.BOLD, 22));
        title.setForeground(ThemeColors.textPrimary());
        headerWest.add(subtitle);
        subtitle.setFont(new Font("Nunito", Font.PLAIN, 13));
        subtitle.setForeground(ThemeColors.textSecondary());

        headerEast.setBackground(ThemeColors.background());
        headerEast.setLayout(new BoxLayout(headerEast, BoxLayout.Y_AXIS));

        themeBtn.setFont(themeBtn.getFont().deriveFont(18f));
        themeBtn.putClientProperty("JButton.buttonType", "borderless");
        themeBtn.setMargin(new Insets(6, 6, 6, 6));
        themeBtn.setBackground(ThemeColors.buttonBackground());
        themeBtn.setForeground(ThemeColors.textTertiary());
        themeBtn.setFocusPainted(false);
        themeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        themeBtn.putClientProperty("FlatLaf.style", "arc: 10; borderColor: " + ThemeColors.toHex(ThemeColors.border()));

        addBookBtn.setBackground(ThemeColors.actionButtonBackground());
        addBookBtn.setForeground(ThemeColors.actionButtonForeground());
        addBookBtn.setMargin(new Insets(9, 16, 9, 16));
        addBookBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addBookBtn.putClientProperty("FlatLaf.style",
                "arc: 8; borderColor: " + ThemeColors.toHex(ThemeColors.actionButtonBorder())
                        + "; hoverBorderColor: " + ThemeColors.toHex(ThemeColors.actionButtonBorder())
                        + "; pressedBackground: " + ThemeColors.toHex(ThemeColors.buttonBackground()));

        btnsPanel.setOpaque(false);
        btnsPanel.add(addBookBtn);
        btnsPanel.add(themeBtn);

        headerEast.add(Box.createVerticalGlue());
        headerEast.add(btnsPanel);
        headerEast.add(Box.createVerticalGlue());

        // MAIN
        principal.add(main, BorderLayout.CENTER);

        // Statistics
        main.add(stats, BorderLayout.NORTH);
        stats.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 16));
        stats.setBackground(ThemeColors.background());
        stats.setPreferredSize(new Dimension(0, 95));

        read = UIUtils.createStatPanel(txtRead, numRead, ThemeColors.statReadBackground());
        pending = UIUtils.createStatPanel(txtPending, numPending, ThemeColors.statPendingBackground());
        wishlist = UIUtils.createStatPanel(txtWishlist, numWishlist, ThemeColors.statWishlistBackground());
        avgRating = UIUtils.createStatPanel(txtAvg, numAvg, ThemeColors.statAvgBackground());

        numRead.setFont(new Font("Nunito", Font.BOLD, 22));
        numRead.setForeground(ThemeColors.statReadValue());
        txtRead.setForeground(ThemeColors.statReadLabel());

        numPending.setFont(new Font("Nunito", Font.BOLD, 22));
        numPending.setForeground(ThemeColors.statPendingValue());
        txtPending.setForeground(ThemeColors.statPendingLabel());

        numWishlist.setFont(new Font("Nunito", Font.BOLD, 22));
        numWishlist.setForeground(ThemeColors.statWishlistValue());
        txtWishlist.setForeground(ThemeColors.statWishlistLabel());

        numAvg.setFont(new Font("Nunito", Font.BOLD, 22));
        numAvg.setForeground(ThemeColors.statAvgValue());
        txtAvg.setForeground(ThemeColors.statAvgLabel());

        stats.add(read);
        stats.add(pending);
        stats.add(wishlist);
        stats.add(avgRating);

        // Filters
        filtersPanel.setBackground(ThemeColors.background());
        filtersPanel.setBorder(BorderFactory.createEmptyBorder(16, 12, 8, 16));
        filtersPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        filterScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        filterScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        filterScroll.setBorder(null);

        setupFilterButton(btnRead, ThemeColors.readAccent(), ThemeColors.filterReadBackground(),
                ThemeColors.filterReadBackground());
        setupFilterButton(btnPending, ThemeColors.upcomingAccent(), ThemeColors.filterPendingBackground(),
                ThemeColors.filterPendingBackground());
        setupFilterButton(btnWishlist, ThemeColors.wishlistAccent(), ThemeColors.filterWishlistBackground(),
                ThemeColors.filterWishlistBackground());
        setupFilterButton(btnReading, ThemeColors.readingAccent(), ThemeColors.filterReadingBackground(),
                ThemeColors.filterReadingBackground());
        setupFilterButton(btnAll, ThemeColors.actionButtonBorder(), ThemeColors.filterAllBackground(),
                ThemeColors.filterAllBackground());

        filtersPanel.add(btnRead);
        filtersPanel.add(btnPending);
        filtersPanel.add(btnWishlist);
        filtersPanel.add(btnReading);
        filtersPanel.add(btnAll);
        filtersPanel.add(combo);
        filtersPanel.add(txtSearch);

        UIUtils.styleComboBox(combo);
        UIUtils.styleSearchField(txtSearch);

        // Cards
        centerContent.add(filterScroll, BorderLayout.NORTH);
        centerContent.add(scroll, BorderLayout.CENTER);
        centerContent.setAlignmentX(Component.LEFT_ALIGNMENT);
        main.add(centerContent, BorderLayout.CENTER);

        scroll.setBorder(null);
        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        scroll.getViewport().setBackground(ThemeColors.background());
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getViewport().addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                cardsPanel.revalidate();
            }
        });

        cardsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardsPanel.setBackground(ThemeColors.background());
        cardsPanel.setBorder(null);

        loadBooks("ALL");
        setLocationRelativeTo(null);
    }

    private void setupFilterButton(JButton btn, Color hoverBorder, Color hoverBg, Color pressed) {
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setMargin(new Insets(6, 16, 6, 16));
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.setBackground(ThemeColors.buttonBackground());
        btn.setForeground(ThemeColors.textTertiary());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.putClientProperty("FlatLaf.style",
                "borderColor: " + ThemeColors.toHex(ThemeColors.border()) + "; hoverBorderColor: "
                        + ThemeColors.toHex(hoverBorder) + "; hoverBackground: " + ThemeColors.toHex(hoverBg)
                        + "; pressedBackground: " + ThemeColors.toHex(pressed));
    }

    /*----------------------------LISTENERS----------------------------- */
    private void setUpListeners() {
        themeBtn.addActionListener(e -> toggleTheme());
        addBookBtn.addActionListener(e -> new AddBookDialog(this, bookFile));

        setupFilterListener(btnRead, "LEIDO");
        setupFilterListener(btnPending, "PROXIMO");
        setupFilterListener(btnWishlist, "QUIERO_LEER");
        setupFilterListener(btnReading, "LEYENDO");
        setupFilterListener(btnAll, "ALL");

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

    private void setupFilterListener(JButton btn, String filter) {
        btn.addActionListener(e -> {
            setActiveFilter(btn);
            currentFilter = filter;
            loadBooks(filter);
        });
    }

    private void toggleTheme() {
        try {
            Point loc = getLocation();
            Dimension size = getSize();
            boolean willBeDark = !(UIManager.getLookAndFeel() instanceof FlatDarkLaf);
            UIManager.setLookAndFeel(willBeDark ? new FlatDarkLaf() : new FlatLightLaf());
            Window w = new Window(willBeDark);
            w.setLocation(loc);
            w.setSize(size);
            w.setVisible(true);
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*----------------------------LOGIC----------------------------- */
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

    /**
     * Reloads all books from the file and refreshes the card grid.
     * Called by {@link AddBookDialog} after adding or editing a book.
     */
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
            showEmptyState();
        } else {
            cardsPanel.setLayout(new WrapLayout(FlowLayout.CENTER, 10, 10));
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private void showEmptyState() {
        String msg = bookFile.getBookList().isEmpty() ? "Tu biblioteca está vacía"
                : "No tienes libros en esta categoría";
        String sub = bookFile.getBookList().isEmpty() ? "Añade tu primer libro para empezar" : "Prueba con otro filtro";

        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
        emptyPanel.setOpaque(false);

        JLabel icon = new JLabel("📚");
        icon.setFont(icon.getFont().deriveFont(60f));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel msgLabel = new JLabel(msg);
        msgLabel.setFont(new Font("Nunito", Font.BOLD, 18));
        msgLabel.setForeground(ThemeColors.textTertiary());
        msgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subLabel = new JLabel(sub);
        subLabel.setForeground(ThemeColors.textTertiary());
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        emptyPanel.add(Box.createVerticalGlue());
        emptyPanel.add(icon);
        emptyPanel.add(Box.createVerticalStrut(12));
        emptyPanel.add(msgLabel);
        emptyPanel.add(Box.createVerticalStrut(6));
        emptyPanel.add(subLabel);
        emptyPanel.add(Box.createVerticalGlue());

        cardsPanel.setLayout(new BorderLayout());
        cardsPanel.add(emptyPanel, BorderLayout.CENTER);
    }

    private void setActiveFilter(JButton activeBtn) {
        JButton[] filterBtns = { btnRead, btnPending, btnWishlist, btnReading, btnAll };
        Color[] bgColors = {
                ThemeColors.filterReadBackground(),
                ThemeColors.filterPendingBackground(),
                ThemeColors.filterWishlistBackground(),
                ThemeColors.filterReadingBackground(),
                ThemeColors.filterAllBackground()
        };
        Color[] borderColors = { ThemeColors.readAccent(), ThemeColors.upcomingAccent(), ThemeColors.wishlistAccent(),
                ThemeColors.readingAccent(), ThemeColors.actionButtonBorder() };
        Color[] hoverColors = { ThemeColors.readAccent(), ThemeColors.upcomingAccent(), ThemeColors.wishlistAccent(),
                ThemeColors.readingAccent(), ThemeColors.actionButtonBorder() };
        Color[] hoverBgColors = { ThemeColors.filterReadBackground(), ThemeColors.filterPendingBackground(),
                ThemeColors.filterWishlistBackground(), ThemeColors.filterReadingBackground(),
                ThemeColors.filterAllBackground() };
        Color[] pressedColors = { ThemeColors.filterReadBackground(), ThemeColors.filterPendingBackground(),
                ThemeColors.filterWishlistBackground(), ThemeColors.filterReadingBackground(),
                ThemeColors.filterAllBackground() };

        for (int i = 0; i < filterBtns.length; i++) {
            if (filterBtns[i] == activeBtn) {
                filterBtns[i].setBackground(bgColors[i]);
                filterBtns[i].setForeground(ThemeColors.textPrimary());
                filterBtns[i].putClientProperty("FlatLaf.style",
                        "borderColor: " + ThemeColors.toHex(borderColors[i]) + "; hoverBorderColor: "
                                + ThemeColors.toHex(borderColors[i]) + "; pressedBackground: "
                                + ThemeColors.toHex(pressedColors[i]));
            } else {
                filterBtns[i].setBackground(ThemeColors.buttonBackground());
                filterBtns[i].setForeground(ThemeColors.textTertiary());
                filterBtns[i].putClientProperty("FlatLaf.style",
                        "borderColor: " + ThemeColors.toHex(ThemeColors.border()) + "; hoverBorderColor: "
                                + ThemeColors.toHex(hoverColors[i]) + "; hoverBackground: "
                                + ThemeColors.toHex(hoverBgColors[i]) + "; pressedBackground: "
                                + ThemeColors.toHex(pressedColors[i]));
            }
            filterBtns[i].repaint();
        }
    }

    private void updateStats() {
        int readCount = 0, pendingCount = 0, wishlistCount = 0;
        int totalRating = 0, ratedBooks = 0;

        for (Book book : bookFile.getBookList()) {
            switch (book.getStatus()) {
                case "LEIDO" -> readCount++;
                case "PROXIMO" -> pendingCount++;
                case "QUIERO_LEER" -> wishlistCount++;
            }
            if (book.getRating() > 0) {
                totalRating += book.getRating();
                ratedBooks++;
            }
        }

        numRead.setText(String.valueOf(readCount));
        numPending.setText(String.valueOf(pendingCount));
        numWishlist.setText(String.valueOf(wishlistCount));
        numAvg.setText(ratedBooks > 0 ? String.format("%.1f", (double) totalRating / ratedBooks) : "-");
    }
}