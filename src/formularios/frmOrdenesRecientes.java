package formularios;

import clases.Orden;
import clases.OrdenDAO;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class frmOrdenesRecientes extends JDialog {

    private int ordenSeleccionada = -1;
    private DefaultTableModel modelo;
    private JTable tblOrdenes;
    private Color azulClaro = new Color(234, 244, 248);

    public frmOrdenesRecientes(Frame owner) {
        super(owner, "Órdenes Recientes", true);
        setSize(900, 500);
        setLocationRelativeTo(owner);
        setResizable(false);
        initComponents();
        cargarOrdenes();
    }

    public int getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(azulClaro);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Órdenes Recientes");
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
        modelo.addColumn("Fecha");
        modelo.addColumn("Total");
        modelo.addColumn("Estado");
        modelo.addColumn("Usuario");

        tblOrdenes = new JTable(modelo);
        tblOrdenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblOrdenes.getTableHeader().setReorderingAllowed(false);
        tblOrdenes.setRowHeight(25);
        tblOrdenes.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tblOrdenes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        tblOrdenes.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblOrdenes.getColumnModel().getColumn(0).setMinWidth(60);
        tblOrdenes.getColumnModel().getColumn(0).setMaxWidth(60);
        tblOrdenes.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblOrdenes.getColumnModel().getColumn(1).setMinWidth(150);
        tblOrdenes.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblOrdenes.getColumnModel().getColumn(2).setMinWidth(80);
        tblOrdenes.getColumnModel().getColumn(2).setMaxWidth(80);
        tblOrdenes.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblOrdenes.getColumnModel().getColumn(3).setMinWidth(100);
        tblOrdenes.getColumnModel().getColumn(3).setMaxWidth(100);
        tblOrdenes.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblOrdenes.getColumnModel().getColumn(4).setMinWidth(150);

        tblOrdenes.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    c.setForeground(table.getSelectionForeground());
                    return c;
                }
                String estado = value != null ? value.toString().trim().toUpperCase() : "";
                switch (estado) {
                    case "PROCESADA":
                        c.setBackground(new Color(255, 255, 150));
                        c.setForeground(Color.BLACK);
                        break;
                    case "DESPACHADA":
                        c.setBackground(new Color(144, 238, 144));
                        c.setForeground(Color.BLACK);
                        break;
                    case "ANULADA":
                        c.setBackground(new Color(220, 50, 50));
                        c.setForeground(Color.WHITE);
                        break;
                    default:
                        c.setBackground(Color.WHITE);
                        c.setForeground(Color.BLACK);
                        break;
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tblOrdenes);
        scrollPane.setBackground(azulClaro);
        tblOrdenes.getParent().setBackground(azulClaro);
        tblOrdenes.getTableHeader().setBackground(azulClaro);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelBotones.setBackground(azulClaro);

        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setBackground(new Color(0, 149, 183));
        btnSeleccionar.setForeground(Color.WHITE);
        btnSeleccionar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnSeleccionar.setPreferredSize(new Dimension(120, 35));
        btnSeleccionar.addActionListener(e -> seleccionarOrden());

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(200, 230, 235));
        btnCerrar.setForeground(new Color(10, 50, 60));
        btnCerrar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnCerrar.setPreferredSize(new Dimension(120, 35));
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnSeleccionar);
        panelBotones.add(btnCerrar);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        tblOrdenes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    seleccionarOrden();
                }
            }
        });

        setContentPane(panelPrincipal);
    }

    private void seleccionarOrden() {
        int fila = tblOrdenes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una orden de la tabla.");
            return;
        }
        try {
            ordenSeleccionada = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID de orden invalido.");
            return;
        }
        dispose();
    }

    private void cargarOrdenes() {
        modelo.setRowCount(0);
        try {
            List<Orden> lista = new OrdenDAO().listarRecientes(50);
            for (Orden o : lista) {
                modelo.addRow(new Object[]{
                    o.getIdOrden(),
                    o.getFechaHora(),
                    String.format("%.2f", o.getTotal()),
                    o.getEstado(),
                    o.getUsuarioNombre() != null ? o.getUsuarioNombre() : ""
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las órdenes: " + e.getMessage());
        }
    }
}
