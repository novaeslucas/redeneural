package com.novaeslucas.redeneural;

class Matrix {

    private int rows;

    private int cols;

    private double[][] matrix;

    Matrix(int rows, int cols, boolean randomize){
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[this.rows][this.cols];
        if(randomize){
            for (int i = 0; i < this.rows; i++){
                for (int j = 0; j < this.cols; j++){
                    matrix[i][j] = Math.random()*2-1;
                }
            }
        }else{
            for (int i = 0; i < this.rows; i++){
                for (int j = 0; j < this.cols; j++){
                    matrix[i][j] = 0.0;
                }
            }
        }
    }

    static Matrix arrayToMatrix(double[] arr){
        Matrix matrix = new Matrix(arr.length, 1, false);
        for (int i = 0; i < matrix.getRows(); i++){
            for (int j = 0; j < matrix.getCols(); j++){
                matrix.matrix[i][j] = arr[i];
            }
        }
        return matrix;
    }

    static double[] matrixToArray(Matrix matrix){
        double[] arr = new double[matrix.getRows()*matrix.getCols()];
        int k = 0;
        for (int i = 0; i < matrix.getRows(); i++){
            for (int j = 0; j < matrix.getCols(); j++){
                arr[k] = matrix.matrix[i][j];
                k++;
            }
        }
        return arr;
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
        Matrix m = new Matrix(a.getRows(), a.getCols(), false);
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                m.matrix[i][j] = a.matrix[i][j] + b.matrix[i][j];
            }
        }
        return m;
    }

    static Matrix multiply(Matrix a, Matrix b){
        Matrix m = new Matrix(a.getRows(), b.getCols(), false);
        for (int i = 0; i < a.getRows(); i++){
            for (int j = 0; j < b.getCols(); j++){
                double sum = 0;
                for (int k = 0; k < a.getCols(); k++){
                    double product = a.matrix[i][k] * b.matrix[k][j];
                    sum += product;
                }
                m.matrix[i][j] = sum;
            }
        }
        return m;
    }

    static Matrix transpose(Matrix a){
        Matrix m = new Matrix(a.getCols(), a.getRows(), false);
        for (int i = 0; i < a.getCols(); i++){
            for (int j = 0; j < a.getRows(); j++){
                m.matrix[i][j] = a.matrix[j][i];
            }
        }
        return m;
    }

    static Matrix escalarMultiply(Matrix a, double escalar){
        Matrix m = new Matrix(a.getRows(), a.getCols(), false);
        for (int i = 0; i < a.getRows(); i++){
            for (int j = 0; j < a.getCols(); j++){
                m.matrix[i][j] = a.matrix[i][j] * escalar;
            }
        }
        return m;
    }

    static Matrix hadamard(Matrix a, Matrix b){
        Matrix m = new Matrix(a.getRows(), a.getCols(), false);
        for (int i = 0; i < a.getRows(); i++){
            for (int j = 0; j < a.getCols(); j++){
                m.matrix[i][j] = a.getMatrix()[i][j] * b.getMatrix()[i][j];
            }
        }
        return m;
    }

    static Matrix subtract(Matrix a, Matrix b){
        Matrix m = new Matrix(a.getRows(), a.getCols(), false);
        for (int i = 0; i < a.getRows(); i++){
            for (int j = 0; j < a.getCols(); j++){
                m.matrix[i][j] = a.getMatrix()[i][j] - b.getMatrix()[i][j];
            }
        }
        return m;
    }

    int getRows() {
        return rows;
    }

    int getCols() {
        return cols;
    }

    double[][] getMatrix() {
        return matrix;
    }
}
