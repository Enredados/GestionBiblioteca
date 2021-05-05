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
public class Bibliotecario extends Persona {
    private String usuario;
    private String clave;
    
    public Bibliotecario(String numeroCedula, String nombre, String apellido, String telefono,String usuario, String clave){
        super(numeroCedula, nombre, apellido, telefono);
        this.usuario = usuario;
        this.clave = clave;
    }
    public String obtenerUsuario(){
        return usuario;
    }
    public String obtenerClave(){
        return clave;
    }
}
