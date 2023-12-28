package br.com.uem.informatica.ia.population.chromosome;

import java.util.Arrays;

public class Chromosome implements Comparable<Chromosome>{
    private int[] queens;
    private Integer fitnessValue = null;

    public Chromosome(int n){
        queens = new int[n];
    }

    public int getQueen(int col){
        return queens[col];
    }

    public int[] getQueens(){
        return queens;
    }

    public void setQueens(int[]  queens){
        this.queens = queens;
    }

    public boolean isQueen(int row, int col){
        return queens[col] == row;
    }

    public void moveQueen(int row, int col){
        queens[col] = row;
        fitnessValue = null;
    }

    public int getLength(){
        return queens.length;
    }

    public int fitness(){

        if(fitnessValue == null){
            int cont = 0;

            for(int i=0;i<getLength();i++){
                cont = countConflictRow(queens[i], i) + countConflictDiagonal(queens[i], i);
            }

            fitnessValue = cont;
        }

        return fitnessValue;
    }

    public int countConflictRow(int row, int col){
        int cont = 0;

        for (int i = 0; i < getLength(); i++){
            if(i==col)
                continue;


            cont ++;
        }

        return cont;
    }

    public  int countConflictDiagonal(int row, int col){
        int cont = 0;

        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--){
            if(isQueen(i, j))
                cont ++;
        }

        for (int i = row+1, j = col+1; i < getLength() && j < getLength(); i++, j++){
            if(isQueen(i, j))
                cont ++;
        }

        for (int i = row+1, j = col-1; j >= 0 && i < this.getLength(); i++, j--){
            if(isQueen(i, j))
                cont ++;
        }

        for (int i = row-1, j = col+1; j < this.getLength() && i >= 0; i--, j++){
            if(isQueen(i, j))
                cont ++;
        }

        return cont;
    }

    public Chromosome clone(){
        Chromosome chromosome = new Chromosome(getLength());
        chromosome.setQueens(Arrays.copyOf(this.getQueens(), this.getLength()));
        return chromosome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(int i=0;i<queens.length;i++){
            sb.append(queens[i]);
            sb.append(" ");
        }
        sb.append("]");


        sb.append(" fitness: "+fitness());

        return sb.toString();
    }

    @Override
    public int compareTo(Chromosome chromosome) {
        return Integer.compare(this.fitness(), chromosome.fitness());
    }
}
