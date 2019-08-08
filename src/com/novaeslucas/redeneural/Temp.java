package com.novaeslucas.redeneural;

public class Temp {

    //1 - Criar matriz com numeros randomicos
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(2,3,2);
        System.out.println("Bias input hidden");
        nn.getBiasInputHidden().print();
        System.out.println("Bias hidden output");
        nn.getBiasHiddenOutput().print();
        System.out.println("Weigth input hidden");
        nn.getWeigthInputHidden().print();
        System.out.println("Weigth hidden output");
        nn.getBiasHiddenOutput().print();
        System.out.println("Inputs");
        double[][] inputs = {{1.0},{1.0}};
        print(inputs);
        System.out.println("Target");
        double[][] target = {{0.0},{0.0}};
        print(target);
        nn.backpropagation(inputs, target);
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
