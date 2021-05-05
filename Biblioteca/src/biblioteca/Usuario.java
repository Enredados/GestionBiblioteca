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

    public void agregarUsuario(String cedula, String nombre, String apellido, String telefono, boolean afiliado) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            String data = "Numero de cedula:" + cedula + "; Nombre:" + nombre + "; Apellido:" + apellido + "; Telefono:" + telefono + "; Afiliado:" + afiliado + "\n";
            File file = new File("C:\\Users\\jackr\\OneDrive - Pontificia Universidad Católica del Ecuador\\INGENIERÍA EN SISTEMAS\\CUARTO SEMESTRE\\PROGRAMACIÓN ORIENTADA A OBJETOS\\Biblioteca\\Biblioteca\\src\\biblioteca\\USUARIOS.txt");
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
}
