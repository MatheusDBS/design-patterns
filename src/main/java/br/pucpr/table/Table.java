package br.pucpr.table;

import br.pucpr.user.Theme;

public class Table {

    public void print(
            TableData data,
            boolean alignRight,
            Theme theme
    ) {
        if (data == null || data.getRowCount() == 0) {
            System.out.println("ERRO: Dados vazios ou nulos.");
            return;
        }

        String[] headers = data.getHeaders();
        String borderChar = theme.getBorderChar();

        int[] widths = new int[headers.length];

        // Requisito da atividade:
        // largura da coluna = largura do cabeçalho
        for (int i = 0; i < headers.length; i++) {
            widths[i] = headers[i].length();
        }

        StringBuilder sb = new StringBuilder();

        printBorder(sb, widths, borderChar);

        // Cabeçalho
        sb.append("|");

        for (int i = 0; i < headers.length; i++) {
            sb.append(" ")
                    .append(fit(headers[i], widths[i]))
                    .append(" |");
        }

        sb.append("\n");

        printBorder(sb, widths, borderChar);

        // Linhas
        for (int row = 0; row < data.getRowCount(); row++) {
            sb.append("|");

            for (int column = 0; column < headers.length; column++) {
                String value = data.getValue(row, column);

                sb.append(" ")
                        .append(fit(value, widths[column]))
                        .append(" |");
            }

            sb.append("\n");
        }

        printBorder(sb, widths, borderChar);

        if (alignRight) {
            for (String line : sb.toString().split("\n")) {
                System.out.println(" " + line);
            }
        } else {
            System.out.print(sb);
        }
    }

    private void printBorder(
            StringBuilder sb,
            int[] widths,
            String borderChar
    ) {
        for (int width : widths) {
            sb.append(borderChar.repeat(width + 3));
        }

        sb.append(borderChar).append("\n");
    }

    private String fit(String value, int width) {
        if (value == null) {
            value = "";
        }

        if (value.length() > width) {
            return value.substring(0, width);
        }

        return String.format("%-" + width + "s", value);
    }
}
