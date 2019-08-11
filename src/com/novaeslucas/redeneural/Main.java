package com.novaeslucas.redeneural;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(10,3,1);
        double[] inputTest = {Math.random()*10 > 5 ? 1.0 : 0.0,Math.random()*10 > 5 ? 1.0 : 0.0,Math.random()*10 > 5 ? 1.0 : 0.0,Math.random()*10 > 5 ? 1.0 : 0.0,Math.random()*10 > 5 ? 1.0 : 0.0,Math.random()*10 > 5 ? 1.0 : 0.0,Math.random()*10 > 5 ? 1.0 : 0.0,Math.random()*10 > 5 ? 1.0 : 0.0,Math.random()*10 > 5 ? 1.0 : 0.0,Math.random()*10 > 5 ? 1.0 : 0.0};
        for (double v : inputTest) {
            System.out.print(v + " ");
        }
        System.out.println();
        for (int i = 0; i < 100000; i++){
            int index = (int) Math.floor(Math.random()*4);
            nn.train(getInputs()[index], getOutputs()[index]);
        }
        System.out.println(nn.predict(inputTest)[0]);
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
