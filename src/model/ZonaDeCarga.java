package model;

import java.util.ArrayList;
import java.util.List;

public class ZonaDeCarga {

    //Crea lista pedidos
    private final List<Pedido> pedidos;

    //Constructor
    public ZonaDeCarga() {
        pedidos = new ArrayList<>();
    }

    //Metodo para agregar pedido
    public synchronized void agregarPedido(Pedido p) {
        pedidos.add(p);
        System.out.println("Pedido #" + p.getId() + " agregado. Destino: " + p.getDireccionEntrega());
    }

    //Metodo para retirar pedido
    public synchronized Pedido retirarPedido() {

        //Recorre la lista de pedidos
        for (int i = 0; i < pedidos.size(); i++) {

            //Obtiene un pedido
            Pedido pedido = pedidos.get(i);

            //Si el pedido esta PENDIENTE, lo remueve y cambia el estado a EN_REPARTO
            if (pedido.getEstado() == EstadoPedido.PENDIENTE) {

                pedidos.remove(i);

                pedido.setEstado(EstadoPedido.EN_REPARTO);

                return pedido;
            }
        }

        return null;
    }
}