/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import modelo.Estudiante;
import modelo.Profesor;

/**
 *
 * @author coffe
 */
public class ControladorEscuela {

    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Profesor> profesores;
    private DateTimeFormatter formato;

    // constructor: crea las dos listas vacías y el formato de fecha dd/MM/aaaa
    public ControladorEscuela() {
        estudiantes = new ArrayList<Estudiante>();
        profesores = new ArrayList<Profesor>();
        formato = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
    }
}
