package com.prog;

import java.awt.*;

import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Label.font", new Font("Nunito", Font.PLAIN, 14));
            UIManager.put("Button.font", new Font("Nunito", Font.PLAIN, 14));
            UIManager.put("TextField.font", new Font("Nunito", Font.PLAIN, 14));
            UIManager.put("TextArea.font", new Font("Nunito", Font.PLAIN, 14));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Font regular = Font.createFont(Font.TRUETYPE_FONT,
                    Main.class.getResourceAsStream("/fonts/Nunito-VariableFont_wght.ttf"));
            Font bold = Font.createFont(Font.TRUETYPE_FONT,
                    Main.class.getResourceAsStream("/fonts/Nunito-Italic-VariableFont_wght.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(regular);
            ge.registerFont(bold);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
            window.setVisible(true);
        });
    }
}