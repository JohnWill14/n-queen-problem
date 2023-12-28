package br.com.uem.informatica.ia;

import br.com.uem.informatica.ia.population.chromosome.Chromosome;
import br.com.uem.informatica.ia.population.chromosome.Population;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int limitPopulation = 60;

        Population population = new Population();

        population.initPopulation(n, 20);

        int contBreak = 0;

        while(population.getNumberGeneration()<10000 && !population.foundTheSolution()){
            System.out.println("num: "+population.getNumberGeneration()+" fitness: "+String.format("%.2f",population.getAverage()));
            population.calculateFitnessForPopulation();

            if(population.foundTheSolution()){
                break;
            }

            if(population.size() > limitPopulation){
                population.applyMortalityRate(limitPopulation);
            }

            population.crossover();
        }


        population.showAllPopulation();
        if(population.foundTheSolution()){
            System.out.println("Solucao encontrada !!!");
        }else{
            System.out.println("Não foi encontrada a solucao :(");
        }

    }

}