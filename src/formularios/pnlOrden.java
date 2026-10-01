package formularios;

import clases.Categoria;
import clases.CategoriaDAO;
import clases.Combo;
import clases.ComboDAO;
import clases.DetalleOrden;
import clases.Item;
import clases.Orden;
import clases.OrdenService;
import clases.Producto;
import clases.ProductoDAO;
import clases.Sesion;
import clases.TicketPDF;
import clases.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class pnlOrden extends javax.swing.JPanel {

    DefaultTableModel modeloDetalle;
    private CategoriaDAO categoriaDao = new CategoriaDAO();
    private ProductoDAO productoDao = new ProductoDAO();
    private ComboDAO comboDao = new ComboDAO();
    private boolean cargando = false;
    private int linea = 0;
    private TicketPDF ticketPDF = new TicketPDF();
    private int idOrdenActual = -1;


    public pnlOrden() {
        btnVerOrdenes = new javax.swing.JButton();
        btnVerOrdenes.setText("Ver \u00d3rdenes");
        btnVerOrdenes.setBackground(new Color(0, 149, 183));
        btnVerOrdenes.setForeground(Color.WHITE);
        btnVerOrdenes.setPreferredSize(new java.awt.Dimension(120, 35));
        btnVerOrdenes.addActionListener(e -> {
            formularios.frmOrdenesRecientes dialog = new formularios.frmOrdenesRecientes(null);
            dialog.setVisible(true);
            int idSeleccion = dialog.getOrdenSeleccionada();
            if (idSeleccion != -1) {
                OrdenService service = new OrdenService();
                Orden orden = service.buscarOrden(idSeleccion);
                if (orden != null) {
                    idOrdenActual = orden.getIdOrden();
                    txtIdOrden.setText(String.valueOf(orden.getIdOrden()));
                    txtFechaHora.setText(orden.getFechaHora());
                    txtTotal.setText(String.format("%.2f", orden.getTotal()));
                    txtBusqueda.setText(String.valueOf(orden.getIdOrden()));
                    btnProcesar.setEnabled(false);
                    modeloDetalle.setRowCount(0);
                    linea = 0;
                    for (DetalleOrden d : orden.getDetalles()) {
                        linea++;
                        double subtotal = d.getPrecio() * d.getCantidad();
                        modeloDetalle.addRow(new Object[]{
                            d.getIdLinea(), d.getTipo(), d.getId(), d.getNombre(),
                            d.getPrecio(), d.getCantidad(), subtotal
                        });
                    }
                }
            }
        });
        initComponents();
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (!Character.isDigit(evt.getKeyChar())) evt.consume();
            }
        });
        ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/logoOrdenFer.png"));
        Image imagen = icono.getImage();
        Image imagenEscalada = imagen.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(imagenEscalada));
        cargarCategorias();
        crearDetalle();
        tblDetalle.getColumnModel().getColumn(5).setCellEditor(new javax.swing.DefaultCellEditor(new javax.swing.JTextField() {
            {addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    if (!Character.isDigit(evt.getKeyChar())) evt.consume();
                }
            });}
        }));
        txtIdOrden.setText("Se genera al procesar");
        txtIdOrden.setEditable(false);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        txtFechaHora.setText(LocalDateTime.now().format(formato));
        txtFechaHora.setEditable(false);
        txtUsuario.setText(Sesion.getUsuarioActual().toString());
        Color azulClaro = new Color(234, 244, 248);
        tblDetalle.getParent().setBackground(azulClaro);
        tblDetalle.getTableHeader().setBackground(azulClaro);
        pnlAcciones.setBackground(azulClaro);
        pnlDetalle.setBackground(azulClaro);
        pnlEncabezado.setBackground(azulClaro);
        pnlBusqueda.setBackground(azulClaro);
        aplicarColoresTabla();
        Usuario u = Sesion.getUsuarioActual();
        txtUsuario.setText(u != null ? u.toString() : "Sin sesión");
        txtUsuario.setEditable(false);
        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        btnAnular.setVisible(Sesion.tienePermiso("ANULAR_ORDEN"));
    }


    private void aplicarColoresTabla() {
        tblDetalle.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) return c;
                try {
                    double precio = Double.parseDouble(value.toString());
                    if (precio >= 6.0) {
                        c.setForeground(new Color(200, 0, 0));
                        c.setFont(c.getFont().deriveFont(Font.BOLD));
                    } else if (precio < 5.0) {
                        c.setForeground(new Color(0, 120, 0));
                    } else {
                        c.setForeground(Color.BLACK);
                        c.setFont(c.getFont().deriveFont(Font.PLAIN));
                    }
                } catch (Exception e) {
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });
    }

    private List<DetalleOrden> obtenerDetalleDesdeTabla() {
        List<DetalleOrden> lista = new ArrayList<>();
        DefaultTableModel modelo = (DefaultTableModel) tblDetalle.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object tipoObj = modelo.getValueAt(i, 1);
            Object idObj = modelo.getValueAt(i, 2);
            Object cantObj = modelo.getValueAt(i, 5);
            Object precioObj = modelo.getValueAt(i, 4);
            if (tipoObj == null || idObj == null || cantObj == null || precioObj == null) continue;
            try {
                String tipo = tipoObj.toString();
                int id = Integer.parseInt(idObj.toString());
                int cantidad = Integer.parseInt(cantObj.toString());
                double precio = Double.parseDouble(precioObj.toString());
                lista.add(new DetalleOrden(tipo, id, cantidad, precio));
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return lista;
    }

    private void cargarCategorias() {
        cmbCategoria.removeAllItems();
        cmbCategoria.addItem("COMBOS");
        for (Categoria c : categoriaDao.listar()) {
            ((JComboBox) cmbCategoria).addItem(c);
        }
    }

    private void cargarItems() {
        cargando = true;
        cmbItems.removeAllItems();
        Object seleccion = cmbCategoria.getSelectedItem();
        if (seleccion == null) { cargando = false; return; }

        if (seleccion instanceof Categoria cat) {
            for (Producto p : productoDao.listarPorCategoria(Integer.parseInt(cat.getIdCategoria()))) {
                cmbItems.addItem(new Item(
                    Integer.parseInt(p.getIdProducto()),
                    p.getNombre(),
                    p.getPrecio(),
                    "PRODUCTO"
                ));
            }
        } else if ("COMBOS".equals(seleccion)) {
            for (Combo c : comboDao.listarCombos()) {
                cmbItems.addItem(new Item(
                    c.getIdCombo(),
                    c.getCombo(),
                    c.getPrecio(),
                    "COMBO"
                ));
            }
        }

        if (cmbItems.getItemCount() > 0) {
            cmbItems.setSelectedIndex(0);
        }
        cargando = false;
    }

    private void crearDetalle() {
        modeloDetalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };
        modeloDetalle.addColumn("Línea");
        modeloDetalle.addColumn("Tipo");
        modeloDetalle.addColumn("Id");
        modeloDetalle.addColumn("Nombre");
        modeloDetalle.addColumn("Precio");
        modeloDetalle.addColumn("Cantidad");
        modeloDetalle.addColumn("Subtotal");
        tblDetalle.setModel(modeloDetalle);
        tblDetalle.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(10);
        tblDetalle.getColumnModel().getColumn(2).setPreferredWidth(10);
        tblDetalle.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblDetalle.getColumnModel().getColumn(4).setPreferredWidth(10);
        tblDetalle.getColumnModel().getColumn(5).setPreferredWidth(10);
        tblDetalle.getColumnModel().getColumn(6).setPreferredWidth(10);
        tblDetalle.getTableHeader().setResizingAllowed(false);
        tblDetalle.getTableHeader().setReorderingAllowed(false);

        modeloDetalle = (DefaultTableModel) tblDetalle.getModel();

        txtTotal.setText("0.0");

        tblDetalle.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    eliminarFilaSeleccionada();
                }
            }
        });
    }

    private void calcularTotal() {
        DefaultTableModel modelo = (DefaultTableModel) tblDetalle.getModel();
        double total = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            try {
                total += Double.parseDouble(modelo.getValueAt(i, 6).toString());
            } catch (NumberFormatException e) {
                // treat as 0
            }
        }
        txtTotal.setText(String.format("%.2f", total));
    }

    private void eliminarFilaSeleccionada() {
        int fila = tblDetalle.getSelectedRow();
        if (fila >= 0) {
            modeloDetalle.removeRow(fila);
            calcularTotal();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        pnlEncabezado = new javax.swing.JPanel();
        lblIdOrden = new javax.swing.JLabel();
        txtIdOrden = new javax.swing.JTextField();
        lblusuario = new javax.swing.JLabel();
        lblFechaHora = new javax.swing.JLabel();
        txtFechaHora = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        pnlBusqueda = new javax.swing.JPanel();
        lblBusquedaId = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnEliminarfila = new javax.swing.JButton();
        btnDespachar = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        pnlAcciones = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnProcesar = new javax.swing.JButton();
        pnlDetalle = new javax.swing.JPanel();
        lblCategoria = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        lblProductoCombo = new javax.swing.JLabel();
        cmbItems = new javax.swing.JComboBox<>();
        spnCantidad = new javax.swing.JSpinner();
        lblCantidad = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(800, 500));

        lblIdOrden.setText("Id de Orden");

        txtIdOrden.setEditable(false);
        txtIdOrden.setFocusable(false);

        lblusuario.setText("Usuario");

        lblFechaHora.setText("Fecha y Hora");

        txtFechaHora.setFocusable(false);

        txtUsuario.setEditable(false);
        txtUsuario.setFocusable(false);

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIdOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdOrden)
                    .addComponent(txtIdOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaHora)
                    .addComponent(txtFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblusuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        lblBusquedaId.setText("Escriba el id");

        btnBuscar.setBackground(new java.awt.Color(0, 149, 183));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btnBuscar.setText("Buscar Orden");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        btnLimpiar.setBackground(new java.awt.Color(200, 230, 235));
        btnLimpiar.setForeground(new java.awt.Color(10, 50, 60));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        btnEliminarfila.setBackground(new java.awt.Color(200, 230, 235));
        btnEliminarfila.setForeground(new java.awt.Color(10, 50, 60));
        btnEliminarfila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btnEliminarfila.setText("Eliminar fila");
        btnEliminarfila.addActionListener(this::btnEliminarfilaActionPerformed);

        btnDespachar.setBackground(new java.awt.Color(0, 149, 183));
        btnDespachar.setForeground(new java.awt.Color(255, 255, 255));
        btnDespachar.setText("Despachar Orden");
        btnDespachar.addActionListener(this::btnDespacharActionPerformed);

        btnAnular.setBackground(new java.awt.Color(200, 230, 235));
        btnAnular.setForeground(new java.awt.Color(10, 50, 60));
        btnAnular.setText("Anular Orden");
        btnAnular.addActionListener(this::btnAnularActionPerformed);

        javax.swing.GroupLayout pnlBusquedaLayout = new javax.swing.GroupLayout(pnlBusqueda);
        pnlBusqueda.setLayout(pnlBusquedaLayout);
        pnlBusquedaLayout.setHorizontalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBusquedaId, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnular))
                .addGap(10, 10, 10)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDespachar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBusqueda))
                .addGap(15, 15, 15)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBusquedaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnLimpiar))
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(btnEliminarfila)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBusquedaLayout.setVerticalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBusquedaId)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(btnEliminarfila))
                .addGap(18, 18, 18)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDespachar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnAnular))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTotal.setText("Total $");

        txtTotal.setEditable(false);

        btnProcesar.setBackground(new java.awt.Color(0, 149, 183));
        btnProcesar.setForeground(new java.awt.Color(255, 255, 255));
        btnProcesar.setText("Procesar");
        btnProcesar.addActionListener(this::btnProcesarActionPerformed);

        javax.swing.GroupLayout pnlAccionesLayout = new javax.swing.GroupLayout(pnlAcciones);
        pnlAcciones.setLayout(pnlAccionesLayout);
        pnlAccionesLayout.setHorizontalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccionesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnVerOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        pnlAccionesLayout.setVerticalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        pnlDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblCategoria.setText("Categoria");

        cmbCategoria.setBackground(new java.awt.Color(200, 230, 235));
        cmbCategoria.setForeground(new java.awt.Color(10, 50, 60));
        cmbCategoria.addActionListener(this::cmbCategoriaActionPerformed);

        lblProductoCombo.setText("Producto/Combo");

        cmbItems.setBackground(new java.awt.Color(200, 230, 235));
        cmbItems.setForeground(new java.awt.Color(10, 50, 60));

        lblCantidad.setText("Cantidad");

        btnAgregar.setBackground(new java.awt.Color(0, 149, 183));
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
            }
        ));
        tblDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDetalle);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(500, 250));
        jScrollPane3.setMinimumSize(new java.awt.Dimension(500, 250));

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCategoria)
                    .addComponent(lblProductoCombo)
                    .addComponent(lblCantidad))
                .addGap(10, 10, 10)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbItems, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnAgregar)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoria)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductoCombo)
                    .addComponent(cmbItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad)
                    .addComponent(btnAgregar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        btnProcesar.setEnabled(false);
        String texto = txtBusqueda.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el número de orden a buscar.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de orden debe ser un número.");
            return;
        }

        OrdenService service = new OrdenService();
        Orden orden = service.buscarOrden(id);

        if (orden == null) {
            JOptionPane.showMessageDialog(this, "No se encontró ninguna orden con ID " + id);
            return;
        }

        idOrdenActual = orden.getIdOrden();
        txtIdOrden.setText(String.valueOf(orden.getIdOrden()));
        txtFechaHora.setText(orden.getFechaHora());
        txtTotal.setText(String.format("%.2f", orden.getTotal()));

        modeloDetalle.setRowCount(0);
        linea = 0;
        for (DetalleOrden d : orden.getDetalles()) {
            linea++;
            double subtotal = d.getPrecio() * d.getCantidad();
            modeloDetalle.addRow(new Object[]{
                d.getIdLinea(),
                d.getTipo(),
                d.getId(),
                d.getNombre(),
                d.getPrecio(),
                d.getCantidad(),
                subtotal
            });
        }
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        idOrdenActual = -1;
        txtIdOrden.setText("Se genera al procesar");
        txtBusqueda.setText("");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        txtFechaHora.setText(LocalDateTime.now().format(formato));
        txtTotal.setText("0.0");
        modeloDetalle.setRowCount(0);
        linea = 0;
        btnProcesar.setEnabled(true);
    }

    private void btnEliminarfilaActionPerformed(java.awt.event.ActionEvent evt) {
        eliminarFilaSeleccionada();
    }

    private void btnDespacharActionPerformed(java.awt.event.ActionEvent evt) {
        if (idOrdenActual == -1) {
            JOptionPane.showMessageDialog(this, "Primero busca una orden.");
            return;
        }
        try {
            new OrdenService().despacharOrden(idOrdenActual);
            JOptionPane.showMessageDialog(this, "Orden #" + idOrdenActual + " marcada como DESPACHADA.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {
        if (idOrdenActual == -1) {
            JOptionPane.showMessageDialog(this, "Primero busca una orden.");
            return;
        }
        int ok = JOptionPane.showConfirmDialog(this,
            "¿Anular la orden #" + idOrdenActual + "?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
        if (ok != JOptionPane.YES_OPTION) return;
        try {
            new OrdenService().anularOrden(idOrdenActual);
            JOptionPane.showMessageDialog(this, "Orden #" + idOrdenActual + " ANULADA.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {
        List<DetalleOrden> detalle = obtenerDetalleDesdeTabla();
        if (detalle.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe agregar al menos un ítem");
            return;
        }
        OrdenService service = new OrdenService();
        int idOrden = service.procesarOrden(detalle);
        JOptionPane.showMessageDialog(null, "Orden #" + idOrden + " procesada correctamente");
        ticketPDF.generarTicket(idOrden);

        modeloDetalle.setRowCount(0);
        linea = 0;
        txtTotal.setText("0.0");
    }

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {
        cargarItems();
    }

    private void agregarItemDetalle(Item item, int cantidad) {
        if (item == null || item.getTipo() == null) return;
        DefaultTableModel modelo = (DefaultTableModel) tblDetalle.getModel();
        for (int fila = 0; fila < modelo.getRowCount(); fila++) {
            Object tipoObj = modelo.getValueAt(fila, 1);
            Object idObj = modelo.getValueAt(fila, 2);
            if (tipoObj == null || idObj == null) continue;
            try {
                String tipo = tipoObj.toString();
                int id = Integer.parseInt(idObj.toString());
                if (item.getTipo().equals(tipo) && id == item.getId()) {
                    int cantActual = Integer.parseInt(modelo.getValueAt(fila, 5).toString());
                    int nuevaCantidad = cantActual + cantidad;
                    modelo.setValueAt(nuevaCantidad, fila, 5);
                    modelo.setValueAt(nuevaCantidad * item.getPrecio(), fila, 6);
                    calcularTotal();
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error en los datos de la tabla: " + e.getMessage());
                return;
            }
        }
        linea = linea + 1;
        double subtotal = cantidad * item.getPrecio();
        modelo.addRow(new Object[]{
            linea,
            item.getTipo(),
            item.getId(),
            item.getNombre(),
            item.getPrecio(),
            cantidad,
            subtotal
        });
        calcularTotal();
    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        Item item = (Item) cmbItems.getSelectedItem();
        if (item == null) return;
        int cantidad = (int) spnCantidad.getValue();
        agregarItemDetalle(item, cantidad);
        spnCantidad.setValue(1);
    }

    private void tblDetalleMouseClicked(java.awt.event.MouseEvent evt) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnVerOrdenes;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDespachar;
    private javax.swing.JButton btnEliminarfila;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProcesar;
    private javax.swing.JComboBox<Object> cmbCategoria;
    private javax.swing.JComboBox<Object> cmbItems;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblBusquedaId;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblFechaHora;
    private javax.swing.JLabel lblIdOrden;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblProductoCombo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtFechaHora;
    private javax.swing.JTextField txtIdOrden;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
