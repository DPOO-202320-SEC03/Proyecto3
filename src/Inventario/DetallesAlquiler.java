package Inventario;

import java.io.Serializable;

public class DetallesAlquiler implements Serializable {

    private String usuarioClienteAlquiler;
    private String fechaDevolucion;
    private String sedeDevolucion;

    /**
     * @return retorna el cliente que esta alquilando
     */
    public String getUsuarioClienteAlquiler() {
        return this.usuarioClienteAlquiler;
    }

    /**
     * metodo publico para asignar un usuario a un alquiler
     * @param usuario el usuario que se desea añadir
     */
    public void setUsuarioClienteAlquiler(String usuario) {
        this.usuarioClienteAlquiler = usuario;
    }

    /**
     * @return retorna la fecha de devolucion asignada al alquiler
     */
    public String getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    /**
     * metodo publico para asignar una fecha de devolucion
     * @param fecha fecha que se desea asignar
     */
    public void setFechaDevolucion(String fecha) {
        this.fechaDevolucion = fecha;
    }

    /**
     * @return retorna la sede de devolucion del vehiculo
     */
    public String getSedeDevolucion() {
        return this.sedeDevolucion;
    }

    /**
     * metodo publico para asignar una sede de devolucion para el alquiler
     * @param sede sede en la que se desea devolver
     */
    public void setSedeDevolucion(String sede) {
        this.sedeDevolucion = sede;
    }

}