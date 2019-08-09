package com.novaeslucas.redeneural;

public class Main {

    public static void main(String[] args) {
//        NeuralNetwork nn = new NeuralNetwork(2,3,1);
//        System.out.println("Inputs");
        double[][] inputs = {{1.0,1.0},{1.0,0.0},{0.0,0.1},{0.0,0.0}};
//        print(inputs);
//        System.out.println("Target");
//        double[][] target = {{0.0},{1.0},{1.0},{0.0}};
//        print(target);
//
//        while (nn.predict(inputs)[0][0] > 0.001){
//            train(nn, inputs, target);
//        }
//        System.out.println("output");
//        print(nn.predict(inputs));
        int index = (int) Math.floor(Math.random()*4);
        System.out.println(index);
        print(inputs[index]);
    }

    static void train(NeuralNetwork nn, double[][] inputs, double[][] target){
        for (int i = 0; i < 10000; i++){
            int index = (int) Math.floor(Math.random()*4);
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

    static void print(double[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.print( arr[i] + "\t" );
        }
    }
}
