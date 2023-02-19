
package Modulo;

import java.util.ArrayList;

/**
 *
 * @author johan
 */
public class ProductoAjustado implements Producto {
    
    private ProductoMenu productoBase;
    private ArrayList<Ingrediente> agregados = new ArrayList<>();
    private ArrayList<Ingrediente> eliminados = new ArrayList<>();
    private String nombre = productoBase.getNombre() +" modificado";
    

    public ProductoAjustado(ProductoMenu productoBase) {
        this.productoBase = productoBase;
    }
    
    public void AgregarIngredienteExtra(Ingrediente extra){
        this.agregados.add(extra);
    }
    
    public void EliminarIngrediente(Ingrediente eliminado){
        this.eliminados.add(eliminado);
    }

    @Override
    public int getPrecio(){
        int sumaPrecios = 0;
        for (Ingrediente producto : this.agregados) {
            sumaPrecios += producto.getCostoAdicional();
    }
        sumaPrecios = sumaPrecios + productoBase.getPrecio();
        return (int) sumaPrecios;
    }
    
    @Override
    public String getNombre(){
        return this.nombre;
    }
    
    public String generarTextoFactura(){
    String text = ""; 
    return text;
    }
        
}


