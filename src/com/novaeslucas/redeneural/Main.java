package com.novaeslucas.redeneural;

public class Main {

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(2,3,1);
        //INPUTS
        double[][] inputs = {{1.0,1.0},{1.0,0.0},{0.0,0.1},{0.0,0.0}};
        //TARGETS
        double[][] target = {{0.0},{1.0},{1.0},{0.0}};
        
        double[] arr1 = {0.0,0.0};
        double[] arr2 = {1.0,0.0};
        double predict1 = nn.predict(arr1)[0];
        System.out.println(predict1);
        double predict2 = nn.predict(arr2)[0];
        System.out.println(predict2);
        System.out.println();
        boolean train = true;
        int k = 0;
        while (train){
            for (int i = 0; i < 10000; i++){
                int index = (int) Math.floor(Math.random()*4);
                nn.train(inputs[index], target[index]);
            }
            if(nn.predict(arr1)[0] < 0.04 && nn.predict(arr2)[0] > 0.98){
                train = false;
                System.out.println("terminou iteracao " + k);
            }
            k++;
        }
    }
}
