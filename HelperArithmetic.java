package mlr;

import javax.swing.JOptionPane;

public class HelperArithmetic {
    DataSet DS = new DataSet();

    double[][] X = DS.getMatrizX();
    double[][] Y = DS.getMatrizY();

    double transpuesta[][];
    double producto[][];
    double inversa[][];
    int registrosX;

    public HelperArithmetic() {
    }

    public void MatrizX(int registros, int columnas) {
        this.X = new double[registros][columnas];
        registrosX = 0;
    }

    public void MatrizY(int registros) {
        this.Y = new double[registros][1];
    }
    /*
     * public void NRegistroX(int numero_del_registro){
     * for(int i=0; i<=this.X[numero_del_registro].length-1; i++){
     * if(i==0){X[numero_del_registro][i]=1;}
     * else{
     * String num = JOptionPane.showInputDialog(null,"Registro "
     * +(numero_del_registro+1)+": Valor par el la columna X"+(i));
     * float valor=Float.parseFloat(num);
     * X[numero_del_registro][i]=valor;
     * }
     * }
     * }
     * 
     * public void NRegistroY(int numero_del_registro){
     * for(int i=0; i<this.Y.length; i++){
     * String num = JOptionPane.showInputDialog(null,"Valor para Y"+(i+1));
     * float valor=Float.parseFloat(num);
     * Y[i][0]=valor;
     * }
     * JOptionPane.showMessageDialog(null, "Matriz Llenada");
     * }
     */

    public double[][] TranspuestaMatriz(double[][] matriz) {
        this.transpuesta = new double[matriz[0].length][matriz.length];
        for (int i = 0; i <= matriz[0].length - 1; i++) {
            for (int j = 0; j <= matriz.length - 1; j++) {
                transpuesta[i][j] = matriz[j][i];
            }
        }
        return transpuesta;
    }

    public void ProductoMatriz(double[][] matriz1, double[][] matriz2) {
        this.producto = new double[matriz1.length][matriz2[0].length];
        for (int x = 0; x <= matriz1.length - 1; x++) {
            for (int y = 0; y <= matriz2[0].length - 1; y++) {
                double n = 0;
                for (int c = 0; c <= matriz1[0].length - 1; c++) {
                    n = n + matriz1[x][c] * matriz2[c][y];
                }
                producto[x][y] = n;
            }
        }
    }

