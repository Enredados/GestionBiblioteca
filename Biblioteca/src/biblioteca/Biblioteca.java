/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS
 */
public class Biblioteca {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            
            String raiz = System.getProperty("user.dir");
        try {
            // TODO code application logic here
            String arrLibros[];
            String arrBibliotecario[];
            String arr3[];
            String arrUsuario[];
            String[] lib=new String[5];
            String[] bib=new String[6];
            String[] usu=new String[5];
            
            File libros2 = new File(raiz+"\\LIBROS.txt");
            File bibliotecarios2 = new File(raiz+"\\BIBLIOTECARIOS.txt");
            File usuarios2 = new File(raiz+"\\USUARIOS.txt");
            
            arrLibros=desplegarArchivo(libros2);
            arrBibliotecario=desplegarArchivo(bibliotecarios2);
            arrUsuario=desplegarArchivo(usuarios2);
            
            ArrayList<Libro> libros = Libro.cargarLibros();
            ArrayList<Bibliotecario> bibliotecarios = new ArrayList<>();
            ArrayList<Usuario> usuarios = new ArrayList<>();
            
            for(int i=0;i<arrLibros.length;i++){
                arr3 = arrLibros[i].split(";");
                for(int j=0;j<arr3.length;j++){
                    String arr4[]=arr3[j].split(":");
                    lib[j]=arr4[1];
                }
                    libros.add(new Libro(lib[0],lib[1],lib[2],lib[3],Boolean.parseBoolean(lib[4].trim())));
                    
            }
            
            for(int i=0;i<arrBibliotecario.length;i++){
                arr3 = arrBibliotecario[i].split(";");
                
                for(int j=0;j<arr3.length;j++){
                    String arr4[]=arr3[j].split(":");
                    bib[j]=arr4[1];
                 
                }
                bibliotecarios.add(new Bibliotecario(bib[0],bib[1],bib[2],bib[3],bib[4],bib[5]));
            }
            
            for(int i=0;i<arrUsuario.length;i++){
                arr3 = arrUsuario[i].split(";");
                
                for(int j=0;j<arr3.length;j++){
                    String arr4[]=arr3[j].split(":");
                    usu[j]=arr4[1];
                 
                }
                    usuarios.add(new Usuario(usu[0],usu[1],usu[2],usu[3],Boolean.parseBoolean(usu[4].trim())));
                
                
            }
  
            ConsultaGeneral cg = new ConsultaGeneral(usuarios, bibliotecarios);
            cg.setSize(700,650);
            cg.setVisible(true);
            
        } catch (IOException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    static String[] desplegarArchivo(File f) throws IOException {
        FileReader archivo;
        BufferedReader filtrado;
        String aux="";
        try{
            archivo = new FileReader(f);
            filtrado = new BufferedReader(archivo);
            String linea = "";
            while(linea!=null){
                linea=filtrado.readLine();
                if(linea!=null){
                    aux+=linea+"\n";
                }
            }
            String[] arr = aux.split("\n");
            return arr;
                
        }catch(FileNotFoundException e){
            e.getMessage();
        }
        return null;
    }
    
   
    
}
