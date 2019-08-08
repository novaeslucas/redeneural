package com.novaeslucas.redeneural;

public class Main {

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(4,3,1);
        System.out.println("Inputs");
        double[][] inputs = {{Math.random()},{Math.random()},{Math.random()},{Math.random()}};
        print(inputs);
        System.out.println("Target");
        double[][] target = {{0.0}};
        print(target);

        while (nn.predict(inputs)[0][0] > 0.001){
            train(nn, inputs, target);
        }
        System.out.println("output");
        print(nn.predict(inputs));
    }

    static void train(NeuralNetwork nn, double[][] inputs, double[][] target){
        for (int i = 0; i < 10000; i++){
            nn.train(inputs, target);
        }
    }

    static void print(double[][] arr){
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[0].length; j++){
                System.out.print( arr[i][j] + "\t" );
            }
            System.out.println();
        }
    }
}
