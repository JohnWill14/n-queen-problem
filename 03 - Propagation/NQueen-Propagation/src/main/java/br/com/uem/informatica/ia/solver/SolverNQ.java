package br.com.uem.informatica.ia.solver;

import br.com.uem.informatica.ia.model.BoardNQueenProblem;
import br.com.uem.informatica.ia.util.IOUtil;

public class SolverNQ {
    private BoardNQueenProblem boardNQueenProblem;

    public SolverNQ(int n) {
        this.boardNQueenProblem = new BoardNQueenProblem(n);
    }

    public boolean solveNQ(){

        if (!solve(0)) {
            System.out.print("Solution does not exist");
            return false;
        }

        return true;
    }

    private  boolean solve(int col){
        // caso para parada
        if (col >= boardNQueenProblem.getLength())
            return true;

        for (int i = 0; i < boardNQueenProblem.getLength(); i++) {

            if (boardNQueenProblem.addQueen(i, col)) {

                if (solve(col + 1))
                    return true;

                boardNQueenProblem.removeQueen(col); // BACKTRACK
            }
        }

        return false;
    }

    public void printBoard(){
        IOUtil.printSolutionTextAndJson(this.boardNQueenProblem);
    }
}
