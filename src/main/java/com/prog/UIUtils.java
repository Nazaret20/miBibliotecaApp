package com.prog;

import java.awt.*;

import javax.swing.*;

public class UIUtils {
    public static JButton createSmallButton(String text, Color bg, Color fg, Color border) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setMargin(new Insets(4, 10, 4, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        String borderHex = String.format("#%02x%02x%02x", border.getRed(), border.getGreen(), border.getBlue());
        btn.putClientProperty("FlatLaf.style",
                "arc: 8; borderColor: " + borderHex + "; hoverBorderColor: " + borderHex);
        return btn;
    }

    public static JPanel createRoundedCard(Color backgroundColor) {
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

    public static JPanel createStatPanel(JLabel txtLabel, JLabel numLabel, Color backgroundColor) {
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
        card.setBorder(BorderFactory.createEmptyBorder(18, 14, 18, 14));
        card.add(txtLabel);
        card.add(numLabel);
        return card;
    }

    public static void styleComboBox(JComboBox<?> combo) {
        combo.setBackground(new Color(240, 240, 240));
        combo.setForeground(new Color(80, 80, 80));
        combo.setFocusable(false);
        combo.putClientProperty("FlatLaf.style", "arc: 40; borderColor: #CCCCCC; buttonArrowColor: #797979");
        combo.setPreferredSize(new Dimension(90, 32));
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));

        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                        cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                if (isSelected) {
                    label.setBackground(new Color(220, 220, 220));
                    label.setForeground(new Color(44, 44, 42));
                } else {
                    label.setBackground(new Color(245, 245, 245));
                    label.setForeground(new Color(80, 80, 80));
                }
                return label;
            }
        });
    }
}
