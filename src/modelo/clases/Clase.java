package modelo.clases;

import utilidades.Utilidades;

public abstract class Clase 
{
    private String nombre;
    private String descripcion;
    private Estadisticas estadisticas;
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
    
    // M�todo que sube el nivel y aumenta las estad�sticas bas�ndose en la tasa de crecimiento.
    public void subirNivel() 
    {
        // Obtiene las tasas de crecimiento
        int pv = this.crecimiento.getCrePv();
        int fue = this.crecimiento.getCreFue();
        int hab = this.crecimiento.getCreHab();
        int vel = this.crecimiento.getCreVel();
        int sue = this.crecimiento.getCreSue();
        int def = this.crecimiento.getCreDef();
        int res = this.crecimiento.getCreRes();

        // Aplica el crecimiento a cada estad�stica solo si la tasa de crecimiento es mayor que 0
        if (pv > 0) {
            this.estadisticas.setPv(aumentarEstadistica(pv, this.estadisticas.getPv()));
        }
        if (fue > 0) {
            this.estadisticas.setFue(aumentarEstadistica(fue, this.estadisticas.getFue()));
        }
        if (hab > 0) {
            this.estadisticas.setHab(aumentarEstadistica(hab, this.estadisticas.getHab()));
        }
        if (vel > 0) {
            this.estadisticas.setVel(aumentarEstadistica(vel, this.estadisticas.getVel()));
        }
        if (sue > 0) {
            this.estadisticas.setSue(aumentarEstadistica(sue, this.estadisticas.getSue()));
        }
        if (def > 0) {
            this.estadisticas.setDef(aumentarEstadistica(def, this.estadisticas.getDef()));
        }
        if (res > 0) {
            this.estadisticas.setRes(aumentarEstadistica(res, this.estadisticas.getRes()));
        }
    }

    // M�todo para aumentar una estad�stica seg�n la tasa de crecimiento
    private int aumentarEstadistica(int tasaCrecimiento, int valorEstadisticaActual) 
    {
        int incremento = 1; // El valor m�nimo que se a�ade siempre

        // Si la tasa de crecimiento es mayor o igual a 200, el incremento base es +2, con posibilidad de +3
        if (tasaCrecimiento >= 200) 
        {
            // Para tasas m�s altas, cada 100 unidades adicionales aumentan el incremento m�nimo en 1
            int incrementoBase = (tasaCrecimiento / 100) - 1; // Resta 1 para que 200 sea +2, 300 sea +3, etc.
            incremento = 2 + incrementoBase; // El valor m�nimo es +2 y va aumentando seg�n el incrementoBase

            // El porcentaje adicional se maneja como probabilidad para aumentar el incremento
            tasaCrecimiento -= (100 * (incrementoBase + 1)); // Restamos el valor usado para el incremento base

            // Si la tasa de crecimiento restante tiene probabilidad de incrementar en m�s
            if (Utilidades.ocurrirEvento(tasaCrecimiento)) 
                incremento += 1; // Si la probabilidad se cumple, incrementamos en 1 m�s
        }

        // Suma el incremento a la estad�stica actual
        return valorEstadisticaActual + incremento;
    }
}
