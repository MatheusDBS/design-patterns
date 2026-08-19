package br.pucpr.user;

import br.pucpr.table.TableData;

import java.util.ArrayList;

public class UsersPrinter implements TableData {

    private final ArrayList<User> users;
    private final boolean maskCpf;

    public UsersPrinter(ArrayList<User> users, boolean maskCpf) {
        this.users = users;
        this.maskCpf = maskCpf;
    }

    @Override
    public String[] getHeaders() {
        return new String[]{
                "ID",
                "NOME",
                "EMAIL",
                "CPF"
        };
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public String getValue(int row, int column) {
        User user = users.get(row);

        if (user == null) {
            return "";
        }

        return switch (column) {
            case 0 -> formatId(user.id());
            case 1 -> formatName(user);
            case 2 -> validateAndFormatEmail(user.email());
            case 3 -> formatCpf(user.cpf(), maskCpf);
            default -> "";
        };
    }

    private static String formatId(Long id) {
        return id != null ? id.toString() : "0";
    }

    private static String formatCpf(String cpf, boolean mask) {
        if (cpf == null || cpf.length() != 11) {
            return "CPF INVÁLIDO";
        }

        if (mask) {
            return "***."
                    + cpf.substring(3, 6)
                    + "."
                    + cpf.substring(6, 9)
                    + "-**";
        }

        return cpf.substring(0, 3)
                + "."
                + cpf.substring(3, 6)
                + "."
                + cpf.substring(6, 9)
                + "-"
                + cpf.substring(9, 11);
    }

    private static String validateAndFormatEmail(String email) {
        return email == null || !email.contains("@")
                ? "INVÁLIDO"
                : email;
    }

    private static String formatName(User user) {
        String name = user.name();

        if (name == null || name.isEmpty()) {
            return "NÃO INFORMADO";
        }

        if (name.length() > 20) {
            name = name.substring(0, 17) + "...";
        }

        return name;
    }
}
