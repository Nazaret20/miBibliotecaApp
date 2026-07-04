package com.prog.utils;

import java.awt.Color;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

/**
 * Provides theme-aware colors for the application.
 * Returns different colors depending on whether the current theme is light or
 * dark.
 * Used throughout the UI to ensure consistent styling across both themes.
 */
public class ThemeColors {
    public static Color background() {
        return isDark() ? new Color(48, 54, 64) : new Color(245, 245, 245);
    }

    public static Color cardBackground() {
        return isDark() ? new Color(60, 68, 80) : new Color(250, 250, 250);
    }

    public static Color textPrimary() {
        return isDark() ? new Color(235, 240, 245) : new Color(44, 44, 42);
    }

    public static Color textSecondary() {
        return isDark() ? new Color(190, 197, 208) : new Color(136, 135, 128);
    }

    public static Color border() {
        return isDark() ? new Color(92, 99, 112) : new Color(220, 220, 220);
    }

    public static Color textTertiary() {
        return isDark() ? new Color(166, 174, 186) : new Color(80, 80, 80);
    }

    public static Color buttonBackground() {
        return isDark() ? new Color(70, 75, 85) : new Color(240, 240, 240);
    }

    public static Color cardBorder() {
        return isDark() ? new Color(82, 89, 100) : new Color(200, 200, 200);
    }

    public static Color actionButtonBackground() {
        return isDark() ? new Color(58, 86, 128) : new Color(230, 241, 251);
    }

    public static Color actionButtonForeground() {
        return isDark() ? new Color(218, 235, 255) : new Color(12, 68, 124);
    }

    public static Color actionButtonBorder() {
        return isDark() ? new Color(104, 150, 204) : new Color(133, 183, 235);
    }

    public static Color ratingActive() {
        return isDark() ? new Color(255, 187, 65) : new Color(186, 117, 23);
    }

    public static Color statReadBackground() {
        return isDark() ? new Color(40, 80, 60) : new Color(224, 245, 211);
    }

    public static Color statReadValue() {
        return isDark() ? new Color(166, 231, 186) : new Color(8, 80, 65);
    }

    public static Color statReadLabel() {
        return isDark() ? new Color(205, 245, 220) : new Color(15, 110, 86);
    }

    public static Color statPendingBackground() {
        return isDark() ? new Color(80, 60, 20) : new Color(250, 237, 212);
    }

    public static Color statPendingValue() {
        return isDark() ? new Color(255, 205, 126) : new Color(99, 56, 6);
    }

    public static Color statPendingLabel() {
        return isDark() ? new Color(255, 221, 168) : new Color(133, 79, 11);
    }

    public static Color statWishlistBackground() {
        return isDark() ? new Color(80, 40, 60) : new Color(255, 224, 240);
    }

    public static Color statWishlistValue() {
        return isDark() ? new Color(248, 186, 211) : new Color(114, 36, 62);
    }

    public static Color statWishlistLabel() {
        return isDark() ? new Color(255, 214, 230) : new Color(153, 53, 86);
    }

    public static Color statAvgBackground() {
        return isDark() ? new Color(60, 53, 107) : new Color(223, 220, 252);
    }

    public static Color statAvgValue() {
        return isDark() ? new Color(215, 205, 255) : new Color(60, 52, 137);
    }

    public static Color statAvgLabel() {
        return isDark() ? new Color(232, 224, 255) : new Color(83, 74, 183);
    }

    public static Color readAccent() {
        return isDark() ? new Color(73, 142, 104) : new Color(95, 202, 165);
    }

    public static Color readingAccent() {
        return isDark() ? new Color(110, 92, 192) : new Color(168, 154, 232);
    }

    public static Color upcomingAccent() {
        return isDark() ? new Color(186, 128, 53) : new Color(250, 199, 117);
    }

    public static Color wishlistAccent() {
        return isDark() ? new Color(182, 95, 129) : new Color(237, 147, 177);
    }

