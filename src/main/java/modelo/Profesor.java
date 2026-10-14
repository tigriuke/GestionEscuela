/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Samuel Lopez
 */
public class Profesor extends Persona {

    private String cedula;
    private String area;
    private double salarioPorHora;
    private int horasDictadas;

    // constructor: pide los datos propios del profesor
    public Profesor(String cedula, String area, double salarioPorHora, int horasDictadas) {
        this.cedula = cedula;
        this.area = area;
        this.salarioPorHora = salarioPorHora;
        this.horasDictadas = horasDictadas;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getSalarioPorHora() {
        return salarioPorHora;
    }

    public void setSalarioPorHora(double salarioPorHora) {
        this.salarioPorHora = salarioPorHora;
    }

    public int getHorasDictadas() {
        return horasDictadas;
    }

    public void setHorasDictadas(int horasDictadas) {
        this.horasDictadas = horasDictadas;
    }

    // sobrecarga 1: pago del mes con las horas dictadas
    public double calcularPagoTotal() {
        return calcularPagoTotal(horasDictadas);
    }

    // sobrecarga 2: pago para unas horas dadas (más 30% por preparación)
    public double calcularPagoTotal(int horas) {
        double pagoBase = salarioPorHora * horas;
        return pagoBase + pagoBase * 0.30;
    }

    // prestaciones: 19% del pago total
    public double calcularPrestaciones() {
        return calcularPagoTotal() * 0.19;
    }

    // sobreescribe el método de Persona
    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion()
                + "\n     Cédula: " + cedula + " | Área: " + area
                + "\n     Horas: " + horasDictadas
                + " | Pago total: $" + String.format("%.2f", calcularPagoTotal())
                + " | Prestaciones (19%): $" + String.format("%.2f", calcularPrestaciones());
    }
}
