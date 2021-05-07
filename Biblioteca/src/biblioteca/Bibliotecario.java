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
import java.io.RandomAccessFile;

/**
 *
 * @author alang
 */
public class Bibliotecario extends Persona {
    String raiz = System.getProperty("user.dir");
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
    public void verificarNumeroCedula()
    {
        if(numeroCedula.length() < 25)
        {
            while(numeroCedula.length() != 25){
                numeroCedula += " ";
            }
        }
        else
        {
             numeroCedula.substring(0,25);
        }
    }
    public void verificarNombre()
    {
        if(nombre.length() < 25)
        {
            while(nombre.length() != 25){
                nombre += " ";
            }
        }
        else
        {
             nombre.substring(0,25);
        }
    }
    public void verificarApellido()
    {
        if(apellido.length() < 25)
        {
            while(apellido.length() != 25){
                apellido += " ";
            }
        }
        else
        {
             apellido.substring(0,25);
        }
    }
    public void verificarTelefono()
    {
        if(telefono.length() < 25)
        {
            while(telefono.length() != 25){
                telefono += " ";
            }
        }
        else
        {
             telefono.substring(0,25);
        }
    }
    public void verificarUsuario()
    {
        if(usuario.length() < 25)
        {
            while(usuario.length() != 25){
                usuario += " ";
            }
        }
        else
        {
             usuario.substring(0,25);
        }
    }
    public void verificarClave()
    {
        if(clave.length() < 20)
        {
            while(clave.length() != 20){
                clave += " ";
            }
        }
        else
        {
             clave.substring(0,25);
        }
    }
    public void agregarBibliotecario( ) {
        
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            String raiz = System.getProperty("user.dir");
            String data = "Numero de cedula:" + numeroCedula + "; Nombre:" + nombre + "; Apellido:" + apellido + "; Telefono:" + telefono +"; Usuario:" + usuario + "; ContraseÃ±a:" + clave + "\n";
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
    public void agregarBibliotecarioRandomico(){
        verificarNombre();
        verificarApellido();
        verificarNumeroCedula();
        verificarTelefono();
        verificarUsuario();
        verificarClave();
        try {
            RandomAccessFile archivo = new RandomAccessFile(raiz+"\\BIBLIOTECARIO.dat", "rw");
            if (archivo.length()!=0){
                
                archivo.seek(archivo.length());
                archivo.writeChars(numeroCedula);
                archivo.writeChars(nombre);
                archivo.writeChars(apellido);
                archivo.writeChars(telefono);
                archivo.writeChars(usuario);
                archivo.writeChars(clave);
            }else{
                archivo.writeChars(numeroCedula);
                archivo.writeChars(nombre);
                archivo.writeChars(apellido);
                archivo.writeChars(telefono);         
                archivo.writeChars(usuario);
                archivo.writeChars(clave);
            }
            
        }catch(Exception e)
        {
            System.out.println("error al ingresar");
        }
    }
}
