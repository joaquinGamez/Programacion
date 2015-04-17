import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
/**
 * Write a description of class Telefono here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TelefonoLocoDos extends JFrame
{
    private static final int ESPACIO = 10;
    private static final String MENSAJE_LLAMADA = "¡¡¡LLAMANDO!!!";
    private static final String NUMERO = "1234567890";

    private JTextField pantalla;

    private String numeroMarcado;

    /**
     * Constructor for objects of class Telefono
     */
    public TelefonoLocoDos()
    {
        super("Sony");
        construirVentana();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        setVisible(true);
    }

    /**
     * Marca un dígito.
     */
    private void marcar(String texto)
    {
        String numTelefono = pantalla.getText();
        if(!numTelefono.equals(MENSAJE_LLAMADA)){
            int numDigitos = numTelefono.length();
            char digitoFijo = hallarDigitoFijo(numDigitos);
            pantalla.setText(numTelefono + digitoFijo);
        }
    }

    /**
     * Borra el último dígito pulsado.
     */
    private void borrar()
    {
        String texto = pantalla.getText();
        int tamaño = texto.length();
        if(tamaño > 0 && !texto.equals(MENSAJE_LLAMADA)){
            pantalla.setText(texto.substring(0, tamaño - 1));
        }
    }

    /**
     * "Llama" al número marcado.
     * Muestra en la pantalla un mensaje.
     */
    private void llamar()
    {
        int tamaño = pantalla.getText().length();	
        numeroMarcado = pantalla.getText();
        if(tamaño > 0){
            pantalla.setText(MENSAJE_LLAMADA);
        }	
    }

    /**
     * 
     */
    private void rellamar()
    {
        pantalla.setText(numeroMarcado);
    }

    /**
     * Limpia la pantalla.
     */
    private void limpiar()
    {
        pantalla.setText("");
    }

    /**
     * "Cuelga" y cierra la aplicación.
     */
    private void colgar()
    {
        System.exit(0);
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

        agregarBotonNumero(panelBotones, "1");
        agregarBotonNumero(panelBotones, "2");
        agregarBotonNumero(panelBotones, "3");

        agregarBotonNumero(panelBotones, "4");
        agregarBotonNumero(panelBotones, "5");
        agregarBotonNumero(panelBotones, "6");

        agregarBotonNumero(panelBotones, "7");
        agregarBotonNumero(panelBotones, "8");
        agregarBotonNumero(panelBotones, "9");

        agregarBotonBorrar(panelBotones, "B");
        agregarBotonNumero(panelBotones, "0");
        agregarBotonLimpiar(panelBotones, "L");

        agregarBotonRellamar(panelBotones, "R");
        agregarBotonLlamar(panelBotones, "LL");
        agregarBotonColgar(panelBotones, "C");

        panelContenedor.add(panelBotones, "Center");

        pack();
    }

    /**
     * Agrega los números al panel.
     */
    private void agregarBotonNumero(JPanel panel, String texto)
    {
        JButton boton = new JButton(texto);
        panel.add(boton);
        boton.setToolTipText("Marca el " + texto);
        boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { marcar(texto); }
            });
    }

    /**
     * Agrega los números al panel.
     */
    private void agregarBotonBorrar(JPanel panel, String texto)
    {
        JButton boton = new JButton(texto);
        boton.setToolTipText("Borra el último dígito.");
        boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { borrar(); }
            });
        panel.add(boton);
    }

    /**
     * 
     */
    private void agregarBotonLlamar(JPanel panel, String texto)
    {
        JButton boton = new JButton(texto);
        boton.setToolTipText("Llama al número marcado.");
        boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { llamar(); }
            });
        panel.add(boton);
    }

    /**
     * 
     */
    private void agregarBotonLimpiar(JPanel panel, String texto)
    {
        JButton boton = new JButton(texto);
        boton.setToolTipText("Limpia la pantalla.");
        boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { limpiar(); }
            });
        panel.add(boton);
    }

    /**
     * 
     */
    private void agregarBotonColgar(JPanel panel, String texto)
    {
        JButton boton = new JButton(texto);
        boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { colgar(); }
            });
        panel.add(boton);
    }

    /**
     * 
     */
    private void agregarBotonRellamar(JPanel panel, String texto)
    {
        JButton boton = new JButton(texto);
        boton.setToolTipText("Marca el último número llamado.");
        boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { rellamar(); }
            });
        panel.add(boton);
    } 

    /**
     * Halla el dígito fijo que le corresponde a la posición.
     */
    private char hallarDigitoFijo(int posicion)
    {
        posicion = posicion % NUMERO.length();
        return NUMERO.charAt(posicion);
    }
}