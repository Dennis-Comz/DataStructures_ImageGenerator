package Interfaz;

import javax.print.DocFlavor.STRING;
import javax.swing.*;
import javax.swing.border.LineBorder;
import Estructuras.*;
import Proyecto_Fase2.Actividades;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cliente_Gestionar implements ActionListener{
    JFrame frame = new JFrame("UDrawing Paper Gestionar Imagenes");
    JPanel limitado, por_arbol_imagenes, por_capa;
    JTabbedPane tabbedPane;
    JComboBox<STRING> combo_imagenes, combo_capas;
    JButton buscar_imagen, enviar_capa, preorder, inorder, postorder, generar_imagen, generar_imagen_capas;
    JButton analizar, buscar_imagen2, enviar_img;
    JTextField capas, preorder_field, inorder_field, postorder_field, capas_usar, select_img, select_capa, id_img;
    JTextField create_img;
    JLabel capas_agregadas;
    Long dpi;
    public Cliente_Gestionar(Long dpi_enviado) {
        dpi = dpi_enviado;
        display();
    }

    public void display() {
        frame.setSize(700,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabbedPane = new JTabbedPane();

        // PANEL LIMITADO
        limitado = new JPanel();
        limitado.setLayout(null);
        limitado.setBackground(Color.BLACK);

        JLabel titulo = new JLabel("Generacion de Imagen Por Recorrido Limitado");
        titulo.setBounds(150, 10, 400, 25);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.PLAIN, 15));
        limitado.add(titulo);
        
        JLabel select = new JLabel("Ingrese el ID de la imagen");
        select.setBounds(50, 50, 200, 25);
        select.setForeground(Color.WHITE);
        select.setFont(new Font("Arial", Font.PLAIN, 12));
        limitado.add(select);

        id_img = new JTextField();
        id_img.setForeground(Color.WHITE);
        id_img.setFont(new Font("Arial", Font.PLAIN, 15));
        id_img.setBackground(Color.decode("#2E2E2E"));
        id_img.setBounds(50, 80, 350, 30);
        limitado.add(id_img);

        buscar_imagen = new JButton("Buscar");
        buscar_imagen.setForeground(Color.WHITE);
        buscar_imagen.setFont(new Font("Arial", Font.PLAIN, 15));
        buscar_imagen.setBackground(Color.decode("#0EA9F5"));
        buscar_imagen.setBounds(425, 80, 200, 30);
        buscar_imagen.addActionListener(this);
        buscar_imagen.setActionCommand("buscar");
        limitado.add(buscar_imagen);

        JLabel capas_usadas = new JLabel("Capas de la Imagen");
        capas_usadas.setBounds(50, 125, 200, 25);
        capas_usadas.setForeground(Color.WHITE);
        capas_usadas.setFont(new Font("Arial", Font.PLAIN, 12));
        limitado.add(capas_usadas);

        capas = new JTextField();
        capas.setBounds(50, 155, 150, 25);
        capas.setForeground(Color.WHITE);
        capas.setBackground(Color.decode("#2E2E2E"));
        capas.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        capas.setFont(new Font("Arial", Font.PLAIN, 15));
        capas.setEnabled(false);
        limitado.add(capas);
        
        JLabel capas_a_usar = new JLabel("Capas a Utilizar");
        capas_a_usar.setBounds(250, 125, 200, 25);
        capas_a_usar.setForeground(Color.WHITE);
        capas_a_usar.setFont(new Font("Arial", Font.PLAIN, 12));
        limitado.add(capas_a_usar);

        capas_usar = new JTextField();
        capas_usar.setBounds(250, 155, 150, 25);
        capas_usar.setForeground(Color.WHITE);
        capas_usar.setBackground(Color.decode("#2E2E2E"));
        capas_usar.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        capas_usar.setFont(new Font("Arial", Font.PLAIN, 15));
        limitado.add(capas_usar);

        analizar = new JButton("Analizar");
        analizar.setForeground(Color.WHITE);
        analizar.setFont(new Font("Arial", Font.PLAIN, 15));
        analizar.setBackground(Color.decode("#0EA9F5"));
        analizar.setBounds(425, 155, 200, 30);
        analizar.addActionListener(this);
        analizar.setActionCommand("analizar");
        limitado.add(analizar);

        JLabel preorden_label = new JLabel("Capas en Recorrido Preorder");
        preorden_label.setBounds(50, 195, 250, 25);
        preorden_label.setForeground(Color.WHITE);
        preorden_label.setFont(new Font("Arial", Font.PLAIN, 12));
        limitado.add(preorden_label);

        preorder_field = new JTextField();
        preorder_field.setBounds(50, 225, 350, 25);
        preorder_field.setForeground(Color.WHITE);
        preorder_field.setBackground(Color.decode("#2E2E2E"));
        preorder_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        preorder_field.setFont(new Font("Arial", Font.PLAIN, 15));
        preorder_field.setEnabled(false);
        limitado.add(preorder_field);

        preorder = new JButton("Generar con Preorder");
        preorder.setBounds(425, 225, 200, 30);
        preorder.setForeground(Color.WHITE);
        preorder.setFont(new Font("Arial", Font.PLAIN, 15));
        preorder.setBackground(Color.decode("#0EA9F5"));
        preorder.addActionListener(this);
        preorder.setActionCommand("generar_preorder");
        limitado.add(preorder);

        JLabel inorder_label = new JLabel("Capas en Recorrido Inorder");
        inorder_label.setBounds(50, 270, 200, 25);
        inorder_label.setForeground(Color.WHITE);
        inorder_label.setFont(new Font("Arial", Font.PLAIN, 12));
        limitado.add(inorder_label);
        
        inorder_field = new JTextField();
        inorder_field.setBounds(50, 300, 350, 25);
        inorder_field.setForeground(Color.WHITE);
        inorder_field.setBackground(Color.decode("#2E2E2E"));
        inorder_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        inorder_field.setFont(new Font("Arial", Font.PLAIN, 15));
        inorder_field.setEnabled(false);
        limitado.add(inorder_field);

        inorder = new JButton("Generar con Inorder");
        inorder.setBounds(425, 300, 200, 30);
        inorder.setForeground(Color.WHITE);
        inorder.setFont(new Font("Arial", Font.PLAIN, 15));
        inorder.setBackground(Color.decode("#0EA9F5"));
        inorder.addActionListener(this);
        inorder.setActionCommand("generar_inorder");
        limitado.add(inorder);

        JLabel postorder_label = new JLabel("Capas en Recorrido Postorder");
        postorder_label.setBounds(50, 345, 200, 25);
        postorder_label.setForeground(Color.WHITE);
        postorder_label.setFont(new Font("Arial", Font.PLAIN, 12));
        limitado.add(postorder_label);
        
        postorder_field = new JTextField();
        postorder_field.setBounds(50, 375, 350, 25);
        postorder_field.setForeground(Color.WHITE);
        postorder_field.setBackground(Color.decode("#2E2E2E"));
        postorder_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        postorder_field.setFont(new Font("Arial", Font.PLAIN, 15));
        postorder_field.setEnabled(false);
        limitado.add(postorder_field);

        postorder = new JButton("Generar con Postorder");
        postorder.setBounds(425, 375, 200, 30);
        postorder.setForeground(Color.WHITE);
        postorder.setFont(new Font("Arial", Font.PLAIN, 15));
        postorder.setBackground(Color.decode("#0EA9F5"));
        postorder.addActionListener(this);
        postorder.setActionCommand("generar_postorder");
        limitado.add(postorder);

        // PANEL POR ARBOL DE IMAGENES
        por_arbol_imagenes = new JPanel();
        por_arbol_imagenes.setLayout(null);
        por_arbol_imagenes.setBackground(Color.BLACK);

        JLabel titulo2 = new JLabel("Generacion de Imagen Por Arbol de Imagenes");
        titulo2.setBounds(150, 10, 400, 25);
        titulo2.setForeground(Color.WHITE);
        titulo2.setFont(new Font("Arial", Font.PLAIN, 15));
        por_arbol_imagenes.add(titulo2);

        JLabel id_img = new JLabel("ID de la Imagen");
        id_img.setBounds(50, 50, 150, 25);
        id_img.setForeground(Color.WHITE);
        id_img.setFont(new Font("Arial", Font.PLAIN, 15));
        por_arbol_imagenes.add(id_img);

        select_img = new JTextField();
        select_img.setBounds(50, 80, 350, 25);
        select_img.setForeground(Color.WHITE);
        select_img.setBackground(Color.decode("#2E2E2E"));
        select_img.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        select_img.setFont(new Font("Arial", Font.PLAIN, 15));
        por_arbol_imagenes.add(select_img);

        buscar_imagen2 = new JButton("Buscar");
        buscar_imagen2.setForeground(Color.WHITE);
        buscar_imagen2.setFont(new Font("Arial", Font.PLAIN, 15));
        buscar_imagen2.setBackground(Color.decode("#0EA9F5"));
        buscar_imagen2.setBounds(425, 80, 200, 30);
        buscar_imagen2.addActionListener(this);
        buscar_imagen2.setActionCommand("buscar2");
        por_arbol_imagenes.add(buscar_imagen2);
        
        generar_imagen = new JButton("Generar Imagen");
        generar_imagen.setForeground(Color.WHITE);
        generar_imagen.setFont(new Font("Arial", Font.PLAIN, 15));
        generar_imagen.setBackground(Color.decode("#0EA9F5"));
        generar_imagen.setBounds(50, 150, 575, 30);
        generar_imagen.addActionListener(this);
        generar_imagen.setActionCommand("generar");
        por_arbol_imagenes.add(generar_imagen);

        // PANEL POR CAPAS
        por_capa = new JPanel();
        por_capa.setLayout(null);
        por_capa.setBackground(Color.BLACK);

        JLabel titulo3 = new JLabel("Generacion de Imagen Por Capas");
        titulo3.setBounds(150, 10, 400, 25);
        titulo3.setForeground(Color.WHITE);
        titulo3.setFont(new Font("Arial", Font.PLAIN, 15));
        por_capa.add(titulo3);

        JLabel id_capa = new JLabel("ID de la Capa");
        id_capa.setBounds(50, 50, 150, 25);
        id_capa.setForeground(Color.WHITE);
        id_capa.setFont(new Font("Arial", Font.PLAIN, 15));
        por_capa.add(id_capa);

        select_capa = new JTextField();
        select_capa.setBounds(50, 80, 350, 25);
        select_capa.setForeground(Color.WHITE);
        select_capa.setBackground(Color.decode("#2E2E2E"));
        select_capa.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        select_capa.setFont(new Font("Arial", Font.PLAIN, 15));
        por_capa.add(select_capa);

        enviar_capa = new JButton("Agregar Capa");
        enviar_capa.setForeground(Color.WHITE);
        enviar_capa.setFont(new Font("Arial", Font.PLAIN, 15));
        enviar_capa.setBackground(Color.decode("#0EA9F5"));
        enviar_capa.setBounds(425, 80, 200, 30);
        enviar_capa.addActionListener(this);
        enviar_capa.setActionCommand("agregar_capa");
        por_capa.add(enviar_capa);
        
        JLabel id_img2 = new JLabel("ID de la Imagen");
        id_img2.setBounds(50, 120, 150, 25);
        id_img2.setForeground(Color.WHITE);
        id_img2.setFont(new Font("Arial", Font.PLAIN, 15));
        por_capa.add(id_img2);

        create_img = new JTextField();
        create_img.setBounds(50, 150, 350, 25);
        create_img.setForeground(Color.WHITE);
        create_img.setBackground(Color.decode("#2E2E2E"));
        create_img.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        create_img.setFont(new Font("Arial", Font.PLAIN, 15));
        por_capa.add(create_img);

        enviar_img = new JButton("Agregar Imagen");
        enviar_img.setForeground(Color.WHITE);
        enviar_img.setFont(new Font("Arial", Font.PLAIN, 15));
        enviar_img.setBackground(Color.decode("#0EA9F5"));
        enviar_img.setBounds(425, 150, 200, 30);
        enviar_img.addActionListener(this);
        enviar_img.setActionCommand("agregar_imagen");
        por_capa.add(enviar_img);

        capas_agregadas = new JLabel("Aca se Mostraran las Capas Agregadas");
        capas_agregadas.setBounds(250, 250, 250, 25);
        capas_agregadas.setForeground(Color.WHITE);
        capas_agregadas.setFont(new Font("Arial", Font.PLAIN, 15));
        por_capa.add(capas_agregadas);

        generar_imagen_capas = new JButton("Generar Imagen");
        generar_imagen_capas.setForeground(Color.WHITE);
        generar_imagen_capas.setFont(new Font("Arial", Font.PLAIN, 15));
        generar_imagen_capas.setBackground(Color.decode("#0EA9F5"));
        generar_imagen_capas.setBounds(50, 300, 575, 30);
        generar_imagen_capas.addActionListener(this);
        generar_imagen_capas.setActionCommand("generar_por_capas");
        por_capa.add(generar_imagen_capas);

        // AGREGAR PANELES AL TABBED
        tabbedPane.addTab("Por Recorrido Limitado", limitado);
        tabbedPane.addTab("Por Arbol de Imagenes", por_arbol_imagenes);
        tabbedPane.addTab("Por Capa", por_capa);
        frame.getContentPane().add(tabbedPane);
        frame.setVisible(true);
    }

    ArbolAVL.Nodo imagen;
    ArbolAVL.Nodo img;
    LinkedListCapas list = new LinkedListCapas();
    ArbolABB capas_nuevo = new ArbolABB();
    int id;
    String resultado = "";
    @Override
    public void actionPerformed(ActionEvent e) {
        ArbolB.Nodo usuario = Actividades.arbol_b.realizar_visualizacion(dpi);
        if (e.getActionCommand().equals("buscar")) {
            int id = Integer.parseInt(id_img.getText());
            imagen = usuario.arbol_avl.buscar_imagen(id);
            if (imagen != null) {
                capas.setText(String.valueOf(imagen.capas.cantidad));
            }else{
                desplegar_error("La imagen no se encuentra en el sistema.");
            }
        }else if(e.getActionCommand().equals("analizar")){
            int limite = Integer.parseInt(capas_usar.getText());
            String pre = imagen.capas.preOrder_traversal(limite);
            String post = imagen.capas.postOrder_traversal(limite);
            String in = imagen.capas.inOrder_traversal(limite);
            preorder_field.setText(pre);
            postorder_field.setText(post);
            inorder_field.setText(in);
        }else if(e.getActionCommand().equals("generar_preorder")){
            MatrizDispersa matriz = new MatrizDispersa();
            String pre = preorder_field.getText();
            generar_imagen_limitado(pre, matriz, "Preorder");
        }else if(e.getActionCommand().equals("generar_inorder")){
            MatrizDispersa matriz = new MatrizDispersa();
            String post = postorder_field.getText();
            generar_imagen_limitado(post, matriz, "Inorder");
        }else if(e.getActionCommand().equals("generar_postorder")){
            MatrizDispersa matriz = new MatrizDispersa();
            String in = inorder_field.getText();
            generar_imagen_limitado(in, matriz, "PostOrder");
        }else if(e.getActionCommand().equals("buscar2")){
            id = Integer.valueOf(select_img.getText());
            img = usuario.arbol_avl.buscar_imagen(Integer.valueOf(id));
            if (img != null) {
                img.capas.inOrder_traversal_image_deImagen(String.valueOf(dpi), String.valueOf(id));
                desplegar_info("Imagen encontrada.");
            }else{
                desplegar_error("La imagen no existe.");
            }
        }else if(e.getActionCommand().equals("generar")){
            img.list_capas.generateImage(String.valueOf(dpi), id, img.capas);
            desplegar_info("Imagen Generada.");
        }else if(e.getActionCommand().equals("agregar_capa")){
            int id = Integer.parseInt(select_capa.getText());
            ArbolABB.Nodo nodo = usuario.arbol_binario.search(id);
            if (nodo != null) {
                list.insertar(id);
                capas_nuevo.insertar(id, nodo.matriz);
                resultado += id + " ";
                capas_agregadas.setText(resultado);
                desplegar_info("Capa añadida.");
            }else{
                desplegar_error("La capa no existe.");
            }
        }else if(e.getActionCommand().equals("generar_por_capas")){
            String id2 = create_img.getText();
            list.generateImage_Capas(String.valueOf(dpi), Integer.parseInt(id2), usuario.arbol_binario);
            usuario.arbol_avl.inOrder_traversal_image(String.valueOf(dpi));
            desplegar_info("Imagen Generada.");
        }else if(e.getActionCommand().equals("agregar_imagen")){
            String id2 = create_img.getText();
            if (usuario.arbol_avl != null) {
                ArbolAVL.Nodo nodo = usuario.arbol_avl.buscar_imagen(Integer.parseInt(id2));
                if (nodo == null) {
                    usuario.arbol_avl.add(Integer.parseInt(id2), capas_nuevo, list);
                    desplegar_info("Imagen agregada.");
                }else{
                    desplegar_error("La imagen ya existe.");
                }
            }else{
                usuario.arbol_avl = new ArbolAVL();
                ArbolAVL.Nodo nodo = usuario.arbol_avl.buscar_imagen(Integer.parseInt(id2));
                if (nodo == null) {
                    usuario.arbol_avl.add(Integer.parseInt(id2), capas_nuevo, list);
                    desplegar_info("Imagen agregada.");
                }else{
                    desplegar_error("La imagen ya existe.");
                }
            }
        }
    }

    public void generar_imagen_limitado(String nums_capas, MatrizDispersa matriz, String lugar) {
        String[] nums = nums_capas.split("-");
        ArbolB.Nodo usuario = Actividades.arbol_b.realizar_visualizacion(dpi);
        ArbolABB arbol = new ArbolABB();
        for (int i = 0; i < nums.length; i++) {
            ArbolABB.Nodo capa = usuario.arbol_binario.search(Integer.valueOf(nums[i]));
            if (capa != null) {
                arbol.insertar(capa.key, capa.matriz);
                ListaEncabezado filas = capa.matriz.getEncabezado();
                NodoEncabezado eFila = filas.primero;
                while (eFila != null) {
                    NodoDispersa actual = eFila.accesoNodo;
                    while (actual != null) {
                        matriz.insertar(actual.fila, actual.columna, actual.valor);                        
                        actual = actual.derecha;
                    }
                    eFila = eFila.siguiente;
                }
            }
        }
        if (!matriz.esta_vacia()) {
            String value = id_img.getText();
            matriz.generar_imagen_final(String.valueOf(dpi)+"RecorridoLimitado_"+lugar+value, "\\Imagenes\\");
            matriz.generar_graphviz(String.valueOf(dpi)+"RecorridoLimitado_"+lugar+value, "\\Imagenes\\");            
            usuario.arbol_avl.generar_graphviz(String.valueOf(dpi));
            arbol.generar_graphviz_deImagen(String.valueOf(dpi), value+lugar);
            desplegar_info("Imagen Generada.");
        }else{
            desplegar_error("Imagen no generada.");
        }
    }

    private void desplegar_error(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void desplegar_info(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }
}