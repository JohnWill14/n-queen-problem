package br.com.uem.informatica.ia.population.chromosome;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Population {
    private List<Chromosome> chromosomes;

    private Chromosome solver = null;

    private double average;

    private int generationNumberGeneration;

    public Population(){
        chromosomes = new ArrayList<>();
        average = 0;
        generationNumberGeneration = 0;
    }

    public Chromosome getSolver() {
        return solver;
    }

    public double getAverage(){
        return average;
    }

    public int getNumberGeneration() {
        return generationNumberGeneration;
    }

    public void initPopulation(int length, int number){
        for(int i =0;i<number;i++){
            Chromosome chromosome = new Chromosome(length);

            for(int j=0;j<length;j++){
                chromosome.moveQueen(ThreadLocalRandom.current().nextInt(length), j);
            }
            applyMinimalConflicts(chromosome);
            chromosomes.add(chromosome);

        }
        generationNumberGeneration++;
    }

    public void calculateFitnessForPopulation(){
        average = 0;

        for(Chromosome c:chromosomes){
            average+= c.fitness();
        }

        Collections.sort(chromosomes);

        average = average/(chromosomes.size()*1.0);

        Chromosome first = getBestIndividual();

        if(first.fitness() == 0){
            solver = first;
        }

    }

    public boolean foundTheSolution(){
        return solver != null;
    }

    public void crossover(){
        // selection
        int numberOfSelected = (int) (chromosomes.size()*0.6d);

        List<Chromosome> selecteds = new ArrayList<>();

        for(int i=0;i<numberOfSelected;i++){
            selecteds.add(chromosomes.get(i));
        }

        // rolette
        List<Chromosome> crossovers = new ArrayList<>();

        for(int i=0;i<numberOfSelected && !selecteds.isEmpty();i++){
            Chromosome rolette = rolette(selecteds);
            crossovers.add(rolette);
            selecteds.remove(rolette);
        }

        // crossover
        int positionInArray = 0;

        while(crossovers.size()-positionInArray>1){
            Chromosome father = crossovers.get(positionInArray);
            positionInArray++;
            Chromosome mother = crossovers.get(positionInArray);
            positionInArray++;


            int locus = ThreadLocalRandom.current().nextInt(0, father.getLength());


            Chromosome f1 = father.clone();
            Chromosome f2 = mother.clone();

            for(int i = 0; i<locus; i++){
                f1.moveQueen(mother.getQueen(i) ,i);
            }

            for(int i = locus; i< mother.getLength(); i++){
                f2.moveQueen(father.getQueen(i) ,i);
            }


            chromosomes.add(f1);

            chromosomes.add(f2);

            if(ThreadLocalRandom.current().nextInt(100)<=6){
                Chromosome f3 = f1.clone();
                Chromosome f4 = f2.clone();

                applyMinimalConflicts(f3);

                chromosomes.add(f3);

                applyMinimalConflicts(f4);

                chromosomes.add(f4);
            }
        }
        generationNumberGeneration+=1;
    }

    public void applyMinimalConflicts(Chromosome chromosome){

        for(int col =0; col<chromosome.getLength();col++) {

            int mini = Integer.MAX_VALUE;
            int pos = 0;

            for (int i = 0; i < chromosome.getLength(); i++) {
                int conflicts = chromosome.countConflictRow(i, col) + chromosome.countConflictDiagonal(i, col);
                if (mini > conflicts) {
                    pos = i;
                    mini = conflicts;
                }
            }
            chromosome.moveQueen(pos, col);
        }
    }

    private Chromosome rolette(List<Chromosome> selecteds){
        int total = selecteds.stream().mapToInt(Chromosome::fitness).sum();
        double doubleRandom = ThreadLocalRandom.current().nextDouble(0, 1);
        Iterator<Chromosome> iterator = selecteds.iterator();

        Chromosome chromosome = iterator.next();

        double accumulatedProbability = chromosome.fitness()/(total*1d);

        for (Chromosome chromosomeIterator ; iterator.hasNext(); ){
            chromosomeIterator = iterator.next();

            if(Double.compare(doubleRandom, accumulatedProbability)<0){
                break;
            }

            accumulatedProbability += chromosomeIterator.fitness()/(total*1d);
            chromosome = chromosomeIterator;
        }

        return chromosome;
    }
    public void applyMortalityRate(int limit) {
        int size = this.chromosomes.size();

           while(size > limit){
               chromosomes.remove(chromosomes.size() - 1);
             size -= 1;
        }

    }

    public int size(){
        return chromosomes.size();
    }

    public  void showAllPopulation(){
        System.out.println("Generation Number Generation: "+generationNumberGeneration+"\nAverage: "+String.format("%.2f", average));
        for(Chromosome c:chromosomes){
            System.out.println(c);
        }
    }

    public Chromosome getBestIndividual(){
        return chromosomes.get(0);
    }
}
