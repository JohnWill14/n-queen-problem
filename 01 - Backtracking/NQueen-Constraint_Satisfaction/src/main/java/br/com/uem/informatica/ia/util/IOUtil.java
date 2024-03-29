package br.com.uem.informatica.ia.util;

import br.com.uem.informatica.ia.model.Board;

public class IOUtil {

    public static void printSolutionTextAndJson(Board board){
        System.out.println("\n============================================");
        System.out.println("Result:");
        printSolutionText(board);
        System.out.println("JSON Format:");
        printSolutionJSON(board);
    }


    public static void printSolutionText(Board board){
        int length = board.getLength();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board.get(i, j) == 1)
                    System.out.print("Q");
                else
                    System.out.print("~");
            }
                System.out.println();
        }

        System.out.println();
    }

    public static void printSolutionJSON(Board board){
        System.out.println("{\n\"data\":[");

        int length = board.getLength();

        for (int i = 0; i < length; i++) {
            System.out.print("[");
            for (int j = 0; j < length; j++) {
                if (board.get(i, j) == 1)
                    System.out.print("1");
                else
                    System.out.print("0");

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
