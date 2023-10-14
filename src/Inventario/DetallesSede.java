package Inventario;

public class DetallesSede {
    
    private String sedeUbicacion;
    private Boolean disponibleParaAlquilar;
    private String fechaDisponibilidad;

    public String getSedeUbicacion() {
        return this.sedeUbicacion;
    }

    public void setSedeUbicacion(String sede) {
        this.sedeUbicacion = sede;
    }

    public Boolean getDisponibilidadParaAlquilar() {
        return this.disponibleParaAlquilar;
    }

    public void setDisponibilidadParaAlquilar(Boolean estado) {
        this.disponibleParaAlquilar = estado;
    }

    public String getFechaDisponibilidad() {
        return this.fechaDisponibilidad;
    }

    public void setFechaDisponibilidad(String fecha) {
        this.fechaDisponibilidad = fecha;
    }

}