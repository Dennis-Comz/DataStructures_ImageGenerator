package Interfaz;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import Estructuras.ArbolB;
import Proyecto_Fase2.Actividades;

public class Administracion implements ActionListener{
    JFrame frame = new JFrame("UDrawing Paper Administracion");
    JPanel panel = new JPanel();
    JPanel cargas, reportes;
    JButton cargaMasiva, insertar_cliente, modificar_cliente, eliminar_cliente, buscar_cliente;
    JButton listar_Clientes, cerrar_sesion;
    DefaultTableModel modelClientes;
    JTable tableClientes;
    JScrollPane scrollClientes;
    JFrame lg_frame;
    JFileChooser fileChooser;
    JLabel img_label;

    public Administracion(JFrame lg){
        lg_frame = lg;
        lg_frame.dispose();;
        display();
    }

    public void display() {
        frame.setSize(1200,800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        frame.add(panel);

        cargaMasiva = new JButton("Cargar Clientes");
        cargaMasiva.setForeground(Color.WHITE);
        cargaMasiva.setFont(new Font("Arial", Font.PLAIN, 15));
        cargaMasiva.setBounds(800, 100, 150,50);
        cargaMasiva.setBackground(Color.decode("#0EA9F5"));
        cargaMasiva.addActionListener(this);
        cargaMasiva.setActionCommand("cargar");
        panel.add(cargaMasiva);

        insertar_cliente = new JButton("Crear Cliente");
        insertar_cliente.setForeground(Color.WHITE);
        insertar_cliente.setFont(new Font("Arial", Font.PLAIN, 15));
        insertar_cliente.setBounds(975, 100, 150,50);
        insertar_cliente.setBackground(Color.decode("#0EA9F5"));
        insertar_cliente.addActionListener(this);
        insertar_cliente.setActionCommand("insertar");
        panel.add(insertar_cliente);

        modificar_cliente = new JButton("Modificar Cliente");
        modificar_cliente.setForeground(Color.WHITE);
        modificar_cliente.setFont(new Font("Arial", Font.PLAIN, 15));
        modificar_cliente.setBounds(800, 175, 150,50);
        modificar_cliente.setBackground(Color.decode("#0EA9F5"));
        modificar_cliente.addActionListener(this);
        modificar_cliente.setActionCommand("modificar");
        panel.add(modificar_cliente);

        eliminar_cliente = new JButton("Eliminar Cliente");
        eliminar_cliente.setForeground(Color.WHITE);
        eliminar_cliente.setFont(new Font("Arial", Font.PLAIN, 15));
        eliminar_cliente.setBounds(975, 175, 150,50);
        eliminar_cliente.setBackground(Color.decode("#0EA9F5"));
        eliminar_cliente.addActionListener(this);
        eliminar_cliente.setActionCommand("eliminar");
        panel.add(eliminar_cliente);

        buscar_cliente = new JButton("Buscar Cliente");
        buscar_cliente.setForeground(Color.WHITE);
        buscar_cliente.setFont(new Font("Arial", Font.PLAIN, 15));
        buscar_cliente.setBounds(800, 250, 150,50);
        buscar_cliente.setBackground(Color.decode("#0EA9F5"));
        buscar_cliente.addActionListener(this);
        buscar_cliente.setActionCommand("buscar");
        panel.add(buscar_cliente);

        listar_Clientes = new JButton("Listar Clientes");
        listar_Clientes.setForeground(Color.WHITE);
        listar_Clientes.setFont(new Font("Arial", Font.PLAIN, 15));
        listar_Clientes.setBounds(975, 250, 150,50);
        listar_Clientes.setBackground(Color.decode("#0EA9F5"));
        listar_Clientes.addActionListener(this);
        listar_Clientes.setActionCommand("listar");
        panel.add(listar_Clientes);

        tabla();

        JLabel descripcion = new JLabel("Arbol de Clientes:");
        descripcion.setBounds(50, 385, 150, 25);
        descripcion.setForeground(Color.WHITE);
        panel.add(descripcion);

        JPanel panel_imagen = new JPanel();
        panel_imagen.setBackground(Color.decode("#2E2E2E"));
        img_label = new JLabel();
        img_label.setOpaque(true);
        panel_imagen.add(img_label);
        JScrollPane scrollPane = new JScrollPane(panel_imagen);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 415, 1100, 300);
        scrollPane.setBackground(Color.decode("#2E2E2E"));
        panel.add(scrollPane);

        cerrar_sesion = new JButton("Cerrar Sesion");
        cerrar_sesion.setBounds(1000, 10, 150, 25);
        cerrar_sesion.setForeground(Color.WHITE);
        cerrar_sesion.setFont(new Font("Arial", Font.PLAIN, 15));
        cerrar_sesion.setBackground(Color.decode("#0EA9F5"));
        cerrar_sesion.addActionListener(this);
        cerrar_sesion.setActionCommand("salir");
        panel.add(cerrar_sesion);
        
        frame.setVisible(true);
    }

