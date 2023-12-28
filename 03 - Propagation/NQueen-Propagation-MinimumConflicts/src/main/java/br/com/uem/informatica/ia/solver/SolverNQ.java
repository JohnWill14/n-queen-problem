package br.com.uem.informatica.ia.solver;

import br.com.uem.informatica.ia.model.BoardNQueenProblem;
import br.com.uem.informatica.ia.util.IOUtil;

import java.util.concurrent.ThreadLocalRandom;

public class SolverNQ {
    private BoardNQueenProblem boardNQueenProblem;

    public SolverNQ(int n) {
        this.boardNQueenProblem = new BoardNQueenProblem(n);
        generateValues();
    }

    private void generateValues(){
        int length = boardNQueenProblem.getLength();
        for(int i = 0; i< length; i++){
            int row = ThreadLocalRandom.current().nextInt(length);
            boardNQueenProblem.moveQueen(row, i);
        }
    }

    public void solveNQ(){
        solve();
    }

    private  void solve(){
        int length = boardNQueenProblem.getLength();

        int count = 0;
        while(boardNQueenProblem.thereIsQueenConflict() && count<10000){
                count++;
                int col = boardNQueenProblem.getColMonimalConflicts();
                int rowOld = boardNQueenProblem.getColQueen(col);

                int minimo = Integer.MAX_VALUE, pos=-1;
                for(int row = 0; row< length; row++){
                    if(rowOld == row){
                        continue;
                    }
                    int conflicts = boardNQueenProblem.get(row, col);

                    if(conflicts<minimo){
                        minimo = conflicts;
                        pos = row;
                    }
                }
                boardNQueenProblem.removeQueen(col);
                boardNQueenProblem.moveQueen(pos, col);

        }
        System.out.println(count+" iterations");

    }



    public void printBoard(){
        if(boardNQueenProblem.thereIsQueenConflict()){
            System.out.println("Incomplete solution: try more iterations");
        }else{

            System.out.println("Complete solution");

            System.out.println();
        }
        IOUtil.printSolutionTextAndJson(this.boardNQueenProblem);
    }

    public boolean isSolve(){
        return !boardNQueenProblem.thereIsQueenConflict();
    }
}
