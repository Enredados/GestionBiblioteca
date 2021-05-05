/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;
/**
 *
 * @author alang
 */
public class Persona {
    protected String numeroCedula;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    
    public Persona(String numeroCedula, String nombre, String apellido, String telefono){
        this.numeroCedula = numeroCedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }    
}