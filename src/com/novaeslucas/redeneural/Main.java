package com.novaeslucas.redeneural;

public class Main {

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(2,1000,1);
        //INPUTS
        double[][] inputs = {{1.0,1.0},{1.0,0.0},{0.0,0.1},{0.0,0.0}};
        //TARGETS
        double[][] target = {{0.0},{1.0},{1.0},{0.0}};

        double[] arr1 = {0.0,0.0};
        double[] arr2 = {1.0,0.0};
        double[] arr3 = {1.0,1.0};
        double[] arr4 = {0.0,1.0};

        for (int i = 0; i < 100000; i++){
            int index = (int) Math.floor(Math.random()*4);
            nn.train(inputs[index], target[index]);
        }
        System.out.println(nn.predict(arr1)[0]);
    }
}
