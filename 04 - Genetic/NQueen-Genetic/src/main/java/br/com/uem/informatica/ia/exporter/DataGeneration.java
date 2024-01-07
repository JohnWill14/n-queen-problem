package br.com.uem.informatica.ia.exporter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DataGeneration {
    private int numberGeneration;
    private double average;
    private int bestFitness;


}
