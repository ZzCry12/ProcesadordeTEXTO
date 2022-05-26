
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class procesadorTexto extends JFrame implements ActionListener {

    private JMenuBar mb;
    private JMenu menu1, menu2, menu3, estilo, fuente, tamaño, color;
    private JMenuItem mi11, mi12, mi13, mi14, mi21, mi22, mi23, mi31, mi32;
    private JTextPane areadetexto;
    public String fuentes[];
    private JFileChooser abrirArchivo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    procesadorTexto frame = new procesadorTexto();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public procesadorTexto() {

        setTitle("Procesador de Texto");

        setSize(500, 600);

        setIconImage(new ImageIcon("eventos/eventos/logo.png").getImage());

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);

        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());

        contentPane.setLayout(null);

        setContentPane(contentPane);

        /* Jmenubar PRINCIPAL */
        setLayout(null);
        mb = new JMenuBar();
        setJMenuBar(mb);

        // MENU 1
        menu1 = new JMenu("Archivo");
        mb.add(menu1);

        mi11 = new JMenuItem("Nuevo archivo");
        mi11.addActionListener(this);
        mi12 = new JMenuItem("Abrir archivo");
        mi12.addActionListener(this);
        mi13 = new JMenuItem("Guardar archivo");
        mi13.addActionListener(this);
        mi14 = new JMenuItem("Cerrar");
        mi14.addActionListener(this);

        menu1.add(mi11);
        menu1.add(mi12);
        menu1.add(mi13);
        menu1.add(mi14);

        // MENU 2
        menu2 = new JMenu("Editar");
        mb.add(menu2);
        mi21 = new JMenuItem("Copiar");
        mi21.addActionListener(this);
        mi22 = new JMenuItem("Pegar");
        mi21.addActionListener(this);
        mi23 = new JMenuItem("Cortar");
        mi21.addActionListener(this);

        menu2.add(mi21);
        menu2.add(mi22);
        menu2.add(mi23);

        /* Jmenubar PRINCIPAL */

        /* Jmenubar TIPO DE LETRA ETC. */
        fuente = new JMenu("Fuente");
        mb.add(fuente);
        estilo = new JMenu("Estilo");
        mb.add(estilo);
        tamaño = new JMenu("Tamaño");
        mb.add(tamaño);
        color = new JMenu("Color");
        mb.add(color);

        /* AREA DE TEXTO */
        areadetexto = new JTextPane();
        // areadetexto.setLineWrap(true);
        areadetexto.setBounds(0, 5, 485, 530);
        areadetexto.setBackground(Color.GRAY);
        contentPane.add(areadetexto, BorderLayout.CENTER);
        Font fuente2 = new Font("Serif", Font.ITALIC, 20);
        areadetexto.setFont(fuente2);

        // ACERCA DE

        menu3 = new JMenu("Acerca de");
        mb.add(menu3);
        mi31 = new JMenuItem(">Desarrollado por 'El Gran Dario RATA'<");
        mi32 = new JMenuItem("Versión 0.1 APLHA");
        // mi1.addActionListener(this);

        menu3.add(mi31);
        menu3.add(mi32);

        // --------------------------------------------

        String fuentes[] = getFuentesWin();

        // recorrer todas las fuentes del sistema
        for (String dato : fuentes) {
            JMenuItem elem_menu = new JMenuItem(dato);
            fuente.add(elem_menu);
            elem_menu.addActionListener(new StyledEditorKit.FontFamilyAction("", dato));
        }

        // ------------------ACCIONES PARA LOS ESTILOS DE LETRA-----------------------

        tipos_letra("Negrita");
        tipos_letra("Cursiva");
        tipos_letra("Subrayado");

        // ----------------TAMAÑO DE LA FUENTE------------------------

        for (int sice = 12; sice < 62; sice += 2) {
            JMenuItem tamañoletra = new JMenuItem(String.valueOf(sice));
            tamaño.add(tamañoletra);
            tamañoletra.addActionListener(new StyledEditorKit.FontSizeAction("", sice));
        }

        // ----------------COLOR DE las letras------------------------

        String colores[] = { "Negro", "Rojo", "Azul", "Verde" };

        for (String col : colores) {
            color_fuente(col);
        }

    }

    // --------------OBTENER LOS ESTILOS DE LETRA------------------

    public void tipos_letra(String elemento) {
        JMenuItem estilos = new JMenuItem(elemento);
        estilo.add(estilos);
        if (elemento.equals("Negrita"))
            estilos.addActionListener(new StyledEditorKit.BoldAction());
        if (elemento.equals("Cursiva"))
            estilos.addActionListener(new StyledEditorKit.ItalicAction());
        if (elemento.equals("Subrayado"))
            estilos.addActionListener(new StyledEditorKit.UnderlineAction());

    }

    // --------------OBTENER LOS TIPOS DE LETRA------------------
    String[] getFuentesWin() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }

    // OBTENER LOS COLORES

    public void color_fuente(String elemento) {
        JMenuItem elem_menu = new JMenuItem(elemento);
        color.add(elem_menu);
        if (elemento.equals("Negro"))
            elem_menu.addActionListener(new StyledEditorKit.ForegroundAction("", Color.BLACK));
        if (elemento.equals("Rojo"))
            elem_menu.addActionListener(new StyledEditorKit.ForegroundAction("", Color.RED));
        if (elemento.equals("Azul"))
            elem_menu.addActionListener(new StyledEditorKit.ForegroundAction("", Color.BLUE));
        if (elemento.equals("Verde"))
            elem_menu.addActionListener(new StyledEditorKit.ForegroundAction("", Color.GREEN));
    }

    // -------------------------obtener el contenido de archivo----------------

    public String getArchivo(String ruta) {
        FileReader fr = null;
        BufferedReader br = null;

        // Cadena de texto donde se guardara el contenido del archivo

        String contenido = "";
        try {
            // ruta q puede ser de tipo String o tipo File
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);

            String linea;

            // obtener lo que contiene el archivo

            while ((linea = br.readLine()) != null) {
                contenido += linea + "\n";
            }

        } catch (Exception e) {
        }

        finally {
            try {
                br.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Documento no encontrado :c");
            }
        }
        return contenido;
    }

    // guardar el arxivo

    public void actionPerformed(ActionEvent ae) {

        // NUEVO ARCHIVO
        String nuevo = ae.getActionCommand();
        if (nuevo.equals("Nuevo archivo")) {
            areadetexto.setText("");
        }
        // ABRIR EL ARCHIVO

        String abrir = ae.getActionCommand();
        if (abrir.equals("Abrir archivo")) {
            if (abrirArchivo == null)
                abrirArchivo = new JFileChooser();
            // para abrir solo archivos
            abrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int seleccion = abrirArchivo.showOpenDialog(this);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File f = abrirArchivo.getSelectedFile();
                try {
                    String nombre = f.getName();
                    String path = f.getAbsolutePath();
                    String contenido = getArchivo(path);

                    // cambiar el titulo del jpanel
                    this.setTitle(nombre);

                    // poner el texto en el jtextpane
                    areadetexto.setText(contenido);

                } catch (Exception exp) {
                }
            }
        }

        // GUARDAR EL ARCHIVO
        String guardar = ae.getActionCommand();
        if (guardar.equals("Guardar archivo")) {
            try {
                JFileChooser archivo = new JFileChooser(System.getProperty("user.dir"));
                archivo.showSaveDialog(this);
                if (archivo.getSelectedFile() != null) {
                    try (FileWriter guardado = new FileWriter(archivo.getSelectedFile())) {
                        guardado.write(areadetexto.getText());
                        JOptionPane.showMessageDialog(rootPane,
                                "El archivo fue guardado con éxito en la ruta establecida");
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

        // CERRAR EL PROGRAMA

        String cerrar = ae.getActionCommand();
        if (cerrar.equals("Cerrar")) {
            System.exit(0);
        }

        // COPIAR

        String copiar = ae.getActionCommand();
        if (copiar.equals("Copiar")) {
            areadetexto.copy();
        }

        // PEGAR

        String pegar = ae.getActionCommand();
        if (pegar.equals("Pegar")) {
            areadetexto.paste();
        }
        // CORTAR

        String cortar = ae.getActionCommand();
        if (cortar.equals("Cortar")) {
            areadetexto.cut();
        }
    }
}