    public static Color readPillBackground() {
        return isDark() ? new Color(24, 53, 44) : new Color(231, 247, 221);
    }

    public static Color readPillForeground() {
        return isDark() ? new Color(178, 245, 214) : new Color(8, 80, 65);
    }

    public static Color readingPillBackground() {
        return isDark() ? new Color(55, 44, 88) : new Color(238, 236, 254);
    }

    public static Color readingPillForeground() {
        return isDark() ? new Color(205, 191, 255) : new Color(80, 60, 180);
    }

    public static Color upcomingPillBackground() {
        return isDark() ? new Color(71, 49, 18) : new Color(250, 238, 218);
    }

    public static Color upcomingPillForeground() {
        return isDark() ? new Color(255, 214, 146) : new Color(99, 56, 6);
    }

    public static Color wishlistPillBackground() {
        return isDark() ? new Color(71, 39, 53) : new Color(251, 234, 240);
    }

    public static Color wishlistPillForeground() {
        return isDark() ? new Color(255, 196, 220) : new Color(114, 36, 62);
    }

    public static Color deleteButtonBackground() {
        return isDark() ? new Color(74, 50, 57) : new Color(255, 224, 240);
    }

    public static Color deleteButtonForeground() {
        return isDark() ? new Color(242, 196, 210) : new Color(114, 36, 62);
    }

    public static Color deleteButtonBorder() {
        return isDark() ? new Color(186, 112, 130) : new Color(237, 147, 177);
    }

    public static Color filterReadBackground() {
        return isDark() ? new Color(35, 61, 46) : new Color(225, 245, 218);
    }

    public static Color filterPendingBackground() {
        return isDark() ? new Color(73, 51, 20) : new Color(254, 243, 220);
    }

    public static Color filterWishlistBackground() {
        return isDark() ? new Color(76, 42, 55) : new Color(251, 234, 240);
    }

    public static Color filterReadingBackground() {
        return isDark() ? new Color(59, 48, 92) : new Color(232, 230, 253);
    }

    public static Color filterAllBackground() {
        return isDark() ? new Color(57, 80, 116) : new Color(230, 241, 251);
    }

    public static Color comboBackground() {
        return isDark() ? new Color(53, 58, 70) : new Color(240, 240, 240);
    }

    public static Color comboForeground() {
        return isDark() ? new Color(228, 231, 238) : new Color(80, 80, 80);
    }

    public static Color comboSelectionBackground() {
        return isDark() ? new Color(72, 77, 90) : new Color(220, 220, 220);
    }

    public static Color comboSelectionForeground() {
        return isDark() ? new Color(248, 250, 252) : new Color(44, 44, 42);
    }

    public static Color comboOptionBackground() {
        return isDark() ? new Color(46, 50, 60) : new Color(245, 245, 245);
    }

    public static Color comboOptionForeground() {
        return isDark() ? new Color(203, 208, 218) : new Color(80, 80, 80);
    }

    public static Color inputFocusBorder() {
        return isDark() ? new Color(126, 141, 224) : new Color(168, 154, 232);
    }

    public static Color inputBorder() {
        return isDark() ? new Color(84, 92, 108) : new Color(204, 204, 204);
    }

    public static Color starBorder() {
        return isDark() ? new Color(255, 191, 92) : new Color(239, 159, 39);
    }

    /**
     * Converts a Color to its hexadecimal string representation.
     * 
     * @param color Color to convert
     * @return Hex string in format #rrggbb
     */
    public static String toHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public static Color themeButtonBackground() {
        return isDark() ? new Color(136, 201, 227) : new Color(23, 57, 166);
    }

    public static Color themeButtonForeground() {
        return isDark() ? new Color(254, 255, 191) : new Color(253, 255, 179);
    } // cambiar los colores al boton de tema

    private static boolean isDark() {
        return UIManager.getLookAndFeel() instanceof FlatDarkLaf;
    }
}