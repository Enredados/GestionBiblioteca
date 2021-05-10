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
                BufferedReader file = new BufferedReader(new FileReader(raiz + "\\LIBROS.txt"));
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
                FileOutputStream fileOut = new FileOutputStream(raiz + "\\LIBROS.txt");
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
                BufferedReader file = new BufferedReader(new FileReader(raiz + "\\LIBROS.txt"));
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
                FileOutputStream fileOut = new FileOutputStream(raiz + "\\LIBROS.txt");
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

    public void verificarCodigo() {
        if (codigo.length() < 25) {
            while (codigo.length() != 25) {
                codigo += " ";
            }
        } else {
            codigo.substring(0, 25);
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

    public void agregarUsuarioTxt() {
        String raiz = System.getProperty("user.dir");

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            String data = "Código:" + codigo + "; Nombre:" + titulo + "; Autor:" + autor + "; Género:" + genero + "; Disponibilidad:" + disponibilidad + "\n";
            File file = new File(raiz + "\\LIBROS.txt");
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
            //165 caracteres cada registro
            int tregistro = 265;
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
                for (int j = 0; j < 4; j++) {
                    titulo += archivo.readChar();
                }
                for (int j = 0; j < 4; j++) {
                    autor += archivo.readChar();
                }
                for (int j = 0; j < 4; j++) {
                    genero += archivo.readChar();
                }
                libros.add(new Libro(codigo,titulo,autor,genero, archivo.readBoolean()));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return libros;

    }
}
