package app;

import model.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        System.out.println("------------------------------------------------------------------");
        System.out.println("            SIMULADOR ENTREGAS SINCRONIZADO SPEEDFAST             ");
        System.out.println("------------------------------------------------------------------");

        System.out.println("------------------[Zona de carga inicializada]--------------------");
        System.out.println();

        //Crea instancia de ZonaDeCarga
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        //Agrega los pedidos a la zona de carga
        zonaDeCarga.agregarPedido(new Pedido(1123, "Los Maitenes 987"));
        zonaDeCarga.agregarPedido(new Pedido(1224, "Los Alerces 143"));
        zonaDeCarga.agregarPedido(new Pedido(1325, "Alameda 456"));
        zonaDeCarga.agregarPedido(new Pedido(1426, "Los Carrera 321"));
        zonaDeCarga.agregarPedido(new Pedido(1527, "Manuel Rodríguez 654"));

        System.out.println();
        System.out.println("---------------------[Comenzando repartos]------------------------");
        System.out.println();

        //Crea el ExecutorService y los 3 hilos para los 3 repartidores
        ExecutorService executor = Executors.newFixedThreadPool(3);

        //Crea y ejecuta los repartidores
        executor.submit(new Repartidor("Alberto Castro", zonaDeCarga));
        executor.submit(new Repartidor("Miguel Contreras", zonaDeCarga));
        executor.submit(new Repartidor("Matias Navarro", zonaDeCarga));

        //No acepta nuevos repartos y pertime entregar los ya asignados
        executor.shutdown();

        //Espera a que los hilos terminen
        try {
            boolean finalizo = executor.awaitTermination(30, TimeUnit.SECONDS);

            if (finalizo) {
                System.out.println();
                System.out.println("-------[Todos los pedidos han sido entregados correctamente]------");

            } else {
                System.out.println();
                System.out.println("-----------------[Se agoto el tiempo de espera]-------------------");

                executor.shutdownNow();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executor.shutdownNow();
        }

        System.out.println("------------------------------------------------------------------");
        System.out.println("                      SIMULACION FINALIZADA                       ");
        System.out.println("------------------------------------------------------------------");
    }
}