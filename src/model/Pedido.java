package model;

public class Pedido {

    //Atributos
    private int id;
    private String direccionEntrega;
    private EstadoPedido estado;

    //Constructor
    public Pedido(int id, String direccionEntrega) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        //Estado inicial del pedido (PENDIENTE)
        this.estado = EstadoPedido.PENDIENTE;
    }

    //Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }
    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    //Metodo setEstado para actualizar el estado
    public void setEstado(String nuevoEstado) {
        this.estado = EstadoPedido.valueOf(nuevoEstado.toUpperCase());
    }

    //toString()
    @Override
    public String toString() {
        return "Pedido #" + id +
                " | Dirección: " + direccionEntrega +
                " | Estado: " + estado;
    }
}