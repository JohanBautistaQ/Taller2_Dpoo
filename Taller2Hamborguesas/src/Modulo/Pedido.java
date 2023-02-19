package Modulo;

import java.util.ArrayList;

/**
 *
 * @author johan
 */
public class Pedido {

    private static int numeroPedidos = 0;
    private ArrayList<Producto> productos = new ArrayList<>();;
    private int idPedido;
    private String nombreCliente;
    private String direccionCliente;

    public Pedido(String nombreCliente, String direccionCliente) {
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.idPedido = numeroPedidos;
        Pedido.numeroPedidos += 1;
    }
    
    public void agregarProducto(Producto nuevoItem){
        productos.add(nuevoItem);
 
    }
    
    
    public int getIdPedido() {
        return idPedido;
    }
    
    private int  getPrecioTotalPedido(){     
        double sumaPrecios = 0;
        for (Producto producto : this.productos) {
            sumaPrecios += producto.getPrecio();
    }
        return (int) sumaPrecios;
    }

    private int getPrecioIVAPedido(){
    return (int)(this.getPrecioTotalPedido()*19)/100;
    }

    @Override
    public String toString() {
        return "Pedido{" + "nombreCliente=" + nombreCliente + ", direccionCliente=" + direccionCliente + '}';
    }
    
    
        
        
    }
    
