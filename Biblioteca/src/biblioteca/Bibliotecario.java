/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author alang
 */
public class Bibliotecario extends Persona {
    private String usuario;
    private String clave;
    
    public Bibliotecario(){
        super("", "", "", "");
        
    }
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
    public void agregarBibliotecario(String cedula, String nombre, String apellido, String telefono,String usuario ,String clave ) {
        
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            String raiz = System.getProperty("user.dir");
            String data = "Numero de cedula:" + cedula + "; Nombre:" + nombre + "; Apellido:" + apellido + "; Telefono:" + telefono +"; Usuario:" + usuario + "; ContraseÃ±a:" + clave + "\n";
            File file = new File(raiz+"\\BIBLIOTECARIOS.txt");
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            // flag true, indica adjuntar informaciÃ³n al archivo.
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(data);
            //  System.out.println("informaciÃ³n agregada!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
