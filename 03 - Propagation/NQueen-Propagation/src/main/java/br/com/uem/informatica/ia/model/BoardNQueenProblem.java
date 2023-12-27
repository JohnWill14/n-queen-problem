package br.com.uem.informatica.ia.model;

public class BoardNQueenProblem extends Board{
    private int[] queens;
    public BoardNQueenProblem(int length) {
        super(length);
        queens = new int[length];
    }

    public boolean isQueen(int row, int col){
        return this.queens[col] == row;
    }

    public boolean addQueen( int row, int col){
        if(this.hasConstraint(row, col)){
            return false;
        }

        this.queens[col] = row;
        addConstraint(row, col);

        return true;
    }

    public boolean removeQueen( int col){
        int oldRow = this.queens[col];

        this.queens[col] = 0;
        removeConstraint(oldRow, col);

        return true;
    }

    public void addConstraint(int row, int col){
        setValuesRow(row, (byte) 1);
        setValuesColumn(col, (byte) 1);
        setValuesDiagonal(row, col, (byte) 1);
    }

    public void removeConstraint(int row, int col){
        setValuesRow(row, (byte) -1);
        setValuesColumn(col, (byte) -1);
        setValuesDiagonal(row, col, (byte) -1);
    }

    private void setValuesRow(int row, byte value){
        for (int i = 0; i < getLength(); i++){
            allocateConstraint(value, row, i);
        }
    }

    private void setValuesColumn(int col, byte value){
        for (int i = 0; i < getLength(); i++){
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


    private boolean hasConstraint(int row, int col){
        return this.get(row, col) > 0;
    }

}
