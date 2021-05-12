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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alang
 */
public class Bibliotecario extends Persona {

    String raiz = System.getProperty("user.dir");
    private String usuario;
    private String clave;

    public Bibliotecario() {
        super("", "", "", "");

    }

    public Bibliotecario(String numeroCedula, String nombre, String apellido, String telefono, String usuario, String clave) {
        super(numeroCedula, nombre, apellido, telefono);
        this.usuario = usuario;
        this.clave = clave;
    }

    public String obtenerUsuario() {
        return usuario;
    }

    public String obtenerClave() {
        return clave;
    }

    public void verificarNumeroCedula() {
        if (numeroCedula.length() < 25) {
            while (numeroCedula.length() != 25) {
                numeroCedula += " ";
            }
        } else {
            numeroCedula.substring(0, 25);
        }
    }

    public void verificarNombre() {
        if (nombre.length() < 25) {
            while (nombre.length() != 25) {
                nombre += " ";
            }
        } else {
            nombre.substring(0, 25);
        }
    }

    public void verificarApellido() {
        if (apellido.length() < 25) {
            while (apellido.length() != 25) {
                apellido += " ";
            }
        } else {
            apellido.substring(0, 25);
        }
    }

    public void verificarTelefono() {
        if (telefono.length() < 25) {
            while (telefono.length() != 25) {
                telefono += " ";
            }
        } else {
            telefono.substring(0, 25);
        }
    }

    public void verificarUsuario() {
        if (usuario.length() < 25) {
            while (usuario.length() != 25) {
                usuario += " ";
            }
        } else {
            usuario.substring(0, 25);
        }
    }

    public void verificarClave() {
        if (clave.length() < 25) {
            while (clave.length() != 25) {
                clave += " ";
            }
        } else {
            clave.substring(0, 25);
        }
    }

    public void agregarBibliotecarioRandomico() {
        verificarNombre();
        verificarApellido();
        verificarNumeroCedula();
        verificarTelefono();
        verificarUsuario();
        verificarClave();
        try {
            RandomAccessFile archivo = new RandomAccessFile(raiz + "\\BIBLIOTECARIOS.dat", "rw");
            if (archivo.length() != 0) {

                archivo.seek(archivo.length());
                archivo.writeChars(numeroCedula);
                archivo.writeChars(nombre);
                archivo.writeChars(apellido);
                archivo.writeChars(telefono);
                archivo.writeChars(usuario);
                archivo.writeChars(clave);
            } else {
                archivo.writeChars(numeroCedula);
                archivo.writeChars(nombre);
                archivo.writeChars(apellido);
                archivo.writeChars(telefono);
                archivo.writeChars(usuario);
                archivo.writeChars(clave);
            }

        } catch (Exception e) {
            System.out.println("error al ingresar");
        }
    }

    static public ArrayList<Bibliotecario> leerArchivo() {

        ArrayList<Bibliotecario> bibliotecarios = new ArrayList<>();
        String raiz = System.getProperty("user.dir");
        String cedula = "";
        String nombre = "";
        String apellido = "";
        String telefono = "";
        String usuario = "";
        String clave = "";  

        double cregistros;
        int tregistros = 300;
        try {
            File arch = new File(raiz + "\\BIBLIOTECARIOS.dat");
            RandomAccessFile archivo = new RandomAccessFile(arch, "r");
            cregistros = archivo.length() / tregistros;
           
            for (int r = 0; r < cregistros; r++) {
                cedula = "";
                nombre = "";
                apellido = "";
                telefono = "";
                for (int i = 0; i < 25; i++) {
                    cedula += archivo.readChar();
                }
                for (int i = 0; i < 25; i++) {
                    nombre += archivo.readChar();
                }
                for (int i = 0; i < 25; i++) {
                    apellido += archivo.readChar();
                }
                for (int i = 0; i < 25; i++) {
                    telefono += archivo.readChar();
                }
                for (int i = 0; i < 25; i++) {
                    usuario += archivo.readChar();
                }
                for (int i = 0; i < 25; i++) {
                    clave += archivo.readChar();
                }
                bibliotecarios.add(new Bibliotecario(cedula.trim(), nombre.trim(), apellido.trim(), telefono.trim(), usuario.trim(), clave.trim()));

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bibliotecario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Bibliotecario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bibliotecarios;

    }
}
