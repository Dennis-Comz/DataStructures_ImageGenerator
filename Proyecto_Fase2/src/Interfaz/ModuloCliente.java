package Interfaz;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.*;
import Estructuras.*;
import Proyecto_Fase2.Actividades;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ModuloCliente implements ActionListener{
    JFrame frame = new JFrame("UDrawing Paper Cliente");
    JPanel panel_inicial, panel_reportes;
    JFrame login;
    JButton cargar_capas, cargar_imagenes, cargar_albumes, arbol_imagenes, arbol_capas, lista_albumes;
    JButton gestionar_imagenes, ver_capa, ver_imagen, ver_capas_imagen, eliminar_imagen, cerrar_sesion;
    JTabbedPane tabbedPane;
    JLabel img_label;
    String dpi;
    JComboBox<String> capas, albumes, imagenes, capa, imagen, capas_imagen;

    JButton top5, capas_hojas, profundidad_capas, listar_capas;
    
    public ModuloCliente(JFrame fr_login, Long dpi_inicio) {
        login = fr_login;
        dpi = String.valueOf(dpi_inicio);
        login.setVisible(false);
        display();
    }

    public void display() {
        frame.setSize(1200,800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane();

        // CONTENIDO QUE VA DENTRO DEL PANEL INICIAL
        panel_inicial = new JPanel();
        panel_inicial.setLayout(null);
        panel_inicial.setBackground(Color.BLACK);

        JPanel panel_imagen = new JPanel();
        panel_imagen.setBackground(Color.decode("#2E2E2E"));
        img_label = new JLabel();
        img_label.setOpaque(true);
        panel_imagen.add(img_label);
        JScrollPane scrollPane = new JScrollPane(panel_imagen);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(25, 25, 725, 700);
        scrollPane.setBackground(Color.decode("#2E2E2E"));
        panel_inicial.add(scrollPane);

        cargar_capas = new JButton("Cargar Capas");
        cargar_capas.setForeground(Color.WHITE);
        cargar_capas.setFont(new Font("Arial", Font.PLAIN, 15));
        cargar_capas.setBounds(775, 50, 175,50);
        cargar_capas.setBackground(Color.decode("#0EA9F5"));
        cargar_capas.addActionListener(this);
        cargar_capas.setActionCommand("capas");
        panel_inicial.add(cargar_capas);

        cargar_imagenes = new JButton("Cargar Imagenes");
        cargar_imagenes.setForeground(Color.WHITE);
        cargar_imagenes.setFont(new Font("Arial", Font.PLAIN, 15));
        cargar_imagenes.setBounds(975, 50, 175,50);
        cargar_imagenes.setBackground(Color.decode("#0EA9F5"));
        cargar_imagenes.addActionListener(this);
        cargar_imagenes.setActionCommand("imagenes");
        panel_inicial.add(cargar_imagenes);
        
        cargar_albumes = new JButton("Cargar Albumes");
        cargar_albumes.setForeground(Color.WHITE);
        cargar_albumes.setFont(new Font("Arial", Font.PLAIN, 15));
        cargar_albumes.setBounds(775, 125, 175,50);
        cargar_albumes.setBackground(Color.decode("#0EA9F5"));
        cargar_albumes.addActionListener(this);
        cargar_albumes.setActionCommand("albumes");
        panel_inicial.add(cargar_albumes);

        gestionar_imagenes = new JButton("Gestionar Imagenes");
        gestionar_imagenes.setForeground(Color.WHITE);
        gestionar_imagenes.setFont(new Font("Arial", Font.PLAIN, 15));
        gestionar_imagenes.setBounds(975, 125, 175,50);
        gestionar_imagenes.setBackground(Color.decode("#0EA9F5"));
        gestionar_imagenes.addActionListener(this);
        gestionar_imagenes.setActionCommand("gestionar");
        panel_inicial.add(gestionar_imagenes);

        // ACA SE AGREGA CODIGO QUE SERVIRA PARA DESPLEGAR LAS IMAGENES        
        JLabel capas_label = new JLabel("Arbol de Capas");
        capas_label.setBounds(775, 200, 150, 25);
        capas_label.setForeground(Color.WHITE);
        capas_label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel_inicial.add(capas_label);

        arbol_capas = new JButton(UIManager.getIcon("FileView.floppyDriveIcon"));
        arbol_capas.setBounds(775, 230, 30, 30);
        arbol_capas.setOpaque(false);
        arbol_capas.setContentAreaFilled(false);
        arbol_capas.setBorderPainted(false);
        arbol_capas.addActionListener(this);
        arbol_capas.setActionCommand("arbol_capas");
        panel_inicial.add(arbol_capas);

        capas = new JComboBox<String>();
        capas.setForeground(Color.WHITE);
        capas.setFont(new Font("Arial", Font.PLAIN, 15));
        capas.setBackground(Color.decode("#2E2E2E"));
        capas.setBounds(805, 230, 345, 30);
        panel_inicial.add(capas);

        JLabel imagenes_label = new JLabel("Arbol de Imagenes");
        imagenes_label.setBounds(775, 270, 150, 25);
        imagenes_label.setForeground(Color.WHITE);
        imagenes_label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel_inicial.add(imagenes_label);

        arbol_imagenes = new JButton(UIManager.getIcon("FileView.floppyDriveIcon"));
        arbol_imagenes.setBounds(775, 305, 30, 30);
        arbol_imagenes.setOpaque(false);
        arbol_imagenes.setContentAreaFilled(false);
        arbol_imagenes.setBorderPainted(false);
        arbol_imagenes.addActionListener(this);
        arbol_imagenes.setActionCommand("arbol_imagenes");
        panel_inicial.add(arbol_imagenes);

        imagenes = new JComboBox<String>();
        imagenes.setForeground(Color.WHITE);
        imagenes.setFont(new Font("Arial", Font.PLAIN, 15));
        imagenes.setBackground(Color.decode("#2E2E2E"));
        imagenes.setBounds(805, 305, 345, 30);
        panel_inicial.add(imagenes);
        
        JLabel albumes_label = new JLabel("Lista de Albumes");
        albumes_label.setBounds(775, 345, 150, 25);
        albumes_label.setForeground(Color.WHITE);
        albumes_label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel_inicial.add(albumes_label);

        lista_albumes = new JButton(UIManager.getIcon("FileView.floppyDriveIcon"));
        lista_albumes.setBounds(775, 380, 30, 30);
        lista_albumes.setOpaque(false);
        lista_albumes.setContentAreaFilled(false);
        lista_albumes.setBorderPainted(false);
        lista_albumes.addActionListener(this);
        lista_albumes.setActionCommand("lista_albumes");
        panel_inicial.add(lista_albumes);

        albumes = new JComboBox<String>();
        albumes.setForeground(Color.WHITE);
        albumes.setFont(new Font("Arial", Font.PLAIN, 15));
        albumes.setBackground(Color.decode("#2E2E2E"));
        albumes.setBounds(805, 380, 345, 30);
        panel_inicial.add(albumes);
        
        JLabel capa_label = new JLabel("Ver Capa");
        capa_label.setBounds(775, 420, 150, 25);
        capa_label.setForeground(Color.WHITE);
        capa_label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel_inicial.add(capa_label);

        ver_capa = new JButton(UIManager.getIcon("FileView.floppyDriveIcon"));
        ver_capa.setBounds(775, 455, 30, 30);
        ver_capa.setOpaque(false);
        ver_capa.setContentAreaFilled(false);
        ver_capa.setBorderPainted(false);
        ver_capa.addActionListener(this);
        ver_capa.setActionCommand("ver_capa");
        panel_inicial.add(ver_capa);

        capa = new JComboBox<String>();
        capa.setForeground(Color.WHITE);
        capa.setFont(new Font("Arial", Font.PLAIN, 15));
        capa.setBackground(Color.decode("#2E2E2E"));
        capa.setBounds(805, 455, 345, 30);
        panel_inicial.add(capa);

        JLabel imagen_label = new JLabel("Ver Imagen");
        imagen_label.setBounds(775, 495, 150, 25);
        imagen_label.setForeground(Color.WHITE);
        imagen_label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel_inicial.add(imagen_label);

        ver_imagen = new JButton(UIManager.getIcon("FileView.floppyDriveIcon"));
        ver_imagen.setBounds(775, 530, 30, 30);
        ver_imagen.setOpaque(false);
        ver_imagen.setContentAreaFilled(false);
        ver_imagen.setBorderPainted(false);
        ver_imagen.addActionListener(this);
        ver_imagen.setActionCommand("ver_imagen");
        panel_inicial.add(ver_imagen);

        imagen = new JComboBox<String>();
        imagen.setForeground(Color.WHITE);
        imagen.setFont(new Font("Arial", Font.PLAIN, 15));
        imagen.setBackground(Color.decode("#2E2E2E"));
        imagen.setBounds(805, 530, 345, 30);
        panel_inicial.add(imagen);

        JLabel capa_imagen_label = new JLabel("Ver Arbol de Capas en Imagen");
        capa_imagen_label.setBounds(775, 570, 250, 25);
        capa_imagen_label.setForeground(Color.WHITE);
        capa_imagen_label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel_inicial.add(capa_imagen_label);

        ver_capas_imagen = new JButton(UIManager.getIcon("FileView.floppyDriveIcon"));
        ver_capas_imagen.setBounds(775, 605, 30, 30);
        ver_capas_imagen.setOpaque(false);
        ver_capas_imagen.setContentAreaFilled(false);
        ver_capas_imagen.setBorderPainted(false);
        ver_capas_imagen.addActionListener(this);
        ver_capas_imagen.setActionCommand("ver_capas_imagen");
        panel_inicial.add(ver_capas_imagen);

        capas_imagen = new JComboBox<String>();
        capas_imagen.setForeground(Color.WHITE);
        capas_imagen.setFont(new Font("Arial", Font.PLAIN, 15));
        capas_imagen.setBackground(Color.decode("#2E2E2E"));
        capas_imagen.setBounds(805, 605, 345, 30);
        panel_inicial.add(capas_imagen);

        eliminar_imagen = new JButton("Eliminar Imagen");
        eliminar_imagen.setForeground(Color.WHITE);
        eliminar_imagen.setFont(new Font("Arial", Font.PLAIN, 15));
        eliminar_imagen.setBounds(775, 675, 175,50);
        eliminar_imagen.setBackground(Color.decode("#0EA9F5"));
        eliminar_imagen.addActionListener(this);
        eliminar_imagen.setActionCommand("eliminar");
        panel_inicial.add(eliminar_imagen);

        tabbedPane.addTab("Inicio",panel_inicial);
        // INICIO OPCIONES DE TABBED REPORTES
        panel_reportes = new JPanel();
        panel_reportes = new JPanel();
        panel_reportes.setLayout(null);
        panel_reportes.setBackground(Color.BLACK);
        
        top5 = new JButton("Top 5 Imagenes Con Mas Numero de Capas");
        top5.setForeground(Color.WHITE);
        top5.setFont(new Font("Arial", Font.PLAIN, 15));
        top5.setBounds(200, 100, 800, 100);
        top5.setBackground(Color.decode("#0EA9F5"));
        top5.addActionListener(this);
        top5.setActionCommand("top");
        panel_reportes.add(top5);
        
        capas_hojas = new JButton("Capas que son Hojas");
        capas_hojas.setForeground(Color.WHITE);
        capas_hojas.setFont(new Font("Arial", Font.PLAIN, 15));
        capas_hojas.setBounds(200, 225, 800, 100);
        capas_hojas.setBackground(Color.decode("#0EA9F5"));
        capas_hojas.addActionListener(this);
        capas_hojas.setActionCommand("capas_hojas");
        panel_reportes.add(capas_hojas);
        
        profundidad_capas = new JButton("Profundidad Arbol de Capas");
        profundidad_capas.setForeground(Color.WHITE);
        profundidad_capas.setFont(new Font("Arial", Font.PLAIN, 15));
        profundidad_capas.setBounds(200, 350, 800, 100);
        profundidad_capas.setBackground(Color.decode("#0EA9F5"));
        profundidad_capas.addActionListener(this);
        profundidad_capas.setActionCommand("profundidad");
        panel_reportes.add(profundidad_capas);
        
        listar_capas = new JButton("Listar las Capas");
        listar_capas.setForeground(Color.WHITE);
        listar_capas.setFont(new Font("Arial", Font.PLAIN, 15));
        listar_capas.setBounds(200, 475, 800, 100);
        listar_capas.setBackground(Color.decode("#0EA9F5"));
        listar_capas.addActionListener(this);
        listar_capas.setActionCommand("listar");
        panel_reportes.add(listar_capas);        
        
        cerrar_sesion = new JButton("Cerrar Sesion");
        cerrar_sesion.setBounds(1000, 10, 150, 25);
        cerrar_sesion.setForeground(Color.WHITE);
        cerrar_sesion.setFont(new Font("Arial", Font.PLAIN, 15));
        cerrar_sesion.setBackground(Color.decode("#0EA9F5"));
        cerrar_sesion.addActionListener(this);
        cerrar_sesion.setActionCommand("salir");
        panel_inicial.add(cerrar_sesion);
        panel_reportes.add(cerrar_sesion);

        tabbedPane.addTab("Reportes",panel_reportes);
        frame.getContentPane().add(tabbedPane);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("capas")) {
            JFileChooser fileChooser = new JFileChooser();
            int op = fileChooser.showOpenDialog(frame);
            if (op == JFileChooser.APPROVE_OPTION) {
                leer_capas(fileChooser.getSelectedFile().getAbsolutePath());
                desplegar_info("Capas cargadas al sistema.");
            }else{
                desplegar_error("No se ha seleccion ningun archivo.");
            }
            String carpeta_arbol = "Arboles\\ABB";
            fill_combo(carpeta_arbol, capas);
            String carpeta_imagenes = "Capas";
            fill_combo(carpeta_imagenes, capa);
        }else if(e.getActionCommand().equals("imagenes")){
            JFileChooser fileChooser = new JFileChooser();
            int op = fileChooser.showOpenDialog(frame);
            if (op == JFileChooser.APPROVE_OPTION) {
                leer_imagenes(fileChooser.getSelectedFile().getAbsolutePath());
                desplegar_info("Imagenes cargadas al sistema.");
            }else{
                desplegar_error("No se ha seleccion ningun archivo.");
            }
            String carpeta_arbol = "Arboles\\AVL";
            fill_combo(carpeta_arbol, imagenes);
        }else if(e.getActionCommand().equals("albumes")){
            JFileChooser fileChooser = new JFileChooser();
            int op = fileChooser.showOpenDialog(frame);
            if (op == JFileChooser.APPROVE_OPTION) {
                leer_albumes(fileChooser.getSelectedFile().getAbsolutePath());
                desplegar_info("Albumes cargados al sistema.");
            }else{
                desplegar_error("No se ha seleccion ningun archivo.");
            }
            String carpeta_albumes = "Albumes";
            fill_combo(carpeta_albumes, albumes);
        }else if(e.getActionCommand().equals("gestionar")){
            new Cliente_Gestionar(Long.parseLong(dpi));
            String carpeta_imagenes = "Imagenes";
            fill_combo(carpeta_imagenes, imagen);
            String carpeta_arbol_img = "Arboles\\ABB_Imagenes";
            fill_combo(carpeta_arbol_img, capas_imagen);
        }else if(e.getActionCommand().equals("arbol_capas")){
            if (capas.getSelectedItem() != null) {
                File img = new File("C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\Arboles\\ABB\\"+capas.getSelectedItem());
                if (img.exists()) {
                    try {
                        BufferedImage picture = ImageIO.read(img);
                        img_label.setIcon(new ImageIcon(picture));
                    } catch (Exception ey) {}
                }
            }
        }else if(e.getActionCommand().equals("arbol_imagenes")){
            if (imagenes.getSelectedItem() != null) {
                File img = new File("C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\Arboles\\AVL\\"+imagenes.getSelectedItem());
                if (img.exists()) {
                    try {
                        BufferedImage picture = ImageIO.read(img);
                        img_label.setIcon(new ImageIcon(picture));
                    } catch (Exception ex) {}
                }
            }
        }else if(e.getActionCommand().equals("lista_albumes")){
            if (albumes.getSelectedItem() != null) {
                File img = new File("C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\Albumes\\"+albumes.getSelectedItem());
                if (img.exists()) {
                    try {
                        BufferedImage picture = ImageIO.read(img);
                        img_label.setIcon(new ImageIcon(picture));
                    } catch (Exception ex) {}
                }
            }
        }else if(e.getActionCommand().equals("ver_capa")){
            if (capa.getSelectedItem() != null) {
                File img = new File("C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\Capas\\"+capa.getSelectedItem());
                if (img.exists()) {
                    try {
                        BufferedImage picture = ImageIO.read(img);
                        img_label.setIcon(new ImageIcon(picture));
                    } catch (Exception ex) {}
                }
            }
        }else if(e.getActionCommand().equals("ver_imagen")){
            if (imagen.getSelectedItem() != null) {
                File img = new File("C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\Imagenes\\"+imagen.getSelectedItem());
                if (img.exists()) {
                    try {
                        BufferedImage picture = ImageIO.read(img);
                        img_label.setIcon(new ImageIcon(picture));
                    } catch (Exception ex) {}
                }
            }
        }else if(e.getActionCommand().equals("ver_capas_imagen")){
            if (capas_imagen.getSelectedItem() != null) {
                File img = new File("C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\Arboles\\ABB_Imagenes\\"+capas_imagen.getSelectedItem());
                if (img.exists()) {
                    try {
                        BufferedImage picture = ImageIO.read(img);
                        img_label.setIcon(new ImageIcon(picture));
                    } catch (Exception ex) {}
                }
            }
        }else if(e.getActionCommand().equals("top")){
            ArbolB.Nodo nodo = Actividades.arbol_b.realizar_visualizacion(Long.valueOf(dpi));
            ArrayList<ArbolAVL.Nodo> lista =  nodo.arbol_avl.getTop();
            String value = ""; int cantidad = 1;
            for (int i = (lista.size()-1); i >= 0; i--) {
                value += cantidad+". Imagen "+lista.get(i).key + " Capas "+lista.get(i).capas.cantidad+"\n";
                if (cantidad == 5) {
                    break;
                }
                cantidad++;
            }
            desplegar_info(value);
        }else if(e.getActionCommand().equals("listar")){
            ArbolB.Nodo nodo = Actividades.arbol_b.realizar_visualizacion(Long.valueOf(dpi));
            String resultado = "";
            String pre = nodo.arbol_binario.preOrder_traversal(30); pre += "\n";
            String post = nodo.arbol_binario.postOrder_traversal(30); post += "\n";
            String in = nodo.arbol_binario.inOrder_traversal(30); in += "\n";
            resultado += "Preorder - "+pre+"PostOrder - "+post+"Inorder - "+in;
            desplegar_info(resultado);
        }else if(e.getActionCommand().equals("capas_hojas")){
            ArbolB.Nodo nodo = Actividades.arbol_b.realizar_visualizacion(Long.valueOf(dpi));
            String hojas = nodo.arbol_binario.capas_hojas();
            desplegar_info(hojas);
        }else if(e.getActionCommand().equals("salir")){
            frame.setVisible(false);
            login.setVisible(true);
        }
    }

    public void leer_capas(String path) {
        ArbolABB binary_tree = new ArbolABB();
        // Se obtiene el archivo como una cadena
        Path filename = Path.of(path);
        String contenido = "";
        try {
            contenido = Files.readString(filename);
        } catch (IOException e) {}
        // Se lee el archivo 
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(contenido).getAsJsonArray();
        // Ciclo para los objetos json
        for (JsonElement obj : gsonArr) {
            JsonObject gsonObj = obj.getAsJsonObject();
            int id = gsonObj.get("id_capa").getAsInt();
            JsonArray gsonPix = gsonObj.get("pixeles").getAsJsonArray();
            MatrizDispersa matriz = new MatrizDispersa();
            // Ciclo para llenar la matriz con la info debida
            for (JsonElement objPix : gsonPix) {
                JsonObject gsonP = objPix.getAsJsonObject();
                int fila = gsonP.get("fila").getAsInt();
                int columna = gsonP.get("columna").getAsInt();
                String color = gsonP.get("color").getAsString();
                matriz.insertar(fila, columna, color);
            }
            binary_tree.insertar(id, matriz);
        }
        binary_tree.imagenes_traversal(dpi, "\\Capas\\");
        binary_tree.inOrder_traversal_image(dpi);
        ArbolB.Nodo usuario = Actividades.arbol_b.realizar_visualizacion(Long.valueOf(dpi));
        usuario.arbol_binario = binary_tree;
    }

    public void leer_imagenes(String path) {
        ArbolAVL arbol_avl = new ArbolAVL();
        Path filename = Path.of(path);
        String contenido = "";
        ArbolB.Nodo usuario = Actividades.arbol_b.realizar_visualizacion(Long.valueOf(dpi));
        try {
            contenido = Files.readString(filename);
        } catch (Exception e) {}
        // Se lee el archivo 
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(contenido).getAsJsonArray();
        // Ciclo para los objetos json
        for (JsonElement obj : gsonArr) {
            JsonObject gsonObj = obj.getAsJsonObject();
            int id = gsonObj.get("id").getAsInt();
            JsonArray cps = gsonObj.get("capas").getAsJsonArray();
            ArbolABB capas = new ArbolABB();
            LinkedListCapas list = new LinkedListCapas();
            for (JsonElement objCps : cps) {
                int c = objCps.getAsInt();
                ArbolABB.Nodo nodo = usuario.arbol_binario.search(c);
                if (nodo != null) {
                    list.insertar(c);
                    capas.insertar(c, nodo.matriz);
                }
            }
            arbol_avl.add(id, capas, list);
        }
        arbol_avl.inOrder_traversal_image(String.valueOf(dpi));
        usuario.arbol_avl = arbol_avl;        
        // arbol_avl.generar_imagenes(binary_tree);
    }

    public void leer_albumes(String path) {
        DoubleListAlbumes albumes = new DoubleListAlbumes();
        Path filename = Path.of(path);
        String contenido = "";
        ArbolB.Nodo usuario = Actividades.arbol_b.realizar_visualizacion(Long.valueOf(dpi));
        try{
            contenido = Files.readString(filename);
        }catch(Exception e){}
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(contenido).getAsJsonArray();
        // Ciclo para los objetos json
        for (JsonElement obj : gsonArr) {
            JsonObject gsonObj = obj.getAsJsonObject();
            String nombre = gsonObj.get("nombre_album").getAsString();
            JsonArray cps = gsonObj.get("imgs").getAsJsonArray();
            SimpleListImagenes numeros = new SimpleListImagenes();
            for (JsonElement objCps : cps) {
                int id = objCps.getAsInt();
                numeros.append(id);
            }
            albumes.append(nombre, numeros);
        }
        albumes.generar_graphviz(String.valueOf(dpi));
        usuario.albumes = albumes;
    }

    public void fill_combo(String carpeta, JComboBox<String> elemento){
        File folder = new File("C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\"+carpeta);
        elemento.removeAllItems();
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            elemento.addItem(listOfFiles[i].getName());
        }
    }

    private void desplegar_error(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void desplegar_info(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

}