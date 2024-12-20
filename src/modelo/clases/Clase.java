package modelo.clases;

public abstract class Clase 
{
    private String nombre;
    private String descripcion;
    private Estadisticas estadisticas;
    private Nivel nivel;
    private Crecimiento crecimiento;

    public Clase() 
    {
        this.nombre = "NN";
        this.descripcion = "ND";
        this.estadisticas = null;
        this.nivel = new Nivel();
        this.crecimiento = new Crecimiento();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Nivel getNivel() { return nivel; }
    public void setNivel(Nivel nivel) { this.nivel = nivel; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Estadisticas getEstadisticas() { return estadisticas; }
    public void setEstadisticas(Estadisticas estadisticas) { this.estadisticas = estadisticas; }

    public Crecimiento getCrecimiento() { return crecimiento; }
    public void setCrecimiento(Crecimiento crecimiento) { this.crecimiento = crecimiento; }
}
