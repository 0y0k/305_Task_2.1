package com.example.a305_21p;

public class UnitConverter {
    // Length conversions
    public static double convertLength(String sourceUnit, String destinationUnit, double value) {
        switch (sourceUnit) {
            case "inch": value /= 2.54; break;
            case "foot": value /= 30.48; break;
            case "yard": value /= 91.44; break;
            case "mile": value /= 1609.34; break;
            default: return -1; // Error
        }

        switch (destinationUnit) {
            case "inch": return value * 2.54;
            case "foot": return value * 30.48;
            case "yard": return value * 91.44;
            case "mile": return value * 1609.34;
            case "cm": return value; // Assuming cm as base unit after conversion
            default: return -1; // Error
        }
    }

    // Weight conversions
    public static double convertWeight(String sourceUnit, String destinationUnit, double value) {
        switch (sourceUnit) {
            case "pound": value /= 0.453592; break;
            case "ounce": value /= 28.3495; break;
            case "ton": value /= 907.185; break;
            default: return -1; // Error
        }

        switch (destinationUnit) {
            case "pound": return value * 0.453592;
            case "ounce": return value * 28.3495;
            case "ton": return value * 907.185;
            case "kg": return value; // Assuming kg as base unit after conversion
            default: return -1; // Error
        }
    }

    // Temperature conversions
    public static double convertTemperature(String sourceUnit, String destinationUnit, double value) {
        if (sourceUnit.equals(destinationUnit)) return value;

        if (sourceUnit.equals("C")) {
            if (destinationUnit.equals("F")) return (value * 1.8) + 32;
            if (destinationUnit.equals("K")) return value + 273.15;
        } else if (sourceUnit.equals("F")) {
            if (destinationUnit.equals("C")) return (value - 32) / 1.8;
            if (destinationUnit.equals("K")) return ((value - 32) / 1.8) + 273.15;
        } else if (sourceUnit.equals("K")) {
            if (destinationUnit.equals("C")) return value - 273.15;
            if (destinationUnit.equals("F")) return ((value - 273.15) * 1.8) + 32;
        }
        return -1; // Error
    }
}