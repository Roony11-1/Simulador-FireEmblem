package utilidades;

import javax.swing.JOptionPane;
import java.awt.Component;

public class Utilidades 
{
    public static boolean validarRango(Component componente, int valor, int minimo, int maximo, String mensaje) 
    {
        if (valor < minimo || valor > maximo) {
            JOptionPane.showMessageDialog(componente, mensaje);
            return false;
        }
        return true;
    }
}
