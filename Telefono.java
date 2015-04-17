import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
/**
 * Write a description of class Telefono here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Telefono extends JFrame
{
    private static final int ESPACIO = 10;
    
    private JTextField pantalla;

    /**
     * Constructor for objects of class Telefono
     */
    public Telefono()
    {
        super("Sony");
        construirVentana();
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        setVisible(true);
    }
    
    /**
     * 
     */
    private void agregarBoton(JPanel panel, String cadena)
    {
        JButton boton = new JButton(cadena);
        panel.add(boton);        
    }
    
    /**
     * 
     */
    private void construirVentana()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelContenedor = (JPanel)getContentPane();
        panelContenedor.setLayout(new BorderLayout(ESPACIO, ESPACIO));
        panelContenedor.setBorder(new EmptyBorder(ESPACIO, ESPACIO, ESPACIO, ESPACIO));
        
        pantalla = new JTextField();
        panelContenedor.add(pantalla, "North");
        
        JPanel panelBotones = new JPanel(new GridLayout(5, 3, ESPACIO, ESPACIO));
        
        for(int i = 1; i <= 9; i++){
            agregarBoton(panelBotones, "" + i);
        }
        
        agregarBoton(panelBotones, "B");
        agregarBoton(panelBotones, "0");
        agregarBoton(panelBotones, "L");
        
        agregarBoton(panelBotones, "R");
        agregarBoton(panelBotones, "LL");
        agregarBoton(panelBotones, "C");
        
        panelContenedor.add(panelBotones, "Center");
        
        pack();
    }
}
