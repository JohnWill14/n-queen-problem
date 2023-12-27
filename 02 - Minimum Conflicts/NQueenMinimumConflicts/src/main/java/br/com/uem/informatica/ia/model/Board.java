package br.com.uem.informatica.ia.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
    private byte[][] matriz;
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
       return matriz[row] [col];
    }

    public boolean update(byte value, int row, int col){
        matriz[row] [col] =  value;
        return true;
    }

    public void remove( int row, int col){
        matriz[row] [col] =  0;
    }

}
