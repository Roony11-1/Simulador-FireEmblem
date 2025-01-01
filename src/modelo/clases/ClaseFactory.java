package modelo.clases;

import java.util.HashMap;
import java.util.Map;
import modelo.Unidad;

public class ClaseFactory {
    private static final Map<String, Class<? extends Clase>> clasesRegistradas = new HashMap<>();
    private static final Map<String, String> promociones = new HashMap<>();

    // Bloque estático: aquí se registran todas las clases una sola vez
    static 
    {
        registrarClase("Mirmidon", Mirmidon.class, "Espadachin");
        registrarClase("Guerrero", Guerrero.class);
        registrarClase("Espadachin", Espadachin.class);
    }
    
    public static void registrarClase(String nombreClase, Class<? extends Clase> clase, String promocion) 
    {
        clasesRegistradas.put(nombreClase, clase);
        if (promocion != null)
            promociones.put(nombreClase, promocion);
    }

    // Sin promocion
    public static void registrarClase(String nombreClase, Class<? extends Clase> clase) 
    {
        registrarClase(nombreClase, clase, null);
    }
    
    public static Class<? extends Clase> obtenerClase(String nombreClase) 
    {
        return clasesRegistradas.get(nombreClase);
    }
    
    public static String obtenerStringClase(Unidad unidad) 
    {
        for (Map.Entry<String, Class<? extends Clase>> entrada : clasesRegistradas.entrySet()) 
        {
            if (entrada.getValue().equals(unidad.getClass()))
                return entrada.getKey(); // Devuelve el nombre (String) asociado a la clase
        }
        return null; // Devuelve null si no encuentra una coincidencia
    }
    
    public static String obtenerPromocion(String nombreClase) 
    {
        return promociones.get(nombreClase);
    }
    
    public static void promocionarUnidad(Unidad unidad) 
    {
        if (unidad.getClase() == null)
            throw new IllegalArgumentException("La unidad no tiene una clase asignada.");

        // Obtiene el nombre de la clase actual de la unidad
        String nombreClaseActual = null;
        for (Map.Entry<String, Class<? extends Clase>> entrada : clasesRegistradas.entrySet()) 
        {
            if (entrada.getValue().equals(unidad.getClase().getClass())) 
            {
                nombreClaseActual = entrada.getKey();
                break;
            }
        }
        
        if (nombreClaseActual == null)
            throw new IllegalArgumentException("La clase actual de la unidad no está registrada.");

        // Obtiene el nombre de la clase promocionada
        String nombreClasePromocionada = obtenerPromocion(nombreClaseActual);
        if (nombreClasePromocionada == null)
            throw new IllegalArgumentException("No hay promoción definida para la clase: " + nombreClaseActual);

        // Obtiene la clase promocionada
        Class<? extends Clase> clasePromocionada = obtenerClase(nombreClasePromocionada);
        if (clasePromocionada == null)
            throw new IllegalArgumentException("No se encontró la clase promocionada para: " + nombreClasePromocionada);

        try 
        {
            // Crea una nueva instancia de la clase promocionada
            Clase nuevaClase = clasePromocionada.getDeclaredConstructor().newInstance();
            unidad.setClase(nuevaClase); // Asigna la nueva clase a la unidad
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("No se pudo crear una instancia de la clase promocionada.", e);
        }
    }
    
    public static Class<? extends Clase> obtenerClasePromocionada(String nombreClase) 
    {
        String promocion = obtenerPromocion(nombreClase);

        if (promocion == null)
            throw new IllegalArgumentException("No hay promoción definida para la clase: " + nombreClase);

        return obtenerClase(promocion);
    }
    
    // Método para crear una clase según el nombre
    public static Clase crearClase(String nombreClase) 
    {
        // Busca en el mapa si la clase existe
        Class<? extends Clase> clase = clasesRegistradas.get(nombreClase);

        if (clase == null) 
        {
            // Si no está registrada, lanza un error
            throw new IllegalArgumentException("Clase no reconocida: " + nombreClase);
        }

        try 
        {
            // Crea una nueva instancia de la clase encontrada
            return clase.getDeclaredConstructor().newInstance();
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("Error al instanciar la clase: " + nombreClase, e);
        }
    }
}