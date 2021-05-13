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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    //329

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
                RandomAccessFile archivo = new RandomAccessFile(raiz + "\\LIBROS.dat", "rw");
                archivo.seek(0);
                String auxCodigo = "";

                while (!auxCodigo.equals(codigo)) {
                    for (int i = 0; i < 4; i++) {
                        auxCodigo += archivo.readChar();
                    }

                    if (auxCodigo.equals(codigo)) {
                        archivo.seek(archivo.getFilePointer() + 320);
                        archivo.writeBoolean(disponibilidad);
                    } else {
                        archivo.seek(321);
                    }
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
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
                RandomAccessFile archivo = new RandomAccessFile(raiz + "\\LIBROS.dat", "rw");
                archivo.seek(0);
                String auxCodigo = "";

                while (!auxCodigo.equals(codigo)) {
                    for (int i = 0; i < 4; i++) {
                        auxCodigo += archivo.readChar();
                    }

                    if (auxCodigo.equals(codigo)) {
                        archivo.seek(archivo.getFilePointer() + 320);
                        archivo.writeBoolean(disponibilidad);
                    } else {
                        archivo.seek(321);
                    }
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
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

    public String obtenerGenero() {
        return genero;
    }

    public boolean obtenerDisponibilidad() {
        return disponibilidad;
    }

    public String[] obtenerDatos() {
        return new String[]{codigo, titulo, autor, String.valueOf(disponibilidad)};
    }

    public void verificarCodigo() {
        if (codigo.length() < 4) {
            while (codigo.length() != 4) {
                codigo += " ";
            }
        } else {
            codigo.substring(0, 4);
        }

    }

    public void verificarTitulo() {
        if (titulo.length() < 100) {
            while (titulo.length() != 100) {
                titulo += " ";
            }
        } else {
            titulo.substring(0, 100);
        }

    }

    public void verificarAutor() {
        if (autor.length() < 30) {
            while (autor.length() != 30) {
                autor += " ";
            }
        } else {
            autor.substring(0, 30);
        }

    }

    public void verificarGenero() {
        if (genero.length() < 30) {
            while (genero.length() != 30) {
                genero += " ";
            }
        } else {
            genero.substring(0, 30);
        }

    }

    public void agregarLibro() {

        verificarCodigo();
        verificarTitulo();
        verificarAutor();
        verificarGenero();
        try {
            RandomAccessFile archivo = new RandomAccessFile(raiz + "\\LIBROS.dat", "rw");
            if (archivo.length() != 0) {
                archivo.seek(archivo.length());
                archivo.writeChars(codigo);
                archivo.writeChars(titulo);
                archivo.writeChars(autor);
                archivo.writeChars(genero);
                archivo.writeBoolean(disponibilidad);
            } else {
                archivo.writeChars(codigo);
                archivo.writeChars(titulo);
                archivo.writeChars(autor);
                archivo.writeChars(genero);
                archivo.writeBoolean(disponibilidad);
            }

        } catch (Exception e) {
            System.out.println("error al ingresar");
        }
    }

    static public ArrayList<Libro> cargarLibros() {
        ArrayList<Libro> libros = new ArrayList<>();
        String raiz = System.getProperty("user.dir");
        File arch = new File(raiz + "\\Libros.dat");
        long cregistros;

        try {
            RandomAccessFile archivo = new RandomAccessFile(arch, "r");
            //329 caracteres cada registro
            int tregistro = 329;
            cregistros = archivo.length() / tregistro;

            for (int r = 0; r < cregistros; r++) {
                String codigo = "";
                String titulo = "";
                String autor = "";
                String genero = "";

                for (int j = 0; j < 4; j++) {
                    codigo += archivo.readChar();
                }
                for (int j = 0; j < 100; j++) {
                    titulo += archivo.readChar();
                }
                for (int j = 0; j < 30; j++) {
                    autor += archivo.readChar();
                }
                for (int j = 0; j < 30; j++) {
                    genero += archivo.readChar();
                }
                libros.add(new Libro(codigo.trim(), titulo.trim(), autor.trim(), genero.trim(), archivo.readBoolean()));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return libros;

    }

    static public ArrayList<Libro> LibrosAutor(String autorBusqueda) {
        ArrayList<Libro> libros = new ArrayList<>();
        String raiz = System.getProperty("user.dir");
        File arch = new File(raiz + "\\Libros.dat");
        long cregistros;

        try {
            RandomAccessFile archivo = new RandomAccessFile(arch, "r");
            //329 caracteres cada registro
            int tregistro = 329;
            cregistros = archivo.length() / tregistro;

            String codigo = "";
            String titulo = "";
            String autor = "";
            String genero = "";

            System.out.println(cregistros);
            for (int r = 0; r < cregistros; r++) {
                for (int j = 0; j < 4; j++) {
                    codigo += archivo.readChar();
                }
                for (int j = 0; j < 100; j++) {
                    titulo += archivo.readChar();
                }
                for (int j = 0; j < 30; j++) {
                    autor += archivo.readChar();
                }
                for (int j = 0; j < 30; j++) {
                    genero += archivo.readChar();
                }
                if (autor.equals(autorBusqueda)) {
                    libros.add(new Libro(codigo.trim(), titulo.trim(), autor.trim(), genero.trim(), archivo.readBoolean()));
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return libros;
    }
}
