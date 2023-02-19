package Consola;

import Modulo.Combo;
import Modulo.Ingrediente;
import Modulo.Pedido;
import Modulo.ProductoMenu;
import Modulo.Restaurante;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author johan
 */
public class Aplicacion {

    Restaurante restaurante = new Restaurante();

    public void ejecutarAplicacion() {
        System.out.println("Bienvenido(a) a Burger and Chips");

        boolean continuar = true;
        while (continuar) {
            try {
                mostrarMenu();
                int opcion_seleccionada = Integer.parseInt(input("\nPor favor selecciona una opción:\n"));
                if (opcion_seleccionada == 1) {
                    ejecutarCargarAlimentos();
                } else if (opcion_seleccionada == 2 && restaurante != null) {
                    ejecutarNuevoPedido();
                } else if (opcion_seleccionada == 3 && restaurante != null) {
                    ejecutarBuscarPorId();
                } else if (opcion_seleccionada == 4 && restaurante != null) {
                    System.out.println("Saliendo de la aplicación ...");
                    continuar = false;
                } else if (restaurante == null) {
                    System.out.println("\nTe recomendamos mirar primero el menú.");
                } else {
                    System.out.println("\nPor favor seleccione una opción válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Porfavor selecciona una opción válida.");
            }
        }
    }

    public void mostrarMenu() {
        System.out.println("\n¿En que te podemos colaborar?\n");
        System.out.println("1. Mostrar el menú");
        System.out.println("2. Iniciar un nuevo pedido");
        System.out.println("3. Consultar la información de un pedido dado su id");
        System.out.println("4. Salir de la aplicación");

    }

    //se crea para poder hacer que el usuario lea y ponga una opcion
    public String input(String mensaje) {
        try {
            System.out.print(mensaje + "\nOpción: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error leyendo de la consola");
        }
        return null;
    }

    //mostrar menu al usuario
    
    private void ejecutarCargarAlimentos() {

        File archivoIngredientes = new File("data/ingredientes.txt");
        File archivoMenu = new File("data/menu.txt");
        File archivoCombos = new File("data/combos.txt");

        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
        int iterador = 1;
        System.out.println("PRODUCTOS BASICOS");

        for (ProductoMenu productoMenu : restaurante.getProductoMenuList()) {
            System.out.println(Integer.toString(iterador) + ". " + productoMenu.getNombre() + ": $" + productoMenu.getPrecio());
            iterador += 1;
        }
       
        System.out.println("ADICIONES (costo adicional)");

        for (Ingrediente ingrediente : restaurante.getIngredientes()) {
            System.out.println(Integer.toString(iterador) + ". " + ingrediente.getNombre() + ": $" + ingrediente.getCostoAdicional());
            iterador += 1;
        }
        
        System.out.println("COMBOS");

        for (Combo combo : restaurante.getComboList()) {
            System.out.println(Integer.toString(iterador) + ". " + combo.getNombre() + ": $" + combo.getPrecio());
            iterador += 1;}

    }
    
    private void ejecutarBuscarPorId(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del pedido que quiera buscar (entero)");
        int id = scanner.nextInt();
        System.out.println("Buscando ID " + id + "...");
        Pedido buscado = restaurante.consultarPedidoId(id);
        System.out.println("El pedido con ID "+id+" fue encontrado bajo los datos: "+ buscado.toString());
       
        
    }  
    private void ejecutarNuevoPedido(){
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Iniciando nuevo pedido...");
        System.out.println("Por favor digite su nombre: ");
        
        String nombre = scanner.nextLine();
        System.out.println("Por favor digite la direccion del pedido: ");
        String direccion = scanner.nextLine();
        restaurante.iniciarPedido(nombre, direccion);
        System.out.println("Pedido creado correctamente,\nPor favor seleccione los productos que deseea agregar:");        
        
        
    
    
    
    
    
    }

    //main para comenzar aplicación
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Aplicacion consola = new Aplicacion();
        consola.ejecutarAplicacion();
    }

}
