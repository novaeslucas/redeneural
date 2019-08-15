package com.novaeslucas.redeneural;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        NeuralNetwork nn = new NeuralNetwork(10,10,1);
        int numOfInputsTests = 10000;
        double[][] inputsTests = getInputsTests(numOfInputsTests);
//        System.out.println();
        for (int i = 0; i < 100000; i++){
            int index = (int) Math.floor(Math.random()*704);
//            System.out.print("index: " + index + " | iteracao num. " + i + " | ");
//            System.out.print("input: ");
//            for (double d : getInputs()[index]){
//                System.out.print((int) d + ";");
//            }
//            System.out.print(" | output: ");
//            for (double d : getOutputs()[index]){
//                System.out.print(d + " ");
//                System.out.println();
//            }
            nn.train(getInputs()[index], getOutputs()[index]);
        }
//        System.out.println();
//        System.out.println("Testando entradas aleatórias");
        double[] results = new double[numOfInputsTests];
        for (int i = 0; i < numOfInputsTests; i++) {
//            for (double d : inputsTests[i]){
//                System.out.print((int) d + ";");
//            }
//            System.out.print(" Saída ");
            results[i] = nn.predict(inputsTests[i])[0];
//            System.out.println();
        }
        new Graph(results);
        long time2 = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("mm:ss").format(new Date(time2 - time1)));

    }

    private static double[][] getInputsTests(int numOfInputsTests){
        double[][] tests = new double[numOfInputsTests][10];
        for (int i = 0; i < numOfInputsTests; i++){
            for (int j = 0; j < 10; j++){
                tests[i][j] = Math.random()*10 > 5 ? 1.0 : 0.0;
            }
        }
        return tests;
    }

    private static double[][] getInputs(){
        double[][] inputs = new double[704][10];
        try{
            BufferedReader br = new BufferedReader(new FileReader("autism_train_set.csv"));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                for (int j = 0; j < 9; j++){
                    inputs[i][j] = Double.parseDouble(line.split(";")[j]);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    private static double[][] getOutputs(){
        double[][] outputs = new double[704][1];
        try{
            BufferedReader br = new BufferedReader(new FileReader("autism_output.csv"));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                for (int j = 0; j < 1; j++){
                    outputs[i][j] = Double.parseDouble(line.split(";")[j]);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputs;
    }
}
