package com.prog.ui;

import java.awt.*;
import java.net.*;
import javax.swing.*;

import com.prog.data.BookCoverFetcher;
import com.prog.data.BookFile;
import com.prog.model.Book;
import com.prog.utils.ThemeColors;
import com.prog.utils.UIUtils;

/**
 * Visual card component representing a single book in the library grid.
 * Displays title, author, rating, notes, date, status and optionally the book
 * cover.
 * Cover images are fetched from Google Books API and cached locally.
 */
public class BookCard extends JPanel {

    /**
     * Creates a new book card with all its visual components.
     * 
     * @param book      Book data to display
     * @param bookFile  File handler used for delete and edit operations
     * @param window    Parent window, used to refresh the grid after changes
     * @param cardWidth Width of the card in pixels
     * @param showCover Whether to fetch and display the book cover (only in
     *                  maximized window)
     */
    public BookCard(Book book, BookFile bookFile, Window window, int cardWidth, boolean showCover) {
        setOpaque(false);
        setBackground(ThemeColors.cardBackground());
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(cardWidth, 250));
        setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));

        JPanel colorBar = createColorBar(book.getStatus());

        Object[] statusInfo = getStatusInfo(book.getStatus());
        Color statusBg = (Color) statusInfo[0];
        Color statusFg = (Color) statusInfo[1];
        String statusText = (String) statusInfo[2];

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

        JLabel titleLabel = new JLabel(book.getTitle());
        titleLabel.setFont(new Font("Nunito", Font.BOLD, 18));
        titleLabel.setForeground(ThemeColors.textPrimary());
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel authorLabel = new JLabel(book.getAuthor());
        authorLabel.setForeground(ThemeColors.textSecondary());
        authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel starsLabel = new JLabel("★".repeat(book.getRating()) + "☆".repeat(5 - book.getRating()));
        starsLabel.setFont(new Font("Nunito", Font.PLAIN, 15));
        starsLabel.setForeground(ThemeColors.ratingActive());
        starsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel notesLabel = new JLabel(
                "<html><i>" + (book.getNotes().isEmpty() ? "Sin comentarios" : book.getNotes()) + "</i></html>");
        notesLabel.setFont(new Font("Nunito", Font.PLAIN, 13));
        notesLabel.setForeground(ThemeColors.textSecondary());
        notesLabel.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 0));

        JPanel notesWrapper = new JPanel(new BorderLayout());
        notesWrapper.setOpaque(false);
        notesWrapper.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, ThemeColors.cardBorder()));
        notesWrapper.add(notesLabel);
        notesWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel dateLabel = new JLabel("📆 " + (book.getDate().isEmpty() ? "Sin fecha" : book.getDate()));
        dateLabel.setFont(new Font("Nunito", Font.PLAIN, 13));
        dateLabel.setForeground(ThemeColors.textSecondary());
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton editBtn = UIUtils.createSmallButton("Editar", ThemeColors.buttonBackground(),
                ThemeColors.textTertiary(), ThemeColors.cardBorder());
        JButton deleteBtn = UIUtils.createSmallButton("Eliminar", ThemeColors.deleteButtonBackground(),
                ThemeColors.deleteButtonForeground(), ThemeColors.deleteButtonBorder());

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.X_AXIS));
        actionsPanel.setOpaque(false);
        actionsPanel.add(editBtn);
        actionsPanel.add(Box.createHorizontalStrut(6));
        actionsPanel.add(deleteBtn);
        actionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(pillWrapper);
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalStrut(2));
        leftPanel.add(authorLabel);
        leftPanel.add(Box.createVerticalStrut(6));
        leftPanel.add(starsLabel);
        leftPanel.add(Box.createVerticalStrut(4));
        leftPanel.add(notesWrapper);
        leftPanel.add(Box.createVerticalStrut(2));
        leftPanel.add(dateLabel);
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(actionsPanel);

        add(colorBar, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.CENTER);

        if (showCover) {
            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            rightPanel.setOpaque(false);
            rightPanel.setPreferredSize(new Dimension(85, 130));

            JLabel coverLabel = new JLabel();
            coverLabel.setPreferredSize(new Dimension(75, 110));
            coverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JProgressBar progressBar = new JProgressBar();
            progressBar.setIndeterminate(true);
            progressBar.setMaximumSize(new Dimension(75, 4));
            progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);

            rightPanel.add(Box.createVerticalGlue());
            rightPanel.add(coverLabel);
            rightPanel.add(Box.createVerticalStrut(6));
            rightPanel.add(progressBar);
            rightPanel.add(Box.createVerticalGlue());

            add(rightPanel, BorderLayout.EAST);
            loadCover(book, bookFile, coverLabel, progressBar, rightPanel);
        }

        deleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar este libro?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    bookFile.deleteBook(book.getId());
                    window.refreshBooks();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el libro");
                }
            }
        });

        editBtn.addActionListener(e -> new AddBookDialog(window, bookFile, book));
    }

    private JPanel createColorBar(String status) {
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
        colorBar.setBackground(switch (status) {
            case "LEIDO" -> ThemeColors.readAccent();
            case "LEYENDO" -> ThemeColors.readingAccent();
            case "PROXIMO" -> ThemeColors.upcomingAccent();
            default -> ThemeColors.wishlistAccent();
        });
        return colorBar;
    }

    private Object[] getStatusInfo(String status) {
        return switch (status) {
            case "LEIDO" ->
                new Object[] { ThemeColors.readPillBackground(), ThemeColors.readPillForeground(), "✓ Leído" };
            case "LEYENDO" ->
                new Object[] { ThemeColors.readingPillBackground(), ThemeColors.readingPillForeground(), "📖 Leyendo" };
            case "PROXIMO" -> new Object[] { ThemeColors.upcomingPillBackground(), ThemeColors.upcomingPillForeground(),
                    "🕒 Próximamente" };
            default -> new Object[] { ThemeColors.wishlistPillBackground(), ThemeColors.wishlistPillForeground(),
                    "♡ Quiero leer" };
        };
    }

    private void loadCover(Book book, BookFile bookFile, JLabel coverLabel, JProgressBar progressBar,
            JPanel rightPanel) {
        new Thread(() -> {
            java.io.File cacheFile = new java.io.File("cache/" + book.getId() + ".jpg");

            if (cacheFile.exists()) {
                ImageIcon icon = new ImageIcon(cacheFile.getAbsolutePath());
                Image scaled = icon.getImage().getScaledInstance(75, 110, Image.SCALE_SMOOTH);
                SwingUtilities.invokeLater(() -> {
                    coverLabel.setIcon(new ImageIcon(scaled));
                    progressBar.setVisible(false);
                    rightPanel.revalidate();
                });
            } else {
                String coverUrl = book.getCoverURL();
                if (coverUrl == null || coverUrl.isEmpty()) {
                    coverUrl = BookCoverFetcher.fetchCoverUrl(book.getTitle());
                    if (coverUrl != null) {
                        book.setCoverURL(coverUrl);
                        try {
                            bookFile.editBook(book);
                        } catch (Exception e) {
                            System.out.println("Error al guardar URL: " + e.getMessage());
                        }
                    }
                }

                final String urlToLoad = coverUrl;
                if (urlToLoad != null && !urlToLoad.isEmpty()) {
                    try {
                        URI uri = new URI(urlToLoad);
                        cacheFile.getParentFile().mkdirs();
                        javax.imageio.ImageIO.write(javax.imageio.ImageIO.read(uri.toURL()), "jpg", cacheFile);
                        ImageIcon icon = new ImageIcon(uri.toURL());
                        Image scaled = icon.getImage().getScaledInstance(75, 110, Image.SCALE_SMOOTH);
                        SwingUtilities.invokeLater(() -> {
                            coverLabel.setIcon(new ImageIcon(scaled));
                            progressBar.setVisible(false);
                            rightPanel.revalidate();
                        });
                    } catch (Exception e) {
                        SwingUtilities.invokeLater(() -> progressBar.setVisible(false));
                    }
                } else {
                    SwingUtilities.invokeLater(() -> progressBar.setVisible(false));
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(ThemeColors.cardBorder());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(getBackground());
        g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 15, 15);
        g2.dispose();
        super.paintComponent(g);
    }
}