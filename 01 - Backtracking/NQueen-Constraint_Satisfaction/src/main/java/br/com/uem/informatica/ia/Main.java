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

        long start = System.currentTimeMillis();
        solverNQ.solveNQ();
        System.out.println("Tempo decorrido: "+(System.currentTimeMillis()-start)+" ms");
        solverNQ.printBoard();

    }
}