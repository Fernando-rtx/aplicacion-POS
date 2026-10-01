package formularios;

import clases.Combo;
import clases.ComboDAO;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class frmCombosRecientes extends JDialog {

    private int comboSeleccionado = -1;
    private DefaultTableModel modelo;
    private JTable tblCombos;
    private Color azulClaro = new Color(234, 244, 248);

    public frmCombosRecientes(Frame owner) {
        super(owner, "Combos Registrados", true);
        setSize(700, 400);
        setLocationRelativeTo(owner);
        setResizable(false);
        initComponents();
        cargarCombos();
    }

    public int getComboSeleccionado() {
        return comboSeleccionado;
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(azulClaro);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Combos Registrados");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(10, 50, 60));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");

        tblCombos = new JTable(modelo);
        tblCombos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblCombos.getTableHeader().setReorderingAllowed(false);
        tblCombos.setRowHeight(25);
        tblCombos.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tblCombos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        tblCombos.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblCombos.getColumnModel().getColumn(0).setMinWidth(60);
        tblCombos.getColumnModel().getColumn(0).setMaxWidth(60);
        tblCombos.getColumnModel().getColumn(1).setPreferredWidth(400);
        tblCombos.getColumnModel().getColumn(1).setMinWidth(200);
        tblCombos.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblCombos.getColumnModel().getColumn(2).setMinWidth(80);
        tblCombos.getColumnModel().getColumn(2).setMaxWidth(120);

        JScrollPane scrollPane = new JScrollPane(tblCombos);
        scrollPane.setBackground(azulClaro);
        tblCombos.getParent().setBackground(azulClaro);
        tblCombos.getTableHeader().setBackground(azulClaro);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelBotones.setBackground(azulClaro);

        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setBackground(new Color(0, 149, 183));
        btnSeleccionar.setForeground(Color.WHITE);
        btnSeleccionar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnSeleccionar.setPreferredSize(new Dimension(120, 35));
        btnSeleccionar.addActionListener(e -> seleccionarCombo());

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(200, 230, 235));
        btnCerrar.setForeground(new Color(10, 50, 60));
        btnCerrar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnCerrar.setPreferredSize(new Dimension(120, 35));
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnSeleccionar);
        panelBotones.add(btnCerrar);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        tblCombos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    seleccionarCombo();
                }
            }
        });

        setContentPane(panelPrincipal);
    }

    private void seleccionarCombo() {
        int fila = tblCombos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un combo de la tabla.");
            return;
        }
        try {
            comboSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID de combo invalido.");
            return;
        }
        dispose();
    }

    private void cargarCombos() {
        modelo.setRowCount(0);
        try {
            List<Combo> lista = new ComboDAO().listarCombos();
            for (Combo c : lista) {
                modelo.addRow(new Object[]{
                    c.getIdCombo(),
                    c.getCombo(),
                    String.format("%.2f", c.getPrecio())
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los combos: " + e.getMessage());
        }
    }
}
