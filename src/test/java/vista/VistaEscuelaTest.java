/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControladorEscuela;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Samuel Lopez
 */
public class VistaEscuelaTest {

    @Test
    public void vistaCreaControladorAlIniciar(){
        VistaEscuela v = new VistaEscuela();

        assertNotNull(v.getControlador());
    }

    @Test
    public void vistaControladorInicialEstaVacio(){
        VistaEscuela v = new VistaEscuela();

        assertEquals(0, v.getControlador().getEstudiantes().size());
        assertEquals(0, v.getControlador().getProfesores().size());
    }

    @Test
    public void vistaSetControladorCambiaControlador(){
        VistaEscuela v = new VistaEscuela();
        ControladorEscuela nuevo = new ControladorEscuela();

        v.setControlador(nuevo);

        assertSame(nuevo, v.getControlador());
    }

    @Test
    public void vistaUsaLosDatosDelControladorAsignado(){
        VistaEscuela v = new VistaEscuela();
        ControladorEscuela c = new ControladorEscuela();
        c.registrarEstudiante("Ana", "Calle 1", "3001234567", "15/05/2010", "1", "10");

        v.setControlador(c);

        assertEquals(1, v.getControlador().getEstudiantes().size());
    }

    @Test
    public void vistaTituloVentana(){
        VistaEscuela v = new VistaEscuela();

        assertEquals("Gestión Escuela - Profesores y Estudiantes", v.getTitle());
    }
}