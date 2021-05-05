package biblioteca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alang
 */
public class Libro {

    private String codigo;
    private String titulo;
    private String autor;
    private String genero;
    //private ArrayList<Usuario> fichaHistorica;
    private boolean disponibilidad;

    public Libro(String codigo, String titulo, String autor, String genero, boolean disponibilidad) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponibilidad = disponibilidad;
        //fichaHistorica = new ArrayList<Usuario>();
    }

    public Libro() {

    }

    public boolean prestar(Usuario datosUsuario) {
        if (disponibilidad) {
            //fichaHistorica.add(datosUsuario);
            disponibilidad = false;

            try {
                BufferedReader file = new BufferedReader(new FileReader("C:\\Users\\jackr\\OneDrive - Pontificia Universidad Católica del Ecuador\\INGENIERÍA EN SISTEMAS\\CUARTO SEMESTRE\\PROGRAMACIÓN ORIENTADA A OBJETOS\\Biblioteca\\Biblioteca\\src\\biblioteca\\LIBROS.txt"));
                String line;
                String input = "";
                while ((line = file.readLine()) != null) {
                    /* Podemos verificar si es Usuario_1 y \r\n es para hacer el 
                     Salto de Línea y tener el formato original */
                    if (line.contains(codigo)) {
                        input += line.replaceAll("true", "false\r\n");
                    } else {
                        input += line + "\r\n";
                    }
                }
                FileOutputStream fileOut = new FileOutputStream("C:\\Users\\jackr\\OneDrive - Pontificia Universidad Católica del Ecuador\\INGENIERÍA EN SISTEMAS\\CUARTO SEMESTRE\\PROGRAMACIÓN ORIENTADA A OBJETOS\\Biblioteca\\Biblioteca\\src\\biblioteca\\LIBROS.txt");
                fileOut.write(input.getBytes());
                fileOut.close();
            } catch (FileNotFoundException e) {
                System.out.print(e.getMessage());
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }

            return true;
        } else {
            return false;
        }

    }

    public boolean devolver() {
        if (!disponibilidad) {
            disponibilidad = true;
            try {
                BufferedReader file = new BufferedReader(new FileReader("C:\\Users\\jackr\\OneDrive - Pontificia Universidad Católica del Ecuador\\INGENIERÍA EN SISTEMAS\\CUARTO SEMESTRE\\PROGRAMACIÓN ORIENTADA A OBJETOS\\Biblioteca\\Biblioteca\\src\\biblioteca\\LIBROS.txt"));
                String line;
                String input = "";
                while ((line = file.readLine()) != null) {
                    /* Podemos verificar si es Usuario_1 y \r\n es para hacer el 
                     Salto de Línea y tener el formato original */
                    if (line.contains(codigo)) {
                        input += line.replaceAll("false", "true\r\n");
                    } else {
                        input += line + "\r\n";
                    }
                }
                FileOutputStream fileOut = new FileOutputStream("C:\\Users\\jackr\\OneDrive - Pontificia Universidad Católica del Ecuador\\INGENIERÍA EN SISTEMAS\\CUARTO SEMESTRE\\PROGRAMACIÓN ORIENTADA A OBJETOS\\Biblioteca\\Biblioteca\\src\\biblioteca\\LIBROS.txt");
                fileOut.write(input.getBytes());
                fileOut.close();
            } catch (FileNotFoundException e) {
                System.out.print(e.getMessage());
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
            return true;
        } else {
            return false;
        }
    }

    public String obtenerCodigo() {
        return this.codigo;
    }

    public String obtenerTitulo() {
        return this.titulo;
    }

    public String obtenerAutor() {
        return this.autor;
    }

    public boolean obtenerDisponibilidad() {
        return disponibilidad;
    }

    public String[] obtenerDatos() {
        return new String[]{codigo, autor, titulo, String.valueOf(disponibilidad)};
    }
    
    public void agregarLibro(String codigo, String titulo, String autor, String genero) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            String data = "Código:"+codigo+"; Nombre:"+titulo+"; Autor:"+autor+"; Género:"+genero+"; Disponibilidad:"+true+"\n";
            File file = new File("C:\\Users\\jackr\\OneDrive - Pontificia Universidad Católica del Ecuador\\INGENIERÍA EN SISTEMAS\\CUARTO SEMESTRE\\PROGRAMACIÓN ORIENTADA A OBJETOS\\Biblioteca\\Biblioteca\\src\\biblioteca\\LIBROS.txt");
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
