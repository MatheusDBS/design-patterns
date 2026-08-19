package br.pucpr.planet;

import br.pucpr.table.TableData;

import java.util.ArrayList;

public class PlanetasPrinter implements TableData {

    private final ArrayList<Planet> planets;

    public PlanetasPrinter(ArrayList<Planet> planets) {
        this.planets = planets;
    }

    @Override
    public String[] getHeaders() {
        return new String[]{
                "Nome",
                "Diâmetro",
                "Dist. sol (km)",
                "Dist. sol (ua)",
                "Tipo"
        };
    }

    @Override
    public int getRowCount() {
        return planets.size();
    }

    @Override
    public String getValue(int row, int column) {
        Planet planet = planets.get(row);

        if (planet == null) {
            return "";
        }

        return switch (column) {
            case 0 -> formatName(planet.name());
            case 1 -> String.format("%.1f", planet.diameterKm());
            case 2 -> String.format("%d", planet.sunDistanceKm());
            case 3 -> String.format(
                    "%.2f",
                    Planet.kmToAu(planet.sunDistanceKm())
            );
            case 4 -> formatType(planet.type());
            default -> "";
        };
    }

    private static String formatName(String name) {
        if (name == null || name.isEmpty()) {
            return "NÃO INFORMADO";
        }

        if (name.length() > 20) {
            return name.substring(0, 17) + "...";
        }

        return name;
    }

    private static String formatType(PlanetType type) {
        return switch (type) {
            case ROCK -> "Rochoso";
            case GAS -> "Gasoso";
            case ICE -> "Gelado";
            case DWARF -> "Anão";
        };
    }
}
