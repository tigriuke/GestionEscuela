/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Samuel Lopez
 */
public class PersonaTest {
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
    
    @Test
    public void personaTelefonoNegativo(){
        Persona p = new Persona();
        p.setTelefono("-3134882919");
        //los datos se validan y convierten a int en el controlador, por eso no hay validacion en el modelo
        
        assertEquals("-3134882919", p.getTelefono());
    }
    
    @Test
    public void personaFechaInvalida(){
        Persona p = new Persona();
        LocalDate fecha = LocalDate.parse("15/08/0000", formato);
        p.setFechaNacimiento(fecha);
        //la validacion de la fecha se realiza en controlador, por eso se acepta cualquier valor en el modelo
        LocalDate fechaEsperada = LocalDate.of(0000, 8, 15);
        
        assertEquals(fechaEsperada, p.getFechaNacimiento());
    }
    
    @Test
    public void personaCalcularEdadFechaInvalida(){
        Persona p = new Persona();
        LocalDate fecha = LocalDate.parse("15/12/2200", formato);
        p.setFechaNacimiento(fecha);
        
        //el modelo no recibe fechas futuras, en ese caso retorna 0
        
        assertEquals(0, p.calcularEdad(fecha));
    }
    
    @Test
    public void personaCalcularEdadFechaVacia(){
        Persona p = new Persona();
        LocalDate fecha = null;
        
        //el modelo no recibe fechas futuras, en ese caso retorna 0
        
        assertEquals(0, p.calcularEdad(fecha));
    }
    
    @Test
    public void MayorDeEdadSiEsMenor(){
        Persona p = new Persona();
        LocalDate fecha = LocalDate.parse("15/12/2020", formato);
        p.setFechaNacimiento(fecha);
        
        //el modelo no recibe fechas futuras, en ese caso retorna 0
        
        assertEquals(false, p.esMayorDeEdad());
    }
    
    @Test
    public void personaObtenerDescripcionSinDatos(){
        Persona p = new Persona();
        
        //el controlador se asegura de que obtenerDescripcion() solo se ejecute si el estudiante tiene datos guardados
        
        assertEquals("Nombre: null | Dirección: null | Tel: null | Nacimiento: - (0 años)", p.obtenerDescripcion());
    }
    
    @Test
    public void personaSettersYGetters(){
        Persona p = new Persona();
        p.setNombre("Ana");
        p.setDireccion("Calle 1");
        p.setTelefono("3001234567");

        assertEquals("Ana", p.getNombre());
        assertEquals("Calle 1", p.getDireccion());
        assertEquals("3001234567", p.getTelefono());
    }

    @Test
    public void personaCalcularEdadRespetaCumpleanos(){
        Persona p = new Persona();
        p.setFechaNacimiento(LocalDate.of(2000, 6, 15));

        //el dia antes del cumpleanos todavia tiene 24 años
        assertEquals(24, p.calcularEdad(LocalDate.of(2025, 6, 14)));
        //el dia del cumpleanos ya tiene 25
        assertEquals(25, p.calcularEdad(LocalDate.of(2025, 6, 15)));
    }

    @Test
    public void personaCalcularEdadReferenciaAntesDelNacimiento(){
        Persona p = new Persona();
        p.setFechaNacimiento(LocalDate.of(2200, 12, 15));

        //la referencia es anterior al nacimiento, por eso retorna 0
        assertEquals(0, p.calcularEdad(LocalDate.of(2020, 1, 1)));
    }

    @Test
    public void personaEsMayorDeEdadSiTiene18OMas(){
        Persona p = new Persona();
        p.setFechaNacimiento(LocalDate.now().minusYears(18));

        assertTrue(p.esMayorDeEdad());
    }

    @Test
    public void personaObtenerDescripcionConDatos(){
        Persona p = new Persona();
        p.setNombre("Ana");
        p.setDireccion("Calle 1");
        p.setTelefono("3001234567");
        p.setFechaNacimiento(LocalDate.of(2000, 6, 15));

        String descripcion = p.obtenerDescripcion();
        
        assertTrue(descripcion.contains("Nombre: Ana"));
        assertTrue(descripcion.contains("Dirección: Calle 1"));
        assertTrue(descripcion.contains("Tel: 3001234567"));
        assertTrue(descripcion.contains("Nacimiento: 15/06/2000"));
    }
}

