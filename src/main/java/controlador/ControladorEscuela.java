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

    // registra un estudiante; devuelve "" si salió bien o el mensaje de error
    public String registrarEstudiante(String nombre, String direccion, String telefono,
            String fechaNacimiento, String codigo, String grado) {
        if (estaVacio(nombre) || estaVacio(direccion) || estaVacio(telefono)
                || estaVacio(fechaNacimiento) || estaVacio(codigo) || estaVacio(grado)) {
            return "Todos los campos son obligatorios.";
        }
        LocalDate fecha = convertirFecha(fechaNacimiento);
        if (fecha == null) {
            return "La fecha debe ser válida y tener el formato dd/mm/aaaa.";
        }
        if (fecha.isAfter(LocalDate.now())) {
            return "La fecha de nacimiento no puede ser futura.";
        }
        if (existeEstudiante(codigo.trim())) {
            return "Ya existe un estudiante con ese código.";
        }
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre.trim());
        estudiante.setDireccion(direccion.trim());
        estudiante.setTelefono(telefono.trim());
        estudiante.setFechaNacimiento(fecha);
        estudiante.setCodigo(codigo.trim());
        estudiante.setGrado(grado.trim());
        estudiantes.add(estudiante);
        return "";
    }

    // registra un profesor; devuelve "" si salió bien o el mensaje de error
    public String registrarProfesor(String nombre, String direccion, String telefono,
            String fechaNacimiento, String cedula, String area, String salarioPorHora, String horasDictadas) {
        if (estaVacio(nombre) || estaVacio(direccion) || estaVacio(telefono)
                || estaVacio(fechaNacimiento) || estaVacio(cedula) || estaVacio(area)
                || estaVacio(salarioPorHora) || estaVacio(horasDictadas)) {
            return "Todos los campos son obligatorios.";
        }
        LocalDate fecha = convertirFecha(fechaNacimiento);
        if (fecha == null) {
            return "La fecha debe ser válida y tener el formato dd/mm/aaaa.";
        }
        if (fecha.isAfter(LocalDate.now())) {
            return "La fecha de nacimiento no puede ser futura.";
        }
        if (!esNumero(salarioPorHora) || !esNumero(horasDictadas)) {
            return "El salario por hora y las horas deben ser números enteros.";
        }
        int salario = Integer.parseInt(salarioPorHora.trim());
        int horas = Integer.parseInt(horasDictadas.trim());
        if (salario == 0) {
            return "El salario por hora debe ser mayor que 0.";
        }
        Profesor profesor = new Profesor(cedula.trim(), area.trim(), salario, horas);
        profesor.setNombre(nombre.trim());
        profesor.setDireccion(direccion.trim());
        profesor.setTelefono(telefono.trim());
        profesor.setFechaNacimiento(fecha);
        if (!profesor.esMayorDeEdad()) {
            return "El profesor debe ser mayor de edad.";
        }
        if (existeProfesor(cedula.trim())) {
            return "Ya existe un profesor con esa cédula.";
        }
        profesores.add(profesor);
        return "";
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
