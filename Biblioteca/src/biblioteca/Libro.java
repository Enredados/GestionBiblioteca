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
    
    public void verificarCodigo()
    {
        if(codigo.length() < 25)
        {
            while(codigo.length() != 25){
                codigo += " ";
            }
        }
        else
        {
             codigo.substring(0,25);
        }
        
    }
    public void verificarTitulo()
    {
        if(titulo.length() < 100)
        {
            while(titulo.length() != 100){
                titulo += " ";
            }
        }
        else
        {
            titulo.substring(0,100);
        }
        
    }
    public void verificarAutor()
    {
        if(autor.length() < 30)
        {
            while(autor.length() != 30){
                autor += " ";
            }
        }
        else
        {
            autor.substring(0,30);
        }
        
    }
    public void verificarGenero()
    {
        if(genero.length() < 30)
        {
            while(genero.length() != 30){
                genero += " ";
            }
        }
        else
        {
            genero.substring(0,30);
        }
        
    }
    
    public void agregarLibro() {
   
        verificarCodigo();
        verificarTitulo();
        verificarAutor();
        verificarGenero();
        try {
            RandomAccessFile archivo = new RandomAccessFile(raiz+"\\LIBROS.dat", "rw");
            if (archivo.length()!=0){
                archivo.seek(archivo.length());
                archivo.writeChars(codigo);
                archivo.writeChars(titulo);
                archivo.writeChars(autor);
                archivo.writeChars(genero);
                archivo.writeBoolean(disponibilidad);
            }else{
                archivo.writeChars(codigo);
                archivo.writeChars(titulo);
                archivo.writeChars(autor);
                archivo.writeChars(genero);
                archivo.writeBoolean(disponibilidad);
            }
            
        }catch(Exception e)
        {
            System.out.println("error al ingresar");
        }
    }
}
