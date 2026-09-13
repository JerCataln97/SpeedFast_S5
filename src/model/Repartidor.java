package model;

public class Repartidor implements Runnable {

    //Atributos
    private final String nombre;
    private final ZonaDeCarga zonaDeCarga;

    //Constructor
    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    //Metodo run()
    @Override
    public void run() {

        System.out.println("[Repartidor: " + nombre + "] Comenzó entregas..");

        while (true) {
            //Retira un pedido de la zona de carga
            Pedido pedido = zonaDeCarga.retirarPedido();

            //Comprueba si quedan pedidos
            if (pedido == null) {
                break;
            }

            System.out.println("[Repartidor: " + nombre + "] Retirando pedido #" + pedido.getId() + "...");

            System.out.println("[Repartidor: " + nombre + "] Estado pedido #" + pedido.getId() + ": " + pedido.getEstado());

            System.out.println("[Repartidor: " + nombre + "] Entregando pedido #" + pedido.getId() + "...");

            try {
                //Simula el tiempo de entrega
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                System.out.println("[Repartidor: " + nombre + "] fue interrumpido.");
                break;
            }

            //Cambia el estado a ENTREGADO
            pedido.setEstado(EstadoPedido.ENTREGADO);

            System.out.println("[Repartidor: " + nombre + "] Estado pedido #" + pedido.getId() + ": " + pedido.getEstado());
        }

        System.out.println("[Repartidor: " + nombre + "] termino entregas.");
    }
}