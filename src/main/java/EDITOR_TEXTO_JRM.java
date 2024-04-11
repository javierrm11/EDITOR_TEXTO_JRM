import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EDITOR_TEXTO_JRM extends JFrame {
    private JPanel panel;
    private JButton negrita, cursiva, subraya, color;
    private JComboBox<String> combo, tamano;
    private JTextPane escribir;

    private JMenuBar menubar;
    private JMenu menu;
    private JMenuItem mi1, mi2, mi3;

    public EDITOR_TEXTO_JRM() {
        panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(null);

        negrita = new JButton("Negrita");
        cursiva = new JButton("Cursiva");
        subraya = new JButton("Subrayado");
        color = new JButton("Color");

        combo = new JComboBox<>();
        combo.addItem("Arial");
        combo.addItem("Verdana");
        combo.addItem("Times New Roman");
        combo.addItem("Georgia");

        tamano = new JComboBox<>();
        tamano.addItem("8");
        tamano.addItem("12");
        tamano.addItem("16");
        tamano.addItem("24");
        tamano.addItem("32");
        tamano.addItem("64");
        tamano.addItem("128");

        negrita.setBounds(30, 20, 80, 30);
        cursiva.setBounds(130, 20, 80, 30);
        subraya.setBounds(230, 20, 120, 30);
        combo.setBounds(370, 20, 100, 30);
        tamano.setBounds(490, 20, 100, 30);
        color.setBounds(610, 20, 100, 30);


        negrita.setBackground(Color.darkGray);
        cursiva.setBackground(Color.darkGray);
        subraya.setBackground(Color.darkGray);
        combo.setBackground(Color.darkGray);
        tamano.setBackground(Color.darkGray);
        color.setBackground(Color.darkGray);

        negrita.setForeground(Color.white);
        cursiva.setForeground(Color.white);
        subraya.setForeground(Color.white);
        combo.setForeground(Color.white);
        tamano.setForeground(Color.white);
        color.setForeground(Color.white);

        negrita.setBorder(BorderFactory.createRaisedBevelBorder());
        cursiva.setBorder(BorderFactory.createRaisedBevelBorder());
        subraya.setBorder(BorderFactory.createRaisedBevelBorder());
        combo.setBorder(BorderFactory.createRaisedBevelBorder());
        tamano.setBorder(BorderFactory.createRaisedBevelBorder());
        color.setBorder(BorderFactory.createRaisedBevelBorder());

        panel.add(negrita);
        panel.add(cursiva);
        panel.add(subraya);
        panel.add(combo);
        panel.add(tamano);
        panel.add(color);

        escribir = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(escribir);
        scrollPane.setBounds(30, 60, 680, 300);

        escribir.setEditable(true);
        panel.add(scrollPane);

        add(panel);
        negrita.addActionListener(new Bold());
        cursiva.addActionListener(new Cursiva());
        subraya.addActionListener(new Subraya());
        combo.addActionListener(new CambiarFuente());
        tamano.addActionListener(new Tamano());
        color.addActionListener(new SeColor());

        menubar = new JMenuBar();
        setJMenuBar(menubar);

        menubar.setBackground(Color.darkGray);
        menubar.setBorder(new EmptyBorder(5, 0, 5, 0));

        menu = new JMenu("Archivo"); // Inicializa el menu
        menu.setForeground(Color.white);

        menubar.add(menu);
        mi1 = new JMenuItem("Guardar"); // Inicializa un elemento del menu
        mi1.addActionListener(new Guardar());

        menu.add(mi1);
        mi2 = new JMenuItem("Salir");
        mi2.addActionListener(new Salir());

        mi1.setBackground(Color.darkGray);
        mi2.setBackground(Color.darkGray);

        mi1.setForeground(Color.white);
        mi2.setForeground(Color.white);

        mi1.setBorder(new EmptyBorder(5, 5, 5, 5));
        mi2.setBorder(new EmptyBorder(5, 5, 5, 5));

        menu.add(mi2);

    }

    private class Bold implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MutableAttributeSet attrs = escribir.getInputAttributes();
            boolean bold = (StyleConstants.isBold(attrs)) ? false : true;
            StyleConstants.setBold(attrs, bold);
            escribir.setCharacterAttributes(attrs, false);
        }
    }

    private class Cursiva implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MutableAttributeSet attrs = escribir.getInputAttributes();
            boolean italic = (StyleConstants.isItalic(attrs)) ? false : true;
            StyleConstants.setItalic(attrs, italic);
            escribir.setCharacterAttributes(attrs, false);
        }
    }

    private class Subraya implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MutableAttributeSet attrs = escribir.getInputAttributes();
            boolean underline = (StyleConstants.isUnderline(attrs)) ? false : true;
            StyleConstants.setUnderline(attrs, underline);
            escribir.setCharacterAttributes(attrs, false);
        }
    }

    private class CambiarFuente implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String fontName = (String) combo.getSelectedItem();
            if (fontName != null) {
                MutableAttributeSet attrs = new SimpleAttributeSet();
                StyleConstants.setFontFamily(attrs, fontName);
                escribir.setCharacterAttributes(attrs, false);
            }
        }
    }
    private class Tamano implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String fontSize = (String) tamano.getSelectedItem();
            if (fontSize != null) {
                MutableAttributeSet attrs = new SimpleAttributeSet();
                StyleConstants.setFontSize(attrs, Integer.parseInt(fontSize));
                escribir.setCharacterAttributes(attrs, false);
            }
        }
    }

    private class SeColor implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Color selectedColor = JColorChooser.showDialog(null, "Seleccione un color", Color.BLACK);
            if (selectedColor != null) {
                MutableAttributeSet attrs = new SimpleAttributeSet();
                StyleConstants.setForeground(attrs, selectedColor);
                escribir.setCharacterAttributes(attrs, false);
            }
        }
    }

    private class Guardar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser file = new JFileChooser();
            int seleccion = file.showSaveDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File texto = file.getSelectedFile();
                try {
                    FileWriter escribe = new FileWriter(texto);
                    escribe.write(escribir.getText());
                    escribe.close();
                    JOptionPane.showMessageDialog(null, "Guardado exitosamente");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al guardar el archivo");
                }
            }
        }
    }

    private class Salir implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    public static void main(String[] args) {
        EDITOR_TEXTO_JRM editor = new EDITOR_TEXTO_JRM();
        editor.setTitle("Design Preview [ Editor de Texto ]");
        editor.setLocation(400, 100);
        editor.setSize(750, 600);
        editor.setVisible(true);
        editor.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}