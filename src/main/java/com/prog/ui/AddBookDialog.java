package com.prog.ui;

import java.awt.*;
import javax.swing.*;

import com.prog.data.BookCoverFetcher;
import com.prog.data.BookFile;
import com.prog.model.Book;

public class AddBookDialog extends JDialog {

    private JPanel mainPanel, headerPanel, bodyPanel, footerPanel;
    private JLabel lblTitle, lblAuthor, lblStatus, lblRating, lblDate, lblNotes;
    private JTextField txtTitle, txtAuthor, txtDate;
    private JTextArea txtNotes;
    private JButton cancelBtn, saveBtn;
    private JButton btnRead, btnReading, btnPending, btnWishlist;
    private JPanel statusPanel, starsPanel, ratingPanel, titleAuthorPanel;
    private JButton btn1Star, btn2Star, btn3Star, btn4Star, btn5Star;
    private BookFile bookFile;
    private Window window;
    private Book editingBook = null;
    private String dialogTitle, dialogSubtitle;
    private int selectedRating = 0;

    public AddBookDialog(Window parent, BookFile bookFile, Book book) {
        super(parent, "Editar libro", true);
        this.dialogTitle = "Editar libro";
        this.dialogSubtitle = "Tu opinión sobre este libro, siempre puede cambiar";
        this.editingBook = book;
        init(parent, bookFile);
        fillFields(book);
        setVisible(true);
    }

    public AddBookDialog(Window parent, BookFile bookFile) {
        super(parent, "Añadir libro", true);
        this.dialogTitle = "Añadir libro";
        this.dialogSubtitle = "Cada libro es un nuevo mundo por descubrir";
        init(parent, bookFile);
        setVisible(true);
    }

    private void init(Window parent, BookFile bookFile) {
        this.window = parent;
        this.bookFile = bookFile;
        setSize(500, 560);
        setLocationRelativeTo(parent);
        setResizable(false);
        setUpLayout();
        initComponents();
        setUpListeners();
    }

    /*----------------------------LAYOUT----------------------------- */
    private void setUpLayout() {
        mainPanel = new JPanel(new BorderLayout());
        headerPanel = new JPanel();
        bodyPanel = new JPanel();
        footerPanel = new JPanel();

        lblTitle = new JLabel("Título");
        lblAuthor = new JLabel("Autor");
        lblStatus = new JLabel("Estado");
        lblRating = new JLabel("Puntuación");
        lblDate = new JLabel("Fecha de lectura");
        lblNotes = new JLabel("Notas");

        txtTitle = new JTextField();
        txtAuthor = new JTextField();
        txtDate = new JTextField();
        txtNotes = new JTextArea();

        cancelBtn = new JButton("Cancelar");
        saveBtn = new JButton("Guardar libro");

        btnRead = new JButton("✓ Leído");
        btnReading = new JButton("📖 Leyendo");
        btnPending = new JButton("🕒 Próximamente");
        btnWishlist = new JButton("♡ Quiero leer");

        statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        starsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        ratingPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        titleAuthorPanel = new JPanel(new GridLayout(1, 2, 12, 0));

        btn1Star = new JButton("★");
        btn2Star = new JButton("★");
        btn3Star = new JButton("★");
        btn4Star = new JButton("★");
        btn5Star = new JButton("★");
    }

