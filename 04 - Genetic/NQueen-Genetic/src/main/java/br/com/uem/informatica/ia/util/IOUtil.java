package br.com.uem.informatica.ia.util;


import br.com.uem.informatica.ia.population.chromosome.Chromosome;

public class IOUtil {

    public static void printSolutionTextAndJson(Chromosome chromosome){
        printSolutionText(chromosome);
        printSolutionJSON(chromosome);
    }

    public static void printSolutionText(Chromosome board){
        System.out.println();

        int length = board.getLength();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board.isQueen(i, j))
                    System.out.print("Q");
                else
                    System.out.print("~");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void printSolutionJSON(Chromosome board){
        System.out.println("{\n\"data\":[");

        int length = board.getLength();

        for (int i = 0; i < length; i++) {
            System.out.print("[");
            for (int j = 0; j < length; j++) {
                if (board.isQueen(i, j))
                    System.out.print("1");
                else
                    System.out.print("0");

               // System.out.print(board.get(i,j));

                if(j!= length -1){
                    System.out.print(",");
                }
            }
            System.out.print("]");
            if(i!= length -1){
                System.out.println(",");
            }else{
                System.out.println();
            }
        }

        System.out.println("]\n}");
    }


}
