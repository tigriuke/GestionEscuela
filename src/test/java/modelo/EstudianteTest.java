/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Samuel Lopez
 */
public class EstudianteTest {
    
    @Test
    public void estudianteCodigoNegativo(){
        Estudiante e = new Estudiante();
        e.setCodigo("-19990");
        //los datos se validan y convierten a int en el controlador, por eso no hay validacion en el modelo
        
        assertEquals("-19990", e.getCodigo());
    }
    
    @Test
    public void estudianteGradoNegativo(){
        Estudiante e = new Estudiante();
        e.setGrado("-318281");
        //los datos se validan y convierten a int en el controlador, por eso no hay validacion en el modelo
        
        assertEquals("-318281", e.getGrado());
    }
    
    
    @Test
    public void estudianteObtenerDescripcionSinDatos(){
        Estudiante e = new Estudiante();
        
        //el controlador se asegura de que obtenerDescripcion() solo se ejecute si el estudiante tiene datos guardados
        
        assertEquals("Nombre: null | Dirección: null | Tel: null | Nacimiento: - (0 años)\n     Código: null | Grado: null", e.obtenerDescripcion());
    }
    
        @Test
    public void estudianteObtenerDescripcionConDatos(){
        Estudiante e = new Estudiante();
        e.setNombre("Ana");
        e.setDireccion("Calle 1");
        e.setTelefono("3001234567");
        e.setFechaNacimiento(LocalDate.of(2010, 5, 15));
        e.setCodigo("1");
        e.setGrado("10");
        
        String descripcion = e.obtenerDescripcion();

        assertTrue(descripcion.contains("Nombre: Ana"));
        assertTrue(descripcion.contains("Nacimiento: 15/05/2010"));
        assertTrue(descripcion.contains("Código: 1 | Grado: 10"));
    }
}
