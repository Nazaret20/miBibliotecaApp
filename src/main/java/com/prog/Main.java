package com.prog;

import java.awt.*;

import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;
import com.prog.ui.Window;

/**
 * Entry point of the personal library application.
 * Initializes the UI configuration and launches the main window.
 */
public class Main {
    public static void main(String[] args) {
        setupUI();
        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
            window.setVisible(true);
        });
    }

    private static void setupUI() {
        try {
            Font regular = Font.createFont(Font.TRUETYPE_FONT,
                    Main.class.getResourceAsStream("/fonts/Nunito-VariableFont_wght.ttf"));
            Font bold = Font.createFont(Font.TRUETYPE_FONT,
                    Main.class.getResourceAsStream("/fonts/Nunito-Italic-VariableFont_wght.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(regular);
            ge.registerFont(bold);

            UIManager.setLookAndFeel(new FlatLightLaf());
            Font nunito = new Font("Nunito", Font.PLAIN, 14);
            String[] fontKeys = {
                    "Label.font", "Button.font", "TextField.font", "TextArea.font",
                    "ComboBox.font", "Panel.font", "ProgressBar.font",
                    "OptionPane.font", "OptionPane.messageFont", "OptionPane.buttonFont"
            };

            for (String key : fontKeys) {
                UIManager.put(key, nunito);
            }

            UIManager.put("OptionPane.yesButtonText", "Sí");
            UIManager.put("OptionPane.noButtonText", "No");
            UIManager.put("OptionPane.cancelButtonText", "Cancelar");
            UIManager.put("OptionPane.okButtonText", "Aceptar");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}