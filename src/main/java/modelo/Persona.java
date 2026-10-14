/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author coffe
 */
public class Persona {

    private String nombre;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;

    // constructor sin parámetros (los datos se llenan con los setters)
    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // edad en años cumplidos al día de hoy
    public int calcularEdad() {
        return calcularEdad(LocalDate.now());
    }

    // edad en años cumplidos a una fecha dada (sobrecarga)
    public int calcularEdad(LocalDate fechaReferencia) {
        if (fechaNacimiento == null || fechaReferencia == null) {
            return 0;
        }
        int anios = Period.between(fechaNacimiento, fechaReferencia).getYears();
        if (anios < 0) {
            return 0;
        }
        return anios;
    }

    // true si tiene 18 años o más
    public boolean esMayorDeEdad() {
        return calcularEdad() >= 18;
    }

    // datos de la persona en un texto (las subclases lo sobreescriben)
    public String obtenerDescripcion() {
        String fecha = "-";
        if (fechaNacimiento != null) {
            fecha = fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return "Nombre: " + nombre + " | Dirección: " + direccion + " | Tel: " + telefono
                + " | Nacimiento: " + fecha + " (" + calcularEdad() + " años)";
    }
}
