/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

    // convierte "dd/mm/aaaa" en fecha; devuelve null si no es válida
    private LocalDate convertirFecha(String texto) {
        try {
            return LocalDate.parse(texto.trim(), formato);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private boolean estaVacio(String texto) {
        return texto.trim().equals("");
    }

    // true si el texto tiene solo dígitos (máximo 9)
    private boolean esNumero(String texto) {
        texto = texto.trim();
        if (texto.equals("") || texto.length() > 9) {
            return false;
        }
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private boolean existeEstudiante(String codigo) {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    private boolean existeProfesor(String cedula) {
        for (int i = 0; i < profesores.size(); i++) {
            if (profesores.get(i).getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }
}
