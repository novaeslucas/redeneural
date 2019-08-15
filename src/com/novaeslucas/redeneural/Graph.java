package com.novaeslucas.redeneural;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class Graph {

    Graph(double[] results){
        XYSeries autism = new XYSeries("Autistas");
        XYSeries notAutism = new XYSeries("Não autistas");
        for (int i = 0; i < results.length; i++){
            if(results[i] > 0.5){
                autism.add(i, results[i]);
            }else{
                notAutism.add(i, results[i]);
            }
        }
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(autism);
        collection.addSeries(notAutism);

        JFreeChart grafico = ChartFactory.createScatterPlot("Classificação de autismo em adultos", "Questionario",
                "Saída da rede", collection, PlotOrientation.VERTICAL, true, true, false);
        try {
            OutputStream arquivo = new FileOutputStream("grafico.png");
            ChartUtilities.writeChartAsPNG(arquivo, grafico, 1280, 720);
            arquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
