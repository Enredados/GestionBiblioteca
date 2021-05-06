package biblioteca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.RandomAccess;

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

    String raiz = System.getProperty("user.dir");
    
    //codigo 4 caracteres
    private String codigo;
    //titulo 100 caracteres
    private String titulo;
    //autor 30 caracteres
    private String autor;
    //genero 30 caracteres
    private String genero;
    //disponibilidad 1
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
                BufferedReader file = new BufferedReader(new FileReader(raiz +"\\LIBROS.txt"));
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
                FileOutputStream fileOut = new FileOutputStream(raiz+"\\LIBROS.txt");
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
                BufferedReader file = new BufferedReader(new FileReader(raiz+"\\LIBROS.txt"));
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
                FileOutputStream fileOut = new FileOutputStream(raiz+"\\LIBROS.txt");
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
    
    public boolean verificarCodigo()
    {
        if(codigo.length() < 25)
        {
            while(codigo.length() != 25){
                codigo += " ";
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean agregarLibro(String codigo, String titulo, String autor, String genero) {
   
        try {
            String data = "Código:"+codigo+"; Nombre:"+titulo+"; Autor:"+autor+"; Género:"+genero+"; Disponibilidad:"+true+"\n";
            RandomAccessFile archivo = new RandomAccessFile(raiz+"\\LIBROS.dat", "rw");
            
            return true;
        }catch(Exception e)
        {
            System.out.println("error al ingresar");
            return false;
        }
    }
}
