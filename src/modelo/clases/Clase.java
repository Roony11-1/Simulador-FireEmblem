package modelo.clases;

import utilidades.Utilidades;

public abstract class Clase 
{
    private String nombre;
    private String descripcion;
    private Estadisticas estadisticas;
    private Estadisticas estadisticasMaximas; // Límite de estadísticas
    private Nivel nivel;
    private Crecimiento crecimiento;
    private boolean promocionada;

    public Clase() 
    {
        this.nombre = "NN";
        this.descripcion = "ND";
        this.estadisticas = null;
        this.nivel = new Nivel();
        this.crecimiento = new Crecimiento();
        this.promocionada = false;
        
        // Inicializamos los máximos con valores por defecto
        this.estadisticasMaximas = new Estadisticas(60, 20, 20, 20, 30, 20, 20, 15, 25); // Ejemplo
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Nivel getNivel() { return nivel; }
    public void setNivel(Nivel nivel) { this.nivel = nivel; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Estadisticas getEstadisticas() { return estadisticas; }
    public void setEstadisticas(Estadisticas estadisticas) { this.estadisticas = estadisticas; }
    
    public Estadisticas getEstadisticasMaximas() { return estadisticasMaximas; }
    public void setEstadisticasMaximas(Estadisticas estadisticasMaximas) { this.estadisticasMaximas = estadisticasMaximas; }

    public Crecimiento getCrecimiento() { return crecimiento; }
    public void setCrecimiento(Crecimiento crecimiento) { this.crecimiento = crecimiento; }

    public boolean isPromocionada() { return promocionada; }
    public void setPromocionada(boolean promocionada) { this.promocionada = promocionada; }

    // Método para subir de nivel (modificado para respetar los límites)
    public void subirNivel() 
    {
        int pv = this.crecimiento.getCrePv();
        int fue = this.crecimiento.getCreFue();
        int hab = this.crecimiento.getCreHab();
        int vel = this.crecimiento.getCreVel();
        int sue = this.crecimiento.getCreSue();
        int def = this.crecimiento.getCreDef();
        int res = this.crecimiento.getCreRes();

        // Aplica el crecimiento a cada estadística, respetando los límites
        if (pv > 0)
            this.estadisticas.setPv(aumentarEstadistica(pv, this.estadisticas.getPv(), this.estadisticasMaximas.getPv()));
        if (fue > 0)
            this.estadisticas.setFue(aumentarEstadistica(fue, this.estadisticas.getFue(), this.estadisticasMaximas.getFue()));
        if (hab > 0)
            this.estadisticas.setHab(aumentarEstadistica(hab, this.estadisticas.getHab(), this.estadisticasMaximas.getHab()));
        if (vel > 0)
            this.estadisticas.setVel(aumentarEstadistica(vel, this.estadisticas.getVel(), this.estadisticasMaximas.getVel()));
        if (sue > 0)
            this.estadisticas.setSue(aumentarEstadistica(sue, this.estadisticas.getSue(), this.estadisticasMaximas.getSue()));
        if (def > 0)
            this.estadisticas.setDef(aumentarEstadistica(def, this.estadisticas.getDef(), this.estadisticasMaximas.getDef()));
        if (res > 0)
            this.estadisticas.setRes(aumentarEstadistica(res, this.estadisticas.getRes(), this.estadisticasMaximas.getRes()));
    }

    // Método para aumentar una estadística según la tasa de crecimiento, respetando los límites
    private int aumentarEstadistica(int tasaCrecimiento, int valorEstadisticaActual, int limiteMaximo) {
        int incremento = 0;

        if (tasaCrecimiento > 0) 
        {
            if (tasaCrecimiento < 100) 
            {
                if (Utilidades.ocurrirEvento(tasaCrecimiento)) 
                    incremento = 1;
            } else 
            {
                int base = tasaCrecimiento / 100;
                incremento = base;

                int porcentajeExtra = tasaCrecimiento % 100;
                if (porcentajeExtra > 0 && Utilidades.ocurrirEvento(porcentajeExtra))
                    incremento += 1;
            }
        }

        // Calcula el nuevo valor de la estadística, pero limita al máximo permitido
        return Math.min(valorEstadisticaActual + incremento, limiteMaximo);
    }
}
