package Interfaz;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Admin_Eliminar implements ActionListener {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField dpi_field, nombre_field, contrasena_field;
    JButton buscar, modificar;

    public Admin_Eliminar() {
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
        JLabel titulo = new JLabel("Eliminar Cliente");
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
        nombre_field.setEnabled(false);
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
        contrasena_field.setEnabled(false);
        panel.add(contrasena_field);

        modificar = new JButton("Eliminar");
        modificar.setForeground(Color.WHITE);
        modificar.setFont(new Font("Arial", Font.PLAIN, 15));
        modificar.setBackground(Color.decode("#FF3C2D"));
        modificar.setBounds(50,310,400,25);
        modificar.addActionListener(this);
        modificar.setActionCommand("modificar");
        panel.add(modificar);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}