package br.com.uem.informatica.ia.exporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExporterPopulationCsvHandler {
    public List<String[]> getDatasForFileCSVFromPopulation(List<DataGeneration> datas){
        List<String[]> ans = new ArrayList<>();

        for(DataGeneration data : datas){
            String[] str = new String[3];

            str[0] = Integer.toString(data.getNumberGeneration());
            str[1] = String.format("%f", data.getAverage());
            str[2] = Integer.toString(data.getBestFitness());

            ans.add(str);
        }

        return ans;
    }

    public String[] getHeader(){
        return new String[] {
            "Number", "Average fitness", "best fitness"
        };
    }
}
