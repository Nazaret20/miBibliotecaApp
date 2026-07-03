package com.prog.utils;

import java.awt.Color;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

public class ThemeColors {
    public static Color background() {
        return isDark() ? new Color(40, 40, 40) : new Color(245, 245, 245);
    }

    public static Color cardBackground() {
        return isDark() ? new Color(55, 55, 55) : new Color(250, 250, 250);
    }

    public static Color textPrimary() {
        return isDark() ? new Color(220, 220, 220) : new Color(44, 44, 42);
    }

    public static Color textSecondary() {
        return isDark() ? new Color(160, 160, 160) : new Color(136, 135, 128);
    }

    public static Color border() {
        return isDark() ? new Color(80, 80, 80) : new Color(220, 220, 220);
    }

    public static Color textTertiary() {
        return isDark() ? new Color(130, 130, 130) : new Color(80, 80, 80);
    }

    public static Color buttonBackground() {
        return isDark() ? new Color(60, 60, 60) : new Color(240, 240, 240);
    }

    public static Color cardBorder() {
        return isDark() ? new Color(75, 75, 75) : new Color(200, 200, 200);
    }

    private static boolean isDark() {
        return UIManager.getLookAndFeel() instanceof FlatDarkLaf;
    }
}