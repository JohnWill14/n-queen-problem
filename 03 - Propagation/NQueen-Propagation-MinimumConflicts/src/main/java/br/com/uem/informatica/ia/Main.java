package br.com.uem.informatica.ia;

import br.com.uem.informatica.ia.solver.SolverNQ;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        SolverNQ solverNQ = new SolverNQ(n);

        long start = 0, stop = 0;
        for(int i=0;i<5 && !solverNQ.isSolve();i++ ){
            start=System.currentTimeMillis();
            solverNQ.solveNQ();
            stop = System.currentTimeMillis();
        }

        System.out.println("tempo decorrido "+(stop-start)+" ms");

        solverNQ.printBoard();
    }

}