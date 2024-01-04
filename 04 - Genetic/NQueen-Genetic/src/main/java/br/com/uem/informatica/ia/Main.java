package br.com.uem.informatica.ia;

import br.com.uem.informatica.ia.population.chromosome.Chromosome;
import br.com.uem.informatica.ia.population.chromosome.Population;
import br.com.uem.informatica.ia.util.IOUtil;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int limitPopulation = 100;

        Population population = new Population();

        population.initPopulation(n, 20);

        int contBreak = 0;
        double history = 0;

        long start = System.currentTimeMillis();

        while(contBreak<1000 && !population.foundTheSolution()){
            population.calculateFitnessForPopulation();
            System.out.println("geracao nº: "+population.getNumberGeneration()+" fitness: "+String.format("%.2f",population.getAverage())+" best individual "+population.getBestIndividual());

            if(population.foundTheSolution()){
                break;
            }

            if(population.size() > limitPopulation){
                population.applyMortalityRate(limitPopulation);
            }

            if(contBreak == 0 || population.getAverage() == history){
                contBreak++;
                history= population.getAverage();
            }else{
                contBreak = 0;
            }

            population.crossover();
        }


        long end = System.currentTimeMillis();

        System.out.println("Tempo decorrido: "+(end-start)+" ms");


        if(population.foundTheSolution()){
            System.out.println("Solucao encontrada !!!");
            IOUtil.printSolutionTextAndJson(population.getSolver());
        }else{
            System.out.println("Não foi encontrada a solucao :(");
        }
    }

}