package com.prog;

import java.awt.*;
import javax.swing.*;

public class AddBookDialog extends JDialog {

    private JPanel mainPanel, headerPanel, bodyPanel, footerPanel;
    private JLabel lblTitle, lblAuthor, lblStatus, lblRating, lblDate, lblNotes;
    private JTextField txtTitle, txtAuthor, txtDate;
    private JTextArea txtNotes;
    private JButton cancelBtn, saveBtn;
    private JButton btnRead, btnReading, btnPending, btnWishlist;
    private JPanel statusPanel, starsPanel, ratingPanel, titleAuthorPanel;
    private JButton btn1Star, btn2Star, btn3Star, btn4Star, btn5Star;

    public AddBookDialog(JFrame parent) {
        super(parent, "Añadir libro", true);
        setSize(500, 560);
        setLocationRelativeTo(parent);
        setResizable(false);
        setUpLayout();
        initComponents();
        setUpListeners();
        setVisible(true);
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
        btnReading = new JButton("○ Leyendo");
        btnPending = new JButton("○ Pendiente");
        btnWishlist = new JButton("♡ Quiero leer");

        statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        starsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        ratingPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        titleAuthorPanel = new JPanel(new GridLayout(1, 2, 12, 0));

        btn1Star = new JButton("○");
        btn2Star = new JButton("○");
        btn3Star = new JButton("○");
        btn4Star = new JButton("○");
        btn5Star = new JButton("○");
    }

