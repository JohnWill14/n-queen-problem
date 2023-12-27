package br.com.uem.informatica.ia.model;

import br.com.uem.informatica.ia.util.IOUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class BoardNQueenProblem extends Board{

    private int[] queens;

    public BoardNQueenProblem(int length) {
        super(length);
        queens = new int[length];
    }

    public int getColQueen(int col){
        return queens[col];
    }

    public int getColMonimalConflicts(){
        int col, conflito;
        do {
            col = ThreadLocalRandom.current().nextInt(getLength());
            conflito = this.get(getColQueen(col), col);
        }while (conflito == 0);

        return col;
    }

    public boolean thereIsQueenConflict(){
        for(int i=0;i<getLength();i++){
            if(thereIsQueenConflict(i)){
                return true;
            }
        }
         return false;
    }

    public boolean thereIsQueenConflict(int i){
        return this.get(this.getColQueen(i), i) > 0;
    }

    public boolean moveQueen( int row, int col){

        this.queens[col] = row;
        addConstraint(row, col);

        return true;
    }

    public boolean isQueen(int row, int col){
        return this.getColQueen(col) == row;
    }

    public boolean removeQueen( int col){
        int oldRow = this.queens[col];

        this.queens[col] = 0;
        removeConstraint(oldRow, col);

        return true;
    }

    public void addConstraint(int row, int col){
        setValuesRow(row, col, (byte) 1);
        setValuesColumn(row, col, (byte) 1);
        setValuesDiagonal(row, col, (byte) 1);
    }

    public void removeConstraint(int row, int col){
        setValuesRow(row, col, (byte) -1);
        setValuesColumn(row, col, (byte) -1);
        setValuesDiagonal(row, col, (byte) -1);
    }

    private void setValuesRow(int row, int col, byte value){
        for (int i = 0; i < getLength(); i++){
            if(i==col)
                continue;
            allocateConstraint(value, row, i);
        }
    }

    private void setValuesColumn(int row, int col, byte value){
        for (int i = 0; i < getLength(); i++){
            if(i==row )
                continue;
            allocateConstraint(value, i, col);
        }
    }

    private void setValuesDiagonal(int row, int col, byte value){
        int cont = 0;

        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--){
                allocateConstraint(value, i, j);
        }

        for (int i = row+1, j = col+1; i < getLength() && j < getLength(); i++, j++){
                allocateConstraint(value, i, j);
        }

        for (int i = row+1, j = col-1; j >= 0 && i < this.getLength(); i++, j--){
                allocateConstraint(value, i, j);
        }

        for (int i = row-1, j = col+1; j < this.getLength() && i >= 0; i--, j++){
                allocateConstraint(value, i, j);
        }
    }

}
