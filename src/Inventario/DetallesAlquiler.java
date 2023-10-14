package Inventario;

public class DetallesAlquiler {

    private String usuarioClienteAlquiler;
    private String fechaDevolucion;
    private String sedeDevolucion;

    public String getUsuarioClienteAlquiler() {
        return this.usuarioClienteAlquiler;
    }

    public void setUsuarioClienteAlquiler(String usuario) {
        this.usuarioClienteAlquiler = usuario;
    }

    public String getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    public void setFechaDevolucion(String fecha) {
        this.fechaDevolucion = fecha;
    }

    public String getSedeDevolucion() {
        return this.sedeDevolucion;
    }

    public void setSedeDevolucion(String sede) {
        this.sedeDevolucion = sede;
    }

}