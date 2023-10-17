package Inventario;

import java.io.Serializable;

public class DetallesSede implements Serializable {
    
    private String sedeUbicacion;
    private Boolean disponibleParaAlquilar;
    private String fechaDisponibilidad;

    /**
     * @return retorna la sede en la que se ubica un vehiculo
     */
    public String getSedeUbicacion() {
        return this.sedeUbicacion;
    }

    /**
     * metodo publico para asignar un vehiculo a una sede
     * @param sede
     */
    public void setSedeUbicacion(String sede) {
        this.sedeUbicacion = sede;
    }

    /**
     * @return retorna si un vehiculo esta listo para el alquiler
     */
    public Boolean getDisponibilidadParaAlquilar() {
        return this.disponibleParaAlquilar;
    }

    /**
     * metodo usada para definir si un vehiculo esta listo para alquilar 
     * @param estado el estado del vehivulo (true/false)
     */
    public void setDisponibilidadParaAlquilar(Boolean estado) {
        this.disponibleParaAlquilar = estado;
    }

    /**
     * @return retorna desde que fecha esta disponible el vehiculo
     */
    public String getFechaDisponibilidad() {
        return this.fechaDisponibilidad;
    }

    /**
     * metodo para asignar una fecha en la que el vehiculo estara disponible
     * @param fecha fecha en la que el vehiculo estara disponible
     */
    public void setFechaDisponibilidad(String fecha) {
        this.fechaDisponibilidad = fecha;
    }

}