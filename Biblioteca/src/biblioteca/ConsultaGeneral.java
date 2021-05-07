/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import static biblioteca.Biblioteca.desplegarArchivo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class ConsultaGeneral extends javax.swing.JFrame {

    String raiz = System.getProperty("user.dir");
    ArrayList<Libro> libros = new ArrayList<>();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Bibliotecario> bibliotecarios = new ArrayList<>();
    File libros2 = new File(raiz+"\\LIBROS.txt");

    private String[] desplegarArchivo(File f) throws IOException {
        FileReader archivo;
        BufferedReader filtrado;
        String aux = "";
        try {
            archivo = new FileReader(f);
            filtrado = new BufferedReader(archivo);
            String linea = "";
            while (linea != null) {
                linea = filtrado.readLine();
                if (linea != null) {
                    aux += linea + "\n";
                }
            }
            String[] arr = aux.split("\n");
            return arr;

        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return null;
    }

    private void agregarLibros(String[] arrLibros) throws IOException {
        String[] arr3;
        String[] lib = new String[5];
        for (int i = 0; i < arrLibros.length; i++) {
            arr3 = arrLibros[i].split(";");
            for (int j = 0; j < arr3.length; j++) {
                String arr4[] = arr3[j].split(":");
                lib[j] = arr4[1];
            }
            libros.add(new Libro(lib[0], lib[1], lib[2], lib[3], Boolean.parseBoolean(lib[4].trim())));

        }
    }

    /**
     * Creates new form ConsultaGeneral
     */
    public ConsultaGeneral() throws IOException {
        initComponents();
        setLocationRelativeTo(null);
        agregarLibros(desplegarArchivo(libros2));
    }

    public ConsultaGeneral(ArrayList<Usuario> usuarios, ArrayList<Bibliotecario> bibliotecarios) throws IOException {
        initComponents();
        this.usuarios = usuarios;
        this.bibliotecarios = bibliotecarios;
        setLocationRelativeTo(null);
        agregarLibros(desplegarArchivo(libros2));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        consultaTxt = new javax.swing.JTextField();
        consultaBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        consultaTabla = new javax.swing.JTable();
        consultaTipo = new javax.swing.JComboBox<>();
        consultaLimpiar = new javax.swing.JButton();
        consultaBuscar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Consulta por Código");

        consultaTxt.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        consultaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaTxtActionPerformed(evt);
            }
        });

        consultaBuscar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        consultaBuscar.setText("Buscar");
        consultaBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaBuscarActionPerformed(evt);
            }
        });

        consultaTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Título", "Autor", "Disponible"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(consultaTabla);

        consultaTipo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        consultaTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Título", "Autor", "Código" }));
        consultaTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaTipoActionPerformed(evt);
            }
        });

        consultaLimpiar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        consultaLimpiar.setText("Limpiar tabla");
        consultaLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaLimpiarActionPerformed(evt);
            }
        });

        consultaBuscar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        consultaBuscar1.setText("LOGIN");
        consultaBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaBuscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 89, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(consultaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(consultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(consultaLimpiar)
                            .addComponent(consultaBuscar))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(consultaBuscar1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consultaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(consultaBuscar)
                .addGap(18, 18, 18)
                .addComponent(consultaLimpiar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(consultaBuscar1)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void consultaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consultaTxtActionPerformed

    private void consultaBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaBuscarActionPerformed
        // TODO add your handling code here:
        String consulta = consultaTxt.getText();
        int index = consultaTipo.getSelectedIndex();
        Libro miLibro = new Libro();
        try {
            switch (index) {
                case 0:
                    miLibro = busqueda(consulta, index);
                    break;
                case 1:
                    miLibro = busqueda(consulta, index);
                    break;
                case 2:
                    miLibro = busqueda(consulta, index);
                    break;
            }

            // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            DefaultTableModel model = (DefaultTableModel) consultaTabla.getModel();

            String[] elementos = miLibro.obtenerDatos();
            model.insertRow(model.getRowCount(), new Object[]{elementos[0], elementos[1], elementos[2], elementos[3]});

            // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo encontrar el libro");
        }

    }//GEN-LAST:event_consultaBuscarActionPerformed

    private Libro busqueda(String porBuscar, int tipo) {
        Libro miLibro = null;
        switch (tipo) {
            case 0:
                for (Libro libroTemp : libros) {
                    if (porBuscar.equals(libroTemp.obtenerTitulo())) {
                        miLibro = libroTemp;
                    }
                }
                break;
            case 1:
                for (Libro libroTemp : libros) {
                    if (porBuscar.equals(libroTemp.obtenerAutor())) {
                        miLibro = libroTemp;
                    }
                }
                break;
            case 2:
                for (Libro libroTemp : libros) {
                    if (porBuscar.equals(libroTemp.obtenerCodigo())) {
                        miLibro = libroTemp;
                    }
                }
                break;
        }
        return miLibro;
    }
    private void consultaTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consultaTipoActionPerformed

    private void consultaLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaLimpiarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) consultaTabla.getModel();

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }//GEN-LAST:event_consultaLimpiarActionPerformed

    private void consultaBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaBuscar1ActionPerformed
        this.setVisible(false);
        Login lg = new Login(libros, usuarios, bibliotecarios);
        lg.setSize(780, 670);
        lg.setVisible(true);
    }//GEN-LAST:event_consultaBuscar1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultaGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ConsultaGeneral().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ConsultaGeneral.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton consultaBuscar;
    private javax.swing.JButton consultaBuscar1;
    private javax.swing.JButton consultaLimpiar;
    private javax.swing.JTable consultaTabla;
    private javax.swing.JComboBox<String> consultaTipo;
    private javax.swing.JTextField consultaTxt;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
