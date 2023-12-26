package br.com.uem.informatica.ia.model;

import br.com.uem.informatica.ia.util.IOUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BoardNQueenProblem extends Board{

    private int[] queens;

    private Set<Integer> tabu;

    public BoardNQueenProblem(int length) {
        super(length);
        queens = new int[length];
        tabu = new HashSet<>();
    }

    public int getColQueen(int col){
        return queens[col];
    }

    public int getColMonimalConflicts(){
        int minor = 0;
        int col = 0;
        boolean first = true;

        if(tabu.size() == getLength()-1){
            tabu.clear();
        }

        int conflicts = 0;
        for(int i=0;i<getLength();i++){
            conflicts = contConflicts(getColQueen(i), i);

            if(conflicts == 0){
                continue;
            }

            if((minor <= conflicts  || first) && !tabu.contains(i)){
                minor = conflicts;
                col = i;
                first = false;
            }
        }
        tabu.add(col);

        if(first){
            tabu.clear();
        }

        return col;
    }

    public List<Integer> getColQueen(){
        return Arrays.stream(queens)
                .boxed()
                .collect(Collectors.toList());
    }

    public void removeQueen(int col) {
        int row = queens[col];
        super.remove(row, col);
        queens[col] = 0;
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
        return this.contConflicts(this.getColQueen(i), i) != 0;
    }

    public boolean addQueen(int row, int col) {
        if(update((byte) 1, row, col)){
            queens[col] = row;
            return true;
        }

        return false;
    }

    public boolean isQueen(int row, int col){
        return this.get(row, col) == 1;
    }

    public int contConflicts(int row, int col){
        return checkRow(row) + checkDiagonal(row, col);
    }

    private int checkRow(int row){
        int cont = 0;
        // verifica se já existe uma rainha na linha
        for (int i = 0; i < getLength(); i++){
            if (this.isQueen(row, i)){
               cont +=1;
            }
        }

        return cont -1;
    }

    private int checkDiagonal(int row, int col){
        int cont = 0;
        // verifica se já existe uma rainha na diagonal principal
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--){
            if ( this.isQueen(i,j)){
                cont+=1;
            }
        }


        for (int i = row+1, j = col+1; i < getLength() && j < getLength(); i++, j++){
            if ( this.isQueen(i,j)){
                cont++;
            }
        }

        // verifica se já existe uma rainha na diagonal secundaria
        for (int i = row+1, j = col-1; j >= 0 && i < this.getLength(); i++, j--){
            if (this.isQueen(i, j)){
                cont++;
            }
        }

        for (int i = row-1, j = col+1; j < this.getLength() && i >= 0; i--, j++){
            if (this.isQueen(i, j)){
                cont++;
            }
        }

        return cont;
    }


}
