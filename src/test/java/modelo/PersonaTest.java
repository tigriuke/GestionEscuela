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
}