    public void InversaMatriz(double[][] matriz) {
        // compiar matriz
        double inversa[][] = new double[matriz.length][matriz[0].length + 2];
        for (int i = 0; i <= matriz.length - 1; i++) {
            for (int j = 0; j <= matriz[0].length - 1; j++) {
                inversa[i][j] = matriz[i][j];
            }
        }

        inversa[0][3] = inversa[0][0];
        inversa[0][4] = inversa[0][1];

        inversa[1][3] = inversa[1][0];
        inversa[1][4] = inversa[1][1];

        inversa[2][3] = inversa[2][0];
        inversa[2][4] = inversa[2][1];

        // determinante
        double A, B, C, D, E, F;
        double determinante;
        A = inversa[0][0] * inversa[1][1] * inversa[2][2];
        B = inversa[0][1] * inversa[1][2] * inversa[2][3];
        C = inversa[0][2] * inversa[1][3] * inversa[2][4];

        D = inversa[0][4] * inversa[1][3] * inversa[2][2];
        E = inversa[0][3] * inversa[1][2] * inversa[2][1];
        F = inversa[0][2] * inversa[1][1] * inversa[2][0];

        determinante = (A + B + C - D - E - F);
        /*
         * System.out.print("\n");
         * System.out.print(determinante);
         * System.out.print("\n\n");
         * 
         * for (int i=0; i<inversa.length;i++){
         * //System.out.print("[");
         * for(int j=0; j<inversa[0].length;j++){
         * System.out.print(" "+String.format("%.3f",inversa[i][j])+" ");
         * }
         * System.out.print("\n");
         * }
         */
        double TranspuestaAux[][] = new double[matriz[0].length][matriz.length];
        for (int i = 0; i <= matriz[0].length - 1; i++) {
            for (int j = 0; j <= matriz.length - 1; j++) {
                TranspuestaAux[i][j] = matriz[j][i];
            }
        }
        /*
         * for (int i=0; i<TranspuestaAux.length;i++){
         * //System.out.print("[");
         * for(int j=0; j<TranspuestaAux[0].length;j++){
         * System.out.print(" "+String.format("%.3f",TranspuestaAux[i][j])+" ");
         * }
         * System.out.print("\n");
         * }
         * System.out.print("\n\n");
         */

        // adjunta
        double adj[][] = new double[matriz.length][matriz[0].length];
        double aux[][] = new double[2][2];

        int AuxCorA = 0;
        int AuxCorB = 0;
        // coordenadas de la adjunta
        for (int h = 0; h <= 2; h++) {
            for (int g = 0; g <= 2; g++) {
                for (int m = 0; m <= 2; m++) {
                    for (int n = 0; n <= 2; n++) {
                        if (m != h && n != g) {
                            if (AuxCorB <= 1) {
                                aux[AuxCorA][AuxCorB] = TranspuestaAux[m][n];
                                AuxCorB++;
                                AuxCorA = 0;
                            } else {
                                AuxCorA++;
                                AuxCorB = 0;
                                aux[AuxCorA][AuxCorB] = TranspuestaAux[m][n];
                                AuxCorB++;
                            }
                        }
                    }
                }
                AuxCorA = 0;
                AuxCorB = 0;
                // adj[h][g]= ((aux[0][0]*aux[1][1])-(aux[0][1]*aux[1][0]));
                if (h == 0 && g == 0)
                    adj[h][g] = (aux[0][0] * aux[1][1]) - (aux[0][1] * aux[1][0]);
                if (h == 0 && g == 1)
                    adj[h][g] = (-1) * ((aux[0][0] * aux[1][1]) - (aux[0][1] * aux[1][0]));
                if (h == 0 && g == 2)
                    adj[h][g] = (aux[0][0] * aux[1][1]) - (aux[0][1] * aux[1][0]);

                if (h == 1 && g == 0)
                    adj[h][g] = (-1) * ((aux[0][0] * aux[1][1]) - (aux[0][1] * aux[1][0]));
                if (h == 1 && g == 1)
                    adj[h][g] = (aux[0][0] * aux[1][1]) - (aux[0][1] * aux[1][0]);
                if (h == 1 && g == 2)
                    adj[h][g] = (-1) * ((aux[0][0] * aux[1][1]) - (aux[0][1] * aux[1][0]));

                if (h == 2 && g == 0)
                    adj[h][g] = (aux[0][0] * aux[1][1]) - (aux[0][1] * aux[1][0]);
                if (h == 2 && g == 1)
                    adj[h][g] = (-1) * ((aux[0][0] * aux[1][1]) - (aux[0][1] * aux[1][0]));
                if (h == 2 && g == 2)
                    adj[h][g] = (aux[0][0] * aux[1][1]) - (aux[0][1] * aux[1][0]);
            }
        }

        // Dividiendo la adjunta entre la determinanta
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                adj[i][j] = adj[i][j] / determinante;
            }
        }
        this.inversa = new double[adj.length][adj[0].length];
        for (int i = 0; i <= adj.length - 1; i++) {
            for (int j = 0; j <= adj[0].length - 1; j++) {
                this.inversa[i][j] = adj[i][j];
            }
        }
    }

    public boolean DoMLR() {

        TranspuestaMatriz(getMatrizX());
        ProductoMatriz(getTranspuesta(), getMatrizX());
        InversaMatriz(getProducto());
        ProductoMatriz(getTranspuesta(), getMatrizY());
        ProductoMatriz(getInversa(), getProducto());

        return true;
    }

    public String getFormula() {
        return "Formula: " + String.format("%.3f", this.producto[0][0]) + "+"
                + String.format("%.3f", this.producto[1][0])
                + "x1+" + String.format("%.3f", this.producto[2][0]) + "x2";
    }

    public String prediccion(float x1, float x2) {
        double pre = producto[0][0] + producto[1][0] * x1 + producto[2][0] * x2;
        return String.format("%.3f", pre);
    }

    public void printMatriz(double[][] x) {

        for (int i = 0; i < x.length; i++) {
            // System.out.print("[");
            for (int j = 0; j < x[0].length; j++) {
                System.out.print(" " + String.format("%.3f", x[i][j]) + " ");
            }
            System.out.print("\n");
        }
        // System.out.print("]\n");
    }

    public double[][] getMatrizX() {
        return X;
    }

    public double[][] getMatrizY() {
        return Y;
    }

    public double[][] getTranspuesta() {
        return transpuesta;
    }

    public double[][] getProducto() {
        return producto;
    }

    public double[][] getInversa() {
        return inversa;
    }

}