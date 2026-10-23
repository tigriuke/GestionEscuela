/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Samuel Lopez
 */
public class ProfesorTest {

    @Test
    public void profesorConstructorGuardaDatos(){
        Profesor p = new Profesor("123", "Matemáticas", 10.0, 100);

        assertEquals("123", p.getCedula());
        assertEquals("Matemáticas", p.getArea());
        assertEquals(10.0, p.getSalarioPorHora(), 0.001);
        assertEquals(100, p.getHorasDictadas());
    }

    @Test
    public void profesorCalcularPagoTotalConHorasDadas(){
        Profesor p = new Profesor("123", "Matemáticas", 10.0, 100);

        //10 * 50 horas = 500, mas 30% por preparacion = 650
        assertEquals(650.0, p.calcularPagoTotal(50), 0.001);
    }

    @Test
    public void profesorCalcularPagoTotalUsaHorasDictadas(){
        Profesor p = new Profesor("123", "Matemáticas", 10.0, 100);

        //10 * 100 horas = 1000, mas 30% = 1300
        assertEquals(1300.0, p.calcularPagoTotal(), 0.001);
    }

    @Test
    public void profesorPagoConCeroHoras(){
        Profesor p = new Profesor("123", "Matemáticas", 10.0, 0);

        assertEquals(0.0, p.calcularPagoTotal(), 0.001);
    }

    @Test
    public void profesorCalcularPrestaciones(){
        Profesor p = new Profesor("123", "Matemáticas", 10.0, 100);

        //19% de 1300 = 247
        assertEquals(247.0, p.calcularPrestaciones(), 0.001);
    }

    @Test
    public void profesorSettersActualizanPago(){
        Profesor p = new Profesor("123", "Matemáticas", 10.0, 100);
        p.setHorasDictadas(20);
        p.setSalarioPorHora(15.0);

        //15 * 20 = 300, mas 30% = 390
        assertEquals(390.0, p.calcularPagoTotal(), 0.001);
    }

    @Test
    public void profesorHeredaDePersona(){
        Profesor p = new Profesor("123", "Matemáticas", 10.0, 100);
        p.setNombre("Beto");

        assertEquals("Beto", p.getNombre());
    }

    @Test
    public void profesorObtenerDescripcion(){
        Profesor p = new Profesor("123", "Matemáticas", 10.0, 100);
        p.setNombre("Beto");

        String descripcion = p.obtenerDescripcion();

        assertTrue(descripcion.contains("Nombre: Beto"));
        assertTrue(descripcion.contains("Cédula: 123"));
        assertTrue(descripcion.contains("Área: Matemáticas"));
        assertTrue(descripcion.contains("Horas: 100"));
        assertTrue(descripcion.contains("Pago total: $1300.00"));
        assertTrue(descripcion.contains("Prestaciones (19%): $247.00"));
    }
}