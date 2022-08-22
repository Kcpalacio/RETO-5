package reto4.view;

import reto4.controller.ReportesController;
import reto4.model.vo.*;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ReportesView extends JFrame implements ActionListener{
        private ReportesController controller;
        private JMenuBar mMenuBar;
        private JMenu menu;
        private JMenuItem i1, i2, i3;
        private DefaultTableModel modelo;
        private JTable table;
        private JLabel lblTitulo, lblConsulta;
        // private JPanel panel;


        public ReportesView(){
            controller = new ReportesController();
            menu();
            etiqueta1();
            etiqueta2();
            tabla();
        }

        //Eti-titulo
        public void etiqueta1(){
            lblTitulo = new JLabel("Proyectos Construcción");
            lblTitulo.setBounds(10,10 ,500,30);
            //lblTitulo.setPreferredSize(new Dimension(500,30));
            lblTitulo.setForeground(Color.decode("#5170B8"));
            lblTitulo.setFont(new Font("Arial", Font.BOLD,20));
            add(lblTitulo);  
        }
        //Eti-Cambiando segun la consulta
        public void etiqueta2(){
            lblConsulta = new JLabel();
            lblConsulta.setPreferredSize(new Dimension(500,20));
            lblConsulta.setForeground(Color.decode("#5170B8"));
            lblConsulta.setFont(new Font("Arial", Font.BOLD,14));
            //lblConsulta.setOpaque(true); // Activar el fondo 
            //lblConsulta.setBackground(Color.lightGray); // Colocarle color al fondo 
            add(lblConsulta);  
        }


        public void tabla(){
            table = new JTable(modelo);
            table.setPreferredScrollableViewportSize(new Dimension(500,200));
            add(table);
            JScrollPane  pane = new JScrollPane (table);
            add(pane);
        }

        public void menu(){
            mMenuBar = new JMenuBar();
            setJMenuBar(mMenuBar);
            menu = new JMenu("CONSULTAS");
            menu.setForeground(Color.decode("#4B68AB"));
            mMenuBar.add(menu);
            i1= new JMenuItem("Lideres");
            i2= new JMenuItem("Proyectos");
            i3= new JMenuItem("Compras");
            menu.add(i1);
            menu.add(i2);
            menu.add(i3);
            i1.addActionListener(this);
            i2.addActionListener(this);
            i3.addActionListener(this);
        }
        
        //----- PRIMER INFORME "Primer Informe: Lideres de proyectos LideresDeProyectoVo      "------------------------------
        public void lideresQueMasGastan(){
            try{
                List<LideresDeProyectoVo> compras = controller.listarLideresCompras();
                // Creacion del modelo 
                modelo = new DefaultTableModel();
                modelo.addColumn("Id");
                modelo.addColumn("Nombre");
                modelo.addColumn("Apellido");
                modelo.addColumn("Ciudad");
                for (LideresDeProyectoVo com: compras){
                    Object[] fila = new Object[4];
                    fila[0]=com.getId();
                    fila[1]=com.getNombre(); 
                    fila[2]=com.getApellido();
                    fila[3]=com.getCiudad();
                    modelo.addRow(fila);
                }
                table.setModel(modelo);
                modelo.fireTableDataChanged();

            }
            catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }


        //------   SEGUNDO INFORME "Segundo Informe: Casa Campestre     CasaCampestreVo     CasaCampestreVo "--------------------------------
        public void proyectosFinanciadosPorBanco(){
            try{
                List<CasaCampestreVo> proyectos = controller.listarProyectosPorBanco();
                modelo = new DefaultTableModel();
                modelo.addColumn("Id Proyecto");
                modelo.addColumn("Constructora");
                modelo.addColumn("Habitaciones");
                modelo.addColumn("Ciudad");
                for (CasaCampestreVo proy : proyectos){
                    Object[] fila = new Object[4];
                    fila[0] = proy.getId();
                    fila[1] = proy.getConstructora();
                    fila[2] = proy.getHabitaciones();
                    fila[3] = proy.getCiudad();
                    modelo.addRow(fila);              
                }
                table.setModel(modelo);
                modelo.fireTableDataChanged();
            }
            catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        //------ TERCER INFORME Tercer Informe: Compras realizadas a Homecenter  ComprasHomecenterVo" --------------------------------
        public void totalAdeudadoPorProyectosSuperioresALimite() {
                      
            try{
                List<ComprasHomecenterVo> compras = controller.listarTotalAdeudadoProyectos();
                modelo = new DefaultTableModel();
                modelo.addColumn("Id Compra");
                modelo.addColumn("Constructora");
                modelo.addColumn("Banco Vinculado"); 
                for(ComprasHomecenterVo comp: compras){
                    Object[] fila = new Object[3];
                    fila[0] = comp.getId();
                    fila[1] = comp.getConstructora();
                    fila[2] = comp.getBanco();
                    modelo.addRow(fila);
                }
                table.setModel(modelo);
                modelo.fireTableDataChanged();
            }
            catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
  
        }

        

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == i1){ 
                lideresQueMasGastan();
                lblConsulta.setText("Primer Informe: Lideres de proyectos");
                
            }
            if (e.getSource()== i2){
                proyectosFinanciadosPorBanco();
                lblConsulta.setText("Segundo Informe: Casa Campestre");

            }
            if (e.getSource()== i3){
                totalAdeudadoPorProyectosSuperioresALimite();
                lblConsulta.setText("Tercer Informe: Compras realizadas a Homecenter");

            }
  
        }
}
