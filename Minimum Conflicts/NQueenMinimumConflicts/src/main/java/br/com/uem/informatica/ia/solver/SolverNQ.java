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
            int col = ThreadLocalRandom.current().nextInt(length);
            boardNQueenProblem.addQueen(col,i);
        }
    }

    public void solveNQ(){
        solve();
    }

    private  void solve(){
        System.out.println("before");
        printBoard();;
        int length = boardNQueenProblem.getLength();

        int count = 0;
        while(boardNQueenProblem.thereIsQueenConflict() && count<1000){
                count++;
                int col = boardNQueenProblem.getColMonimalConflicts();
                System.out.println(col);

                int minimo = 0, pos=-1;
                for(int row = 0; row< length; row++){
                    int conflicts = boardNQueenProblem.contConflicts(row, col);

                    if(conflicts<=minimo || pos<0){
                        minimo = conflicts;
                        pos = row;
                    }
                }
                int rowOld = boardNQueenProblem.getColQueen(col);

                if(rowOld != pos){
                    boardNQueenProblem.removeQueen(col);
                    boardNQueenProblem.addQueen(pos, col);
                }

        }
        System.out.println(count);

    }



    public void printBoard(){
        IOUtil.printSolutionJSON(this.boardNQueenProblem);
    }
}
