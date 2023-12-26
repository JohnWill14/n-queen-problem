package br.com.uem.informatica.ia.model;

public class Board {
    private byte matriz[][];
    private int length;

    public Board(int length) {
        this.length = length;
        matriz = new byte[length][length];
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte get(int row, int col){
        return matriz[row][col];
    }

    public boolean update(byte value, int row, int col){
        matriz[row][col] = value;
        return true;
    }

}
