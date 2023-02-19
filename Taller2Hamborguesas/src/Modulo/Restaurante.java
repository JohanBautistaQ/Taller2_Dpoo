package Modulo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author johan bautista
 */
public class Restaurante {

    public Restaurante() {
    }

    private ArrayList<Ingrediente> ingredientes = new ArrayList<>();
    private HashMap<String, Ingrediente> ingredientesHash = new HashMap<>();
    private HashMap<String, ProductoMenu> ProductoMenuHash = new HashMap<>();
    private ArrayList<ProductoMenu> ProductoMenuList = new ArrayList<>();
    private ArrayList<Combo> ComboList = new ArrayList<>();
    private HashMap<String, Pedido> PedidosHash = new HashMap<>();

    public void iniciarPedido(String nombreCliente, String direccionCliente) {
        Pedido pedido = new Pedido(nombreCliente, direccionCliente);
        PedidosHash.put(Integer.toString(pedido.getIdPedido()), pedido);

    }

    public void cerrarYGuardarPedido() {
    }

    public Pedido consultarPedidoId(int id) {
        Pedido pedido = PedidosHash.get(String.valueOf(id));
        if (pedido != null) {
            return pedido;
        } else {
            return null;
        }

    }

    public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) {
        this.cargarIngredientes(archivoIngredientes);
        this.cargarMenu(archivoMenu);
        this.cargarCombos(archivoCombos);
    }

    private void cargarIngredientes(File archivoIngredientes) {

        try {
            FileReader lector = new FileReader(archivoIngredientes);
            BufferedReader buffer = new BufferedReader(lector);

            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] partes = linea.split(";");
                String nombre = partes[0];
                int costoAdicional = Integer.parseInt(partes[1]);
                Ingrediente ingrediente = new Ingrediente(nombre, costoAdicional);
                ingredientesHash.put(nombre, ingrediente);
                ingredientes.add(ingrediente);
            }

            buffer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void cargarMenu(File archivoMenu) {

        try {
            FileReader lector = new FileReader(archivoMenu);
            BufferedReader buffer = new BufferedReader(lector);

            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] partes = linea.split(";");
                String nombre = partes[0];
                int precioBase = Integer.parseInt(partes[1]);
                ProductoMenu productoMenu = new ProductoMenu(nombre, precioBase);
                ProductoMenuHash.put(nombre, productoMenu);
                ProductoMenuList.add(productoMenu);
            }

            buffer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void cargarCombos(File archivoCombos) {

        try {
            FileReader lector = new FileReader(archivoCombos);
            BufferedReader buffer = new BufferedReader(lector);

            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] partes = linea.split(";");
                String nombre = partes[0];
                double descuento = Double.parseDouble(partes[1].replace("%", ""));
                ProductoMenu basic1 = ProductoMenuHash.get(partes[2]);
                ProductoMenu basic2 = ProductoMenuHash.get(partes[3]);
                ProductoMenu basic3 = ProductoMenuHash.get(partes[4]);
                ArrayList<ProductoMenu> ingredientes = new ArrayList<ProductoMenu>();
                ingredientes.add(basic1);
                ingredientes.add(basic2);
                ingredientes.add(basic3);

                Combo combo = new Combo(nombre, descuento, ingredientes);
                ComboList.add(combo);
            }

            buffer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public HashMap<String, Ingrediente> getIngredientesHash() {
        return ingredientesHash;
    }

    public HashMap<String, ProductoMenu> getProductoMenuHash() {
        return ProductoMenuHash;
    }

    public ArrayList<ProductoMenu> getProductoMenuList() {
        return ProductoMenuList;
    }

    public ArrayList<Combo> getComboList() {
        return ComboList;
    }

}