    public void tabla() {
        String[] columnasClientes = {"DPI", "Nombre", "Contraseña", "Cant. Imagenes"};
        modelClientes = new DefaultTableModel(null, columnasClientes){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableClientes = new JTable(modelClientes);
        tableClientes.setOpaque(true);
        tableClientes.setFillsViewportHeight(true);
        tableClientes.setBackground(Color.decode("#2E2E2E"));
        tableClientes.setForeground(Color.WHITE);
        scrollClientes = new JScrollPane(tableClientes);
        scrollClientes.setBackground(Color.decode("#2E2E2E"));
        tableClientes.getTableHeader().setBackground(Color.decode("#2E2E2E"));
        tableClientes.getTableHeader().setForeground(Color.WHITE);
        scrollClientes.setBounds(50,25,700,350);
        panel.add(scrollClientes);
    }
    
    public boolean leer_clientes(String path) {
        boolean exito = false;
        Path filename = Path.of(path);
        String contenido = "";
        try {
            contenido = Files.readString(filename);
            // Se lee el archivo 
            JsonParser parser = new JsonParser();
            JsonArray gsonArr = parser.parse(contenido).getAsJsonArray();
            // Ciclo para los objetos json
            for (JsonElement obj : gsonArr) {
                JsonObject gsonObj = obj.getAsJsonObject();
                long dpi = gsonObj.get("dpi").getAsLong();
                String name = gsonObj.get("nombre_cliente").getAsString();
                String password = gsonObj.get("password").getAsString();
                Actividades.arbol_b.insertar(dpi, name, password);            
            }
            exito = true;
            // Actividades.arbol_b.traversal();
        } catch (Exception e) {}
        return exito;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cargar")){
            fileChooser = new JFileChooser();
            int op = fileChooser.showOpenDialog(frame);
            if (op == JFileChooser.APPROVE_OPTION) {
                if (leer_clientes(fileChooser.getSelectedFile().getAbsolutePath())) {
                    desplegar_info("Los clientes han sido cargados al sistema.");
                }else{
                    desplegar_error("El archivo seleccionado no es valido.");
                }
            }else{
                desplegar_error("No se ha seleccionado un archivo.");
            }
        }else if (e.getActionCommand().equals("insertar")) {
            new NewUser();
            llenar_tabla();
        }else if(e.getActionCommand().equals("modificar")){
            new Admin_Modificar();
            llenar_tabla();
        }else if(e.getActionCommand().equals("eliminar")){
            new Admin_Eliminar();
            llenar_tabla();
        }else if(e.getActionCommand().equals("buscar")){
            new Admin_Buscar();
        }else if(e.getActionCommand().equals("listar")){
            llenar_tabla();
            // Codigo para mostrar el arbol
            Actividades.arbol_b.traversal();
            File img = new File("C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\Arboles\\B\\ArbolB.png");
            if (img.exists()) {
                try {
                    BufferedImage picture = ImageIO.read(img);
                    img_label.setIcon(new ImageIcon(picture));
                } catch (Exception ex) {}
            }else{
                desplegar_error("No existen clientes en el sistema.");
            }
        }else if(e.getActionCommand().equals("salir")){
            frame.setVisible(false);
            lg_frame.setVisible(true);
        }
    }

    // EN ESTE SEGMENTO SE OBTIENE EL ARREGLO QUE PROPORCIONA EL ARBOL
    // PARA LLENAR LA TABLA
    public void llenar_tabla() {
        ArrayList<ArbolB.Nodo> clientes = new ArrayList<ArbolB.Nodo>();
        clientes = Actividades.arbol_b.get_clientes_array();
        modelClientes.setRowCount(0);
        for (int i = 0; i < clientes.size(); i++) {
            String[] datos = new String[4];
            datos[0] = String.valueOf(clientes.get(i).dpi);                
            datos[1] = String.valueOf(clientes.get(i).name);                
            datos[2] = String.valueOf(clientes.get(i).password);                
            datos[3] = String.valueOf(0);
            modelClientes.addRow(datos);
        }
    }

    private void desplegar_error(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void desplegar_info(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

}