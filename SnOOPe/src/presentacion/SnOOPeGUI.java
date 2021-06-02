package presentacion;


import aplicacion.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class SnOOPeGUI extends JFrame implements Serializable{
    String [] opciones = {"Aceptar", "Cancelar"};
    ArrayList<Alimento> frutas;
    ArrayList<Sorpresa> sorpresas;
    private char caso;
    private char gameOverCase;

    // BACKEND
    private SnOOPe snoopeGame;

    // VENTANA
    private static CardLayout cl;
    private static Container mainPanel;

    // PANEL MENU
    private JPanel menuPanel;
    private JLabel titulo;
    private JButton botonJugar;

    // private JButton botonConfig;
    private JButton botonGuardar;
    private JButton botonCargar;
    private JButton botonSalir;
    private ArrayList<JButton> botonesMenu;

    // PANEL JUEGO SOLO
    private PanelTablero tableroPanel;
    private Color colorTablero;
    private JPanel nivelPanel;
    private PanelSnakeP1 panelSnakeP1;
    private Color colorCuerpoSolo;
    private Color colorCabezaSolo;
    private int numCuadros, tamMax;
    private String nombreSolo;
    private JLabel puntuacionJugadorSolo;
    private JLabel sorpresaJugadorSolo;
    private KeyListener movimientosTablero;
    private int puntuacion;

    // PANEL JUEGO MULTI
    private JPanel nivelMultiPanel;
    private PanelTablero tableroMultiPanel;
    private PanelSnakeP1 panelJugador1;
    private PanelSnakeP2 panelJugador2;

    private Color colorCuerpoP1;
    private Color colorCabezaP1;
    private Color colorCuerpoP2;
    private Color colorCabezaP2;
    private String nombreP1;
    private String nombreP2;
    private JLabel puntuacionJugador1;
    private JLabel puntuacionJugador2;
    private JLabel sorpresaJugador1;
    private JLabel sorpresaJugador2;
    private KeyListener movimientosTableroMulti;
    private int puntuacionP1;
    private int puntuacionP2;

    // PANEL JUEGO MULTI
    private JPanel nivelMaquinaPanel;
    private PanelSnakeP1 panelJugador1M;
    private PanelSnakePM panelJugador3;

    private Color colorCuerpoP1M;
    private Color colorCabezaP1M;
    private Color colorCuerpoP3;
    private Color colorCabezaP3;
    private String nombreP1M;
    private String nombreP3;
    private JLabel puntuacionJugador1M;
    private JLabel puntuacionJugador3;
    private JLabel sorpresaJugador1M;
    private JLabel sorpresaJugador3;
    private KeyListener movimientosTableroMaquina;
    private int puntuacionP1M;
    private int puntuacionP3;

    // PANEL OVER
    private JPanel gameOverPanel;
    private JLabel tituloGameOver;
    private JButton botonReiniciar;
    private JButton botonMenu;
    private ArrayList<JButton> botonesGameOver;

    // PANEL CONFIG
    private JPanel configPanel;
    private JLabel tituloConfig;
    private JButton botonCancelar;
    private JButton botonJCJ;
    private JButton botonSOLO;
    private JButton botonJCM;
    private ArrayList<JButton> botonesConfig;

    // PANEL CONFIG SOLO;
    private JPanel configPanelSolo;
    private JLabel tituloConfigSolo;
    private JButton botonColorCabezaSolo;
    private JButton botonColorCuerpoSolo;
    private JButton botonGuardarConfigSolo;
    private JLabel textoNombreSolo;
    private JTextField campoNombre;
    private JButton botonFrutasSorpresasSolo;
    private JButton botonCancelarSolo;
    private ArrayList<JLabel> labelsConfigSolo;
    private ArrayList<JButton> botonesConfigSolo;


    // PANEL SELECCION FRUTAS Y SORPRESAS
    private JPanel panelFrutasSorpresas;
    private JLabel tituloFrutasSorpresas;
    private JCheckBox checkBoxFruta;
    private JCheckBox checkBoxFrutaArcoiris;
    private JCheckBox checkBoxDulce;
    private JCheckBox checkBoxVeneno;

    private JTextArea textoFruta;
    private JTextArea textoFrutaArcoiris;
    private JTextArea textoDulce;
    private JTextArea textoVeneno;

    private JCheckBox checkBoxVelocidadMas;
    private JCheckBox checkBoxVelocidadMenos;
    private JCheckBox checkBoxDivision;
    private JCheckBox checkBoxBloqueTrampa;
    private JCheckBox checkBoxEstrellaFuego;
    private JCheckBox checkBoxLupa;

    private JTextArea textoVelocidadMas;
    private JTextArea textoVelocidadMenos;
    private JTextArea textoDivision;
    private JTextArea textoBloqueTrampa;
    private JTextArea textoEstrellaFuego;
    private JTextArea textoLupa;

    private JButton botonGuardarSeleccion;
    private ArrayList<JCheckBox> checkBoxesFrutasSorpresas;

    // PANEL CONFIG MULTI
    private JPanel configPanelMulti;
    private JLabel tituloConfigMulti;
    private JButton botonColorCabezaP1;
    private JButton botonColorCabezaP2;
    private JButton botonColorCuerpoP1;
    private JButton botonColorCuerpoP2;
    private JButton botonGuardarConfigMulti;
    private JLabel textoNombreP1;
    private JLabel textoNombreP2;
    private JTextField campoNombreP1;
    private JTextField campoNombreP2;
    private JButton botonFrutasSorpresasMulti;
    private JButton botonCancelarMulti;
    private ArrayList<JLabel> labelsConfigMulti;
    private ArrayList<JButton> botonesConfigMulti;

    // PANEL CONFIG MAQUINA
    private JPanel configPanelMaquina;
    private JLabel tituloConfigMaquina;
    private JButton botonColorCabezaP1M;
    private JButton botonColorCabezaP3;
    private JButton botonColorCuerpoP1M;
    private JButton botonColorCuerpoP3;
    private JButton botonGuardarConfigMaquina;
    private JLabel textoNombreP1M;
    private JLabel textoNombreP3;
    private JTextField campoNombreP1M;
    private JTextField campoNombreP3;
    private JButton botonFrutasSorpresasMaquina;
    private JButton botonCancelarMaquina;
    private ArrayList<JLabel> labelsConfigMaquina;
    private ArrayList<JButton> botonesConfigMaquina;

    // PANEL PAUSA;
    private JPanel pausaPanel;
    private JButton botonVolver;
    private JLabel tituloPausa;
    private JButton botonSalirPausa;


    /**
     * Metodo constructor de la clase SnOOPeGUI
     */
    public SnOOPeGUI(){
        prepareElementos();
        prepareAcciones();
    }

    // ELEMENTOS

    /**
     * Metodo inicial los elementos del JFrame y inicia algunos atributos
     */
    private void prepareElementos(){
        // Ajustes ventana
        setTitle("SnOOPe");
        Dimension pantalla = getToolkit().getScreenSize();
        pantalla.setSize((int)(pantalla.getWidth()/3),(int)(pantalla.getWidth()/3));
        setSize(pantalla);
        setLocationRelativeTo(null);

        // Logo
        ImageIcon logo = new ImageIcon("./images/logo.png");
        setIconImage(logo.getImage());

        // CardLayout
        cl = new CardLayout();
        mainPanel = new JPanel(cl);
        add(mainPanel);

        // Otros elementos
        botonesMenu = new ArrayList<>();
        botonesGameOver = new ArrayList<>();
        botonesConfig = new ArrayList<>();
        botonesConfigSolo = new ArrayList<>();
        botonesConfigMulti = new ArrayList<>();
        botonesConfigMaquina = new ArrayList<>();
        labelsConfigSolo = new ArrayList<>();
        labelsConfigMulti = new ArrayList<>();
        labelsConfigMaquina = new ArrayList<>();
        checkBoxesFrutasSorpresas = new ArrayList<>();
        colorTablero = new Color(112, 186, 74, 255);
        tamMax = this.getWidth();
        numCuadros = 25;

        colorCuerpoSolo = Color.CYAN;
        colorCabezaSolo = Color.BLUE;

        colorCabezaP1 = Color.RED;
        colorCuerpoP1 = Color.ORANGE;
        colorCabezaP2 = Color.MAGENTA;
        colorCuerpoP2 = Color.PINK;

        colorCabezaP1M = Color.darkGray;
        colorCuerpoP1M = Color.GRAY;
        colorCabezaP3 = Color.YELLOW;
        colorCuerpoP3 = Color.WHITE;

        nombreSolo = "Player 1";

        nombreP1 = "Player 1";
        nombreP2 = "Player 2";

        nombreP1M = "Player 1";
        nombreP3 = "Player 3";

        puntuacion = 0;

        puntuacionP1 = 0;
        puntuacionP2 = 0;

        puntuacionP1M = 0;
        puntuacionP3 = 0;

        prepareElementosMenu();
        prepareElementosJuego();
        prepareElementosLose();
        prepareElementosConfig();
        prepareElementosConfigSolo();
        prepareElementosPausa();
        prepareElementosFrutasSorpresas();
        prepareElementosConfigMulti();
        prepareElementosJuegoMulti();
        prepareElementosConfigMaquina();
        prepareElementosJuegoMaquina();
    }

    /**
     * Metodo que prepara los elementos del JPanel del menu principal
     */
    private void prepareElementosMenu(){
        menuPanel = new JPanel();
        mainPanel.add(menuPanel,"Menu");
        menuPanel.setLayout(null);

        //Creamos el titulo (label) y lo añadimos al panelMenu
        titulo = new JLabel();
        titulo.setIcon(new ImageIcon("./images/tituloLogo.gif"));
        titulo.setBounds((this.getWidth()/2)-272,0,544,112);
        menuPanel.add(titulo);

        // Creamos y añadimos el boton de jugar
        botonJugar = new JButton();
        botonJugar.setIcon(new ImageIcon("./images/botonJugar64.png"));
        botonJugar.setBounds(this.getWidth()/2-92,160,171,42);
        botonJugar.setFocusPainted(false);
        botonJugar.setBorderPainted(false);
        botonJugar.setContentAreaFilled(false);
        botonJugar.setHorizontalAlignment(JButton.CENTER);
        botonJugar.setVerticalAlignment(JButton.CENTER);
        menuPanel.add(botonJugar);
        botonesMenu.add(botonJugar);

        // Creamos y añadimos el boton salir
        botonSalir = new JButton();
        botonSalir.setIcon(new ImageIcon("./images/botonSalir64.png"));
        botonSalir.setBounds(this.getWidth()/2-92,252,171,42);
        botonSalir.setFocusPainted(false);
        botonSalir.setBorderPainted(false);
        botonSalir.setContentAreaFilled(false);
        botonSalir.setHorizontalAlignment(JButton.CENTER);
        botonSalir.setVerticalAlignment(JButton.CENTER);
        menuPanel.add(botonSalir);
        botonesMenu.add(botonSalir);

        // Creamos y añadimos el boton cargar
        botonCargar = new JButton("Cargar");
        botonCargar.setHorizontalTextPosition(JButton.CENTER);
        botonCargar.setVerticalTextPosition(JButton.BOTTOM);
        botonCargar.setFont(new Font("Pogrubiona",Font.BOLD,10));
        botonCargar.setIcon(new ImageIcon("./images/botonCargar64.png"));
        botonCargar.setBounds(10,this.getHeight()-120,75,80);
        botonCargar.setFocusPainted(false);
        botonCargar.setBorderPainted(false);
        botonCargar.setContentAreaFilled(false);
        botonCargar.setHorizontalAlignment(JButton.CENTER);
        botonCargar.setVerticalAlignment(JButton.CENTER);
        menuPanel.add(botonCargar);
        botonesMenu.add(botonCargar);

        // Creamos y añadimos un fondo
        ImageIcon imagenFondo = new ImageIcon("./images/background2.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,getWidth()-16, getHeight()-29);
        fondo.setIcon(imagenFondo);
        menuPanel.add(fondo);
    }

    /**
     * Metodo que prepara los elementos del JPanel del juego solo
     */
    private void prepareElementosJuego(){
        // snake = new SnakeGame();
        nivelPanel = new JPanel();
        nivelPanel.setLayout(null);
        mainPanel.add(nivelPanel, "Juego");
        nivelPanel.setBackground(Color.BLACK);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        puntuacionJugadorSolo = new JLabel();
        puntuacionJugadorSolo.setBorder(border);
        puntuacionJugadorSolo.setBackground(Color.BLACK);
        puntuacionJugadorSolo.setForeground(Color.WHITE);
        puntuacionJugadorSolo.setFont(customFont(15));
        puntuacionJugadorSolo.setBounds(10,4,250,25);
        nivelPanel.add(puntuacionJugadorSolo);

        sorpresaJugadorSolo = new JLabel("Sorpresa: ");
        sorpresaJugadorSolo.setBorder(border);
        sorpresaJugadorSolo.setHorizontalAlignment(JLabel.RIGHT);
        sorpresaJugadorSolo.setHorizontalTextPosition(JLabel.LEFT);
        sorpresaJugadorSolo.setBackground(Color.BLACK);
        sorpresaJugadorSolo.setForeground(Color.WHITE);
        sorpresaJugadorSolo.setFont(customFont(15));
        sorpresaJugadorSolo.setBounds(270,4,250,25);
        sorpresaJugadorSolo.setVisible(false);
        nivelPanel.add(sorpresaJugadorSolo);
    }

    /**
     * Metodo que prepara los elementos del JPanel del game over
     */
    private void prepareElementosLose(){
        gameOverPanel = new JPanel();
        gameOverPanel.setLayout(null);
        mainPanel.add(gameOverPanel, "Over");

        tituloGameOver = new JLabel();
        tituloGameOver.setIcon(new ImageIcon("./images/gameOver.png"));
        tituloGameOver.setBounds((getWidth()/2)-125,15,230,130);
        gameOverPanel.add(tituloGameOver);

        botonReiniciar = new JButton();
        botonReiniciar.setIcon(new ImageIcon("./images/botonReiniciar64.png"));
        botonReiniciar.setBounds(this.getWidth()/2-151,getHeight()/2-105,303,42);
        botonReiniciar.setFocusPainted(false);
        botonReiniciar.setBorderPainted(false);
        botonReiniciar.setContentAreaFilled(false);
        botonReiniciar.setHorizontalAlignment(JButton.CENTER);
        botonReiniciar.setVerticalAlignment(JButton.CENTER);
        gameOverPanel.add(botonReiniciar);
        botonesGameOver.add(botonReiniciar);

        botonMenu = new JButton();
        botonMenu.setIcon(new ImageIcon("./images/botonMenu64.png"));
        botonMenu.setBounds(this.getWidth()/2-85,getHeight()/2,171,42);
        botonMenu.setFocusPainted(false);
        botonMenu.setBorderPainted(false);
        botonMenu.setContentAreaFilled(false);
        botonMenu.setHorizontalAlignment(JButton.CENTER);
        botonMenu.setVerticalAlignment(JButton.CENTER);
        gameOverPanel.add(botonMenu);
        botonesGameOver.add(botonMenu);

        ImageIcon fondo = new ImageIcon("./images/backgroundGameOver.png");
        JLabel fondoGameOver = new JLabel();
        fondoGameOver.setBounds(0,0, 625,604);
        fondoGameOver.setIcon(fondo);
        gameOverPanel.add(fondoGameOver);
    }

    /**
     * Metodo que prepara los elementos del JPanel de la configuracion
     */
    private void prepareElementosConfig(){
        configPanel = new JPanel();
        configPanel.setLayout(null);
        mainPanel.add(configPanel, "Config");

        tituloConfig = new JLabel();
        tituloConfig.setIcon(new ImageIcon("./images/tituloConfig.png"));
        tituloConfig.setBounds((getWidth()/2)-300,10,600,112);
        configPanel.add(tituloConfig);

        botonCancelar = new JButton();
        botonCancelar.setIcon(new ImageIcon("./images/botonCancelar64.png"));
        botonCancelar.setBounds(5,getHeight()-105,66,66);
        botonCancelar.setFocusPainted(false);
        botonCancelar.setBorderPainted(false);
        botonCancelar.setContentAreaFilled(false);
        botonCancelar.setHorizontalAlignment(JButton.CENTER);
        botonCancelar.setVerticalAlignment(JButton.CENTER);
        configPanel.add(botonCancelar);
        botonesConfig.add(botonCancelar);

        botonSOLO = new JButton();
        botonSOLO.setIcon(new ImageIcon("./images/solo64.png"));
        botonSOLO.setBounds((getWidth()/2)-123,200,246,42);
        botonSOLO.setFocusPainted(false);
        botonSOLO.setBorderPainted(false);
        botonSOLO.setContentAreaFilled(false);
        botonSOLO.setHorizontalAlignment(JButton.CENTER);
        botonSOLO.setVerticalAlignment(JButton.CENTER);
        configPanel.add(botonSOLO);
        botonesConfig.add(botonSOLO);

        botonJCJ = new JButton();
        botonJCJ.setIcon(new ImageIcon("./images/jcj64.png"));
        botonJCJ.setBounds((getWidth()/2)-135,275,273,42);
        botonJCJ.setFocusPainted(false);
        botonJCJ.setBorderPainted(false);
        botonJCJ.setContentAreaFilled(false);
        botonJCJ.setHorizontalAlignment(JButton.CENTER);
        botonJCJ.setVerticalAlignment(JButton.CENTER);
        configPanel.add(botonJCJ);
        botonesConfig.add(botonJCJ);

        botonJCM = new JButton();
        botonJCM.setIcon(new ImageIcon("./images/jcm64.png"));
        botonJCM.setBounds((getWidth()/2)-135,350,273,42);
        botonJCM.setFocusPainted(false);
        botonJCM.setBorderPainted(false);
        botonJCM.setContentAreaFilled(false);
        botonJCM.setHorizontalAlignment(JButton.CENTER);
        botonJCM.setVerticalAlignment(JButton.CENTER);
        configPanel.add(botonJCM);
        botonesConfig.add(botonJCM);

        ImageIcon imagenFondo = new ImageIcon("./images/background.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,getWidth()-16, getHeight()-29);
        fondo.setIcon(imagenFondo);
        configPanel.add(fondo);
    }

    /**
     * Metodo que prepara los elementos del JPanel de la configuracion de un solo jugador
     */
    private void prepareElementosConfigSolo(){
        configPanelSolo = new JPanel();
        configPanelSolo.setLayout(null);
        mainPanel.add(configPanelSolo,"ConfigSolo");

        tituloConfigSolo = new JLabel();
        tituloConfigSolo.setIcon(new ImageIcon("./images/tituloConfigSolo64.png"));
        tituloConfigSolo.setBounds((getWidth()/2)-284,10,574,98);
        tituloConfigSolo.setHorizontalAlignment(JLabel.CENTER);
        tituloConfigSolo.setVerticalAlignment(JLabel.CENTER);
        configPanelSolo.add(tituloConfigSolo);
        labelsConfigSolo.add(tituloConfigSolo);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);

        botonColorCabezaSolo = new JButton();
        botonColorCabezaSolo.setIcon(new ImageIcon("./images/colorCabeza64.png"));
        botonColorCabezaSolo.setBounds(45,250,243,78);
        botonColorCabezaSolo.setFocusPainted(false);
        botonColorCabezaSolo.setBackground(colorCabezaSolo);
        botonColorCabezaSolo.setBorder(border);
        botonColorCabezaSolo.setHorizontalAlignment(JLabel.CENTER);
        botonColorCabezaSolo.setVerticalAlignment(JLabel.CENTER);
        configPanelSolo.add(botonColorCabezaSolo);
        botonesConfigSolo.add(botonColorCabezaSolo);

        botonColorCuerpoSolo = new JButton();
        botonColorCuerpoSolo.setIcon(new ImageIcon("./images/colorCuerpo64.png"));
        botonColorCuerpoSolo.setBounds(getWidth()-306,250,243,78);
        botonColorCuerpoSolo.setFocusPainted(false);
        botonColorCuerpoSolo.setBackground(colorCuerpoSolo);
        botonColorCuerpoSolo.setBorder(border);
        botonColorCuerpoSolo.setHorizontalAlignment(JLabel.CENTER);
        botonColorCuerpoSolo.setVerticalAlignment(JLabel.CENTER);
        configPanelSolo.add(botonColorCuerpoSolo);
        botonesConfigSolo.add(botonColorCuerpoSolo);

        botonGuardarConfigSolo = new JButton();
        botonGuardarConfigSolo.setIcon(new ImageIcon("./images/botonSaveConfig64.png"));
        botonGuardarConfigSolo.setBounds(getWidth()-87,getHeight()-105,66,66);
        botonGuardarConfigSolo.setFocusPainted(false);
        botonGuardarConfigSolo.setBorderPainted(false);
        botonGuardarConfigSolo.setContentAreaFilled(false);
        botonGuardarConfigSolo.setHorizontalAlignment(JButton.CENTER);
        botonGuardarConfigSolo.setVerticalAlignment(JButton.CENTER);
        configPanelSolo.add(botonGuardarConfigSolo);
        botonesConfigSolo.add(botonGuardarConfigSolo);

        botonFrutasSorpresasSolo = new JButton();
        botonFrutasSorpresasSolo.setIcon(new ImageIcon("./images/botonFrutasSorpresas64.png"));
        botonFrutasSorpresasSolo.setBounds((getWidth()/2)-150,getHeight()-115,278,72);
        botonFrutasSorpresasSolo.setFocusPainted(false);
        botonFrutasSorpresasSolo.setBorderPainted(false);
        botonFrutasSorpresasSolo.setContentAreaFilled(false);
        botonFrutasSorpresasSolo.setHorizontalAlignment(JButton.CENTER);
        botonFrutasSorpresasSolo.setVerticalAlignment(JButton.CENTER);
        configPanelSolo.add(botonFrutasSorpresasSolo);
        botonesConfigSolo.add(botonFrutasSorpresasSolo);

        textoNombreSolo = new JLabel();
        textoNombreSolo.setIcon(new ImageIcon("./images/textoNombreSolo64.png"));
        textoNombreSolo.setBounds((getWidth()/2)-296,150,300,56);
        textoNombreSolo.setHorizontalAlignment(JButton.LEFT);
        textoNombreSolo.setVerticalAlignment(JButton.CENTER);
        configPanelSolo.add(textoNombreSolo);
        labelsConfigSolo.add(textoNombreSolo);

        campoNombre = new JTextField("Player 1");
        campoNombre.setHorizontalAlignment(JTextField.CENTER);
        campoNombre.setBounds((getWidth()/2)+15,168,200,28);
        campoNombre.setFont(customFont(15));
        campoNombre.setForeground(new Color(0x00FF00));
        campoNombre.setBorder(border);
        campoNombre.setBackground(Color.BLACK);
        configPanelSolo.add(campoNombre);

        botonCancelarSolo = new JButton();
        botonCancelarSolo.setIcon(new ImageIcon("./images/botonCancelar64.png"));
        botonCancelarSolo.setBounds(5,getHeight()-105,66,66);
        botonCancelarSolo.setFocusPainted(false);
        botonCancelarSolo.setBorderPainted(false);
        botonCancelarSolo.setContentAreaFilled(false);
        botonCancelarSolo.setHorizontalAlignment(JButton.CENTER);
        botonCancelarSolo.setVerticalAlignment(JButton.CENTER);
        configPanelSolo.add(botonCancelarSolo);
        botonesConfigSolo.add(botonCancelarSolo);

        ImageIcon imagenFondo = new ImageIcon("./images/background.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,getWidth()-16, getHeight()-29);
        fondo.setIcon(imagenFondo);
        configPanelSolo.add(fondo);
    }

    /**
     * Metodo que prepara los elementos del JPanel de seleccion de Frutas y Sorpresas
     */
    private void prepareElementosFrutasSorpresas(){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);

        panelFrutasSorpresas = new JPanel();
        panelFrutasSorpresas.setLayout(null);
        mainPanel.add(panelFrutasSorpresas,"FrutasSorpresas");

        tituloFrutasSorpresas = new JLabel();
        tituloFrutasSorpresas.setIcon(new ImageIcon("./images/tituloFrutasSorpresas.png"));
        tituloFrutasSorpresas.setBounds(((getWidth()-16)/2)-202,10,404,102);
        panelFrutasSorpresas.add(tituloFrutasSorpresas);

        checkBoxFruta = new JCheckBox();
        checkBoxFruta.setBorderPainted(false);
        checkBoxFruta.setContentAreaFilled(false);
        checkBoxFruta.setIcon(new ImageIcon("./images/checkboxFruta.png"));
        checkBoxFruta.setBounds((getWidth()/2)+9,120,96,84);
        checkBoxFruta.setSelected(true);
        panelFrutasSorpresas.add(checkBoxFruta);
        checkBoxesFrutasSorpresas.add(checkBoxFruta);

        textoFruta = new JTextArea();
        textoFruta.setBounds((getWidth()/2)+110,141,185,42);
        textoFruta.setFont(customFont(6));
        textoFruta.setForeground(new Color(0x00FF00));
        textoFruta.setBorder(border);
        textoFruta.setBackground(Color.BLACK);
        textoFruta.setEditable(false);
        textoFruta.setText("Fruta:\n\nAumenta la serpiente en 1\n\nSi es de tu color aumenta 2");
        textoFruta.setVisible(false);
        panelFrutasSorpresas.add(textoFruta);

        checkBoxFrutaArcoiris = new JCheckBox();
        checkBoxFrutaArcoiris.setBorderPainted(false);
        checkBoxFrutaArcoiris.setContentAreaFilled(false);
        checkBoxFrutaArcoiris.setIcon(new ImageIcon("./images/checkboxFrutaArcoiris.png"));
        checkBoxFrutaArcoiris.setBounds((getWidth()/2)+9,220,96,84);
        checkBoxFrutaArcoiris.setSelected(true);
        panelFrutasSorpresas.add(checkBoxFrutaArcoiris);
        checkBoxesFrutasSorpresas.add(checkBoxFrutaArcoiris);

        textoFrutaArcoiris = new JTextArea();
        textoFrutaArcoiris.setBounds((getWidth()/2)+110,241,175,42);
        textoFrutaArcoiris.setFont(customFont(6));
        textoFrutaArcoiris.setForeground(new Color(0x00FF00));
        textoFrutaArcoiris.setBorder(border);
        textoFrutaArcoiris.setBackground(Color.BLACK);
        textoFrutaArcoiris.setEditable(false);
        textoFrutaArcoiris.setText("Fruta Arcoiris:\n\nAumenta la serpiente en 3");
        textoFrutaArcoiris.setVisible(false);
        panelFrutasSorpresas.add(textoFrutaArcoiris);

        checkBoxDulce = new JCheckBox();
        checkBoxDulce.setBorderPainted(false);
        checkBoxDulce.setContentAreaFilled(false);
        checkBoxDulce.setIcon(new ImageIcon("./images/checkboxDulce.png"));
        checkBoxDulce.setBounds((getWidth()/2)+9,320,96,84);
        checkBoxDulce.setSelected(true);
        panelFrutasSorpresas.add(checkBoxDulce);
        checkBoxesFrutasSorpresas.add(checkBoxDulce);

        textoDulce = new JTextArea();
        textoDulce.setBounds((getWidth()/2)+110,341,185,42);
        textoDulce.setFont(customFont(6));
        textoDulce.setForeground(new Color(0x00FF00));
        textoDulce.setBorder(border);
        textoDulce.setBackground(Color.BLACK);
        textoDulce.setEditable(false);
        textoDulce.setText("Dulce:\n\nDisminuye la serpiente en 1\n\no 2 si es del color rival");
        textoDulce.setVisible(false);
        panelFrutasSorpresas.add(textoDulce);

        checkBoxVeneno = new JCheckBox();
        checkBoxVeneno.setBorderPainted(false);
        checkBoxVeneno.setContentAreaFilled(false);
        checkBoxVeneno.setIcon(new ImageIcon("./images/checkboxVeneno.png"));
        checkBoxVeneno.setBounds((getWidth()/2)+9,420,96,84);
        checkBoxVeneno.setSelected(true);
        panelFrutasSorpresas.add(checkBoxVeneno);
        checkBoxesFrutasSorpresas.add(checkBoxVeneno);

        textoVeneno = new JTextArea();
        textoVeneno.setBounds((getWidth()/2)+110,441,125,42);
        textoVeneno.setFont(customFont(6));
        textoVeneno.setForeground(new Color(0x00FF00));
        textoVeneno.setBorder(border);
        textoVeneno.setBackground(Color.BLACK);
        textoVeneno.setEditable(false);
        textoVeneno.setText("Veneno:\n\nLa serpiente muere");
        textoVeneno.setVisible(false);
        panelFrutasSorpresas.add(textoVeneno);

        // SORPRESAS

        checkBoxVelocidadMas = new JCheckBox();
        checkBoxVelocidadMas.setBorderPainted(false);
        checkBoxVelocidadMas.setContentAreaFilled(false);
        checkBoxVelocidadMas.setIcon(new ImageIcon("./images/checkboxFlechaVelocidadMas.png"));
        checkBoxVelocidadMas.setBounds(25,100,96,84);
        checkBoxVelocidadMas.setSelected(true);
        panelFrutasSorpresas.add(checkBoxVelocidadMas);
        checkBoxesFrutasSorpresas.add(checkBoxVelocidadMas);

        textoVelocidadMas = new JTextArea();
        textoVelocidadMas.setBounds(122,121,200,42);
        textoVelocidadMas.setFont(customFont(6));
        textoVelocidadMas.setForeground(new Color(0x00FF00));
        textoVelocidadMas.setBorder(border);
        textoVelocidadMas.setBackground(Color.BLACK);
        textoVelocidadMas.setEditable(false);
        textoVelocidadMas.setText("Flecha Aumento de velocidad:\n\nAumenta la velocidad de\n\ntu serpiente");
        textoVelocidadMas.setVisible(false);
        panelFrutasSorpresas.add(textoVelocidadMas);

        checkBoxVelocidadMenos = new JCheckBox();
        checkBoxVelocidadMenos.setBorderPainted(false);
        checkBoxVelocidadMenos.setContentAreaFilled(false);
        checkBoxVelocidadMenos.setIcon(new ImageIcon("./images/checkboxFlechaVelocidadMenos.png"));
        checkBoxVelocidadMenos.setBounds(25,185,96,84);
        checkBoxVelocidadMenos.setSelected(true);
        panelFrutasSorpresas.add(checkBoxVelocidadMenos);
        checkBoxesFrutasSorpresas.add(checkBoxVelocidadMenos);

        textoVelocidadMenos = new JTextArea();
        textoVelocidadMenos.setBounds(122,206,200,42);
        textoVelocidadMenos.setFont(customFont(6));
        textoVelocidadMenos.setForeground(new Color(0x00FF00));
        textoVelocidadMenos.setBorder(border);
        textoVelocidadMenos.setBackground(Color.BLACK);
        textoVelocidadMenos.setEditable(false);
        textoVelocidadMenos.setText("Flecha Desminuye de velocidad:\n\nReduce la velocidad de\n\ntu serpiente");
        textoVelocidadMenos.setVisible(false);
        panelFrutasSorpresas.add(textoVelocidadMenos);

        checkBoxDivision = new JCheckBox();
        checkBoxDivision.setBorderPainted(false);
        checkBoxDivision.setContentAreaFilled(false);
        checkBoxDivision.setIcon(new ImageIcon("./images/checkboxDivision.png"));
        checkBoxDivision.setBounds(25,270,96,84);
        checkBoxDivision.setSelected(true);
        panelFrutasSorpresas.add(checkBoxDivision);
        checkBoxesFrutasSorpresas.add(checkBoxDivision);

        textoDivision = new JTextArea();
        textoDivision.setBounds(122,291,200,42);
        textoDivision.setFont(customFont(6));
        textoDivision.setForeground(new Color(0x00FF00));
        textoDivision.setBorder(border);
        textoDivision.setBackground(Color.BLACK);
        textoDivision.setEditable(false);
        textoDivision.setText("Division:\n\nReduce tu serpiente a la mitad");
        textoDivision.setVisible(false);
        panelFrutasSorpresas.add(textoDivision);

        checkBoxBloqueTrampa = new JCheckBox();
        checkBoxBloqueTrampa.setBorderPainted(false);
        checkBoxBloqueTrampa.setContentAreaFilled(false);
        checkBoxBloqueTrampa.setIcon(new ImageIcon("./images/checkboxSorpresaBloqueTrampa.png"));
        checkBoxBloqueTrampa.setBounds(25,355,96,84);
        checkBoxBloqueTrampa.setSelected(true);
        panelFrutasSorpresas.add(checkBoxBloqueTrampa);
        checkBoxesFrutasSorpresas.add(checkBoxBloqueTrampa);

        textoBloqueTrampa = new JTextArea();
        textoBloqueTrampa.setBounds(122,376,200,42);
        textoBloqueTrampa.setFont(customFont(6));
        textoBloqueTrampa.setForeground(new Color(0x00FF00));
        textoBloqueTrampa.setBorder(border);
        textoBloqueTrampa.setBackground(Color.BLACK);
        textoBloqueTrampa.setEditable(false);
        textoBloqueTrampa.setText("Bloque trampa:\n\nCrea un boque trampa Cuidado!");
        textoBloqueTrampa.setVisible(false);
        panelFrutasSorpresas.add(textoBloqueTrampa);

        checkBoxEstrellaFuego = new JCheckBox();
        checkBoxEstrellaFuego.setBorderPainted(false);
        checkBoxEstrellaFuego.setContentAreaFilled(false);
        checkBoxEstrellaFuego.setIcon(new ImageIcon("./images/checkboxBolaFuego.png"));
        checkBoxEstrellaFuego.setBounds(25,440,96,84);
        checkBoxEstrellaFuego.setSelected(true);
        checkBoxEstrellaFuego.setText("FUEGO");
        panelFrutasSorpresas.add(checkBoxEstrellaFuego);
        checkBoxesFrutasSorpresas.add(checkBoxEstrellaFuego);

        textoEstrellaFuego = new JTextArea();
        textoEstrellaFuego.setBounds(122,461,165,42);
        textoEstrellaFuego.setFont(customFont(6));
        textoEstrellaFuego.setForeground(new Color(0x00FF00));
        textoEstrellaFuego.setBorder(border);
        textoEstrellaFuego.setBackground(Color.BLACK);
        textoEstrellaFuego.setEditable(false);
        textoEstrellaFuego.setText("Estrella de Fuego:\n\nLanza una llama de fuego");
        textoEstrellaFuego.setVisible(false);
        panelFrutasSorpresas.add(textoEstrellaFuego);

        checkBoxLupa = new JCheckBox();
        checkBoxLupa.setBorderPainted(false);
        checkBoxLupa.setContentAreaFilled(false);
        checkBoxLupa.setIcon(new ImageIcon("./images/checkboxLupa.png"));
        checkBoxLupa.setBounds(25,520,96,84);
        checkBoxLupa.setSelected(true);
        panelFrutasSorpresas.add(checkBoxLupa);
        checkBoxesFrutasSorpresas.add(checkBoxLupa);

        textoLupa = new JTextArea();
        textoLupa.setBounds(122,541,200,42);
        textoLupa.setFont(customFont(6));
        textoLupa.setForeground(new Color(0x00FF00));
        textoLupa.setBorder(border);
        textoLupa.setBackground(Color.BLACK);
        textoLupa.setEditable(false);
        textoLupa.setText("Lupa:\n\nLa siguiente fruta no tendrá\n\nefecto");
        textoLupa.setVisible(false);
        panelFrutasSorpresas.add(textoLupa);

        botonGuardarSeleccion = new JButton();
        botonGuardarSeleccion.setIcon(new ImageIcon("./images/botonSaveConfig64.png"));
        botonGuardarSeleccion.setBounds(getWidth()-87,getHeight()-105,66,66);
        botonGuardarSeleccion.setFocusPainted(false);
        botonGuardarSeleccion.setBorderPainted(false);
        botonGuardarSeleccion.setContentAreaFilled(false);
        botonGuardarSeleccion.setHorizontalAlignment(JButton.CENTER);
        botonGuardarSeleccion.setVerticalAlignment(JButton.CENTER);
        panelFrutasSorpresas.add(botonGuardarSeleccion);

        ImageIcon imagenFondo = new ImageIcon("./images/background.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,getWidth()-16, getHeight()-29);
        fondo.setIcon(imagenFondo);
        panelFrutasSorpresas.add(fondo);

        crearFrutas();
        crearSorpresas();
    }

    /**
     * Metodo que prepara los elementos del JPanel de la configuracion multijugador
     */
    private void prepareElementosConfigMulti(){
        configPanelMulti = new JPanel();
        configPanelMulti.setLayout(null);
        mainPanel.add(configPanelMulti,"ConfigMulti");

        tituloConfigMulti = new JLabel();
        tituloConfigMulti.setIcon(new ImageIcon("./images/tituloConfigMulti64.png"));
        tituloConfigMulti.setBounds((getWidth()/2)-284,10,574,98);
        tituloConfigMulti.setHorizontalAlignment(JLabel.CENTER);
        tituloConfigMulti.setVerticalAlignment(JLabel.CENTER);
        configPanelMulti.add(tituloConfigMulti);
        labelsConfigMulti.add(tituloConfigMulti);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);

        textoNombreP1 = new JLabel();
        textoNombreP1.setIcon(new ImageIcon("./images/textoNombreSolo64.png"));
        textoNombreP1.setBounds((getWidth()/2)-296,125,300,56);
        textoNombreP1.setHorizontalAlignment(JButton.LEFT);
        textoNombreP1.setVerticalAlignment(JButton.CENTER);
        configPanelMulti.add(textoNombreP1);
        labelsConfigMulti.add(textoNombreP1);

        campoNombreP1 = new JTextField("Player 1");
        campoNombreP1.setHorizontalAlignment(JTextField.CENTER);
        campoNombreP1.setBounds((getWidth()/2)+15,143,200,28);
        campoNombreP1.setFont(customFont(15));
        campoNombreP1.setForeground(new Color(0x00FF00));
        campoNombreP1.setBorder(border);
        campoNombreP1.setBackground(Color.BLACK);
        configPanelMulti.add(campoNombreP1);

        botonColorCabezaP1 = new JButton();
        botonColorCabezaP1.setIcon(new ImageIcon("./images/colorCabeza64.png"));
        botonColorCabezaP1.setBounds(45,190,243,78);
        botonColorCabezaP1.setFocusPainted(false);
        botonColorCabezaP1.setBackground(colorCabezaP1);
        botonColorCabezaP1.setBorder(border);
        botonColorCabezaP1.setHorizontalAlignment(JLabel.CENTER);
        botonColorCabezaP1.setVerticalAlignment(JLabel.CENTER);
        configPanelMulti.add(botonColorCabezaP1);
        botonesConfigMulti.add(botonColorCabezaP1);

        botonColorCuerpoP1 = new JButton();
        botonColorCuerpoP1.setIcon(new ImageIcon("./images/colorCuerpo64.png"));
        botonColorCuerpoP1.setBounds(getWidth()-306,190,243,78);
        botonColorCuerpoP1.setFocusPainted(false);
        botonColorCuerpoP1.setBackground(colorCuerpoP1);
        botonColorCuerpoP1.setBorder(border);
        botonColorCuerpoP1.setHorizontalAlignment(JLabel.CENTER);
        botonColorCuerpoP1.setVerticalAlignment(JLabel.CENTER);
        configPanelMulti.add(botonColorCuerpoP1);
        botonesConfigMulti.add(botonColorCuerpoP1);

        textoNombreP2 = new JLabel();
        textoNombreP2.setIcon(new ImageIcon("./images/textoNombreSolo64.png"));
        textoNombreP2.setBounds((getWidth()/2)-296,300,300,56);
        textoNombreP2.setHorizontalAlignment(JButton.LEFT);
        textoNombreP2.setVerticalAlignment(JButton.CENTER);
        configPanelMulti.add(textoNombreP2);
        labelsConfigMulti.add(textoNombreP2);

        campoNombreP2 = new JTextField("Player 2");
        campoNombreP2.setHorizontalAlignment(JTextField.CENTER);
        campoNombreP2.setBounds((getWidth()/2)+15,318,200,28);
        campoNombreP2.setFont(customFont(15));
        campoNombreP2.setForeground(new Color(0x00FF00));
        campoNombreP2.setBorder(border);
        campoNombreP2.setBackground(Color.BLACK);
        configPanelMulti.add(campoNombreP2);

        botonColorCabezaP2 = new JButton();
        botonColorCabezaP2.setIcon(new ImageIcon("./images/colorCabeza64.png"));
        botonColorCabezaP2.setBounds(45,365,243,78);
        botonColorCabezaP2.setFocusPainted(false);
        botonColorCabezaP2.setBackground(colorCabezaP2);
        botonColorCabezaP2.setBorder(border);
        botonColorCabezaP2.setHorizontalAlignment(JLabel.CENTER);
        botonColorCabezaP2.setVerticalAlignment(JLabel.CENTER);
        configPanelMulti.add(botonColorCabezaP2);
        botonesConfigMulti.add(botonColorCabezaP2);

        botonColorCuerpoP2 = new JButton();
        botonColorCuerpoP2.setIcon(new ImageIcon("./images/colorCuerpo64.png"));
        botonColorCuerpoP2.setBounds(getWidth()-306,365,243,78);
        botonColorCuerpoP2.setFocusPainted(false);
        botonColorCuerpoP2.setBackground(colorCuerpoP2);
        botonColorCuerpoP2.setBorder(border);
        botonColorCuerpoP2.setHorizontalAlignment(JLabel.CENTER);
        botonColorCuerpoP2.setVerticalAlignment(JLabel.CENTER);
        configPanelMulti.add(botonColorCuerpoP2);
        botonesConfigMulti.add(botonColorCuerpoP2);

        botonGuardarConfigMulti = new JButton();
        botonGuardarConfigMulti.setIcon(new ImageIcon("./images/botonSaveConfig64.png"));
        botonGuardarConfigMulti.setBounds(getWidth()-87,getHeight()-105,66,66);
        botonGuardarConfigMulti.setFocusPainted(false);
        botonGuardarConfigMulti.setBorderPainted(false);
        botonGuardarConfigMulti.setContentAreaFilled(false);
        botonGuardarConfigMulti.setHorizontalAlignment(JButton.CENTER);
        botonGuardarConfigMulti.setVerticalAlignment(JButton.CENTER);
        configPanelMulti.add(botonGuardarConfigMulti);
        botonesConfigMulti.add(botonGuardarConfigMulti);

        botonCancelarMulti = new JButton();
        botonCancelarMulti.setIcon(new ImageIcon("./images/botonCancelar64.png"));
        botonCancelarMulti.setBounds(5,getHeight()-105,66,66);
        botonCancelarMulti.setFocusPainted(false);
        botonCancelarMulti.setBorderPainted(false);
        botonCancelarMulti.setContentAreaFilled(false);
        botonCancelarMulti.setHorizontalAlignment(JButton.CENTER);
        botonCancelarMulti.setVerticalAlignment(JButton.CENTER);
        configPanelMulti.add(botonCancelarMulti);
        botonesConfigMulti.add(botonCancelarMulti);

        botonFrutasSorpresasMulti = new JButton();
        botonFrutasSorpresasMulti.setIcon(new ImageIcon("./images/botonFrutasSorpresas64.png"));
        botonFrutasSorpresasMulti.setBounds((getWidth()/2)-150,getHeight()-115,278,72);
        botonFrutasSorpresasMulti.setFocusPainted(false);
        botonFrutasSorpresasMulti.setBorderPainted(false);
        botonFrutasSorpresasMulti.setContentAreaFilled(false);
        botonFrutasSorpresasMulti.setHorizontalAlignment(JButton.CENTER);
        botonFrutasSorpresasMulti.setVerticalAlignment(JButton.CENTER);
        configPanelMulti.add(botonFrutasSorpresasMulti);
        botonesConfigMulti.add(botonFrutasSorpresasMulti);

        ImageIcon imagenFondo = new ImageIcon("./images/background.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,getWidth()-16, getHeight()-29);
        fondo.setIcon(imagenFondo);
        configPanelMulti.add(fondo);
    }

    /**
     * Metodo que prepara los elementos del JPanel de la configuracion multijugador
     */
    private void prepareElementosConfigMaquina(){
        configPanelMaquina = new JPanel();
        configPanelMaquina.setLayout(null);
        mainPanel.add(configPanelMaquina,"ConfigMulti");

        tituloConfigMaquina = new JLabel();
        tituloConfigMaquina.setIcon(new ImageIcon("./images/tituloConfigMaquina64.png"));
        tituloConfigMaquina.setBounds((getWidth()/2)-284,10,574,98);
        tituloConfigMaquina.setHorizontalAlignment(JLabel.CENTER);
        tituloConfigMaquina.setVerticalAlignment(JLabel.CENTER);
        configPanelMaquina.add(tituloConfigMaquina);
        labelsConfigMaquina.add(tituloConfigMaquina);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);

        textoNombreP1M = new JLabel();
        textoNombreP1M.setIcon(new ImageIcon("./images/textoNombreSolo64.png"));
        textoNombreP1M.setBounds((getWidth()/2)-296,125,300,56);
        textoNombreP1M.setHorizontalAlignment(JButton.LEFT);
        textoNombreP1M.setVerticalAlignment(JButton.CENTER);
        configPanelMaquina.add(textoNombreP1M);
        labelsConfigMaquina.add(textoNombreP1M);

        campoNombreP1M = new JTextField("Player 1");
        campoNombreP1M.setHorizontalAlignment(JTextField.CENTER);
        campoNombreP1M.setBounds((getWidth()/2)+15,143,200,28);
        campoNombreP1M.setFont(customFont(15));
        campoNombreP1M.setForeground(new Color(0x00FF00));
        campoNombreP1M.setBorder(border);
        campoNombreP1M.setBackground(Color.BLACK);
        configPanelMaquina.add(campoNombreP1M);

        botonColorCabezaP1M = new JButton();
        botonColorCabezaP1M.setIcon(new ImageIcon("./images/colorCabeza64.png"));
        botonColorCabezaP1M.setBounds(45,190,243,78);
        botonColorCabezaP1M.setFocusPainted(false);
        botonColorCabezaP1M.setBackground(colorCabezaP1);
        botonColorCabezaP1M.setBorder(border);
        botonColorCabezaP1M.setHorizontalAlignment(JLabel.CENTER);
        botonColorCabezaP1M.setVerticalAlignment(JLabel.CENTER);
        configPanelMaquina.add(botonColorCabezaP1M);
        botonesConfigMaquina.add(botonColorCabezaP1M);

        botonColorCuerpoP1M = new JButton();
        botonColorCuerpoP1M.setIcon(new ImageIcon("./images/colorCuerpo64.png"));
        botonColorCuerpoP1M.setBounds(getWidth()-306,190,243,78);
        botonColorCuerpoP1M.setFocusPainted(false);
        botonColorCuerpoP1M.setBackground(colorCuerpoP1);
        botonColorCuerpoP1M.setBorder(border);
        botonColorCuerpoP1M.setHorizontalAlignment(JLabel.CENTER);
        botonColorCuerpoP1M.setVerticalAlignment(JLabel.CENTER);
        configPanelMaquina.add(botonColorCuerpoP1M);
        botonesConfigMaquina.add(botonColorCuerpoP1M);

        textoNombreP3 = new JLabel();
        textoNombreP3.setIcon(new ImageIcon("./images/textoNombreSolo64.png"));
        textoNombreP3.setBounds((getWidth()/2)-296,300,300,56);
        textoNombreP3.setHorizontalAlignment(JButton.LEFT);
        textoNombreP3.setVerticalAlignment(JButton.CENTER);
        configPanelMaquina.add(textoNombreP3);
        labelsConfigMaquina.add(textoNombreP3);

        campoNombreP3 = new JTextField("Player M");
        campoNombreP3.setHorizontalAlignment(JTextField.CENTER);
        campoNombreP3.setBounds((getWidth()/2)+15,318,200,28);
        campoNombreP3.setFont(customFont(15));
        campoNombreP3.setForeground(new Color(0x00FF00));
        campoNombreP3.setBorder(border);
        campoNombreP3.setBackground(Color.BLACK);
        configPanelMaquina.add(campoNombreP3);

        botonColorCabezaP3 = new JButton();
        botonColorCabezaP3.setIcon(new ImageIcon("./images/colorCabeza64.png"));
        botonColorCabezaP3.setBounds(45,365,243,78);
        botonColorCabezaP3.setFocusPainted(false);
        botonColorCabezaP3.setBackground(colorCabezaP2);
        botonColorCabezaP3.setBorder(border);
        botonColorCabezaP3.setHorizontalAlignment(JLabel.CENTER);
        botonColorCabezaP3.setVerticalAlignment(JLabel.CENTER);
        configPanelMaquina.add(botonColorCabezaP3);
        botonesConfigMaquina.add(botonColorCabezaP3);

        botonColorCuerpoP3 = new JButton();
        botonColorCuerpoP3.setIcon(new ImageIcon("./images/colorCuerpo64.png"));
        botonColorCuerpoP3.setBounds(getWidth()-306,365,243,78);
        botonColorCuerpoP3.setFocusPainted(false);
        botonColorCuerpoP3.setBackground(colorCuerpoP2);
        botonColorCuerpoP3.setBorder(border);
        botonColorCuerpoP3.setHorizontalAlignment(JLabel.CENTER);
        botonColorCuerpoP3.setVerticalAlignment(JLabel.CENTER);
        configPanelMaquina.add(botonColorCuerpoP3);
        botonesConfigMaquina.add(botonColorCuerpoP3);

        botonGuardarConfigMaquina = new JButton();
        botonGuardarConfigMaquina.setIcon(new ImageIcon("./images/botonSaveConfig64.png"));
        botonGuardarConfigMaquina.setBounds(getWidth()-87,getHeight()-105,66,66);
        botonGuardarConfigMaquina.setFocusPainted(false);
        botonGuardarConfigMaquina.setBorderPainted(false);
        botonGuardarConfigMaquina.setContentAreaFilled(false);
        botonGuardarConfigMaquina.setHorizontalAlignment(JButton.CENTER);
        botonGuardarConfigMaquina.setVerticalAlignment(JButton.CENTER);
        configPanelMaquina.add(botonGuardarConfigMaquina);
        botonesConfigMaquina.add(botonGuardarConfigMaquina);

        botonCancelarMaquina = new JButton();
        botonCancelarMaquina.setIcon(new ImageIcon("./images/botonCancelar64.png"));
        botonCancelarMaquina.setBounds(5,getHeight()-105,66,66);
        botonCancelarMaquina.setFocusPainted(false);
        botonCancelarMaquina.setBorderPainted(false);
        botonCancelarMaquina.setContentAreaFilled(false);
        botonCancelarMaquina.setHorizontalAlignment(JButton.CENTER);
        botonCancelarMaquina.setVerticalAlignment(JButton.CENTER);
        configPanelMaquina.add(botonCancelarMaquina);
        botonesConfigMaquina.add(botonCancelarMaquina);

        botonFrutasSorpresasMaquina = new JButton();
        botonFrutasSorpresasMaquina.setIcon(new ImageIcon("./images/botonFrutasSorpresas64.png"));
        botonFrutasSorpresasMaquina.setBounds((getWidth()/2)-150,getHeight()-115,278,72);
        botonFrutasSorpresasMaquina.setFocusPainted(false);
        botonFrutasSorpresasMaquina.setBorderPainted(false);
        botonFrutasSorpresasMaquina.setContentAreaFilled(false);
        botonFrutasSorpresasMaquina.setHorizontalAlignment(JButton.CENTER);
        botonFrutasSorpresasMaquina.setVerticalAlignment(JButton.CENTER);
        configPanelMaquina.add(botonFrutasSorpresasMaquina);
        botonesConfigMaquina.add(botonFrutasSorpresasMaquina);

        ImageIcon imagenFondo = new ImageIcon("./images/background.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,getWidth()-16, getHeight()-29);
        fondo.setIcon(imagenFondo);
        configPanelMaquina.add(fondo);
    }

    /**
     * Metodo que prepara los elementos del JPanel del juego multijugador
     */
    private void prepareElementosJuegoMulti(){
        nivelMultiPanel = new JPanel();
        nivelMultiPanel.setLayout(null);
        mainPanel.add(nivelMultiPanel, "JuegoMulti");
        nivelMultiPanel.setBackground(Color.BLACK);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        puntuacionJugador1 = new JLabel();
        puntuacionJugador1.setBorder(border);
        puntuacionJugador1.setBackground(Color.BLACK);
        puntuacionJugador1.setForeground(Color.WHITE);
        puntuacionJugador1.setFont(customFont(15));
        puntuacionJugador1.setBounds(10,4,250,25);
        nivelMultiPanel.add(puntuacionJugador1);

        puntuacionJugador2 = new JLabel();
        puntuacionJugador2.setBorder(border);
        puntuacionJugador2.setBackground(Color.BLACK);
        puntuacionJugador2.setForeground(Color.WHITE);
        puntuacionJugador2.setFont(customFont(15));
        puntuacionJugador2.setBounds(10,569,250,25);
        nivelMultiPanel.add(puntuacionJugador2);

        sorpresaJugador1 = new JLabel("Sorpresa: ");
        sorpresaJugador1.setBorder(border);
        sorpresaJugador1.setHorizontalAlignment(JLabel.RIGHT);
        sorpresaJugador1.setHorizontalTextPosition(JLabel.LEFT);
        sorpresaJugador1.setBackground(Color.BLACK);
        sorpresaJugador1.setForeground(Color.WHITE);
        sorpresaJugador1.setFont(customFont(15));
        sorpresaJugador1.setBounds(270,4,250,25);
        sorpresaJugador1.setVisible(false);
        nivelMultiPanel.add(sorpresaJugador1);

        sorpresaJugador2 = new JLabel("Sorpresa: ");
        sorpresaJugador2.setBorder(border);
        sorpresaJugador2.setHorizontalAlignment(JLabel.RIGHT);
        sorpresaJugador2.setHorizontalTextPosition(JLabel.LEFT);
        sorpresaJugador2.setBackground(Color.BLACK);
        sorpresaJugador2.setForeground(Color.WHITE);
        sorpresaJugador2.setFont(customFont(15));
        sorpresaJugador2.setBounds(270,569,250,25);
        sorpresaJugador2.setVisible(false);
        nivelMultiPanel.add(sorpresaJugador2);
    }

    /**
     * Metodo que prepara los elementos del JPanel del juego maquina
     */
    private void prepareElementosJuegoMaquina(){
        nivelMaquinaPanel = new JPanel();
        nivelMaquinaPanel.setLayout(null);
        mainPanel.add(nivelMaquinaPanel, "JuegoMaquina");
        nivelMaquinaPanel.setBackground(Color.BLACK);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        puntuacionJugador1M = new JLabel();
        puntuacionJugador1M.setBorder(border);
        puntuacionJugador1M.setBackground(Color.BLACK);
        puntuacionJugador1M.setForeground(Color.WHITE);
        puntuacionJugador1M.setFont(customFont(15));
        puntuacionJugador1M.setBounds(10,4,250,25);
        nivelMaquinaPanel.add(puntuacionJugador1M);

        puntuacionJugador3 = new JLabel();
        puntuacionJugador3.setBorder(border);
        puntuacionJugador3.setBackground(Color.BLACK);
        puntuacionJugador3.setForeground(Color.WHITE);
        puntuacionJugador3.setFont(customFont(15));
        puntuacionJugador3.setBounds(10,569,250,25);
        nivelMaquinaPanel.add(puntuacionJugador3);

        sorpresaJugador1M = new JLabel("Sorpresa: ");
        sorpresaJugador1M.setBorder(border);
        sorpresaJugador1M.setHorizontalAlignment(JLabel.RIGHT);
        sorpresaJugador1M.setHorizontalTextPosition(JLabel.LEFT);
        sorpresaJugador1M.setBackground(Color.BLACK);
        sorpresaJugador1M.setForeground(Color.WHITE);
        sorpresaJugador1M.setFont(customFont(15));
        sorpresaJugador1M.setBounds(270,4,250,25);
        sorpresaJugador1M.setVisible(false);
        nivelMaquinaPanel.add(sorpresaJugador1M);

        sorpresaJugador3 = new JLabel("Sorpresa: ");
        sorpresaJugador3.setBorder(border);
        sorpresaJugador3.setHorizontalAlignment(JLabel.RIGHT);
        sorpresaJugador3.setHorizontalTextPosition(JLabel.LEFT);
        sorpresaJugador3.setBackground(Color.BLACK);
        sorpresaJugador3.setForeground(Color.WHITE);
        sorpresaJugador3.setFont(customFont(15));
        sorpresaJugador3.setBounds(270,569,250,25);
        sorpresaJugador3.setVisible(false);
        nivelMaquinaPanel.add(sorpresaJugador3);
    }

    /**
     * Metodo que prepara los elementos del JPanel de la pausa
     */
    private void prepareElementosPausa(){
        pausaPanel = new JPanel();
        pausaPanel.setLayout(null);
        mainPanel.add(pausaPanel,"Pausa");

        botonVolver = new JButton();
        botonVolver.setIcon(new ImageIcon("./images/botonVolver64.png"));
        botonVolver.setBounds((getWidth()/2)-135,getHeight()/2-105,270,42);
        botonVolver.setFocusPainted(false);
        botonVolver.setContentAreaFilled(false);
        botonVolver.setBorderPainted(false);
        botonVolver.setHorizontalAlignment(JButton.CENTER);
        botonVolver.setVerticalAlignment(JButton.CENTER);
        pausaPanel.add(botonVolver);

        botonSalirPausa = new JButton();
        botonSalirPausa.setIcon(new ImageIcon("./images/botonMenu64.png"));
        botonSalirPausa.setBounds((getWidth()/2)-86,getHeight()/2,171,42);
        botonSalirPausa.setFocusPainted(false);
        botonSalirPausa.setContentAreaFilled(false);
        botonSalirPausa.setBorderPainted(false);
        botonSalirPausa.setHorizontalAlignment(JButton.CENTER);
        botonSalirPausa.setVerticalAlignment(JButton.CENTER);
        pausaPanel.add(botonSalirPausa);

        tituloPausa = new JLabel();
        tituloPausa.setIcon(new ImageIcon("./images/tituloPausa.png"));
        tituloPausa.setBounds((getWidth()/2)-232,20,456,112);
        tituloPausa.setHorizontalAlignment(JLabel.CENTER);
        tituloPausa.setVerticalAlignment(JLabel.CENTER);
        pausaPanel.add(tituloPausa);

        // Creamos y añadimos el obton de guardar
        botonGuardar = new JButton("Guardar");
        botonGuardar.setHorizontalTextPosition(JButton.CENTER);
        botonGuardar.setVerticalTextPosition(JButton.BOTTOM);
        botonGuardar.setFont(new Font("Pogrubiona",Font.BOLD,10));
        botonGuardar.setIcon(new ImageIcon("./images/botonGuardar64.png"));
        botonGuardar.setBounds(10,this.getHeight()-120,75,80);
        botonGuardar.setFocusPainted(false);
        botonGuardar.setBorderPainted(false);
        botonGuardar.setContentAreaFilled(false);
        botonGuardar.setHorizontalAlignment(JButton.CENTER);
        botonGuardar.setVerticalAlignment(JButton.CENTER);
        pausaPanel.add(botonGuardar);

        ImageIcon fondo = new ImageIcon("./images/backgroundGameOver.png");
        JLabel fondoPause = new JLabel();
        fondoPause.setBounds(0,0, 625,604);
        fondoPause.setIcon(fondo);
        pausaPanel.add(fondoPause);
    }

    // ACCIONES

    /**
     * Metodo que prepara los event listener del JFrame
     */
    private void prepareAcciones(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                salga();
            }
        });
        prepareAccionesMenu();
        //prepareAccionesTablero();
        prepareAccionesGameOver();
        prepareAccionesConfig();
        prepareAccionesConfigSolo();
        prepareAccionesPause();
        prepareAccionesFrutasSorpresas();
        prepareAccionesConfigMulti();
        prepareAccionesConfigMaquina();
        //prepareAccionesTableroMulti();
    }

    /**
     * Metodo que prepara los listener de los elementos del JPanel del menu
     */
    private void prepareAccionesMenu(){
        MouseListener mouseListenerMenu = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(botonJugar)){
                    botonJugar.setIcon(new ImageIcon("./images/botonJugar96.png"));
                    config();
                }

                else if(e.getSource().equals(botonSalir)){
                    botonSalir.setIcon(new ImageIcon("./images/botonSalir96.png"));
                    salga();
                }

                else if(e.getSource().equals(botonCargar)){
                    botonCargar.setIcon(new ImageIcon("./images/botonCargar96.png"));
                    opcionCargar();
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(botonJugar)){
                    botonJugar.setIcon(new ImageIcon("./images/botonJugar64.png"));
                }

                else if(e.getSource().equals(botonSalir)){
                    botonSalir.setIcon(new ImageIcon("./images/botonSalir64.png"));
                }

                else if(e.getSource().equals(botonCargar)){
                    botonCargar.setIcon(new ImageIcon("./images/botonCargar64.png"));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(botonJugar)){
                    botonJugar.setIcon(new ImageIcon("./images/botonJugar96.png"));
                    config();
                }

                else if(e.getSource().equals(botonSalir)){
                    botonSalir.setIcon(new ImageIcon("./images/botonSalir96.png"));
                    salga();
                }

                else if(e.getSource().equals(botonCargar)){
                    botonCargar.setIcon(new ImageIcon("./images/botonCargar96.png"));
                    opcionCargar();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(botonJugar)){
                    botonJugar.setIcon(new ImageIcon("./images/botonJugar96.png"));}

                else if(e.getSource().equals(botonSalir)){
                    botonSalir.setIcon(new ImageIcon("./images/botonSalir96.png"));
                }

                else if(e.getSource().equals(botonCargar)){
                    botonCargar.setIcon(new ImageIcon("./images/botonCargar96.png"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

                if(e.getSource().equals(botonJugar)){
                    botonJugar.setIcon(new ImageIcon("./images/botonJugar64.png"));}

                else if(e.getSource().equals(botonSalir)){
                    botonSalir.setIcon(new ImageIcon("./images/botonSalir64.png"));
                }

                else if(e.getSource().equals(botonCargar)){
                    botonCargar.setIcon(new ImageIcon("./images/botonCargar64.png"));
                }

            }
        };

        for (JButton boton: botonesMenu) {
            boton.addMouseListener(mouseListenerMenu);
        }
    }

    /**
     * Metodo que prepara los listener de los elementos del JPanel del juego
     */
    private void prepareAccionesTablero(){
        movimientosTablero = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    panelSnakeP1.cambiarDireccion('L');
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    panelSnakeP1.cambiarDireccion('R');
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    panelSnakeP1.cambiarDireccion('U');
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    panelSnakeP1.cambiarDireccion('D');
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    panelSnakeP1.setPause(false);
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    snoopeGame.activarSorpresa();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    panelSnakeP1.cambiarDireccion('L');
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    panelSnakeP1.cambiarDireccion('R');
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    panelSnakeP1.cambiarDireccion('U');
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    panelSnakeP1.cambiarDireccion('D');
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    pausar();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    try{
                        snoopeGame.activarSorpresa();
                    }catch (Exception ex){
                    }
                }
            }
        };
        requestFocus(true);
        addKeyListener(movimientosTablero);
    }

    /**
     * Metodo que prepara los listener de los elementos del JPanel del game over
     */
    private void prepareAccionesGameOver(){
        MouseListener mouseListenerGameOver = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(botonReiniciar)){
                    botonReiniciar.setIcon(new ImageIcon("./images/botonReiniciar96.png"));
                }
                else if(e.getSource().equals(botonMenu)){
                    botonMenu.setIcon(new ImageIcon("./images/botonMenu96.png"));
                    menuGameOver();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(botonReiniciar)){
                    botonReiniciar.setIcon(new ImageIcon("./images/botonReiniciar64.png"));
                    reiniciar();
                }
                else if(e.getSource().equals(botonMenu)){
                    botonMenu.setIcon(new ImageIcon("./images/botonMenu64.png"));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(botonReiniciar)){
                    botonReiniciar.setIcon(new ImageIcon("./images/botonReiniciar96.png"));
                }
                else if(e.getSource().equals(botonMenu)){
                    botonMenu.setIcon(new ImageIcon("./images/botonMenu96.png"));
                    menuGameOver();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(botonReiniciar)){
                    botonReiniciar.setIcon(new ImageIcon("./images/botonReiniciar96.png"));
                }
                else if(e.getSource().equals(botonMenu)){
                    botonMenu.setIcon(new ImageIcon("./images/botonMenu96.png"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(botonReiniciar)){
                    botonReiniciar.setIcon(new ImageIcon("./images/botonReiniciar64.png"));
                }
                else if(e.getSource().equals(botonMenu)){
                    botonMenu.setIcon(new ImageIcon("./images/botonMenu64.png"));
                }
            }
        };

        for (JButton boton: botonesGameOver) {
            boton.addMouseListener(mouseListenerGameOver);
        }
    }

    /**
     * Metodo que prepara los listener de los elementos del JPanel de configuracion
     */
    private void prepareAccionesConfig(){
        MouseListener mouseListenerConfig = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(botonCancelar)){
                    botonCancelar.setIcon(new ImageIcon("./images/botonCancelar96.png"));
                    cancel();
                }
                else if(e.getSource().equals(botonSOLO)) {
                    botonSOLO.setIcon(new ImageIcon("./images/solo96.png"));
                    configsolo();
                }
                else if(e.getSource().equals(botonJCJ)){
                    botonJCJ.setIcon(new ImageIcon("./images/jcj96.png"));
                    configMulti();
                }
                else if(e.getSource().equals(botonJCM)){
                    botonJCM.setIcon(new ImageIcon("./images/jcm96.png"));
                    configMaquina();

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(botonCancelar)){
                    botonCancelar.setIcon(new ImageIcon("./images/botonCancelar64.png"));
                }
                else if(e.getSource().equals(botonSOLO)) {
                    botonSOLO.setIcon(new ImageIcon("./images/solo64.png"));
                }
                else if(e.getSource().equals(botonJCJ)){
                    botonJCJ.setIcon(new ImageIcon("./images/jcj64.png"));
                }
                else if(e.getSource().equals(botonJCM)){
                    botonJCM.setIcon(new ImageIcon("./images/jcm64.png"));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(botonCancelar)){
                    botonCancelar.setIcon(new ImageIcon("./images/botonCancelar96.png"));
                    cancel();
                }
                else if(e.getSource().equals(botonSOLO)) {
                    botonSOLO.setIcon(new ImageIcon("./images/solo96.png"));
                    configsolo();
                }
                else if(e.getSource().equals(botonJCJ)){
                    botonJCJ.setIcon(new ImageIcon("./images/jcj96.png"));
                    configMulti();
                }
                else if(e.getSource().equals(botonJCM)){
                    botonJCM.setIcon(new ImageIcon("./images/jcm96.png"));
                    configMaquina();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(botonCancelar)){
                    botonCancelar.setIcon(new ImageIcon("./images/botonCancelar96.png"));
                }
                else if(e.getSource().equals(botonSOLO)) {
                    botonSOLO.setIcon(new ImageIcon("./images/solo96.png"));
                }
                else if(e.getSource().equals(botonJCJ)){
                    botonJCJ.setIcon(new ImageIcon("./images/jcj96.png"));
                }
                else if(e.getSource().equals(botonJCM)){
                    botonJCM.setIcon(new ImageIcon("./images/jcm96.png"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(botonCancelar)){
                    botonCancelar.setIcon(new ImageIcon("./images/botonCancelar64.png"));
                }
                else if(e.getSource().equals(botonSOLO)) {
                    botonSOLO.setIcon(new ImageIcon("./images/solo64.png"));
                }
                else if(e.getSource().equals(botonJCJ)){
                    botonJCJ.setIcon(new ImageIcon("./images/jcj64.png"));
                }
                else if(e.getSource().equals(botonJCM)){
                    botonJCM.setIcon(new ImageIcon("./images/jcm64.png"));
                }
            }
        };

        for (JButton boton: botonesConfig) {
            boton.addMouseListener(mouseListenerConfig);
        }
    }

    /**
     * Metodo que prepara los listener de los elementos del JPanel de la configuracion en modo un solo jugador
     */
    private void prepareAccionesConfigSolo(){
        MouseListener mouseListenerConfigSolo = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(botonColorCabezaSolo)){
                    cambioColor(botonColorCabezaSolo);
                }
                else if(e.getSource().equals(botonColorCuerpoSolo)){
                    cambioColor(botonColorCuerpoSolo);
                }
                else if(e.getSource().equals(botonGuardarConfigSolo)){
                    botonGuardarConfigSolo.setIcon((new ImageIcon("./images/botonSaveConfig96.png")));
                    jugarSolo();
                }
                else if(e.getSource().equals(botonFrutasSorpresasSolo)){
                    botonFrutasSorpresasSolo.setIcon((new ImageIcon("./images/botonFrutasSorpresas96.png")));
                    caso = 'S';
                    seleccionFrutasSorpresas();
                }
                else if(e.getSource().equals(botonCancelarSolo)){
                    botonCancelarSolo.setIcon((new ImageIcon("./images/botonCancelar96.png")));
                    config();
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(botonGuardarConfigSolo)){
                    botonGuardarConfigSolo.setIcon((new ImageIcon("./images/botonSaveConfig64.png")));
                    guardarNombreSolo();
                }
                else if(e.getSource().equals(botonFrutasSorpresasSolo)){
                    botonFrutasSorpresasSolo.setIcon((new ImageIcon("./images/botonFrutasSorpresas64.png")));
                }
                else if(e.getSource().equals(botonCancelarSolo)){
                    botonCancelarSolo.setIcon((new ImageIcon("./images/botonCancelar64.png")));
                }
                else if(e.getSource().equals(botonCancelarSolo)){
                    botonCancelarSolo.setIcon((new ImageIcon("./images/botonCancelar64.png")));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(botonColorCabezaSolo)){
                    cambioColor(botonColorCabezaSolo);
                }
                else if(e.getSource().equals(botonColorCuerpoSolo)){
                    cambioColor(botonColorCuerpoSolo);
                }
                else if(e.getSource().equals(botonGuardarConfigSolo)){
                    botonGuardarConfigSolo.setIcon((new ImageIcon("./images/botonSaveConfig96.png")));
                    jugarSolo();
                }
                else if(e.getSource().equals(botonFrutasSorpresasSolo)){
                    botonFrutasSorpresasSolo.setIcon((new ImageIcon("./images/botonFrutasSorpresas96.png")));
                    caso = 'S';
                    seleccionFrutasSorpresas();
                }
                else if(e.getSource().equals(botonCancelarSolo)){
                    botonCancelarSolo.setIcon((new ImageIcon("./images/botonCancelar96.png")));
                    config();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(tituloConfigSolo)){
                    tituloConfigSolo.setIcon(new ImageIcon("./images/tituloConfigSolo96.png"));
                }
                else if(e.getSource().equals(botonColorCabezaSolo)){
                    botonColorCabezaSolo.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                }
                else if(e.getSource().equals(botonColorCuerpoSolo)){
                    botonColorCuerpoSolo.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                }
                else if(e.getSource().equals(botonGuardarConfigSolo)){
                    botonGuardarConfigSolo.setIcon((new ImageIcon("./images/botonSaveConfig96.png")));
                }
                else if(e.getSource().equals(textoNombreSolo)){
                    textoNombreSolo.setIcon(new ImageIcon("./images/textoNombreSolo96.png"));
                }
                else if(e.getSource().equals(botonFrutasSorpresasSolo)){
                    botonFrutasSorpresasSolo.setIcon((new ImageIcon("./images/botonFrutasSorpresas96.png")));
                }
                else if(e.getSource().equals(botonCancelarSolo)){
                    botonCancelarSolo.setIcon((new ImageIcon("./images/botonCancelar96.png")));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(tituloConfigSolo)){
                    tituloConfigSolo.setIcon(new ImageIcon("./images/tituloConfigSolo64.png"));
                }
                else if(e.getSource().equals(botonColorCabezaSolo)){
                    botonColorCabezaSolo.setIcon(new ImageIcon("./images/colorCabeza64.png"));
                }
                else if(e.getSource().equals(botonColorCuerpoSolo)){
                    botonColorCuerpoSolo.setIcon(new ImageIcon("./images/colorCuerpo64.png"));
                }
                else if(e.getSource().equals(botonGuardarConfigSolo)){
                    botonGuardarConfigSolo.setIcon((new ImageIcon("./images/botonSaveConfig64.png")));
                }
                else if(e.getSource().equals(textoNombreSolo)){
                    textoNombreSolo.setIcon(new ImageIcon("./images/textoNombreSolo64.png"));
                }
                else if(e.getSource().equals(botonFrutasSorpresasSolo)){
                    botonFrutasSorpresasSolo.setIcon((new ImageIcon("./images/botonFrutasSorpresas64.png")));
                }
                else if(e.getSource().equals(botonCancelarSolo)){
                    botonCancelarSolo.setIcon((new ImageIcon("./images/botonCancelar64.png")));
                }
            }
        };

        for (JLabel label: labelsConfigSolo) {
            label.addMouseListener(mouseListenerConfigSolo);
        }
        for (JButton boton: botonesConfigSolo) {
            boton.addMouseListener(mouseListenerConfigSolo);
        }
    }

    /**
     * Metodo que prepara los listener de los elementos del JPanel de la seleccion de frutas y sorpresas
     */
    private void prepareAccionesFrutasSorpresas(){
        MouseListener mouseListenerFrutasSorpresas = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(checkBoxFruta)){
                    if(checkBoxFruta.isSelected()){
                        checkBoxFruta.setIcon(new ImageIcon("./images/checkboxFruta.png"));
                    }
                    else{
                        checkBoxFruta.setIcon(new ImageIcon("./images/checkboxFrutaNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxFrutaArcoiris)){
                    if(checkBoxFrutaArcoiris.isSelected()){
                        checkBoxFrutaArcoiris.setIcon(new ImageIcon("./images/checkboxFrutaArcoiris.png"));
                    }
                    else{
                        checkBoxFrutaArcoiris.setIcon(new ImageIcon("./images/checkboxFrutaArcoirisNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxDulce)){
                    if(checkBoxDulce.isSelected()){
                        checkBoxDulce.setIcon(new ImageIcon("./images/checkboxDulce.png"));
                    }
                    else{
                        checkBoxDulce.setIcon(new ImageIcon("./images/checkboxDulceNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxVeneno)){
                    if(checkBoxVeneno.isSelected()){
                        checkBoxVeneno.setIcon(new ImageIcon("./images/checkboxVeneno.png"));
                    }
                    else{
                        checkBoxVeneno.setIcon(new ImageIcon("./images/checkboxVenenoNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxVelocidadMas)){
                    if(checkBoxVelocidadMas.isSelected()){
                        checkBoxVelocidadMas.setIcon(new ImageIcon("./images/checkboxFlechaVelocidadMas.png"));
                    }
                    else{
                        checkBoxVelocidadMas.setIcon(new ImageIcon("./images/checkboxFlechaVelocidadMasNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxVelocidadMenos)){
                    if(checkBoxVelocidadMenos.isSelected()){
                        checkBoxVelocidadMenos.setIcon(new ImageIcon("./images/checkboxFlechaVelocidadMenos.png"));
                    }
                    else{
                        checkBoxVelocidadMenos.setIcon(new ImageIcon("./images/checkboxFlechaVelocidadMenosNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxDivision)){
                    if(checkBoxDivision.isSelected()){
                        checkBoxDivision.setIcon(new ImageIcon("./images/checkboxDivision.png"));
                    }
                    else{
                        checkBoxDivision.setIcon(new ImageIcon("./images/checkboxDivisionNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxBloqueTrampa)){
                    if(checkBoxBloqueTrampa.isSelected()){
                        checkBoxBloqueTrampa.setIcon(new ImageIcon("./images/checkboxSorpresaBloqueTrampa.png"));
                    }
                    else{
                        checkBoxBloqueTrampa.setIcon(new ImageIcon("./images/checkboxSorpresaBloqueTrampaNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxEstrellaFuego)){
                    if(checkBoxEstrellaFuego.isSelected()){
                        checkBoxEstrellaFuego.setIcon(new ImageIcon("./images/checkboxBolaFuego.png"));
                    }
                    else{
                        checkBoxEstrellaFuego.setIcon(new ImageIcon("./images/checkboxBolaFuegoNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxLupa)){
                    if(checkBoxLupa.isSelected()){
                        checkBoxLupa.setIcon(new ImageIcon("./images/checkboxLupa.png"));
                    }
                    else{
                        checkBoxLupa.setIcon(new ImageIcon("./images/checkboxLupaNull.png"));
                    }
                }

                else if(e.getSource().equals(botonGuardarSeleccion)){
                    botonGuardarSeleccion.setIcon(new ImageIcon("./images/botonSaveConfig96.png"));
                    switch (caso){
                        case 'M':
                            configMulti();
                            break;
                        case 'S':
                            configsolo();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(botonGuardarSeleccion)){
                    botonGuardarSeleccion.setIcon(new ImageIcon("./images/botonSaveConfig64.png"));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(checkBoxFruta)){
                    if(checkBoxFruta.isSelected()){
                        checkBoxFruta.setIcon(new ImageIcon("./images/checkboxFruta.png"));
                    }
                    else{
                        checkBoxFruta.setIcon(new ImageIcon("./images/checkboxFrutaNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxFrutaArcoiris)){
                    if(checkBoxFrutaArcoiris.isSelected()){
                        checkBoxFrutaArcoiris.setIcon(new ImageIcon("./images/checkboxFrutaArcoiris.png"));
                    }
                    else{
                        checkBoxFrutaArcoiris.setIcon(new ImageIcon("./images/checkboxFrutaArcoirisNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxDulce)){
                    if(checkBoxDulce.isSelected()){
                        checkBoxDulce.setIcon(new ImageIcon("./images/checkboxDulce.png"));
                    }
                    else{
                        checkBoxDulce.setIcon(new ImageIcon("./images/checkboxDulceNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxVeneno)){
                    if(checkBoxVeneno.isSelected()){
                        checkBoxVeneno.setIcon(new ImageIcon("./images/checkboxVeneno.png"));
                    }
                    else{
                        checkBoxVeneno.setIcon(new ImageIcon("./images/checkboxVenenoNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxVelocidadMas)){
                    if(checkBoxVelocidadMas.isSelected()){
                        checkBoxVelocidadMas.setIcon(new ImageIcon("./images/checkboxFlechaVelocidadMas.png"));
                    }
                    else{
                        checkBoxVelocidadMas.setIcon(new ImageIcon("./images/checkboxFlechaVelocidadMasNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxVelocidadMenos)){
                    if(checkBoxVelocidadMenos.isSelected()){
                        checkBoxVelocidadMenos.setIcon(new ImageIcon("./images/checkboxFlechaVelocidadMenos.png"));
                    }
                    else{
                        checkBoxVelocidadMenos.setIcon(new ImageIcon("./images/checkboxFlechaVelocidadMenosNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxDivision)){
                    if(checkBoxDivision.isSelected()){
                        checkBoxDivision.setIcon(new ImageIcon("./images/checkboxDivision.png"));
                    }
                    else{
                        checkBoxDivision.setIcon(new ImageIcon("./images/checkboxDivisionNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxBloqueTrampa)){
                    if(checkBoxBloqueTrampa.isSelected()){
                        checkBoxBloqueTrampa.setIcon(new ImageIcon("./images/checkboxSorpresaBloqueTrampa.png"));
                    }
                    else{
                        checkBoxBloqueTrampa.setIcon(new ImageIcon("./images/checkboxSorpresaBloqueTrampaNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxEstrellaFuego)){
                    if(checkBoxEstrellaFuego.isSelected()){
                        checkBoxEstrellaFuego.setIcon(new ImageIcon("./images/checkboxBolaFuego.png"));
                    }
                    else{
                        checkBoxEstrellaFuego.setIcon(new ImageIcon("./images/checkboxBolaFuegoNull.png"));
                    }
                }

                else if(e.getSource().equals(checkBoxLupa)){
                    if(checkBoxLupa.isSelected()){
                        checkBoxLupa.setIcon(new ImageIcon("./images/checkboxLupa.png"));
                    }
                    else{
                        checkBoxLupa.setIcon(new ImageIcon("./images/checkboxLupaNull.png"));
                    }
                }

                else if(e.getSource().equals(botonGuardarSeleccion)){
                    botonGuardarSeleccion.setIcon(new ImageIcon("./images/botonSaveConfig96.png"));
                    switch (caso){
                        case 'M':
                            configMulti();
                            break;
                        case 'S':
                            configsolo();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(botonGuardarSeleccion)){
                    botonGuardarSeleccion.setIcon(new ImageIcon("./images/botonSaveConfig96.png"));

                }else if(e.getSource().equals(checkBoxFruta)){
                    textoFruta.setVisible(true);

                }else if(e.getSource().equals(checkBoxFrutaArcoiris)){
                    textoFrutaArcoiris.setVisible(true);

                }else if(e.getSource().equals(checkBoxDulce)){
                    textoDulce.setVisible(true);

                }else if(e.getSource().equals(checkBoxVeneno)){
                    textoVeneno.setVisible(true);

                }else if(e.getSource().equals(checkBoxVelocidadMas)){
                    textoVelocidadMas.setVisible(true);
                }
                else if(e.getSource().equals(checkBoxVelocidadMenos)){
                    textoVelocidadMenos.setVisible(true);
                }
                else if(e.getSource().equals(checkBoxDivision)){
                    textoDivision.setVisible(true);
                }
                else if(e.getSource().equals(checkBoxBloqueTrampa)){
                    textoBloqueTrampa.setVisible(true);
                }
                else if(e.getSource().equals(checkBoxEstrellaFuego)){
                    textoEstrellaFuego.setVisible(true);
                }
                else if(e.getSource().equals(checkBoxLupa)){
                    textoLupa.setVisible(true);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(botonGuardarSeleccion)){
                    botonGuardarSeleccion.setIcon(new ImageIcon("./images/botonSaveConfig64.png"));

                }else if(e.getSource().equals(checkBoxFruta)){
                    textoFruta.setVisible(false);

                }else if(e.getSource().equals(checkBoxFrutaArcoiris)){
                    textoFrutaArcoiris.setVisible(false);

                }else if(e.getSource().equals(checkBoxDulce)){
                    textoDulce.setVisible(false);

                }else if(e.getSource().equals(checkBoxVeneno)){
                    textoVeneno.setVisible(false);

                }else if(e.getSource().equals(checkBoxVelocidadMas)){
                    textoVelocidadMas.setVisible(false);
                }
                else if(e.getSource().equals(checkBoxVelocidadMenos)){
                    textoVelocidadMenos.setVisible(false);
                }
                else if(e.getSource().equals(checkBoxDivision)){
                    textoDivision.setVisible(false);
                }
                else if(e.getSource().equals(checkBoxBloqueTrampa)){
                    textoBloqueTrampa.setVisible(false);
                }
                else if(e.getSource().equals(checkBoxEstrellaFuego)){
                    textoEstrellaFuego.setVisible(false);
                }
                else if(e.getSource().equals(checkBoxLupa)){
                    textoLupa.setVisible(false);
                }
            }
        };

        for (JCheckBox checkBox: checkBoxesFrutasSorpresas) {
            checkBox.addMouseListener(mouseListenerFrutasSorpresas);
        }

        botonGuardarSeleccion.addMouseListener(mouseListenerFrutasSorpresas);
    }

    /**
     * Metodo que prepara los listener de los elementos del JPanel de pausa
     */
    private void prepareAccionesPause(){
        MouseListener mouseListenerPause = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(botonVolver)){
                    botonVolver.setIcon(new ImageIcon("./images/botonVolver96.png"));
                }
                else if(e.getSource().equals(botonGuardar)){
                    botonGuardar.setIcon(new ImageIcon("./images/botonGuardar96.png"));
                    opcionGuardar();
                }
                else if(e.getSource().equals(botonSalirPausa)){
                    botonSalirPausa.setIcon(new ImageIcon("./images/botonMenu96.png"));
                    salirPausa();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(botonVolver)){
                    botonVolver.setIcon(new ImageIcon("./images/botonVolver64.png"));
                    resume();
                }

                else if(e.getSource().equals(botonGuardar)){
                    botonGuardar.setIcon(new ImageIcon("./images/botonGuardar64.png"));
                }
                else if(e.getSource().equals(botonSalirPausa)){
                    botonSalirPausa.setIcon(new ImageIcon("./images/botonMenu64.png"));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(botonVolver)){
                    botonVolver.setIcon(new ImageIcon("./images/botonVolver96.png"));
                }

                else if(e.getSource().equals(botonGuardar)){
                    botonGuardar.setIcon(new ImageIcon("./images/botonGuardar96.png"));
                    opcionGuardar();
                }
                else if(e.getSource().equals(botonSalirPausa)){
                    botonSalirPausa.setIcon(new ImageIcon("./images/botonMenu96.png"));
                    salirPausa();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(botonVolver)){
                    botonVolver.setIcon(new ImageIcon("./images/botonVolver96.png"));
                }

                else if(e.getSource().equals(botonGuardar)){
                    botonGuardar.setIcon(new ImageIcon("./images/botonGuardar96.png"));
                }
                else if(e.getSource().equals(botonSalirPausa)){
                    botonSalirPausa.setIcon(new ImageIcon("./images/botonMenu96.png"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(botonVolver)){
                    botonVolver.setIcon(new ImageIcon("./images/botonVolver64.png"));
                }

                else if(e.getSource().equals(botonGuardar)){
                    botonGuardar.setIcon(new ImageIcon("./images/botonGuardar64.png"));
                }
                else if(e.getSource().equals(botonSalirPausa)){
                    botonSalirPausa.setIcon(new ImageIcon("./images/botonMenu64.png"));
                }
            }
        };

        botonVolver.addMouseListener(mouseListenerPause);
        botonGuardar.addMouseListener(mouseListenerPause);
        botonSalirPausa.addMouseListener(mouseListenerPause);
    }

    /**
     * Metodo que prepara los listener de los elementos del JPanel de la configuracion en modo multijugador
     */
    private void prepareAccionesConfigMulti(){
        MouseListener mouseListenerConfigMulti = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(botonColorCabezaP1)){
                    botonColorCabezaP1.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                    cambioColor(botonColorCabezaP1);
                }
                else if(e.getSource().equals(botonColorCabezaP2)){
                    botonColorCabezaP2.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                    cambioColor(botonColorCabezaP2);
                }
                else if(e.getSource().equals(botonColorCuerpoP1)){
                    botonColorCuerpoP1.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                    cambioColor(botonColorCuerpoP1);
                }
                else if(e.getSource().equals(botonColorCuerpoP2)){
                    botonColorCuerpoP2.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                    cambioColor(botonColorCuerpoP2);
                }
                else if(e.getSource().equals(botonGuardarConfigMulti)) {
                    botonGuardarConfigMulti.setIcon((new ImageIcon("./images/botonSaveConfig96.png")));
                    jugarMulti();
                }
                else if(e.getSource().equals(botonFrutasSorpresasMulti)){
                    botonFrutasSorpresasMulti.setIcon((new ImageIcon("./images/botonFrutasSorpresas96.png")));
                    caso = 'M';
                    seleccionFrutasSorpresas();
                }
                else if(e.getSource().equals(botonCancelarMulti)){
                    botonCancelarMulti.setIcon((new ImageIcon("./images/botonCancelar96.png")));
                    config();
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(botonGuardarConfigMulti)){
                    botonGuardarConfigMulti.setIcon((new ImageIcon("./images/botonSaveConfig64.png")));
                    guardarNombresMulti();
                }
                else if(e.getSource().equals(botonFrutasSorpresasMulti)){
                    botonFrutasSorpresasMulti.setIcon((new ImageIcon("./images/botonFrutasSorpresas64.png")));
                }
                else if(e.getSource().equals(botonCancelarMulti)){
                    botonCancelarMulti.setIcon((new ImageIcon("./images/botonCancelar64.png")));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(botonColorCabezaP1)){
                    botonColorCabezaP1.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                    cambioColor(botonColorCabezaP1);
                }
                else if(e.getSource().equals(botonColorCabezaP2)){
                    botonColorCabezaP2.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                    cambioColor(botonColorCabezaP2);
                }
                else if(e.getSource().equals(botonColorCuerpoP1)){
                    botonColorCuerpoP1.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                    cambioColor(botonColorCuerpoP1);
                }
                else if(e.getSource().equals(botonColorCuerpoP2)){
                    botonColorCuerpoP2.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                    cambioColor(botonColorCuerpoP2);
                }
                else if(e.getSource().equals(botonGuardarConfigMulti)) {
                    botonGuardarConfigMulti.setIcon((new ImageIcon("./images/botonSaveConfig96.png")));
                    jugarMulti();
                }
                else if(e.getSource().equals(botonFrutasSorpresasMulti)){
                    botonFrutasSorpresasMulti.setIcon((new ImageIcon("./images/botonFrutasSorpresas96.png")));
                    caso = 'M';
                    seleccionFrutasSorpresas();
                }
                else if(e.getSource().equals(botonCancelarMulti)){
                    botonCancelarMulti.setIcon((new ImageIcon("./images/botonCancelar96.png")));
                    config();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(tituloConfigMulti)){
                    tituloConfigMulti.setIcon(new ImageIcon("./images/tituloConfigMulti96.png"));
                }
                else if(e.getSource().equals(botonColorCabezaP1)){
                    botonColorCabezaP1.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                }
                else if(e.getSource().equals(botonColorCabezaP2)){
                    botonColorCabezaP2.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                }
                else if(e.getSource().equals(botonColorCuerpoP1)){
                    botonColorCuerpoP1.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                }
                else if(e.getSource().equals(botonColorCuerpoP2)){
                    botonColorCuerpoP2.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                }
                else if(e.getSource().equals(botonGuardarConfigMulti)){
                    botonGuardarConfigMulti.setIcon((new ImageIcon("./images/botonSaveConfig96.png")));
                }
                else if(e.getSource().equals(textoNombreP1)){
                    textoNombreP1.setIcon(new ImageIcon("./images/textoNombreSolo96.png"));
                }
                else if(e.getSource().equals(textoNombreP2)){
                    textoNombreP2.setIcon(new ImageIcon("./images/textoNombreSolo96.png"));
                }
                else if(e.getSource().equals(botonFrutasSorpresasMulti)){
                    botonFrutasSorpresasMulti.setIcon((new ImageIcon("./images/botonFrutasSorpresas96.png")));
                }
                else if(e.getSource().equals(botonCancelarMulti)){
                    botonCancelarMulti.setIcon((new ImageIcon("./images/botonCancelar96.png")));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(tituloConfigMulti)){
                    tituloConfigMulti.setIcon(new ImageIcon("./images/tituloConfigMulti64.png"));
                }
                else if(e.getSource().equals(botonColorCabezaP1)){
                    botonColorCabezaP1.setIcon(new ImageIcon("./images/colorCabeza64.png"));
                }
                else if(e.getSource().equals(botonColorCabezaP2)){
                    botonColorCabezaP2.setIcon(new ImageIcon("./images/colorCabeza64.png"));
                }
                else if(e.getSource().equals(botonColorCuerpoP1)){
                    botonColorCuerpoP1.setIcon(new ImageIcon("./images/colorCuerpo64.png"));
                }
                else if(e.getSource().equals(botonColorCuerpoP2)){
                    botonColorCuerpoP2.setIcon(new ImageIcon("./images/colorCuerpo64.png"));
                }
                else if(e.getSource().equals(botonGuardarConfigMulti)){
                    botonGuardarConfigMulti.setIcon((new ImageIcon("./images/botonSaveConfig64.png")));
                }
                else if(e.getSource().equals(textoNombreP1)){
                    textoNombreP1.setIcon(new ImageIcon("./images/textoNombreSolo64.png"));
                }
                else if(e.getSource().equals(textoNombreP2)){
                    textoNombreP2.setIcon(new ImageIcon("./images/textoNombreSolo64.png"));
                }
                else if(e.getSource().equals(botonFrutasSorpresasMulti)){
                    botonFrutasSorpresasMulti.setIcon((new ImageIcon("./images/botonFrutasSorpresas64.png")));
                }
                else if(e.getSource().equals(botonCancelarMulti)){
                    botonCancelarMulti.setIcon((new ImageIcon("./images/botonCancelar64.png")));
                }
            }
        };

        for (JLabel label: labelsConfigMulti) {
            label.addMouseListener(mouseListenerConfigMulti);
        }
        for (JButton boton: botonesConfigMulti) {
            boton.addMouseListener(mouseListenerConfigMulti);
        }
    }

    /**
     * Metodo que prepara los listener de los elementos del JPanel de la configuracion en modo maquina
     */
    private void prepareAccionesConfigMaquina(){
        MouseListener mouseListenerConfigMaquina = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(botonColorCabezaP1M)){
                    botonColorCabezaP1M.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                    cambioColor(botonColorCabezaP1M);
                }
                else if(e.getSource().equals(botonColorCabezaP3)){
                    botonColorCabezaP3.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                    cambioColor(botonColorCabezaP3);
                }
                else if(e.getSource().equals(botonColorCuerpoP1M)){
                    botonColorCuerpoP1M.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                    cambioColor(botonColorCuerpoP1M);
                }
                else if(e.getSource().equals(botonColorCuerpoP3)){
                    botonColorCuerpoP3.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                    cambioColor(botonColorCuerpoP3);
                }
                else if(e.getSource().equals(botonGuardarConfigMaquina)) {
                    botonGuardarConfigMaquina.setIcon((new ImageIcon("./images/botonSaveConfig96.png")));
                    jugarMaquina();
                }
                else if(e.getSource().equals(botonFrutasSorpresasMaquina)){
                    botonFrutasSorpresasMaquina.setIcon((new ImageIcon("./images/botonFrutasSorpresas96.png")));
                    caso = 'A';
                    seleccionFrutasSorpresas();
                }
                else if(e.getSource().equals(botonCancelarMaquina)){
                    botonCancelarMaquina.setIcon((new ImageIcon("./images/botonCancelar96.png")));
                    config();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(botonGuardarConfigMulti)){
                    botonGuardarConfigMulti.setIcon((new ImageIcon("./images/botonSaveConfig64.png")));
                    guardarNombresMaquina();
                }
                else if(e.getSource().equals(botonFrutasSorpresasMulti)){
                    botonFrutasSorpresasMulti.setIcon((new ImageIcon("./images/botonFrutasSorpresas64.png")));
                }
                else if(e.getSource().equals(botonCancelarMulti)){
                    botonCancelarMulti.setIcon((new ImageIcon("./images/botonCancelar64.png")));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(botonColorCabezaP1M)){
                    botonColorCabezaP1M.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                    cambioColor(botonColorCabezaP1M);
                }
                else if(e.getSource().equals(botonColorCabezaP3)){
                    botonColorCabezaP3.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                    cambioColor(botonColorCabezaP3);
                }
                else if(e.getSource().equals(botonColorCuerpoP1M)){
                    botonColorCuerpoP1M.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                    cambioColor(botonColorCuerpoP1M);
                }
                else if(e.getSource().equals(botonColorCuerpoP3)){
                    botonColorCuerpoP3.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                    cambioColor(botonColorCuerpoP3);
                }
                else if(e.getSource().equals(botonGuardarConfigMaquina)) {
                    botonGuardarConfigMaquina.setIcon((new ImageIcon("./images/botonSaveConfig96.png")));
                    jugarMaquina();
                }
                else if(e.getSource().equals(botonFrutasSorpresasMaquina)){
                    botonFrutasSorpresasMaquina.setIcon((new ImageIcon("./images/botonFrutasSorpresas96.png")));
                    caso = 'A';
                    seleccionFrutasSorpresas();
                }
                else if(e.getSource().equals(botonCancelarMaquina)){
                    botonCancelarMulti.setIcon((new ImageIcon("./images/botonCancelar96.png")));
                    config();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(tituloConfigMaquina)){
                    tituloConfigMaquina.setIcon(new ImageIcon("./images/tituloConfigMaquina96.png"));
                }
                else if(e.getSource().equals(botonColorCabezaP1M)){
                    botonColorCabezaP1M.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                }
                else if(e.getSource().equals(botonColorCabezaP3)){
                    botonColorCabezaP3.setIcon(new ImageIcon("./images/colorCabeza96.png"));
                }
                else if(e.getSource().equals(botonColorCuerpoP1M)){
                    botonColorCuerpoP1M.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                }
                else if(e.getSource().equals(botonColorCuerpoP3)){
                    botonColorCuerpoP3.setIcon(new ImageIcon("./images/colorCuerpo96.png"));
                }
                else if(e.getSource().equals(botonGuardarConfigMaquina)){
                    botonGuardarConfigMaquina.setIcon((new ImageIcon("./images/botonSaveConfig96.png")));
                }
                else if(e.getSource().equals(textoNombreP1M)){
                    textoNombreP1M.setIcon(new ImageIcon("./images/textoNombreSolo96.png"));
                }
                else if(e.getSource().equals(textoNombreP3)){
                    textoNombreP3.setIcon(new ImageIcon("./images/textoNombreSolo96.png"));
                }
                else if(e.getSource().equals(botonFrutasSorpresasMaquina)){
                    botonFrutasSorpresasMaquina.setIcon((new ImageIcon("./images/botonFrutasSorpresas96.png")));
                }
                else if(e.getSource().equals(botonCancelarMaquina)){
                    botonCancelarMaquina.setIcon((new ImageIcon("./images/botonCancelar96.png")));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(tituloConfigMaquina)){
                    tituloConfigMaquina.setIcon(new ImageIcon("./images/tituloConfigMaquina64.png"));
                }
                else if(e.getSource().equals(botonColorCabezaP1M)){
                    botonColorCabezaP1M.setIcon(new ImageIcon("./images/colorCabeza64.png"));
                }
                else if(e.getSource().equals(botonColorCabezaP3)){
                    botonColorCabezaP3.setIcon(new ImageIcon("./images/colorCabeza64.png"));
                }
                else if(e.getSource().equals(botonColorCuerpoP1M)){
                    botonColorCuerpoP1M.setIcon(new ImageIcon("./images/colorCuerpo64.png"));
                }
                else if(e.getSource().equals(botonColorCuerpoP3)){
                    botonColorCuerpoP3.setIcon(new ImageIcon("./images/colorCuerpo64.png"));
                }
                else if(e.getSource().equals(botonGuardarConfigMaquina)){
                    botonGuardarConfigMaquina.setIcon((new ImageIcon("./images/botonSaveConfig64.png")));
                }
                else if(e.getSource().equals(textoNombreP1M)){
                    textoNombreP1M.setIcon(new ImageIcon("./images/textoNombreSolo64.png"));
                }
                else if(e.getSource().equals(textoNombreP3)){
                    textoNombreP3.setIcon(new ImageIcon("./images/textoNombreSolo64.png"));
                }
                else if(e.getSource().equals(botonFrutasSorpresasMaquina)){
                    botonFrutasSorpresasMaquina.setIcon((new ImageIcon("./images/botonFrutasSorpresas64.png")));
                }
                else if(e.getSource().equals(botonCancelarMaquina)){
                    botonCancelarMaquina.setIcon((new ImageIcon("./images/botonCancelar64.png")));
                }
            }
        };

        for (JLabel label: labelsConfigMaquina) {
            label.addMouseListener(mouseListenerConfigMaquina);
        }
        for (JButton boton: botonesConfigMaquina) {
            boton.addMouseListener(mouseListenerConfigMaquina);
        }
    }

    /**
     *
     */
    private void prepareAccionesTableroMulti(){
        movimientosTableroMulti = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    panelJugador1.cambiarDireccion('L');
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    panelJugador1.cambiarDireccion('R');
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    panelJugador1.cambiarDireccion('U');
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    panelJugador1.cambiarDireccion('D');
                } else if(e.getKeyCode() == KeyEvent.VK_A){
                    panelJugador2.cambiarDireccion('L');
                } else if(e.getKeyCode() == KeyEvent.VK_D){
                    panelJugador2.cambiarDireccion('R');
                } else if(e.getKeyCode() == KeyEvent.VK_W){
                    panelJugador2.cambiarDireccion('U');
                } else if(e.getKeyCode() == KeyEvent.VK_S){
                    panelJugador2.cambiarDireccion('D');
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    pausar();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    snoopeGame.activarSorpresa();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    panelJugador1.cambiarDireccion('L');
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    panelJugador1.cambiarDireccion('R');
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    panelJugador1.cambiarDireccion('U');
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    panelJugador1.cambiarDireccion('D');
                } else if(e.getKeyCode() == KeyEvent.VK_A){
                    panelJugador2.cambiarDireccion('L');
                } else if(e.getKeyCode() == KeyEvent.VK_D){
                    panelJugador2.cambiarDireccion('R');
                } else if(e.getKeyCode() == KeyEvent.VK_W){
                    panelJugador2.cambiarDireccion('U');
                } else if(e.getKeyCode() == KeyEvent.VK_S){
                    panelJugador2.cambiarDireccion('D');
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    pausar();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    try{
                        snoopeGame.activarSorpresa();
                    }catch (Exception ex){
                    }
                }
            }
        };
        requestFocus(true);
        addKeyListener(movimientosTableroMulti);
    }

    private void prepareAccionesTableroMaquina(){
        movimientosTableroMaquina = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    panelJugador1.cambiarDireccion('L');
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    panelJugador1.cambiarDireccion('R');
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    panelJugador1.cambiarDireccion('U');
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    panelJugador1.cambiarDireccion('D');
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    pausar();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    snoopeGame.activarSorpresa();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    panelJugador1M.cambiarDireccion('L');
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    panelJugador1M.cambiarDireccion('R');
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    panelJugador1M.cambiarDireccion('U');
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    panelJugador1M.cambiarDireccion('D');
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    pausar();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    try{
                        snoopeGame.activarSorpresa();
                    }catch (Exception ex){
                    }
                }
            }
        };
        requestFocus(true);
        addKeyListener(movimientosTableroMaquina);
    }

    // METODOS

    /**
     * Metodo que advierte al usuario si desea salir del juego
     */
    private void salga(){
        int variable = JOptionPane.showOptionDialog (null, "¿Seguro que quieres salir?",
                "Advertencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null/*icono*/, opciones, opciones[0]);
        if(variable == 0){
            System.exit(0);
        }
    }

    /**
     * Metodo que guarda el nombre del usuario
     */
    private void guardarNombreSolo(){
        nombreSolo = campoNombre.getText();
    }

    /**
     * Metodo que guarda el nombre de los jugadores en multijugador
     */
    private void guardarNombresMulti(){
        nombreP1 = campoNombreP1.getText();
        nombreP2 = campoNombreP2.getText();
    }

    /**
     * Metodo que guarda el nombre de los jugadores en multijugador
     */
    private void guardarNombresMaquina(){
        nombreP1M = campoNombreP1M.getText();
        nombreP3 = campoNombreP3.getText();
    }

    /**
     * Metodo que inicia el juego solo
     */
    private void jugarSolo(){
        gameOverCase = 'S';
        snoopeGame = new SnOOPe(this.getWidth() - 16, this.getWidth() - 36, numCuadros);
        crearSorpresas();
        crearFrutas();
        try{
            snoopeGame.setColorSnake1(colorCuerpoSolo, colorCabezaSolo);
            snoopeGame.setFrutas(frutas);
            snoopeGame.setTiposDeSorpresas(sorpresas);
            panelSnakeP1 = new PanelSnakeP1(colorCuerpoSolo, colorCabezaSolo, tamMax, numCuadros, snoopeGame, this);
            tableroPanel = new PanelTablero(tamMax,numCuadros, colorTablero);
            nivelPanel.add(panelSnakeP1);
            nivelPanel.add(tableroPanel);
            refreshPuntuacion();
            cl.show(mainPanel,"Juego");
            prepareAccionesTablero();
        } catch (SnOOPeExcepcion e){
            JOptionPane.showMessageDialog(this,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            cl.show(mainPanel,"ConfigSolo");
        }
    }

    /**
     * Metodo que inicia el juego multijugador
     */
    private void jugarMulti(){
        gameOverCase = 'M';
        snoopeGame = new SnOOPe(this.getWidth() - 16, this.getWidth() - 36, numCuadros);
        snoopeGame.activarSnakeP2();
        crearSorpresas();
        crearFrutas();
        try{
            snoopeGame.setColorSnake1(colorCuerpoP1, colorCabezaP1);
            snoopeGame.setColorSnake2(colorCuerpoP2, colorCabezaP2);
            snoopeGame.setFrutas(frutas);
            snoopeGame.setTiposDeSorpresas(sorpresas);
            panelJugador1 = new PanelSnakeP1(colorCuerpoP1, colorCuerpoP1, tamMax, numCuadros, snoopeGame, this);
            panelJugador2 = new PanelSnakeP2(colorCuerpoP2, colorCuerpoP2, tamMax, numCuadros, snoopeGame, this);
            tableroPanel = new PanelTablero(tamMax,numCuadros, colorTablero);
            nivelMultiPanel.add(panelJugador1);
            nivelMultiPanel.add(panelJugador2);
            nivelMultiPanel.add(tableroPanel);
            refreshPuntuacionMulti();
            cl.show(mainPanel, "JuegoMulti");
            prepareAccionesTableroMulti();
        } catch (SnOOPeExcepcion e){
            JOptionPane.showMessageDialog(this,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            cl.show(mainPanel,"ConfigMulti|");
        }
    }

    /**
     * Metodo que inicia el juego multijugador
     */
    private void jugarMaquina(){
        gameOverCase = 'A';
        snoopeGame = new SnOOPe(this.getWidth() - 16, this.getWidth() - 36, numCuadros);
        snoopeGame.activarSnakeP3();
        crearSorpresas();
        crearFrutas();
        try{
            snoopeGame.setColorSnake1(colorCuerpoP1M, colorCabezaP1M);
            snoopeGame.setColorSnake3(colorCuerpoP3, colorCabezaP3);
            snoopeGame.setFrutas(frutas);
            snoopeGame.setTiposDeSorpresas(sorpresas);
            panelJugador1M = new PanelSnakeP1(colorCuerpoP1M, colorCuerpoP1M, tamMax, numCuadros, snoopeGame, this);
            panelJugador3 = new PanelSnakePM(colorCuerpoP3, colorCuerpoP3, tamMax, numCuadros, snoopeGame, this);
            tableroPanel = new PanelTablero(tamMax,numCuadros, colorTablero);
            nivelMaquinaPanel.add(panelJugador1M);
            nivelMaquinaPanel.add(panelJugador3);
            nivelMaquinaPanel.add(tableroPanel);
            refreshPuntuacionMaquina();
            cl.show(mainPanel, "JuegoMaquina");
            prepareAccionesTableroMaquina();
        } catch (SnOOPeExcepcion e){
            JOptionPane.showMessageDialog(this,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            cl.show(mainPanel,"ConfigMulti");
        }
    }

    /**
     * Metodo que termina el juego
     */
    protected void lose(){
        cl.show(mainPanel,"Over");
        snoopeGame.timerOver();
        switch (gameOverCase) {
            case 'S':
                removeKeyListener(movimientosTablero);
                break;
            case 'M':
                removeKeyListener(movimientosTableroMulti);
                break;
            case 'A':
                removeKeyListener(movimientosTableroMaquina);
                break;
        }
    }

    public void stopHilo1() {
        try {
            panelJugador1.stopHilo();
        } catch (Exception e){}
    }

    public void stopHilo2() {
        try {
            panelJugador2.stopHilo();
        } catch (Exception e){}
    }

    public void stopHilo3(){
        try {
            panelJugador3.stopHilo();
        } catch (Exception e){ }
    }

    /**
     * Metodo que muestra el menu de Game Over
     */
    private void menuGameOver(){
        switch (gameOverCase) {
            case 'S':
                nivelPanel.remove(panelSnakeP1);
                nivelPanel.remove(tableroPanel);
                break;
            case 'M':
                nivelMultiPanel.remove(panelJugador1);
                nivelMultiPanel.remove(panelJugador2);
                nivelMultiPanel.remove(tableroPanel);
                break;
            case 'A':
                nivelMaquinaPanel.remove(panelJugador1M);
                nivelMaquinaPanel.remove(panelJugador3);
                nivelMaquinaPanel.remove(tableroPanel);
                break;
        }
        cl.show(mainPanel,"Menu");
    }

    /**
     *  Metodo que permite salir de un juego en curso
     */
    protected void salirPausa(){
        snoopeGame.timerOver();
        switch (gameOverCase) {
            case 'S':
                removeKeyListener(movimientosTablero);
                nivelPanel.remove(panelSnakeP1);
                nivelPanel.remove(tableroPanel);
                break;
            case 'M':
                stopHilo1();
                stopHilo2();
                removeKeyListener(movimientosTableroMulti);
                nivelMultiPanel.remove(panelJugador1);
                nivelMultiPanel.remove(panelJugador2);
                nivelMultiPanel.remove(tableroPanel);
                break;
            case 'A':
                stopHilo1();
                stopHilo3();
                removeKeyListener(movimientosTableroMaquina);
                nivelMaquinaPanel.remove(panelJugador1M);
                nivelMaquinaPanel.remove(panelJugador3);
                nivelMaquinaPanel.remove(tableroPanel);
                break;

        }
        cl.show(mainPanel,"Menu");
    }

    /**
     *  Metodo que reinicia el juego
     */
    private void reiniciar(){
        switch (gameOverCase) {
            case 'S':
                nivelPanel.remove(panelSnakeP1);
                nivelPanel.remove(tableroPanel);
                jugarSolo();
                break;
            case 'M':
                nivelMultiPanel.remove(panelJugador1);
                nivelMultiPanel.remove(panelJugador2);
                nivelMultiPanel.remove(tableroPanel);
                jugarMulti();
                break;
            case 'A':
                nivelMaquinaPanel.remove(panelJugador1M);
                nivelMaquinaPanel.remove(panelJugador3);
                nivelMaquinaPanel.remove(tableroPanel);
                jugarMaquina();
                break;
        }
    }

    /**
     * Metodo que muestra la configuracion
     */
    private void config(){
        cl.show(mainPanel,"Config");
    }

    /**
     * Metodo muestra la configuracion en el modo solitario
     */
    private void configsolo() {
        cl.show(mainPanel, "ConfigSolo");
    }

    /**
     * Metodo muestra la configuracion en el modo multijugador
     */
    private void configMulti(){
        cl.show(mainPanel,"ConfigMulti");
    }

    /**
     * Metodo muestra la configuracion en el modo maquina
     */
    private void configMaquina(){
        cl.show(mainPanel,"ConfigMulti");
    }

    /**
     * Metodo que cancela la configuracion
     */
    private void cancel(){
        cl.show(mainPanel, "Menu");
    }

    /**
     * Metodo que cambia el color de la serpiente
     * @param boton del color que se desea cambiar
     */
    private void cambioColor(JButton boton){
        if(boton.equals(botonColorCabezaSolo)){
            colorCabezaSolo = JColorChooser.showDialog(null,"Color Cabeza",Color.BLACK);
            boton.setBackground(colorCabezaSolo);
        }else if(boton.equals(botonColorCuerpoSolo)){
            colorCuerpoSolo = JColorChooser.showDialog(null,"Color Cuerpo",Color.BLACK);
            boton.setBackground(colorCuerpoSolo);
        }else if(boton.equals(botonColorCabezaP1)){
            colorCabezaP1 = JColorChooser.showDialog(null,"Color Cabeza",Color.BLACK);
            boton.setBackground(colorCabezaP1);
        }else if(boton.equals(botonColorCuerpoP1)){
            colorCuerpoP1 = JColorChooser.showDialog(null,"Color Cuerpo",Color.BLACK);
            boton.setBackground(colorCuerpoP1);
        }else if(boton.equals(botonColorCabezaP2)){
            colorCabezaP2 = JColorChooser.showDialog(null,"Color Cabeza",Color.BLACK);
            boton.setBackground(colorCabezaP2);
        }else if(boton.equals(botonColorCuerpoP2)){
            colorCuerpoP2 = JColorChooser.showDialog(null,"Color Cuerpo",Color.BLACK);
            boton.setBackground(colorCuerpoP2);
        }else if(boton.equals(botonColorCabezaP1M)){
            colorCabezaP1M = JColorChooser.showDialog(null,"Color Cabeza",Color.BLACK);
            boton.setBackground(colorCabezaP1M);
        }else if(boton.equals(botonColorCuerpoP1M)){
            colorCuerpoP1M = JColorChooser.showDialog(null,"Color Cuerpo",Color.BLACK);
            boton.setBackground(colorCuerpoP1M);
        }else if(boton.equals(botonColorCabezaP3)){
            colorCabezaP3 = JColorChooser.showDialog(null,"Color Cabeza",Color.BLACK);
            boton.setBackground(colorCabezaP3);
        }else if(boton.equals(botonColorCuerpoP3)) {
            colorCuerpoP3 = JColorChooser.showDialog(null, "Color Cuerpo", Color.BLACK);
            boton.setBackground(colorCuerpoP3);
        }
    }

    /**
     * Metodo pausa el juego
     */
    private void pausar(){
        switch (gameOverCase){
            case 'S':
                panelSnakeP1.setPause(true);
                snoopeGame.timerOver();
                break;
            case 'M':
                panelJugador1.setPause(true);
                panelJugador2.setPause(true);
                snoopeGame.timerOver();
                break;
            case 'A':
                panelJugador1M.setPause(true);
                panelJugador3.setPause(true);
                snoopeGame.timerOver();
                break;
        }
        cl.show(mainPanel,"Pausa");
    }

    /**
     * Metodo que despausa el juego
     */
    private void resume(){
        switch (gameOverCase){
            case 'S':
                panelSnakeP1.setPause(false);
                snoopeGame.timerReset();
                cl.show(mainPanel,"Juego");
                prepareAccionesTablero();
                break;
            case 'M':
                panelJugador1.setPause(false);
                panelJugador2.setPause(false);
                snoopeGame.timerReset();
                cl.show(mainPanel,"JuegoMulti");
                prepareAccionesTableroMulti();
                break;
            case 'A':
                panelJugador1M.setPause(false);
                panelJugador3.setPause(false);
                snoopeGame.timerReset();
                cl.show(mainPanel,"JuegoMaquina");
                prepareAccionesTableroMaquina();
                break;
        }
    }

    /**
     * Metodo que muestra el panel de frutas y sorpresas
     */
    private void seleccionFrutasSorpresas(){
        cl.show(mainPanel,"FrutasSorpresas");
    }

    /**
     * Metodo que actualiza las frutas que se usaran
     */
    private void crearFrutas(){
        frutas = new ArrayList<>();
        if(checkBoxFruta.isSelected()){
            frutas.add(new Fruta(snoopeGame));
        }if(checkBoxFrutaArcoiris.isSelected()){
            frutas.add(new FrutaArcoiris(snoopeGame));
        }if(checkBoxDulce.isSelected()){
            frutas.add(new Dulce(snoopeGame));
        }if(checkBoxVeneno.isSelected()){
            frutas.add(new Veneno(snoopeGame));
        }
    }

    /**
     * Metodo que despausa el juego
     */
    private void crearSorpresas(){
        sorpresas = new ArrayList<>();
        if(checkBoxVelocidadMas.isSelected()){
            sorpresas.add(new FlehasAumentanVelocidad(snoopeGame));
        }if(checkBoxVelocidadMenos.isSelected()){
            sorpresas.add(new FlechasDisminucionVelocidad(snoopeGame));
        }if(checkBoxDivision.isSelected()){
            sorpresas.add(new Division(snoopeGame));
        }if(checkBoxBloqueTrampa.isSelected()){
            sorpresas.add(new BloqueTrampa(snoopeGame));
        }if(checkBoxEstrellaFuego.isSelected()){
            sorpresas.add(new EstrellaDeFuego(snoopeGame));
        }if(checkBoxLupa.isSelected()){
            sorpresas.add(new Lupa(snoopeGame));
        }
    }

    /**
     * Metodo que refresca el marcador de la puntuacion en solo
     */
    protected void refreshPuntuacion(){
        puntuacionJugadorSolo.setText(nombreSolo+": "+puntuacion);
        puntuacionJugador2.setText(nombreP2+": "+puntuacionP2);
    }

    /**
     * Metodo que refresca el marcador de la puntuacion en multi
     */
    protected void refreshPuntuacionMulti(){
        puntuacionJugador1.setText(nombreP1+": "+puntuacionP1);
        puntuacionJugador2.setText(nombreP2+": "+puntuacionP2);
    }

    /**
     * Metodo que refresca el marcador de la puntuacion en maquina
     */
    protected void refreshPuntuacionMaquina(){
        puntuacionJugador1M.setText(nombreP1M+": "+puntuacionP1M);
        puntuacionJugador3.setText(nombreP3+": "+puntuacionP3);
    }

    /**
     * Metodo asigna la puntuacion actual
     * @param puntuacion puntuacion a asignar
     */
    protected void setPuntuacion(int puntuacion){
        if(puntuacion <= 3){
            this.puntuacion = 0;
        }
        else{
            this.puntuacion = puntuacion - 3;
        }
    }

    /**
     * Metodo asigna la puntuacion actual del Jugador 1
     * @param puntuacion puntuacion a asignar
     */
    protected void setPuntuacionP1(int puntuacion){
        if(puntuacion <= 3){
            this.puntuacionP1 = 0;
        }
        else{
            this.puntuacionP1 = puntuacion - 3;
        }
    }

    /**
     * Metodo asigna la puntuacion actual del Jugador 1
     * @param puntuacion puntuacion a asignar
     */
    protected void setPuntuacionP2(int puntuacion){
        if(puntuacion <= 3){
            this.puntuacionP2 = 0;
        }
        else{
            this.puntuacionP2 = puntuacion - 3;
        }
    }

    /**
     * Metodo asigna la puntuacion actual del Jugador 1
     * @param puntuacion puntuacion a asignar
     */
    protected void setPuntuacionP3(int puntuacion){
        if(puntuacion <= 3){
            this.puntuacionP3 = 0;
        }
        else{
            this.puntuacionP3 = puntuacion - 3;
        }
    }

    /**
     * Muestra la sorpresa si esta guardada en modo solo
     * @param flag si la sorpresa esta guardada o no
     */
    public void actualizarSorpresaSolo(boolean flag){
        if(flag){
            if(snoopeGame.getSorpresasPendiente() instanceof Division){
                sorpresaJugadorSolo.setIcon(new ImageIcon("./images/divisionW.png"));
            }
            else {sorpresaJugadorSolo.setIcon(new ImageIcon(snoopeGame.getSorpresasPendiente().getIconRoute()));}
            sorpresaJugadorSolo.setVisible(true);
        }
        else {
            sorpresaJugadorSolo.setVisible(false);
        }
    }

    /**
     * Muestra la sorpresa si esta guardada en modo multijugador
     * @param flag si la sorpresa esta guardada o no
     */
    public void actualizarSorpresaMulti(boolean flag){
        if(flag) {
            if (snoopeGame.getSorpresasPendiente().getSnakeQueComio() instanceof SnakePlayer1) {
                if (snoopeGame.getSorpresasPendiente() instanceof Division) {
                    sorpresaJugador1.setIcon(new ImageIcon("./images/divisionW.png"));
                } else {
                    sorpresaJugador1.setIcon(new ImageIcon(snoopeGame.getSorpresasPendiente().getIconRoute()));
                }
                sorpresaJugador1.setVisible(true);
            } else if (snoopeGame.getSorpresasPendiente().getSnakeQueComio() instanceof SnakePlayer2) {
                if (snoopeGame.getSorpresasPendiente() instanceof Division) {
                    sorpresaJugador2.setIcon(new ImageIcon("./images/divisionW.png"));
                } else {
                    sorpresaJugador2.setIcon(new ImageIcon(snoopeGame.getSorpresasPendiente().getIconRoute()));
                }
                sorpresaJugador2.setVisible(true);
            }
        }
        else {
            sorpresaJugador1.setVisible(false);
            sorpresaJugador2.setVisible(false);
        }
    }

    /**
     * Metodo que guarda un objeto de SnOOPe en un archivo .dat
     */
    private void opcionGuardar(){
        JFileChooser chooser = new JFileChooser();
        int archivoVal = chooser.showDialog(null,"Guardar");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (archivoVal == JFileChooser.APPROVE_OPTION )
        {
            try {
                snoopeGame.guardar(chooser.getSelectedFile());
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR GUARDADO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Metodo que abre un archivo .dat de AutomataCelular
     */
    private void opcionCargar() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("dat", "dat");
        chooser.setFileFilter(filter);
        int archivoVal = chooser.showDialog(null, "Abrir");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (archivoVal == JFileChooser.APPROVE_OPTION) {
            System.out.println(gameOverCase);
            try {
                snoopeGame = new SnOOPe(this.getWidth() - 16, this.getWidth() - 36, numCuadros);
                snoopeGame = snoopeGame.cargar(chooser.getSelectedFile());
                if (snoopeGame.isTwoPlayer()) {
                    gameOverCase = 'M';
                    snoopeGame.readyUp();
                    panelJugador1 = new PanelSnakeP1(colorCuerpoP1, colorCabezaP1, tamMax, numCuadros, snoopeGame, this);
                    panelJugador2 = new PanelSnakeP2(colorCuerpoP2, colorCabezaP2, tamMax, numCuadros, snoopeGame, this);
                    tableroPanel = new PanelTablero(tamMax, numCuadros, colorTablero);
                    nivelMultiPanel.add(panelJugador1);
                    nivelMultiPanel.add(panelJugador2);
                    nivelMultiPanel.add(tableroPanel);
                    refreshPuntuacionMulti();
                    cl.show(mainPanel, "JuegoMulti");
                    prepareAccionesTableroMulti();

                } else {
                    gameOverCase = 'S';
                    snoopeGame.readyUp();
                    panelSnakeP1 = new PanelSnakeP1(colorCuerpoSolo, colorCabezaSolo, tamMax, numCuadros, snoopeGame, this);
                    tableroPanel = new PanelTablero(tamMax, numCuadros, colorTablero);
                    nivelPanel.add(panelSnakeP1);
                    nivelPanel.add(tableroPanel);
                    refreshPuntuacion();
                    cl.show(mainPanel, "Juego");
                    prepareAccionesTablero();
                }
                System.out.println(snoopeGame.getAlimentos()[0]+", "+snoopeGame.getAlimentos()[1]);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Archivo no encontrado", "ERROR AL ABRIR", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Clase invalida", "ERROR AL ABRIR", JOptionPane.ERROR_MESSAGE);
            } catch (SnOOPeExcepcion snOOPeExcepcion) {
                JOptionPane.showMessageDialog(null, "Version en serie de la clase no coincide", "ERROR AL ABRIR", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que crea una fuente personalizada
     */
    private Font customFont(int size) {
        try {
            InputStream is = new FileInputStream("./fonts/arcade.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(Font.PLAIN, size);
        }
        catch (Exception a){
            return new Font("Arial", Font.PLAIN, 14);
        }

    }

    public static void main(String[] args){
        SnOOPeGUI gui = new SnOOPeGUI();
        gui.setVisible(true);
    }
}
