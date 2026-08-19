package br.pucpr.table;

public interface TableData {
    String[] getHeaders();

    int getRowCount();

    String getValue(int row, int column);
}
