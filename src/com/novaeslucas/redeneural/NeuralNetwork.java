package com.novaeslucas.redeneural;

public class NeuralNetwork {

    private int inputNodes;

    private int hiddenNodes;

    private int outputNodes;

    private Matrix biasInputHidden;

    private Matrix biasHiddenOutput;

    private Matrix weigthInputHidden;

    private Matrix weigthHiddenOutput;

    private double leaningRate;

    NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes){
        this.inputNodes = inputNodes;
        this.hiddenNodes = hiddenNodes;
        this.outputNodes = outputNodes;
        this.biasInputHidden = new Matrix(this.hiddenNodes, 1, true);
        this.biasHiddenOutput = new Matrix(this.outputNodes, 1, true);
        this.weigthInputHidden = new Matrix(this.hiddenNodes, this.inputNodes, true);
        this.weigthHiddenOutput = new Matrix(this.outputNodes, this.hiddenNodes, true);
        this.leaningRate = 0.1;
    }

    void backpropagation(double[][] inputs, double[][] target){
        System.out.println("Feedforward");
        System.out.println();
        System.out.println("INPUT -> HIDDEN");
        System.out.println();
        System.out.println("Input");
        Matrix input = new Matrix(inputs);
        System.out.println("Result multiply");
        Matrix hidden = Matrix.multiply(this.weigthInputHidden, input);
        hidden.print();
        System.out.println("Bias");
        hidden = Matrix.add(hidden, this.biasInputHidden);
        hidden.print();
        System.out.println("Activation function");
        runSigmoid(hidden);
        hidden.print();
        System.out.println();
        System.out.println("HIDDEN -> OUTPUT");
        System.out.println();
        System.out.println("Call multiply");
        Matrix output = Matrix.multiply(this.weigthHiddenOutput, hidden);
        System.out.println("Result multiply");
        output.print();
        System.out.println("BIAS");
        output = Matrix.add(output, this.biasHiddenOutput);
        output.print();
        System.out.println("Activation function");
        runSigmoid(output);
        output.print();
        System.out.println("END Feedforward");
        System.out.println();
        System.out.println("Backpropagation");
        System.out.println();

        System.out.println("Output -> Hidden");
        Matrix expected = new Matrix(target);

        System.out.println("Output error");
        Matrix outputError = Matrix.subtract(expected, output);
        outputError.print();

        System.out.println("Derivada output");
        Matrix derivadaOutput = runDsigmoid(output);
        derivadaOutput.print();

        System.out.println("Hidden Transpose");
        Matrix hiddenT = Matrix.transpose(hidden);
        hiddenT.print();

        System.out.println("Gradient");
        Matrix gradient = Matrix.hadamard(derivadaOutput, outputError);
        gradient = Matrix.escalarMultiply(gradient, this.leaningRate);
        gradient.print();

        System.out.println("Adjust bias o->h");
        this.biasHiddenOutput = Matrix.add(this.biasHiddenOutput, gradient);
        this.biasHiddenOutput.print();

        System.out.println("Adjust weigths o->h");
        Matrix weigthsHiddenOutputDeltas = Matrix.multiply(gradient, hiddenT);
        this.weigthHiddenOutput = Matrix.add(this.weigthHiddenOutput, weigthsHiddenOutputDeltas);
        this.weigthHiddenOutput.print();

        System.out.println();

        System.out.println("Hidden -> Input");
        Matrix weigthsHiddenOutputT = Matrix.transpose(this.weigthHiddenOutput);

        System.out.println("Hidden error");
        Matrix hiddenError = Matrix.multiply(weigthsHiddenOutputT, outputError);
        hiddenError.print();

        System.out.println("Derivada hidden");
        Matrix derivadaHidden = runDsigmoid(hidden);
        derivadaHidden.print();

        System.out.println("Input Transpose");
        Matrix inputT = Matrix.transpose(input);
        inputT.print();

        System.out.println("Hidden gradient");
        Matrix gradientHidden = Matrix.hadamard(derivadaHidden, derivadaHidden);
        gradientHidden = Matrix.escalarMultiply(gradientHidden, this.leaningRate);
        gradientHidden.print();

        System.out.println("Adjust bias i->h");
        this.biasInputHidden = Matrix.add(this.biasInputHidden, gradientHidden);
        this.biasInputHidden.print();

        System.out.println("Adjust weigths h->i");
        Matrix weigthsInputHiddenDeltas = Matrix.multiply(gradientHidden, inputT);
        this.weigthInputHidden = Matrix.add(this.weigthInputHidden, weigthsInputHiddenDeltas);
        this.weigthInputHidden.print();

        System.out.println();
        System.out.println("END");
    }

    static Matrix runSigmoid(Matrix m){
        for (int i = 0; i < m.getRows(); i++){
            for (int j = 0; j < m.getCols(); j++){
                m.getMatrix()[i][j] = sigmoidFunction(m.getMatrix()[i][j]);
            }
        }
        return m;
    }

    static double sigmoidFunction(double x){
        return 1 / (1 + Math.exp(-x));
    }

    static Matrix runDsigmoid(Matrix m){
        for (int i = 0; i < m.getRows(); i++){
            for (int j = 0; j < m.getCols(); j++){
                m.getMatrix()[i][j] = dSigmoidFunction(m.getMatrix()[i][j]);
            }
        }
        return m;
    }

    static double dSigmoidFunction(double x){
        return x * (1 - x);
    }

    public int getInputNodes() {
        return inputNodes;
    }

    public void setInputNodes(int inputNodes) {
        this.inputNodes = inputNodes;
    }

    public int getHiddenNodes() {
        return hiddenNodes;
    }

    public void setHiddenNodes(int hiddenNodes) {
        this.hiddenNodes = hiddenNodes;
    }

    public int getOutputNodes() {
        return outputNodes;
    }

    public void setOutputNodes(int outputNodes) {
        this.outputNodes = outputNodes;
    }

    public Matrix getBiasInputHidden() {
        return biasInputHidden;
    }

    public void setBiasInputHidden(Matrix biasInputHidden) {
        this.biasInputHidden = biasInputHidden;
    }

    public Matrix getBiasHiddenOutput() {
        return biasHiddenOutput;
    }

    public void setBiasHiddenOutput(Matrix biasHiddenOutput) {
        this.biasHiddenOutput = biasHiddenOutput;
    }

    Matrix getWeigthInputHidden() {
        return weigthInputHidden;
    }

    public void setWeigthInputHidden(Matrix weigthInputHidden) {
        this.weigthInputHidden = weigthInputHidden;
    }

    public Matrix getWeigthHiddenOutput() {
        return weigthHiddenOutput;
    }

    public void setWeigthHiddenOutput(Matrix weigthHiddenOutput) {
        this.weigthHiddenOutput = weigthHiddenOutput;
    }

    public double getLeaningRate() {
        return leaningRate;
    }

    public void setLeaningRate(double leaningRate) {
        this.leaningRate = leaningRate;
    }
}
