package br.com.uem.nformatica.ia.model;

public class BoardNQueenProblem extends Board{
    public BoardNQueenProblem(int length) {
        super(length);
    }

    @Override
    public boolean update(byte value, int row, int col) {
        if(!isSafe(row, col)){
            return false;
        }

        return super.update(value, row, col);
    }

    public boolean isQueen(int row, int col){
        return this.get(row, col) == 1;
    }

    public boolean isSafe( int row, int col){
        return checkColumn(row, col) && checkRow(row, col) && checkDiagonal(row, col);
    }

    private boolean checkRow(int row, int col){
        // verifica se já existe uma rainha na linha
        for (int i = 0; i < col; i++){
            if (this.isQueen(row, i)){
                return false;
            }
        }

        return true;
    }

    private boolean checkColumn(int row, int col){
        // verifica se já existe uma rainha na coluna
        for (int i = 0; i < row; i++){
            if (this.isQueen(i,col)) {
                return false;
            }
        }

        return true;
    }

    private boolean checkDiagonal(int row, int col){
        // verifica se já existe uma rainha na diagonal acima
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--){
            if (this.isQueen(i,j)){
                return false;
            }
        }

        // verifica se já existe uma rainha na diagonal abaixo
        for (int i = row, j = col; j >= 0 && i < this.getLength(); i++, j--){
            if (this.isQueen(i, j)){
                return false;
            }
        }

        return true;
    }


}
