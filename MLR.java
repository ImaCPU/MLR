package mlr;

import javax.swing.JOptionPane;

public class MLR {

    public static void main(String[] args) {

        HelperArithmetic HA = new HelperArithmetic();
        boolean comando = HA.DoMLR();
        double[][] mlr = HA.getProducto();

        System.out.println(HA.getFormula());
        JOptionPane.showMessageDialog(null, HA.getFormula());

        float x1 = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingresa El Valor De X1: "));
        float x2 = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingresa El Valor De X2: "));

        System.out.println("Prediccion: " + HA.prediccion(x1, x2));
        JOptionPane.showMessageDialog(null, "Prediccion: " + HA.prediccion(x1, x2));
    }

}
