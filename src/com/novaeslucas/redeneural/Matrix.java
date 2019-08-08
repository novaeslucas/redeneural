package com.novaeslucas.redeneural;

public class Matrix {

    private int rows;

    private int cols;

    private double[][] matrix;

    Matrix(int rows, int cols, boolean randomize){
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[this.rows][this.cols];
        if(randomize){
            this.randomize();
        }else{
            this.create();
        }
    }

    Matrix(double[][] arr){
        this.rows = arr.length;
        this.cols = arr[0].length;
        this.matrix = new double[this.rows][this.cols];
        this.createFromArray(arr);
    }

    private void randomize(){
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.cols; j++){
                this.matrix[i][j] = Math.random()*2-1;
            }
        }
    }

    private void createFromArray(double[][] arr){
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.cols; j++){
                this.matrix[i][j] = arr[i][j];
            }
        }
    }

    private void create(){
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.cols; j++){
                this.matrix[i][j] = Math.floor(Math.random()*10);
            }
        }
    }

    void print(){
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.cols; j++){
                System.out.print( matrix[i][j] + "\t" );
            }
            System.out.println();
        }
    }

    static Matrix add(Matrix a, Matrix b){
        double[][] result = new double[a.getRows()][a.getCols()];
        for (int i = 0; i < a.getRows(); i++){
            for (int j = 0; j < a.getCols(); j++){
                result[i][j] = a.matrix[i][j] + b.matrix[i][j];
            }
        }
        return new Matrix(result);
    }

    static Matrix multiply(Matrix a, Matrix b){
        double[][] result = new double[a.getRows()][b.getCols()];
        for (int i = 0; i < a.getRows(); i++){
            for (int j = 0; j < b.getCols(); j++){
                double sum = 0;
                for (int k = 0; k < a.getCols(); k++){
                    double product = a.matrix[i][k] * b.matrix[k][j];
                    sum += product;
                }
                result[i][j] = sum;
            }
        }
        return new Matrix(result);
    }

    static Matrix transpose(Matrix a){
        double[][] result = new double[a.getCols()][a.getRows()];
        for (int i = 0; i < a.getCols(); i++){
            for (int j = 0; j < a.getRows(); j++){
                result[i][j] = a.getMatrix()[j][i];
            }
        }

        return new Matrix(result);
    }

    static Matrix escalarMultiply(Matrix a, double escalar){
        double[][] result = new double[a.getRows()][a.getCols()];
        for (int i = 0; i < a.getRows(); i++){
            for (int j = 0; j < a.getCols(); j++){
                result[i][j] = a.getMatrix()[i][j] * escalar;
            }
        }

        return new Matrix(result);
    }

    static Matrix hadamard(Matrix a, Matrix b){
        double[][] result = new double[a.getRows()][a.getCols()];
        for (int i = 0; i < a.getRows(); i++){
            for (int j = 0; j < a.getCols(); j++){
                result[i][j] = a.getMatrix()[i][j] * b.getMatrix()[i][j];
            }
        }

        return new Matrix(result);
    }

    static Matrix subtract(Matrix a, Matrix b){
        double[][] result = new double[a.getRows()][a.getCols()];
        for (int i = 0; i < a.getRows(); i++){
            for (int j = 0; j < a.getCols(); j++){
                result[i][j] = a.getMatrix()[i][j] - b.getMatrix()[i][j];
            }
        }

        return new Matrix(result);
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
}
