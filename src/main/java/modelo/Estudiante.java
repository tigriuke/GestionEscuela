/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Samuel Lopez
 */
public class Estudiante extends Persona {

    private String codigo;
    private String grado;

    // esta clase no tiene constructor

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    // sobreescribe el método de Persona
    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion() + "\n     Código: " + codigo + " | Grado: " + grado;
    }
}