    /*----------------------------COMPONENTS----------------------------- */
    private void initComponents() {
        mainPanel.setBackground(new Color(245, 245, 245));
        add(mainPanel);

        // HEADER
        headerPanel.setLayout(new GridLayout(2, 1, 0, 4));
        headerPanel.setBackground(new Color(245, 245, 245));
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 24, 16, 24)));

        JLabel titleDialogLabel = new JLabel(dialogTitle);
        titleDialogLabel.setFont(new Font("Nunito", Font.BOLD, 18));
        titleDialogLabel.setForeground(new Color(44, 44, 42));

        JLabel subtitleDialogLabel = new JLabel(dialogSubtitle);
        subtitleDialogLabel.setFont(new Font("Nunito", Font.PLAIN, 13));
        subtitleDialogLabel.setForeground(new Color(136, 135, 128));

        headerPanel.add(titleDialogLabel);
        headerPanel.add(subtitleDialogLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // BODY
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        bodyPanel.setBackground(new Color(245, 245, 245));
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
        mainPanel.add(bodyPanel, BorderLayout.CENTER);

        // Estilo común para labels de campo
        JLabel[] fieldLabels = { lblTitle, lblAuthor, lblStatus, lblRating, lblDate, lblNotes };
        for (JLabel lbl : fieldLabels) {
            lbl.setFont(new Font("Nunito", Font.BOLD, 13));
            lbl.setForeground(new Color(80, 80, 80));
        }

        // Título y autor
        titleAuthorPanel.setBackground(new Color(245, 245, 245));
        titleAuthorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleAuthorPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        txtTitle.putClientProperty("FlatLaf.style", "arc: 8; focusedBorderColor: #A89AE8");
        txtAuthor.putClientProperty("FlatLaf.style", "arc: 8; focusedBorderColor: #A89AE8");

        JPanel titleField = new JPanel(new BorderLayout(0, 5));
        titleField.setBackground(new Color(245, 245, 245));
        titleField.add(lblTitle, BorderLayout.NORTH);
        titleField.add(txtTitle, BorderLayout.CENTER);

        JPanel authorField = new JPanel(new BorderLayout(0, 5));
        authorField.setBackground(new Color(245, 245, 245));
        authorField.add(lblAuthor, BorderLayout.NORTH);
        authorField.add(txtAuthor, BorderLayout.CENTER);

        titleAuthorPanel.add(titleField);
        titleAuthorPanel.add(authorField);
        bodyPanel.add(titleAuthorPanel);
        bodyPanel.add(Box.createVerticalStrut(14));

        // Estado
        lblStatus.setAlignmentX(Component.LEFT_ALIGNMENT);
        statusPanel.setBackground(new Color(245, 245, 245));
        statusPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statusPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

        JButton[] statusBtns = { btnRead, btnReading, btnPending, btnWishlist };
        String[] statusStyles = {
            "borderColor: #CCCCCC; hoverBorderColor: #7EC87A; hoverBackground: #E1F5DA; pressedBackground: #cee9c2",
            "borderColor: #CCCCCC; hoverBorderColor: #A89AE8; hoverBackground: #e8e6fd; pressedBackground: #d0cbfa",
            "borderColor: #CCCCCC; hoverBorderColor: #F5A623; hoverBackground: #FEF3DC; pressedBackground: #f7e5bd",
            "borderColor: #CCCCCC; hoverBorderColor: #ED93B1; hoverBackground: #FBEAF0; pressedBackground: #f7d8e8"
        };
        for (int i = 0; i < statusBtns.length; i++) {
            JButton btn = statusBtns[i];
            btn.setFocusPainted(false);
            btn.setFocusable(false);
            btn.putClientProperty("JButton.buttonType", "roundRect");
            btn.setBackground(new Color(240, 240, 240));
            btn.setForeground(new Color(80, 80, 80));
            btn.setMargin(new Insets(5, 10, 5, 10));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.putClientProperty("FlatLaf.style", statusStyles[i]);
            statusPanel.add(btn);
        }

        bodyPanel.add(lblStatus);
        bodyPanel.add(Box.createVerticalStrut(6));
        bodyPanel.add(statusPanel);
        bodyPanel.add(Box.createVerticalStrut(14));

        // Puntuación y fecha
        JButton[] starBtns = { btn1Star, btn2Star, btn3Star, btn4Star, btn5Star };
        starsPanel.setBackground(new Color(245, 245, 245));
        for (JButton btn : starBtns) {
            btn.setFocusPainted(false);
            btn.setFocusable(false);
            btn.setBackground(new Color(250, 250, 250));
            btn.setForeground(new Color(180, 180, 180));
            btn.putClientProperty("FlatLaf.style", "arc: 6; borderColor: #DDDDDD; hoverBorderColor: #DDDDDD");
            btn.setMargin(new Insets(4, 8, 4, 8));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            starsPanel.add(btn);
        }

        JPanel ratingField = new JPanel(new BorderLayout(0, 5));
        ratingField.setBackground(new Color(245, 245, 245));
        ratingField.add(lblRating, BorderLayout.NORTH);
        ratingField.add(starsPanel, BorderLayout.CENTER);

        txtDate.putClientProperty("FlatLaf.style", "arc: 8; focusedBorderColor: #A89AE8");
        txtDate.putClientProperty("JTextField.placeholderText", "Ej: 2/4/2024");

        JPanel dateField = new JPanel(new BorderLayout(0, 5));
        dateField.setBackground(new Color(245, 245, 245));
        dateField.add(lblDate, BorderLayout.NORTH);
        dateField.add(txtDate, BorderLayout.CENTER);

        ratingPanel.setBackground(new Color(245, 245, 245));
        ratingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ratingPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        ratingPanel.add(ratingField);
        ratingPanel.add(dateField);
        bodyPanel.add(ratingPanel);
        bodyPanel.add(Box.createVerticalStrut(14));

        // Notas
        lblNotes.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtNotes.setRows(3);
        txtNotes.setLineWrap(true);
        txtNotes.setWrapStyleWord(true);
        txtNotes.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        JScrollPane notesScroll = new JScrollPane(txtNotes);
        notesScroll.putClientProperty("FlatLaf.style", "arc: 8; focusedBorderColor: #A89AE8");
        notesScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        notesScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        bodyPanel.add(lblNotes);
        bodyPanel.add(Box.createVerticalStrut(6));
        bodyPanel.add(notesScroll);
        bodyPanel.add(Box.createVerticalGlue());

        // FOOTER
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 24));
        footerPanel.setBackground(new Color(245, 245, 245));
        footerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));

        cancelBtn.setBackground(new Color(240, 240, 240));
        cancelBtn.setForeground(new Color(80, 80, 80));
        cancelBtn.setMargin(new Insets(8, 16, 8, 16));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelBtn.putClientProperty("FlatLaf.style",
                "arc: 8; borderColor: #CCCCCC; hoverBorderColor: #CCCCCC; focusedBorderColor: #CCCCCC");

        saveBtn.setBackground(new Color(230, 241, 251));
        saveBtn.setForeground(new Color(12, 68, 124));
        saveBtn.setMargin(new Insets(8, 16, 8, 16));
        saveBtn.setFocusPainted(false);
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveBtn.putClientProperty("FlatLaf.style", "arc: 8; borderColor: #85B7EB");

        footerPanel.add(cancelBtn);
        footerPanel.add(saveBtn);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
    }

    /*----------------------------LISTENERS----------------------------- */
    private void setUpListeners() {
        Timer debounceTimer = new Timer(800, e -> {
            String title = txtTitle.getText().trim();
            if (!title.isEmpty()) {
                new Thread(() -> {
                    String author = BookCoverFetcher.fetchAuthor(title);
                    if (author != null) {
                        SwingUtilities.invokeLater(() -> {
                            txtAuthor.putClientProperty("JTextField.placeholderText", "");
                            txtAuthor.setText(author);
                        });
                    }
                }).start();
            }
        });
        debounceTimer.setRepeats(false);

        txtTitle.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                txtAuthor.setText("");
                txtAuthor.putClientProperty("JTextField.placeholderText", "Buscando autor...");
                debounceTimer.restart();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { debounceTimer.restart(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}
        });

        btnRead.addActionListener(e -> setActiveStatus(btnRead));
        btnReading.addActionListener(e -> setActiveStatus(btnReading));
        btnPending.addActionListener(e -> setActiveStatus(btnPending));
        btnWishlist.addActionListener(e -> setActiveStatus(btnWishlist));

        JButton[] starBtns = { btn1Star, btn2Star, btn3Star, btn4Star, btn5Star };
        for (int i = 0; i < starBtns.length; i++) {
            final int rating = i + 1;
            starBtns[i].addActionListener(e -> setRating(selectedRating == rating ? 0 : rating));
        }

        saveBtn.addActionListener(e -> saveBook());
        cancelBtn.addActionListener(e -> dispose());
    }

    /*----------------------------LOGIC----------------------------- */
    private void saveBook() {
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String date = normalizeDate(txtDate.getText().trim());
        String notes = txtNotes.getText().trim();
        String status = getSelectedStatus();

        if (title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El título y el autor son obligatorios");
            return;
        }
        if (!txtDate.getText().trim().isEmpty() && !txtDate.getText().trim().matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            JOptionPane.showMessageDialog(this, "El formato de fecha debe ser dd/mm/aaaa");
            return;
        }
        if (status == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un estado para el libro");
            return;
        }

        try {
            if (editingBook == null) {
                int id = bookFile.getBookList().isEmpty() ? 1
                        : bookFile.getBookList().get(bookFile.getBookList().size() - 1).getId() + 1;
                bookFile.writeFile(new Book(id, title, author, selectedRating, notes, date, status, ""));
                bookFile.getBookList().add(new Book(id, title, author, selectedRating, notes, date, status, ""));
            } else {
                bookFile.editBook(new Book(editingBook.getId(), title, author, selectedRating, notes, date, status,
                        editingBook.getCoverURL()));
            }
            window.refreshBooks();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el libro");
        }
    }

    private String normalizeDate(String date) {
        if (date.isEmpty()) return "";
        String[] parts = date.split("/");
        String day = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
        String month = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
        return day + "/" + month + "/" + parts[2];
    }

    private String getSelectedStatus() {
        if (btnRead.getBackground().equals(new Color(225, 245, 218))) return "LEIDO";
        if (btnReading.getBackground().equals(new Color(238, 236, 254))) return "LEYENDO";
        if (btnPending.getBackground().equals(new Color(254, 243, 220))) return "PROXIMO";
        if (btnWishlist.getBackground().equals(new Color(251, 234, 240))) return "QUIERO_LEER";
        return null;
    }

    private void setRating(int rating) {
        selectedRating = rating;
        JButton[] stars = { btn1Star, btn2Star, btn3Star, btn4Star, btn5Star };
        for (int i = 0; i < stars.length; i++) {
            if (i < rating) {
                stars[i].setText("★");
                stars[i].setForeground(new Color(186, 117, 23));
                stars[i].putClientProperty("FlatLaf.style", "arc: 6; borderColor: #EF9F27; hoverBorderColor: #EF9F27");
            } else {
                stars[i].setText("☆");
                stars[i].setForeground(new Color(180, 180, 180));
                stars[i].putClientProperty("FlatLaf.style", "arc: 6; borderColor: #DDDDDD; hoverBorderColor: #DDDDDD");
            }
        }
    }

    private void setActiveStatus(JButton activeBtn) {
        JButton[] statusBtns = { btnRead, btnReading, btnPending, btnWishlist };
        Color[] bgColors = {
                new Color(225, 245, 218), new Color(238, 236, 254),
                new Color(254, 243, 220), new Color(251, 234, 240)
        };
        String[] borderColors = { "#7EC87A", "#A89AE8", "#F5A623", "#ED93B1" };
        String[] hoverColors =  { "#7EC87A", "#A89AE8", "#F5A623", "#ED93B1" };
        String[] hoverBgColors = { "#E0F5D3", "#e8e6fd", "#FEF3DC", "#FBEAF0" };
        String[] pressedColors = { "#cee9c2", "#d0cbfa", "#f7e5bd", "#f7d8e8" };

        for (int i = 0; i < statusBtns.length; i++) {
            if (statusBtns[i] == activeBtn) {
                statusBtns[i].setBackground(bgColors[i]);
                statusBtns[i].setForeground(new Color(44, 44, 42));
                statusBtns[i].putClientProperty("FlatLaf.style",
                        "borderColor: " + borderColors[i] + "; hoverBorderColor: " + borderColors[i]
                                + "; pressedBackground: " + pressedColors[i]);
            } else {
                statusBtns[i].setBackground(new Color(240, 240, 240));
                statusBtns[i].setForeground(new Color(80, 80, 80));
                statusBtns[i].putClientProperty("FlatLaf.style",
                        "borderColor: #CCCCCC; hoverBorderColor: " + hoverColors[i] + "; hoverBackground: "
                                + hoverBgColors[i] + "; pressedBackground: " + pressedColors[i]);
            }
            statusBtns[i].repaint();
        }
    }

    private void fillFields(Book book) {
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        txtDate.setText(book.getDate());
        txtNotes.setText(book.getNotes());
        setRating(book.getRating());
        switch (book.getStatus()) {
            case "LEIDO" -> setActiveStatus(btnRead);
            case "LEYENDO" -> setActiveStatus(btnReading);
            case "PROXIMO" -> setActiveStatus(btnPending);
            case "QUIERO_LEER" -> setActiveStatus(btnWishlist);
        }
    }
}