import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;
/**
 * Write a description of class Telefono here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TelefonoConMemoria extends JFrame
{
    private static final int ESPACIO = 10;
    private static final String MENSAJE_LLAMADA = "¡¡¡LLAMANDO!!!";

    private JTextField pantalla;

    private JButton botonLlamar;
    private JButton botonBorrar;
    private JButton botonRepetir;
    private JButton botonLimpiar;

    private JPanel panelBotones;

    private String numeroMarcado;
    private Stack<String> pilaNumeros;

    /**
     * Constructor for objects of class Telefono
     */
    public TelefonoConMemoria()
    {
        super("Sony");
        pilaNumeros = new Stack<String>();
        construirVentana();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        setVisible(true);
    }

    /**
     * Construye la ventana.
     */
    private void construirVentana()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelContenedor = (JPanel)getContentPane();
        panelContenedor.setLayout(new BorderLayout(ESPACIO, ESPACIO));
        panelContenedor.setBorder(new EmptyBorder(ESPACIO, ESPACIO, ESPACIO, ESPACIO));

        pantalla = new JTextField();
        panelContenedor.add(pantalla, "North");

        panelBotones = new JPanel(new GridLayout(5, 3, ESPACIO, ESPACIO));

        crearBotones();

        panelContenedor.add(panelBotones, "Center");

        pack();
    }

    /**
     * 
     */
    private void crearBotones()
    {
        for(int i = 1; i <= 9; i++){
            agregarBotonNumero(panelBotones, "" + i);
        }

        agregarBotonBorrar(panelBotones, "B");
        agregarBotonNumero(panelBotones, "0");
        agregarBotonLimpiar(panelBotones, "L");

        agregarBotonRepetir(panelBotones, "R");
        agregarBotonLlamar(panelBotones, "LL");
        agregarBotonColgar(panelBotones, "C");        
    }

    /**
     * Marca un dígito.
     */
    private void marcar(String texto)
    {
        if(!pantalla.getText().equals(MENSAJE_LLAMADA)){
            pantalla.setText(pantalla.getText() + texto);
        }
    }

    /**
     * Borra el último dígito pulsado.
     */
    private void borrar()
    {
        String texto = pantalla.getText();
        int tamaño = texto.length();
        pantalla.setText(texto.substring(0, tamaño - 1));
        if(tamaño == 1){
            botonBorrar.setEnabled(false);
            botonLimpiar.setEnabled(false);
            botonLlamar.setEnabled(false);        
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
        pilaNumeros.push(numeroMarcado);
        if(tamaño > 0){
            pantalla.setText(MENSAJE_LLAMADA);
        }    
        botonLimpiar.setEnabled(true);
        botonRepetir.setEnabled(true);
        botonLlamar.setEnabled(false);
        botonBorrar.setEnabled(false);
    }

    /**
     * Repite el último número marcado.
     */
    private void rellamar()
    {
        if(!pilaNumeros.empty()){
            pantalla.setText(pilaNumeros.pop());
            botonRepetir.setEnabled(false);
        }
    }

    /**
     * Limpia la pantalla.
     */
    private void limpiar()
    {
        pantalla.setText("");
        botonBorrar.setEnabled(false);
        botonLimpiar.setEnabled(false);
        botonLlamar.setEnabled(false);
    }

    /**
     * "Cuelga" y cierra la aplicación.
     */
    private void colgar()
    {
        System.exit(0);
    }

    //Métodos para los botones.

    /**
     * Agrega los números al panel.
     * @param panel El panel al que se agrega el botón.
     * @param texto El texto que contiene el botón.
     */
    private void agregarBotonNumero(JPanel panel, String texto)
    {
        JButton boton = new JButton(texto);
        panel.add(boton);
        boton.setToolTipText("Marca el " + texto);
        boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    marcar(texto); 
                    botonLlamar.setEnabled(true);
                    botonRepetir.setEnabled(false);
                    botonBorrar.setEnabled(true);
                    botonLimpiar.setEnabled(true);
                }
            });
    }

    /**
     * Agrega el botón de borrar.
     * @param panel El panel al que se agrega el botón.
     * @param texto El texto que contiene el botón.
     */
    private void agregarBotonBorrar(JPanel panel, String texto)
    {
        botonBorrar = new JButton(texto);
        botonBorrar.setEnabled(false);
        botonBorrar.setToolTipText("Borra el último dígito.");
        botonBorrar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    borrar();
                }
            });
        panel.add(botonBorrar);
    }

    /**
     * Agrega el botón de llamar
     * @param panel El panel al que se agrega el botón.
     * @param texto El texto que contiene el botón.
     */
    private void agregarBotonLlamar(JPanel panel, String texto)
    {
        botonLlamar = new JButton(texto);
        botonLlamar.setEnabled(false);
        botonLlamar.setToolTipText("Llama al número marcado.");
        botonLlamar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    llamar(); 
                }
            });
        panel.add(botonLlamar);
    }

    /**
     * Agrega el botón de limpiar.
     * @param panel El panel al que se agrega el botón.
     * @param texto El texto que contiene el botón.
     */
    private void agregarBotonLimpiar(JPanel panel, String texto)
    {
        botonLimpiar = new JButton(texto);
        botonLimpiar.setEnabled(false);
        botonLimpiar.setToolTipText("Limpia la pantalla.");
        botonLimpiar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    limpiar();
                }
            });
        panel.add(botonLimpiar);
    }

    /**
     * Agrega el botón de colgar.
     * @param panel El panel al que se agrega el botón.
     * @param texto El texto que contiene el botón.
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
     * Agrega el botón de repetir.
     * @param panel El panel al que se agrega el botón.
     * @param texto El texto que contiene el botón.
     */
    private void agregarBotonRepetir(JPanel panel, String texto)
    {
        botonRepetir = new JButton(texto);
        botonRepetir.setEnabled(false);
        botonRepetir.setToolTipText("Marca el último número llamado.");
        botonRepetir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    rellamar(); 
                    botonLlamar.setEnabled(true);
                    botonLimpiar.setEnabled(true);
                    botonBorrar.setEnabled(true);
                    botonRepetir.setEnabled(true);
                }
            });
        panel.add(botonRepetir);
    }
}