    /*----------------------------COMPONENTS----------------------------- */
    private void initComponents() {
        mainPanel.setBackground(new Color(250, 250, 250));
        add(mainPanel);

        // HEADER
        headerPanel.setLayout(new GridLayout(2, 1, 0, 4));
        headerPanel.setBackground(new Color(245, 245, 245));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 24, 16, 24));

        JLabel titleDialogLabel = new JLabel("Añadir libro");
        titleDialogLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleDialogLabel.setForeground(new Color(44, 44, 42));

        JLabel subtitleDialogLabel = new JLabel("Añade un nuevo libro a tu biblioteca");
        subtitleDialogLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleDialogLabel.setForeground(new Color(136, 135, 128));

        headerPanel.add(titleDialogLabel);
        headerPanel.add(subtitleDialogLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // BODY
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        bodyPanel.setBackground(new Color(245, 245, 245));
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
        mainPanel.add(bodyPanel, BorderLayout.CENTER);

        // Título y autor
        titleAuthorPanel.setBackground(new Color(245, 245, 245));
        titleAuthorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleAuthorPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTitle.setForeground(new Color(80, 80, 80));
        lblAuthor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblAuthor.setForeground(new Color(80, 80, 80));

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
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblStatus.setForeground(new Color(80, 80, 80));
        lblStatus.setAlignmentX(Component.LEFT_ALIGNMENT);
        statusPanel.setBackground(new Color(245, 245, 245));
        statusPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton[] statusBtns = { btnRead, btnReading, btnPending, btnWishlist };
        for (JButton btn : statusBtns) {
            btn.setFocusPainted(false);
            btn.setFocusable(false);
            btn.putClientProperty("JButton.buttonType", "roundRect");
            btn.setBackground(new Color(240, 240, 240));
            btn.setForeground(new Color(80, 80, 80));
            btn.putClientProperty("FlatLaf.style", "borderColor: #CCCCCC");
            statusPanel.add(btn);
        }
        btnRead.putClientProperty("FlatLaf.style",
                "borderColor: #CCCCCC; hoverBorderColor: #7EC87A; hoverBackground: #E1F5DA; pressedBackground: #cee9c2");
        btnReading.putClientProperty("FlatLaf.style",
                "borderColor: #CCCCCC; hoverBorderColor: #85B7EB; hoverBackground: #E6F1FB; pressedBackground: #c8dff3");
        btnPending.putClientProperty("FlatLaf.style",
                "borderColor: #CCCCCC; hoverBorderColor: #F5A623; hoverBackground: #FEF3DC; pressedBackground: #f7e5bd");
        btnWishlist.putClientProperty("FlatLaf.style",
                "borderColor: #CCCCCC; hoverBorderColor: #ED93B1; hoverBackground: #FBEAF0; pressedBackground: #f7d8e8");

        bodyPanel.add(lblStatus);
        bodyPanel.add(Box.createVerticalStrut(6));
        bodyPanel.add(statusPanel);
        bodyPanel.add(Box.createVerticalStrut(14));

        // Puntuación y fecha
        lblRating.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblRating.setForeground(new Color(80, 80, 80));
        lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDate.setForeground(new Color(80, 80, 80));

        JButton[] starBtns = { btn1Star, btn2Star, btn3Star, btn4Star, btn5Star };
        starsPanel.setBackground(new Color(245, 245, 245));
        for (JButton btn : starBtns) {
            btn.setFocusPainted(false);
            btn.setFocusable(false);
            btn.setBackground(new Color(250, 250, 250));
            btn.setForeground(new Color(180, 180, 180));
            btn.putClientProperty("FlatLaf.style", "arc: 6; borderColor: #DDDDDD");
            btn.setMargin(new Insets(4, 8, 4, 8));
            starsPanel.add(btn);
        }

        JPanel ratingField = new JPanel(new BorderLayout(0, 5));
        ratingField.setBackground(new Color(245, 245, 245));
        ratingField.add(lblRating, BorderLayout.NORTH);
        ratingField.add(starsPanel, BorderLayout.CENTER);

        JPanel dateField = new JPanel(new BorderLayout(0, 5));
        dateField.setBackground(new Color(245, 245, 245));
        dateField.add(lblDate, BorderLayout.NORTH);
        dateField.add(txtDate, BorderLayout.CENTER);

        ratingPanel.setBackground(new Color(245, 245, 245));
        ratingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ratingPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        ratingPanel.add(ratingField);
        ratingPanel.add(dateField);
        bodyPanel.add(ratingPanel);
        bodyPanel.add(Box.createVerticalStrut(14));

        // Notas
        lblNotes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblNotes.setForeground(new Color(80, 80, 80));
        lblNotes.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtNotes.setRows(3);
        txtNotes.setLineWrap(true);
        txtNotes.setWrapStyleWord(true);
        txtNotes.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        JScrollPane notesScroll = new JScrollPane(txtNotes);
        notesScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        notesScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        bodyPanel.add(lblNotes);
        bodyPanel.add(Box.createVerticalStrut(6));
        bodyPanel.add(notesScroll);

        // FOOTER
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 14));
        footerPanel.setBackground(new Color(245, 245, 245));
        footerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));

        cancelBtn.setBackground(new Color(235, 235, 235));
        cancelBtn.setForeground(new Color(80, 80, 80));
        cancelBtn.setFocusPainted(false);
        cancelBtn.putClientProperty("FlatLaf.style", "arc: 8; borderColor: #CCCCCC");

        saveBtn.setBackground(new Color(230, 241, 251));
        saveBtn.setForeground(new Color(12, 68, 124));
        saveBtn.setFocusPainted(false);
        saveBtn.putClientProperty("FlatLaf.style", "arc: 8; borderColor: #85B7EB");

        footerPanel.add(cancelBtn);
        footerPanel.add(saveBtn);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
    }

    /*----------------------------LISTENERS----------------------------- */
    private void setUpListeners() {
        btnRead.addActionListener(e -> {
            setActiveStatus(btnRead);
        });
        btnReading.addActionListener(e -> {
            setActiveStatus(btnReading);
        });
        btnPending.addActionListener(e -> {
            setActiveStatus(btnPending);
        });
        btnWishlist.addActionListener(e -> {
            setActiveStatus(btnWishlist);
        });
    }

    private void setActiveStatus(JButton activeBtn) {
        JButton[] filterBtns = { btnRead, btnReading, btnPending, btnWishlist };
        Color[] bgColors = {
                new Color(225, 245, 218),
                new Color(230, 241, 251),
                new Color(254, 243, 220),
                new Color(251, 234, 240)
        };
        String[] borderColors = { "#7EC87A", "#85B7EB", "#F5A623", "#ED93B1" };
        String[] hoverColors = { "#7EC87A", "#85B7EB", "#F5A623", "#ED93B1" };
        String[] hoverBgColors = { "#E0F5D3", "#E6F1FB", "#FEF3DC", "#FBEAF0" };
        String[] pressedColors = { "#cee9c2", "#c8dff3", "#f7e5bd", "#f7d8e8" };

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

}