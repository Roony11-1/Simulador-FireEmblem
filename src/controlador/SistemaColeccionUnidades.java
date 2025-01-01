package controlador;

import java.util.ArrayList;
import modelo.Unidad;
import modelo.clases.Clase;
import modelo.clases.ClaseFactory;

public class SistemaColeccionUnidades 
{
    private ArrayList<Unidad> unidades;

    public SistemaColeccionUnidades() 
    {
        this.unidades = new ArrayList<>();
    }
    
    public boolean isEmpty()
    {
        return this.unidades.isEmpty();
    }

    public ArrayList<Unidad> getUnidades() { return unidades; }
    public void setUnidades(ArrayList<Unidad> unidades) { this.unidades = unidades; }
    
    public boolean agregarUnidad(Unidad unidad) 
    {
        if (unidad == null) 
        {
            System.out.println("La unidad proporcionada es nula. No se puede agregar.");
            return false;
        }
        try 
        {
            unidades.add(unidad);
        } 
        catch (Exception e) 
        {
            System.out.println("Error al agregar la unidad: " + e.getMessage());
            return false;
        }
        return true;
    }
    
    public ArrayList<Unidad> listarUnidadPorClase(String clase) 
    {
        ArrayList<Unidad> lista = new ArrayList<>(); // Inicializar siempre una lista vacía

        // Obtener la clase desde el factory
        Class<? extends Clase> claseObj = ClaseFactory.obtenerClase(clase);
        
        try 
        {
            if (claseObj != null) 
            {
                for (Unidad unidad : unidades) 
                {
                    // Verificar si la unidad es una instancia de la clase obtenida
                    if (claseObj.isAssignableFrom(unidad.getClase().getClass())) 
                    {
                        System.out.println("Añadido: "+unidad.getNombre());
                        lista.add(unidad);
                    }
                }
            }
        } 
        catch (Exception e) 
        {
            System.out.println("No se ha podido obtener la lista de unidades: "+e.getMessage());
            return new ArrayList<>(); // Devuelve una lista vacia
        }


        return lista; // Si no se encuentra la clase, se devuelve una lista vacía
    }
    
    // Método para promocionar una unidad a su clase promocionada
    public void promocionarUnidad(Unidad unidad) 
    {
        // Obtener el nombre de la clase actual de la unidad
        String nombreClaseActual = ClaseFactory.obtenerStringClase(unidad);

        if (nombreClaseActual == null)
            throw new IllegalArgumentException("La clase de la unidad no está registrada: " + unidad.getClass().getName());

        // Obtener la clase promocionada
        Class<? extends Clase> clasePromocionada = ClaseFactory.obtenerClasePromocionada(nombreClaseActual);

        try 
        {
            // Crear una nueva instancia de la clase promocionada
            Clase nuevaClase = clasePromocionada.getDeclaredConstructor().newInstance();

            // Establecer la nueva clase a la unidad
            unidad.setClase(nuevaClase);
            
            System.out.println(unidad.getNombre()+" ha promocionado de "+nombreClaseActual+" a "+nuevaClase.getNombre());
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("No se pudo promocionar la unidad a la nueva clase: " + clasePromocionada.getName(), e);
        }
    }
}
