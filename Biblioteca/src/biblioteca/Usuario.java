/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author alang
 */
public class Usuario extends Persona {
    
    
    
    private boolean afiliado;

    public Usuario(String numeroCedula, String nombre, String apellido, String telefono, boolean afiliado) {
        super(numeroCedula, nombre, apellido, telefono);
        this.afiliado = afiliado;
    }

    public Usuario() {
        super("", "", "", "");
    }

    public String obtenerCedula() {
        return numeroCedula;
    }

    public String[] obtenerDatos() {
        String[] datos = new String[5];

        datos[0] = numeroCedula;
        datos[1] = nombre;
        datos[2] = apellido;
        datos[3] = telefono;

        if (afiliado) {
            datos[4] = "true";
        } else {
            datos[4] = "false";
        }

        return datos;
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
   
    public void agregarUsuario() {
        String raiz = System.getProperty("user.dir");

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            String data = "Numero de cedula:" + numeroCedula+ "; Nombre:" + nombre + "; Apellido:" + apellido + "; Telefono:" + telefono + "; Afiliado:" + afiliado + "\n";
            File file = new File(raiz+"\\USUARIOS.txt");
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            // flag true, indica adjuntar información al archivo.
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(data);
            //  System.out.println("información agregada!");
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
    
    public void agregarUsuarioRandomico(){
        String raiz = System.getProperty("user.dir");
        verificarNombre();
        verificarApellido();
        verificarNumeroCedula();
        verificarTelefono();
        try {
            RandomAccessFile archivo = new RandomAccessFile(raiz+"\\USUARIOS.dat", "rw");
            archivo.seek(0);
            if (archivo.length()!=0){
                
                archivo.seek(archivo.length());
                archivo.writeChars(numeroCedula);
                archivo.writeChars(nombre);
                archivo.writeChars(apellido);
                archivo.writeChars(telefono);
                archivo.writeBoolean(afiliado);
            }else{
                archivo.writeChars(numeroCedula);
                archivo.writeChars(nombre);
                archivo.writeChars(apellido);
                archivo.writeChars(telefono);         
                archivo.writeBoolean(afiliado);
            }
            
        }catch(Exception e)
        {
            System.out.println("error al ingresar");
        }
    }
    
    static public ArrayList<Usuario> leerArchivo() throws FileNotFoundException, IOException {
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String raiz = System.getProperty("user.dir");
        String cedula="";
        String nombre="";
        String apellido="";
        String telefono="";
        boolean afiliado = true;
        
        double cregistros;
        int tregistros = 201;
        File arch = new File(raiz+"\\USUARIOS.dat");
        RandomAccessFile archivo = new RandomAccessFile(arch, "r"); 
        cregistros = archivo.length()/tregistros;
        System.out.println(cregistros);
        for(int r=0; r<cregistros; r++){
            cedula="";
            nombre="";
            apellido="";
            telefono="";
            for(int i=0;i<25;i++){
                cedula+=archivo.readChar();
            }
            for(int i=0;i<25;i++){
                nombre+=archivo.readChar();
            }
            for(int i=0;i<25;i++){
                apellido+=archivo.readChar();
            }
            for(int i=0;i<25;i++){
                telefono+=archivo.readChar();
            }
            afiliado = archivo.readBoolean();
            usuarios.add(new Usuario(cedula,nombre,apellido,telefono,afiliado));
                        
        }
        return usuarios;
        
        
    }
}
