package br.com.uem.informatica.ia;

import br.com.uem.informatica.ia.exporter.DataGeneration;
import br.com.uem.informatica.ia.exporter.ExporterHandler;
import br.com.uem.informatica.ia.exporter.ExporterPopulationCsvHandler;
import br.com.uem.informatica.ia.population.chromosome.Chromosome;
import br.com.uem.informatica.ia.population.chromosome.Population;
import br.com.uem.informatica.ia.util.IOUtil;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static int n = 0;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        int limitPopulation = 100;

        Population population = new Population();
        List<DataGeneration> dataGenerations = new LinkedList<>();

        population.initPopulation(n, 20);

        int contBreak = 0;
        double history = 0;

        long start = System.currentTimeMillis();

        while(contBreak<1000 && !population.foundTheSolution()){
            population.calculateFitnessForPopulation();
            System.out.println("geracao nº: "+population.getNumberGeneration()+" fitness: "+String.format("%.2f",population.getAverage())+" best individual "+population.getBestIndividual());
            dataGenerations.add(getStatisticFromPopulation(population));

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

        exportInfo(dataGenerations);
    }

    private static void exportInfo(List<DataGeneration> dataGenerationsStatistic    ) {
        ExporterHandler exporterHandler = new ExporterHandler();
        ExporterPopulationCsvHandler exportPopulation = new ExporterPopulationCsvHandler();
        List<String[]> datas = exportPopulation.getDatasForFileCSVFromPopulation(dataGenerationsStatistic);

        String desktopPath = System.getProperty("user.home") + File.separator +"Desktop"+ File.separator;
        String file = desktopPath+"caso_de_teste_"+n+"X_"+n+ LocalDateTime.now() +".csv";

        exporterHandler.writeList(file, exportPopulation.getHeader(), datas);

        System.out.println("infos salvas em "+file);
    }

    private static DataGeneration getStatisticFromPopulation(Population populationInstance) {
        Chromosome bestChromosome = populationInstance.getBestIndividual();
        return DataGeneration.builder()
                .numberGeneration(populationInstance.getNumberGeneration())
                .average(populationInstance.getAverage())
                .bestFitness(bestChromosome.fitness())
                .build();
    }

}