package com.novaeslucas.redeneural;

class NeuralNetwork {

    private Matrix biasInputHidden;

    private Matrix biasHiddenOutput;

    private Matrix weigthInputHidden;

    private Matrix weigthHiddenOutput;

    private double leaningRate;

    NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes){
        this.biasInputHidden = new Matrix(hiddenNodes, 1, true);
        this.biasHiddenOutput = new Matrix(outputNodes, 1, true);
        this.weigthInputHidden = new Matrix(hiddenNodes, inputNodes, true);
        this.weigthHiddenOutput = new Matrix(outputNodes, hiddenNodes, true);
        this.leaningRate = 0.1;
    }

    void train(double[] arr, double[] target){
        //INPUT -> HIDDEN
        Matrix input = Matrix.arrayToMatrix(arr);
        Matrix hidden = Matrix.multiply(this.weigthInputHidden, input);
        hidden = Matrix.add(hidden, this.biasInputHidden);

        runSigmoid(hidden);

        //HIDDEN -> OUTPUT
        Matrix output = Matrix.multiply(this.weigthHiddenOutput, hidden);
        output = Matrix.add(output, this.biasHiddenOutput);
        runSigmoid(output);

        //BACKPROPAGATION
        //OUTPUT -> HIDDEN
        Matrix expected = Matrix.arrayToMatrix(target);
        Matrix outputError = Matrix.subtract(expected, output);
        Matrix derivadaOutput = runDsigmoid(output);
        Matrix hiddenT = Matrix.transpose(hidden);
        Matrix gradient = Matrix.hadamard(derivadaOutput, outputError);
        gradient = Matrix.escalarMultiply(gradient, this.leaningRate);

        //Adjust bias o->h
        this.biasHiddenOutput = Matrix.add(this.biasHiddenOutput, gradient);

        //Adjust weigths o->h
        Matrix weigthsHiddenOutputDeltas = Matrix.multiply(gradient, hiddenT);
        this.weigthHiddenOutput = Matrix.add(this.weigthHiddenOutput, weigthsHiddenOutputDeltas);

        //HIDDEN -> INPUT
        Matrix weigthsHiddenOutputT = Matrix.transpose(this.weigthHiddenOutput);
        Matrix hiddenError = Matrix.multiply(weigthsHiddenOutputT, outputError);
        Matrix derivadaHidden = runDsigmoid(hidden);
        Matrix inputT = Matrix.transpose(input);
        Matrix gradientHidden = Matrix.hadamard(derivadaHidden, hiddenError);
        gradientHidden = Matrix.escalarMultiply(gradientHidden, this.leaningRate);

        //Adjust bias i->h
        this.biasInputHidden = Matrix.add(this.biasInputHidden, gradientHidden);
        //Adjust weigths h->i
        Matrix weigthsInputHiddenDeltas = Matrix.multiply(gradientHidden, inputT);
        this.weigthInputHidden = Matrix.add(this.weigthInputHidden, weigthsInputHiddenDeltas);
    }

    double[] predict(double[] arr){
        //INPUT -> HIDDEN
        Matrix input = Matrix.arrayToMatrix(arr);
        Matrix hidden = Matrix.multiply(this.weigthInputHidden, input);
        hidden = Matrix.add(hidden, this.biasInputHidden);

        runSigmoid(hidden);

        //HIDDEN -> OUTPUT
        Matrix output = Matrix.multiply(this.weigthHiddenOutput, hidden);
        output = Matrix.add(output, this.biasHiddenOutput);
        runSigmoid(output);
//        output.print();

        return Matrix.matrixToArray(output);
    }

    private static void runSigmoid(Matrix m){
        for (int i = 0; i < m.getRows(); i++){
            for (int j = 0; j < m.getCols(); j++){
                m.getMatrix()[i][j] = sigmoidFunction(m.getMatrix()[i][j]);
            }
        }
    }

    private static double sigmoidFunction(double x){
        return 1 / (1 + Math.exp(-x));
    }

    private static Matrix runDsigmoid(Matrix m){
        for (int i = 0; i < m.getRows(); i++){
            for (int j = 0; j < m.getCols(); j++){
                m.getMatrix()[i][j] = dSigmoidFunction(m.getMatrix()[i][j]);
            }
        }
        return m;
    }

    private static double dSigmoidFunction(double x){
        return x * (1 - x);
    }
}
