package com.anahuac.desarrollo.arquitecturas.presentacion;

import java.util.List;

import com.anahuac.desarrollo.arquitecturas.datos.DAOLibroSqlite;
import com.anahuac.desarrollo.arquitecturas.entidades.Libros;
import com.anahuac.desarrollo.arquitecturas.logica.ControllerLibro;
import com.anahuac.desarrollo.arquitecturas.logica.Servicios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Cliente extends JFrame {
    private ControllerLibro controller;

    private JButton btnCrear;
    private JButton btnActualizar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnObtener;
    private JTextArea textArea;

    public Cliente(ControllerLibro controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Gestión de Libros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        btnCrear = new JButton("Crear Libro");
        btnActualizar = new JButton("Actualizar Libro");
        btnBuscar = new JButton("Buscar Libro");
        btnEliminar = new JButton("Eliminar Libro");
        btnObtener = new JButton("Obtener Todos los Libros");
        textArea = new JTextArea();

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Nombre del libro:");
                String autor = JOptionPane.showInputDialog("Autor del libro:");
                String isbn = JOptionPane.showInputDialog("ISBN del libro:");
                Libros nuevoLibro = controller.crearLibro(nombre, autor, isbn);
                if (nuevoLibro != null) {
                    textArea.setText("Libro creado:\n" + nuevoLibro.toString());
                } else {
                    textArea.setText("No se pudo crear el libro.");
                }
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("ID del libro a actualizar:");
                int id = Integer.parseInt(idStr);
                String nombre = JOptionPane.showInputDialog("Nuevo nombre del libro:");
                String autor = JOptionPane.showInputDialog("Nuevo autor del libro:");
                String isbn = JOptionPane.showInputDialog("Nuevo ISBN del libro:");
                Libros libro = new Libros(id, nombre, autor, isbn);
                boolean actualizado = controller.actualizarLibro(libro);
                if (actualizado) {
                    textArea.setText("Libro actualizado:\n" + libro.toString());
                } else {
                    textArea.setText("No se pudo actualizar el libro.");
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("ID del libro a buscar:");
                int id = Integer.parseInt(idStr);
                Libros libro = controller.buscarLibroo(id);
                if (libro != null) {
                    textArea.setText("Libro encontrado:\n" + libro.toString());
                } else {
                    textArea.setText("No se encontró el libro.");
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("ID del libro a eliminar:");
                int id = Integer.parseInt(idStr);
                boolean eliminado = controller.eliminarLibro(id);
                if (eliminado) {
                    textArea.setText("Libro eliminado correctamente.");
                } else {
                    textArea.setText("No se pudo eliminar el libro.");
                }
            }
        });

        btnObtener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Libros> libros = controller.obtenerLibros();
                StringBuilder sb = new StringBuilder();
                for (Libros libro : libros) {
                    sb.append(libro.toString()).append("\n");
                }
                textArea.setText(sb.toString());
            }
        });

        panel.add(btnCrear);
        panel.add(btnActualizar);
        panel.add(btnBuscar);
        panel.add(btnEliminar);
        panel.add(btnObtener);

        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Crear instancia del controlador
        DAOLibroSqlite dao = new DAOLibroSqlite();
        Servicios servicios = new Servicios(dao);
        final ControllerLibro controller = new ControllerLibro(servicios);

        // Crear instancia de la GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Cliente(controller);
            }
        });
    }
}

