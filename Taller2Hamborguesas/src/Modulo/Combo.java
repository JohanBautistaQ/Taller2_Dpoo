
package Modulo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author johan
 */
public class Combo implements Producto {
    
    private double descuento;
    private String nombreCombo;
    private ArrayList<ProductoMenu> BasicosCombo;

    public Combo(String nombreCombo, double descuento, ArrayList<ProductoMenu> BasicosCombo) {
        this.descuento = descuento;
        this.nombreCombo = nombreCombo;
        this.BasicosCombo = BasicosCombo;
    }
    
    @Override
    public String getNombre() {
    return this.nombreCombo;
    }

    @Override
    public int getPrecio(){
        double sumaPrecios = 0;
        for (ProductoMenu producto : this.BasicosCombo) {
            sumaPrecios += producto.getPrecio();
    }
        sumaPrecios = sumaPrecios - (sumaPrecios*this.descuento)/100;
        return (int) sumaPrecios;
    }

    public String generarTextoFactura(){
        String text = ""; 
        return text;
    }

    
}
