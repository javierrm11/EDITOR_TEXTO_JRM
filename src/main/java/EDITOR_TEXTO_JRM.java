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
    private JMenuItem mi1,mi2,mi3;

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

        menubar=new JMenuBar();
        setJMenuBar(menubar);

        menubar.setBackground(Color.darkGray);
        menubar.setBorder(new EmptyBorder(5, 0, 5, 0));

        menu=new JMenu("Archivo");
        menu.setForeground(Color.white);

        menubar.add(menu);
        mi1=new JMenuItem("Guardar");
        mi1.addActionListener(new Guardar());

        menu.add(mi1);
        mi2=new JMenuItem("Salir");
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
            int start = escribir.getSelectionStart();
            int end = escribir.getSelectionEnd();
            StyledDocument doc = escribir.getStyledDocument();
            Style style = doc.addStyle("Negrita", null);
            AttributeSet atributos = doc.getCharacterElement(start).getAttributes();
            boolean esNegrita = StyleConstants.isBold(atributos);
            StyleConstants.setBold(style, !esNegrita);
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    private class Cursiva implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int start = escribir.getSelectionStart();
            int end = escribir.getSelectionEnd();
            StyledDocument doc = escribir.getStyledDocument();
            Style style = doc.addStyle("Cursiva", null);
            AttributeSet atributos = doc.getCharacterElement(start).getAttributes();
            boolean esCursiva = StyleConstants.isItalic(atributos);
            StyleConstants.setItalic(style, !esCursiva);
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    private class Subraya implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int start = escribir.getSelectionStart();
            int end = escribir.getSelectionEnd();
            StyledDocument doc = escribir.getStyledDocument();
            Style style = doc.addStyle("Subrayado", null);
            AttributeSet atributos = doc.getCharacterElement(start).getAttributes();
            boolean esSubrayado = StyleConstants.isUnderline(atributos);
            StyleConstants.setUnderline(style, !esSubrayado);
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    private class CambiarFuente implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int start = escribir.getSelectionStart();
            int end = escribir.getSelectionEnd();
            StyledDocument doc = escribir.getStyledDocument();
            String fontName = (String) combo.getSelectedItem();
            Style style = doc.addStyle("Fuente", null);
            StyleConstants.setFontFamily(style, fontName);
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    private class Tamano implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int start = escribir.getSelectionStart();
            int end = escribir.getSelectionEnd();
            StyledDocument doc = escribir.getStyledDocument();
            String fontSize = (String) tamano.getSelectedItem();
            Style style = doc.addStyle("Tamano", null);
            StyleConstants.setFontSize(style, Integer.parseInt(fontSize));
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    private class SeColor implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Color selectedColor = JColorChooser.showDialog(null, "Seleccione un color", Color.BLACK);
            if (selectedColor != null) {
                int start = escribir.getSelectionStart();
                int end = escribir.getSelectionEnd();
                StyledDocument doc = escribir.getStyledDocument();
                SimpleAttributeSet attrs = new SimpleAttributeSet();
                StyleConstants.setForeground(attrs, selectedColor);
                doc.setCharacterAttributes(start, end - start, attrs, false);
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
        editor.setLocation(100, 0);
        editor.setSize(750, 600);
        editor.setVisible(true);
        editor.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
