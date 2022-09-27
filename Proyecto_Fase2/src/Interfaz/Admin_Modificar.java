package Interfaz;

import javax.swing.*;
import javax.swing.border.LineBorder;

import Estructuras.ArbolB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Proyecto_Fase2.Actividades;
import java.awt.*;

public class Admin_Modificar implements ActionListener{
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField dpi_field, nombre_field, contrasena_field;
    JButton buscar, modificar;

    public Admin_Modificar() {
        display();
    }

    public void display() {
        frame.setTitle("UDrawing Paper");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        panel.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);
        JLabel titulo = new JLabel("Modificar Cliente");
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(150, 25, 200,25);
        titulo.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(titulo);

        JLabel dpi_label = new JLabel("DPI:");
        dpi_label.setForeground(Color.WHITE);
        dpi_label.setBounds(50, 75, 50,25);
        dpi_label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(dpi_label);

        dpi_field = new JTextField();
        dpi_field.setForeground(Color.WHITE);
        dpi_field.setBackground(Color.decode("#2E2E2E"));
        dpi_field.setFont(new Font("Arial", Font.PLAIN, 20));
        dpi_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        dpi_field.setBounds(50,110, 300, 25);
        panel.add(dpi_field);

        buscar = new JButton("Buscar");
        buscar.setForeground(Color.WHITE);
        buscar.setFont(new Font("Arial", Font.PLAIN, 15));
        buscar.setBackground(Color.decode("#0EA9F5"));
        buscar.setBounds(360,110,90,25);
        if (dpi_field.getText().equals("")) {
            buscar.setEnabled(true);
        }else{
            buscar.setEnabled(false);
        }
        buscar.addActionListener(this);
        buscar.setActionCommand("buscar");
        panel.add(buscar);

        JLabel nombre_label = new JLabel("Nombre:");
        nombre_label.setForeground(Color.WHITE);
        nombre_label.setBounds(50, 155, 100,25);
        nombre_label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(nombre_label);

        nombre_field = new JTextField();
        nombre_field.setForeground(Color.WHITE);
        nombre_field.setBackground(Color.decode("#2E2E2E"));
        nombre_field.setFont(new Font("Arial", Font.PLAIN, 20));
        nombre_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        nombre_field.setBounds(50,190, 400, 25);
        panel.add(nombre_field);

        JLabel pass_label = new JLabel("Contraseña:");
        pass_label.setForeground(Color.WHITE);
        pass_label.setBounds(50, 235, 100,25);
        pass_label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(pass_label);

        contrasena_field = new JTextField();
        contrasena_field.setForeground(Color.WHITE);
        contrasena_field.setBackground(Color.decode("#2E2E2E"));
        contrasena_field.setFont(new Font("Arial", Font.PLAIN, 20));
        contrasena_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        contrasena_field.setBounds(50,270, 400, 25);
        panel.add(contrasena_field);

        modificar = new JButton("Modificar");
        modificar.setForeground(Color.WHITE);
        modificar.setFont(new Font("Arial", Font.PLAIN, 15));
        modificar.setBackground(Color.decode("#1C8622"));
        modificar.setBounds(50,310,400,25);
        modificar.addActionListener(this);
        modificar.setActionCommand("modificar");
        panel.add(modificar);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("buscar")) {
            Long dpi = Long.valueOf(dpi_field.getText());
            ArbolB.Nodo encontrado =  Actividades.arbol_b.realizar_visualizacion(dpi);
            if (encontrado != null) {
                nombre_field.setText(encontrado.name);
                contrasena_field.setText(encontrado.password);                    
            }else{
                desplegar_error("El usuario no se encuentra en el sistema.");
            }
        }else if(e.getActionCommand().equals("modificar")){
            Long dpi = Long.valueOf(dpi_field.getText());
            String name = nombre_field.getText();
            String password = contrasena_field.getText();
            boolean modificado = Actividades.arbol_b.realizar_modificar(dpi, name, password);
            dpi_field.setText("");
            nombre_field.setText("");
            contrasena_field.setText("");
            if (modificado) {
                desplegar_info("Usuario modificado");
            }else{
                desplegar_error("No se pudo modificar al usuario");
            }
        }
    }

    private void desplegar_error(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void desplegar_info(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

}