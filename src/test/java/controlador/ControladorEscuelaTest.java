/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.Estudiante;
import modelo.Profesor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Samuel Lopez
 */
public class ControladorEscuelaTest {

    // registra un estudiante con datos válidos
    private String registrarEstudianteValido(ControladorEscuela c, String codigo){
        return c.registrarEstudiante("Ana", "Calle 1", "3001234567", "15/05/2010", codigo, "10");
    }

    // registra un profesor con datos válidos (mayor de edad)
    private String registrarProfesorValido(ControladorEscuela c, String cedula, String nombre, String salario){
        return c.registrarProfesor(nombre, "Calle 2", "3009876543", "01/01/1980", cedula, "Matemáticas", salario, "100");
    }

    // ---------- estudiantes ----------

    @Test
    public void estudianteCamposVaciosDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        String resultado = c.registrarEstudiante("", "Calle 1", "3001234567", "15/05/2010", "1", "10");

        assertEquals("Todos los campos son obligatorios.", resultado);
        assertEquals(0, c.getEstudiantes().size());
    }

    @Test
    public void estudianteCampoSoloEspaciosDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        String resultado = c.registrarEstudiante("Ana", "   ", "3001234567", "15/05/2010", "1", "10");

        assertEquals("Todos los campos son obligatorios.", resultado);
    }

    @Test
    public void estudianteFechaInvalidaDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        //el 31 de febrero no existe, el formato STRICT lo rechaza
        String resultado = c.registrarEstudiante("Ana", "Calle 1", "3001234567", "31/02/2010", "1", "10");

        assertEquals("La fecha debe ser válida y tener el formato dd/mm/aaaa.", resultado);
        assertEquals(0, c.getEstudiantes().size());
    }

    @Test
    public void estudianteFechaFuturaDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        String resultado = c.registrarEstudiante("Ana", "Calle 1", "3001234567", "01/01/2999", "1", "10");

        assertEquals("La fecha de nacimiento no puede ser futura.", resultado);
        assertEquals(0, c.getEstudiantes().size());
    }

    @Test
    public void estudianteRegistroValidoSeGuarda(){
        ControladorEscuela c = new ControladorEscuela();
        String resultado = registrarEstudianteValido(c, "1");

        assertEquals("", resultado);
        assertEquals(1, c.getEstudiantes().size());
        Estudiante e = c.getEstudiantes().get(0);
        assertEquals("Ana", e.getNombre());
        assertEquals("1", e.getCodigo());
        assertEquals("10", e.getGrado());
    }

    @Test
    public void estudianteDatosSeRecortanEspacios(){
        ControladorEscuela c = new ControladorEscuela();
        c.registrarEstudiante("  Ana  ", " Calle 1 ", " 3001234567 ", "15/05/2010", " 1 ", " 10 ");

        Estudiante e = c.getEstudiantes().get(0);
        assertEquals("Ana", e.getNombre());
        assertEquals("Calle 1", e.getDireccion());
        assertEquals("1", e.getCodigo());
        assertEquals("10", e.getGrado());
    }

    @Test
    public void estudianteCodigoDuplicadoDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        registrarEstudianteValido(c, "1");
        String resultado = registrarEstudianteValido(c, "1");

        assertEquals("Ya existe un estudiante con ese código.", resultado);
        assertEquals(1, c.getEstudiantes().size());
    }

    @Test
    public void reporteEstudiantesVacio(){
        ControladorEscuela c = new ControladorEscuela();
        String reporte = c.generarReporteEstudiantes();

        assertTrue(reporte.contains("No hay estudiantes registrados."));
    }

    @Test
    public void reporteEstudiantesConDatos(){
        ControladorEscuela c = new ControladorEscuela();
        registrarEstudianteValido(c, "1");
        registrarEstudianteValido(c, "2");
        String reporte = c.generarReporteEstudiantes();

        assertTrue(reporte.contains("1. Nombre: Ana"));
        assertTrue(reporte.contains("2. Nombre: Ana"));
        assertTrue(reporte.contains("Total de estudiantes: 2"));
    }

    // ---------- profesores ----------

    @Test
    public void profesorCamposVaciosDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        String resultado = c.registrarProfesor("Beto", "Calle 2", "", "01/01/1980", "123", "Mate", "10", "100");

        assertEquals("Todos los campos son obligatorios.", resultado);
        assertEquals(0, c.getProfesores().size());
    }

    @Test
    public void profesorFechaInvalidaDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        String resultado = c.registrarProfesor("Beto", "Calle 2", "3009876543", "99/99/1980", "123", "Mate", "10", "100");

        assertEquals("La fecha debe ser válida y tener el formato dd/mm/aaaa.", resultado);
    }

    @Test
    public void profesorFechaFuturaDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        String resultado = c.registrarProfesor("Beto", "Calle 2", "3009876543", "01/01/2999", "123", "Mate", "10", "100");

        assertEquals("La fecha de nacimiento no puede ser futura.", resultado);
    }

    @Test
    public void profesorSalarioNoNumericoDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        String resultado = c.registrarProfesor("Beto", "Calle 2", "3009876543", "01/01/1980", "123", "Mate", "abc", "100");

        assertEquals("El salario por hora y las horas deben ser números enteros.", resultado);
    }

    @Test
    public void profesorHorasNegativasDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        //el signo menos no es un digito, asi que se rechaza
        String resultado = c.registrarProfesor("Beto", "Calle 2", "3009876543", "01/01/1980", "123", "Mate", "10", "-5");

        assertEquals("El salario por hora y las horas deben ser números enteros.", resultado);
    }

    @Test
    public void profesorSalarioCeroDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        String resultado = registrarProfesorValido(c, "123", "Beto", "0");

        assertEquals("El salario por hora debe ser mayor que 0.", resultado);
        assertEquals(0, c.getProfesores().size());
    }

    @Test
    public void profesorMenorDeEdadDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        //nacio en 2015, tiene 11 años
        String resultado = c.registrarProfesor("Beto", "Calle 2", "3009876543", "01/01/2015", "123", "Mate", "10", "100");

        assertEquals("El profesor debe ser mayor de edad.", resultado);
        assertEquals(0, c.getProfesores().size());
    }

    @Test
    public void profesorCedulaDuplicadaDevuelveError(){
        ControladorEscuela c = new ControladorEscuela();
        registrarProfesorValido(c, "123", "Beto", "10");
        String resultado = registrarProfesorValido(c, "123", "Carla", "20");

        assertEquals("Ya existe un profesor con esa cédula.", resultado);
        assertEquals(1, c.getProfesores().size());
    }

    @Test
    public void profesorRegistroValidoSeGuarda(){
        ControladorEscuela c = new ControladorEscuela();
        String resultado = registrarProfesorValido(c, "123", "Beto", "10");

        assertEquals("", resultado);
        assertEquals(1, c.getProfesores().size());
        Profesor p = c.getProfesores().get(0);
        assertEquals("Beto", p.getNombre());
        assertEquals("123", p.getCedula());
        assertEquals(10.0, p.getSalarioPorHora(), 0.001);
    }

    @Test
    public void reporteProfesoresVacio(){
        ControladorEscuela c = new ControladorEscuela();
        String reporte = c.generarReporteProfesores();

        assertTrue(reporte.contains("No hay profesores registrados."));
    }

    @Test
    public void reporteProfesoresOrdenadoDeMayorAMenorPago(){
        ControladorEscuela c = new ControladorEscuela();
        //Ana: 10 * 100 * 1.3 = 1300 | Beto: 20 * 100 * 1.3 = 2600
        registrarProfesorValido(c, "111", "Ana", "10");
        registrarProfesorValido(c, "222", "Beto", "20");

        String reporte = c.generarReporteProfesores();

        assertTrue(reporte.indexOf("Nombre: Beto") < reporte.indexOf("Nombre: Ana"));
        assertEquals("Beto", c.getProfesores().get(0).getNombre());
    }

    @Test
    public void reporteProfesoresTotalPrestaciones(){
        ControladorEscuela c = new ControladorEscuela();
        //prestaciones: 1300 * 0.19 = 247 | 2600 * 0.19 = 494 | total = 741
        registrarProfesorValido(c, "111", "Ana", "10");
        registrarProfesorValido(c, "222", "Beto", "20");

        String reporte = c.generarReporteProfesores();

        assertTrue(reporte.contains("TOTAL PRESTACIONES SOCIALES: $741.00"));
    }
}