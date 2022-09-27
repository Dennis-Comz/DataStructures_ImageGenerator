package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import Proyecto_Fase2.Actividades;


public class Login implements ActionListener{
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JTextField user_field = new JTextField();
    JPasswordField pass_field = new JPasswordField();
    JButton sign_in = new JButton("Iniciar Sesión");
    JButton no_usuario;

    public Login(){
        Actividades.getInstance(); 
        display();
    }

    // public void save_info(Actividades objeto) {
    //     actividades = objeto;    
    // }

    public void display() {
        frame.setTitle("UDrawing Paper");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(Color.BLACK);
        frame.add(panel);

        panel.setLayout(null);
        JLabel titulo = new JLabel("Inicia Sesion en UDrawing Paper");
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(100,50,300,25);
        titulo.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(titulo);

        JLabel usuario = new JLabel("Usuario");
        usuario.setForeground(Color.WHITE);
        usuario.setFont(new Font("Arial", Font.PLAIN, 15));
        usuario.setBounds(50, 125, 75, 25);
        panel.add(usuario);
        
        user_field.setBounds(50, 150, 400, 25);
        user_field.setForeground(Color.WHITE);
        user_field.setBackground(Color.decode("#2E2E2E"));
        user_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        user_field.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(user_field);
        
        JLabel contrasena = new JLabel("Contraseña");
        contrasena.setForeground(Color.WHITE);
        contrasena.setFont(new Font("Arial", Font.PLAIN, 15));
        contrasena.setBounds(50, 190, 100, 25);
        panel.add(contrasena);

        pass_field.setBounds(50, 215, 400, 25);
        pass_field.setForeground(Color.WHITE);
        pass_field.setBackground(Color.decode("#2E2E2E"));
        pass_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        pass_field.setFont(new Font("Arial", Font.PLAIN, 15));
        pass_field.setEchoChar((char)0);
        panel.add(pass_field);

        sign_in.setBounds(50,250,400,25);
        sign_in.setForeground(Color.WHITE);
        sign_in.setBackground(Color.decode("#1C8622"));
        sign_in.setFont(new Font("Arial", Font.PLAIN, 15));
        sign_in.addActionListener(this);
        sign_in.setActionCommand("iniciar");
        panel.add(sign_in);

        no_usuario = new JButton("<html>¿No tienes un usuario? <font color='#0EA9F5'>Crea uno.</font></html>");
        no_usuario.setFocusPainted(false);
        no_usuario.setMargin(new Insets(0,0,0,0));
        no_usuario.setContentAreaFilled(false);
        no_usuario.setBorderPainted(false);
        no_usuario.setOpaque(false);
        no_usuario.setBorder(new EmptyBorder(0,0,0,0));
        no_usuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        no_usuario.setForeground(Color.WHITE);
        no_usuario.setFont(new Font("Arial", Font.PLAIN, 15));
        no_usuario.setBounds(135,290,250,25);
        panel.add(no_usuario);
        no_usuario.addActionListener(this);
        no_usuario.setActionCommand("no_user");

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("no_user")) {
            new NewUser(frame);
        }else if(e.getActionCommand().equals("iniciar")){
            String usuario = user_field.getText();
            String contrasena = String.valueOf(pass_field.getPassword());
            if (usuario.equals("admin") && contrasena.equals("EDD2022")) {
                new Administracion(frame);
            }else if(Actividades.arbol_b.realizar_login(Long.parseLong(usuario), contrasena)){
                new ModuloCliente(frame, Long.parseLong(usuario));
            }else{
                desplegar_error("El usuario o la contraseña son invalidos");
            }
        }
    }

    private void desplegar_error(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // private void desplegar_info(String mensaje){
    //     JOptionPane.showMessageDialog(frame, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
    // }
}