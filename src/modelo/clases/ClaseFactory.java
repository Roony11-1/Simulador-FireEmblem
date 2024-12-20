package modelo.clases;

import java.util.HashMap;
import java.util.Map;

public class ClaseFactory {
    // Un mapa para registrar todas las clases disponibles
    private static final Map<String, Class<? extends Clase>> clasesRegistradas = new HashMap<>();

    // Bloque est�tico: aqu� se registran todas las clases una sola vez
    static 
    {
        clasesRegistradas.put("Mirmidon", Mirmidon.class); // Relaciona "Mirmidon" con la clase Mirmidon
        clasesRegistradas.put("Guerrero", Guerrero.class); // Relaciona "Guerrero" con la clase Guerrero
        // Si en el futuro agregas m�s clases, solo las agregas aqu�.
    }

    // M�todo para crear una clase seg�n el nombre
    public static Clase crearClase(String nombreClase) 
    {
        // Busca en el mapa si la clase existe
        Class<? extends Clase> clase = clasesRegistradas.get(nombreClase);

        if (clase == null) 
        {
            // Si no est� registrada, lanza un error
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