import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Fenetre extends JFrame {
    public Plateau plateau;
    private ControlMenu ctrlMenu;
    private ControlBouton ctrlBouton;
    private ControlPlateau controlPlateau;
    private ControlGroup controlGroup;
    private Model model;


    /**
     * Menu
     */
    private JMenuBar barMenu;
    private JMenu menu1;
    private JMenu menu2;
    private JMenuItem item1;
    private JMenuItem item2;
    private JMenuItem item3;
    private JMenuItem item4;


    /**
     * Boutons dans l'accueil
     */
    private JButton jButJouer;
    private JButton jButReseau;
    private JButton jButParam;
    private JButton jButExit;
    private JButton jButRetour;
    private BufferedImage myPicture;
    private ImageIcon icon;
    private JLabel scoreLabel;

    //partie reseau
    // JTextField
    private JTextField textValider;
    // JButton
    private JButton jButValider;
    // label to display text
    private JLabel labelValider;

    /**
     * Taille plateau de jeu
     */
    private static final int intRows = 20;
    private static final int intCols = 10;


    /**
     * Constructeur Fenetre à partir de plateau, controlPlateau et model
     * @param plateau
     * @param controlPlateau
     * @param model
     * @throws InterruptedException
     */
    public Fenetre(Plateau plateau, ControlPlateau controlPlateau, Model model) throws InterruptedException {
        this.plateau = plateau;
        this.controlPlateau = controlPlateau;
        this.model = model;
        scoreLabel = new JLabel("0");
        initAttribut();
        changerVersion2();
        setTitle("Tetris1");
        setLocation(200,200);
        setPreferredSize(new Dimension(500,600));
        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initAttribut(){
        myPicture = null;

    }

    /**
     * Permet de changer de fenetre, via les contrôleurs
     * @param version
     * @throws Exception
     */
    public void changerVersion(int version) throws Exception {

        initAttribut();
        if(version == 1){
            changerVersion1();
        }
        else if(version == 2){
            changerVersion2();
        }else if(version == 3){
            changerVersion3();
        }else if(version == 4){
            changerVersion4();
        }
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Fenetre de JEU
     * @throws InterruptedException
     */
    public void changerVersion1() throws InterruptedException {

        //Appel d'un nouveau controlGroup pour pouvoir bouger lors du lancement du jeu
        //après le premier controller créer dans le Menu
        //BUG: SI COMMENTÉ, LES PIECES NE BOUGENT PAS DANS LE JEU, MAIS CELA NE CRÉER PAS DE NOUVELLE FENETRE
        controlGroup = new ControlGroup();
        //BUG : SI NON COMMENTÉ, NE RECRÉER PAS DE NOUVELLE FENETRE  ; MAIS LES TOUCHES DE DÉPLACEMENT DU JEU NE SONT PAS PRISE EN COMPTE
//        this.model = new Model();
//        this.controlPlateau = new ControlPlateau(model);
//        this.plateau = new Plateau(model,controlPlateau);
        //RESOLUTION : CE BUG VIENT DE CONTROLGROUP OÙ L'ON CRÉER UNE NOUVELLE FENETRE, CELA NE DEVRAIT PAS SE PRODUIRE
        //MANQUE DE TEMPS POUR RÉSOUDRE CELA...


        /**
         * Menu
         */
        barMenu = new JMenuBar();
        menu1 = new JMenu("Menu");
        menu2 = new JMenu("Jeu");
        menu1.setBorder(new LineBorder(Color.black));
        menu2.setBorder(new LineBorder(Color.black));
        item1 = new JMenuItem("Nouvelle partie", new ImageIcon("A REMPLIR"));
        item2 = new JMenuItem("Home Page", new ImageIcon("A REMPLIR"));
        item3 = new JMenuItem("Pause", new ImageIcon("A REMPLIR"));
        item4 = new JMenuItem("Mute Sound", new ImageIcon("A REMPLIR"));
        menu1.add(item1);
        menu1.add(item2);
        menu2.add(item3);
        menu2.add(item4);
        barMenu.add(menu1);
        barMenu.add(menu2);
        setJMenuBar(barMenu);

        /**
         * Contrôle du menu
         */
        ctrlMenu = new ControlMenu(this);
        item1.addActionListener(ctrlMenu);
        item2.addActionListener(ctrlMenu);
        item3.addActionListener(ctrlMenu);
        item4.addActionListener(ctrlMenu);


        JPanel panelGlobal = new JPanel(new BorderLayout());

        /**
         * Panel NORD
         */
        JPanel pano1 = new JPanel();
        pano1.setPreferredSize(new Dimension(100,50));
        pano1.setBackground(new Color(175, 0, 24));
        JLabel labelTitle = new JLabel("TETRIS");
        labelTitle.setFont(new Font("Serif", Font.BOLD, 24));
        pano1.add(labelTitle);
        panelGlobal.add(pano1,BorderLayout.NORTH);

        /**
         * Panel global GAUCHE
         */
        JPanel pano2 = new JPanel();
        pano2.setBackground(new Color(175, 0, 24));
        pano2.setLayout(new BoxLayout(pano2, BoxLayout.Y_AXIS));

        /**
         * Sous-panel Gauche n°1
         */
        JPanel pano2_1 = new JPanel();
        JLabel labelBox = new JLabel("Box");
        pano2_1.add(labelBox);

        pano2_1.setPreferredSize(new Dimension(100,50));
        pano2_1.setBorder(new LineBorder(Color.black));

        /**
         * Sous-panel Gauche n°2
         */
        JPanel pano2_2 = new JPanel();
        JLabel labelLevel = new JLabel("Level");
        pano2_2.add(labelLevel);
        pano2_2.setPreferredSize(new Dimension(100,50));
        pano2_2.setBorder(new LineBorder(Color.black));

        pano2.add(pano2_1);
        pano2.add(Box.createHorizontalStrut(10));
        pano2.add(pano2_2);
        panelGlobal.add(pano2, BorderLayout.WEST);

        /**
         * Panel CENTRAL : PLATEAU DE JEU
         */
        JPanel pano3 = new JPanel();
        pano3.setLayout(new OverlayLayout(pano3));
        pano3.setPreferredSize(new Dimension(300,300));
        pano3.setBorder(new LineBorder(Color.pink));
        pano3.add(this.plateau);
        this.plateau.play(this);
        panelGlobal.add(pano3,BorderLayout.CENTER);

        /**
         * Panel global DROIT
         */
        JPanel pano4 = new JPanel();
        pano4.setBackground(new Color(175, 0, 24));
        pano4.setLayout(new BoxLayout(pano4, BoxLayout.Y_AXIS));

        /**
         * Sous-panel droit n°1
         */
        JPanel pano4_1 = new JPanel(new GridLayout(2,1));
        JLabel labelNext = new JLabel("Score");
        labelNext.setHorizontalAlignment(0);
        pano4_1.add(labelNext);
        pano4_1.setPreferredSize(new Dimension(100,50));
        pano4_1.setBorder(new LineBorder(Color.black));

        /**
         * Sous sous panel score numérique
         */
        JPanel pano4_11 = new JPanel();
        pano4_11.setBorder(new LineBorder(Color.orange));
        pano4_11.setLayout(new OverlayLayout(pano4_11));
        scoreLabel.setPreferredSize(new Dimension(50,50));
        pano4_11.add(scoreLabel);
        pano4_1.add(pano4_11);
        pano4_1.setPreferredSize(new Dimension(100,50));
        pano4_1.setBorder(new LineBorder(Color.black));


        /**
         * Sous panel Droit n°2
         */
        JPanel pano4_2 = new JPanel(new GridLayout(2,1));
        JLabel labelScore = new JLabel("Next");
        labelScore.setHorizontalAlignment(0);
        pano4_2.add(labelScore);
        pano4_2.setPreferredSize(new Dimension(100,50));
        pano4_2.setBorder(new LineBorder(Color.black));

        /**
         * Sous sous panel Next Piece Image
         */
        JPanel pano4_21 = new JPanel();
        pano4_21.setBorder(new LineBorder(Color.orange));
        pano4_21.setLayout(new OverlayLayout(pano4_21));
        //à mettre selon type qui tombe
        JLabel lab2 = new JLabel(new ImageIcon(listeImages()[2].getImage()));
        pano4_21.add(lab2);
        pano4_2.add(pano4_21);
        pano4_2.setPreferredSize(new Dimension(100,50));
        pano4_2.setBorder(new LineBorder(Color.black));


        /**
         * Sous panel Droit n°3
         */
        JPanel pano4_3 = new JPanel();
        JLabel labelBest = new JLabel("Best");
        pano4_3.add(labelBest);
        pano4_3.setPreferredSize(new Dimension(100,50));
        pano4_3.setBorder(new LineBorder(Color.black));

        /**
         * Ajout panel global Droit
         */
        pano4.add(pano4_1);
        pano4.add(Box.createHorizontalStrut(3));
        pano4.add(pano4_2);
        pano4.add(Box.createHorizontalStrut(3));
        pano4.add(pano4_3);
        panelGlobal.add(pano4,BorderLayout.EAST);

        /**
         * Panel SUD
         */
        JPanel pano5 = new JPanel();
        pano5.setPreferredSize(new Dimension(100,50));
        pano5.setBackground(new Color(175, 0, 24));
        panelGlobal.add(pano5,BorderLayout.SOUTH);

        setContentPane(panelGlobal);
    }

    /**
     * Fenetre de l'accueil 
     */
    public void changerVersion2(){

        /**
         * Menu
         */
        barMenu = new JMenuBar();
        menu1 = new JMenu("Menu");
        menu2 = new JMenu("Jeu");
        menu1.setBorder(new LineBorder(Color.black));
        menu2.setBorder(new LineBorder(Color.black));
        item1 = new JMenuItem("Nouvelle partie", new ImageIcon("A REMPLIR"));
        item2 = new JMenuItem("Home Page", new ImageIcon("A REMPLIR"));
        item3 = new JMenuItem("Pause", new ImageIcon("A REMPLIR"));
        item4 = new JMenuItem("Mute Sound", new ImageIcon("A REMPLIR"));
        menu1.add(item1);
        menu1.add(item2);
        menu2.add(item3);
        menu2.add(item4);
        barMenu.add(menu1);
        barMenu.add(menu2);
        setJMenuBar(barMenu);

        /**
         * Contrôle du menu 
         */
        ctrlMenu = new ControlMenu(this);
        item1.addActionListener(ctrlMenu);
        item2.addActionListener(ctrlMenu);
        item3.addActionListener(ctrlMenu);
        item4.addActionListener(ctrlMenu);


        JPanel panelGlobal = new JPanel(new BorderLayout());

        /**
         * Panel titre NORD
         */
        JPanel pano1 = new JPanel();
        pano1.setPreferredSize(new Dimension(100,50));
        pano1.setBackground(new Color(230, 77, 98));
        JLabel labelTitle = new JLabel("▙ TETRIS ▜");
        labelTitle.setFont(new Font("Hyeon", Font.BOLD, 32));
        pano1.add(labelTitle);
        panelGlobal.add(pano1,BorderLayout.NORTH);

        /**
         * Panel CENTRAL
         */
        JPanel pano2 = new JPanel();
        pano2.setLayout(new BoxLayout(pano2, BoxLayout.Y_AXIS));
        pano2.setBackground(new Color(230, 77, 98));

        /**
         * Sous panel central "jouer"
         */
        JPanel pano2_1 = new JPanel();
        pano2_1.setBackground(new Color(175, 0, 24));
        //pano2_1.setBorder(new LineBorder(new Color(230, 77, 98)));
        jButJouer = new JButton("Jouer");
        jButJouer.setPreferredSize(new Dimension(150,50));
        jButJouer.setBackground(new Color(230, 77, 98));
        jButJouer.setForeground(Color.BLACK);
        jButJouer.setFocusPainted(false);
        jButJouer.setFont(new Font("Hyeon", Font.BOLD, 20));
        jButJouer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        pano2_1.add(jButJouer);

        /**
         * Sous panel central "Réseau"
         */
        JPanel pano2_2 = new JPanel();
        pano2_2.setBackground(new Color(175, 0, 24));
        //pano2_2.setBorder(new LineBorder(new Color(230, 77, 98)));
        jButReseau = new JButton("Réseau");
        jButReseau.setPreferredSize(new Dimension(150,50));
        jButReseau.setBackground(new Color(230, 77, 98));
        jButReseau.setForeground(Color.BLACK);
        jButReseau.setFocusPainted(false);
        jButReseau.setFont(new Font("Hyeon", Font.BOLD, 20));
        jButReseau.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        pano2_2.add(jButReseau);

        /**
         * Sous panel central "Touches"
         */
        JPanel pano2_3 = new JPanel();
        pano2_3.setBackground(new Color(175, 0, 24));
        //pano2_3.setBorder(new LineBorder(new Color(230, 77, 98)));
        jButParam = new JButton("Touches");
        jButParam.setPreferredSize(new Dimension(150,50));
        jButParam.setBackground(new Color(230, 77, 98));
        jButParam.setForeground(Color.BLACK);
        jButParam.setFocusPainted(false);
        jButParam.setFont(new Font("Hyeon", Font.BOLD, 20));
        jButParam.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        pano2_3.add(jButParam);


        /**
         * Sous panel central "Exit"
         */
        JPanel pano2_4 = new JPanel();
        pano2_4.setBackground(new Color(175, 0, 24));
        //pano2_4.setBorder(new LineBorder(new Color(230, 77, 98)));
        jButExit = new JButton("Exit");
        jButExit.setPreferredSize(new Dimension(150,50));
        jButExit.setBackground(new Color(230, 77, 98));
        jButExit.setForeground(Color.BLACK);
        jButExit.setFocusPainted(false);
        jButExit.setFont(new Font("Hyeon", Font.BOLD, 20));
        jButExit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        pano2_4.add(jButExit);


        /**
         * Ajout des listeners pour les contrôleurs
         */
        ctrlBouton = new ControlBouton(plateau,this);
        jButJouer.addActionListener(ctrlBouton);
        jButReseau.addActionListener(ctrlBouton);
        jButParam.addActionListener(ctrlBouton);
        jButExit.addActionListener(ctrlBouton);

        /**
         * Ajout des sous panels au panel central global
         */
        pano2.add(pano2_1);
        pano2.add(pano2_2);
        pano2.add(pano2_3);
        pano2.add(pano2_4);

        panelGlobal.add(pano2,BorderLayout.CENTER);
        setContentPane(panelGlobal);
    }

    /**
     * Fenetre Touches
     */
    public void changerVersion3(){

        /**
         * Menu
         */
        barMenu = new JMenuBar();
        menu1 = new JMenu("Menu");
        menu2 = new JMenu("Jeu");
        menu1.setBorder(new LineBorder(Color.black));
        menu2.setBorder(new LineBorder(Color.black));
        item1 = new JMenuItem("Nouvelle partie", new ImageIcon("A REMPLIR"));
        item2 = new JMenuItem("Home Page", new ImageIcon("A REMPLIR"));
        item3 = new JMenuItem("Pause", new ImageIcon("A REMPLIR"));
        item4 = new JMenuItem("Mute Sound", new ImageIcon("A REMPLIR"));
        menu1.add(item1);
        menu1.add(item2);
        menu2.add(item3);
        menu2.add(item4);
        barMenu.add(menu1);
        barMenu.add(menu2);
        setJMenuBar(barMenu);

        /**
         * Contrôle menu
         */
        ctrlMenu = new ControlMenu(this);
        item1.addActionListener(ctrlMenu);
        item2.addActionListener(ctrlMenu);
        item3.addActionListener(ctrlMenu);
        item4.addActionListener(ctrlMenu);


        JPanel panelGlobal = new JPanel(new BorderLayout());

        /**
         * Panel Nord
         */
        JPanel pano1 = new JPanel();
        pano1.setPreferredSize(new Dimension(100,50));
        pano1.setBackground(new Color(230, 77, 98));
        JLabel labelTitle = new JLabel("▙ TETRIS ▜");
        labelTitle.setFont(new Font("Hyeon", Font.BOLD, 32));
        pano1.add(labelTitle);
        panelGlobal.add(pano1,BorderLayout.NORTH);

        /**
         * Panel global
         */
        JPanel pano2 = new JPanel();
        pano2.setLayout(new BoxLayout(pano2, BoxLayout.Y_AXIS));
        pano2.setBackground(new Color(230, 77, 98));


        JPanel pano2_1 = new JPanel();
        pano2_1.setBackground(new Color(175, 0, 24));
       // pano2_1.setBorder(new LineBorder(new Color(230, 77, 98)));
        JLabel label2_1 = new JLabel("Direction : ");
        JLabel label2_2 = new JLabel(" gauche = \t← \tdroite = \t→ ");
        label2_1.setFont(new Font("Hyeon", Font.BOLD, 26));
        label2_1.setForeground(Color.black);
        label2_2.setFont(new Font("Hyeon", Font.BOLD, 20));
        label2_2.setForeground(Color.black);
        pano2_1.add(label2_1);
        pano2_1.add(label2_2);


        JPanel pano2_2 = new JPanel();
        pano2_2.setBackground(new Color(175, 0, 24));
       // pano2_2.setBorder(new LineBorder(new Color(230, 77, 98)));
        JLabel label2_3 = new JLabel("Rotation : ");
        JLabel label2_4 = new JLabel(" gauche = \t↑ \tdroite = \t↓   ");
        label2_3.setFont(new Font("Hyeon", Font.BOLD, 26));
        label2_3.setForeground(Color.black);
        label2_4.setFont(new Font("Hyeon", Font.BOLD, 20));
        label2_4.setForeground(Color.black);
        pano2_2.add(label2_3);
        pano2_2.add(label2_4);

        JPanel pano2_3 = new JPanel();
        pano2_3.setBackground(new Color(175, 0, 24));
       // pano2_3.setBorder(new LineBorder(new Color(230, 77, 98)));
        JLabel label2_5 = new JLabel("Pause : ");
        JLabel label2_6 = new JLabel("touche <P>                           ");
        label2_5.setFont(new Font("Hyeon", Font.BOLD, 26));
        label2_5.setForeground(Color.black);
        label2_6.setFont(new Font("Hyeon", Font.BOLD, 20));
        label2_6.setForeground(Color.black);
        pano2_3.add(label2_5);
        pano2_3.add(label2_6);

        JPanel pano2_4 = new JPanel();
        pano2_4.setBackground(new Color(175, 0, 24));
       // pano2_4.setBorder(new LineBorder(new Color(230, 77, 98)));
        JLabel label2_7 = new JLabel("Chute complète : ");
        JLabel label2_8 = new JLabel("touche <Space>");
        label2_7.setFont(new Font("Hyeon", Font.BOLD, 26));
        label2_7.setForeground(Color.black);
        label2_8.setFont(new Font("Hyeon", Font.BOLD, 20));
        label2_8.setForeground(Color.black);
        pano2_4.add(label2_7);
        pano2_4.add(label2_8);

        JPanel pano2_5 = new JPanel();
        pano2_5.setBackground(new Color(175, 0, 24));
        // pano2_4.setBorder(new LineBorder(new Color(230, 77, 98)));
        jButRetour = new JButton("↺ Retour");
        jButRetour.setPreferredSize(new Dimension(150,50));
        jButRetour.setBackground(new Color(230, 77, 98));
        jButRetour.setForeground(Color.BLACK);
        jButRetour.setFocusPainted(false);
        jButRetour.setFont(new Font("Hyeon", Font.BOLD, 20));
        jButRetour.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        pano2_5.add(jButRetour);


        /**
         * Ajout des listeners pour les contrôleurs
         */
        ctrlBouton = new ControlBouton(plateau,this);
        jButJouer.addActionListener(ctrlBouton);
        jButReseau.addActionListener(ctrlBouton);
        jButParam.addActionListener(ctrlBouton);
        jButExit.addActionListener(ctrlBouton);
        jButRetour.addActionListener(ctrlBouton);

        /**
         * Ajout au panel global
         */
        pano2.add(pano2_1);
        pano2.add(pano2_2);
        pano2.add(pano2_3);
        pano2.add(pano2_4);
        pano2.add(pano2_5);

        panelGlobal.add(pano2,BorderLayout.CENTER);
        setContentPane(panelGlobal);
    }

    /**
     * Fenetre consignes et configuration réseau
     * @throws Exception
     */
    public void changerVersion4() throws Exception {

        /**
         * Menu
         */
        barMenu = new JMenuBar();
        menu1 = new JMenu("Menu");
        menu2 = new JMenu("Jeu");
        menu1.setBorder(new LineBorder(Color.black));
        menu2.setBorder(new LineBorder(Color.black));
        item1 = new JMenuItem("Nouvelle partie", new ImageIcon("A REMPLIR"));
        item2 = new JMenuItem("Home Page", new ImageIcon("A REMPLIR"));
        item3 = new JMenuItem("Pause", new ImageIcon("A REMPLIR"));
        item4 = new JMenuItem("Mute Sound", new ImageIcon("A REMPLIR"));
        menu1.add(item1);
        menu1.add(item2);
        menu2.add(item3);
        menu2.add(item4);
        barMenu.add(menu1);
        barMenu.add(menu2);
        setJMenuBar(barMenu);

        /**
         * Contrôle du menu
         */
        ctrlMenu = new ControlMenu(this);
        item1.addActionListener(ctrlMenu);
        item2.addActionListener(ctrlMenu);
        item3.addActionListener(ctrlMenu);
        item4.addActionListener(ctrlMenu);


        JPanel panelGlobal = new JPanel(new BorderLayout());

        /**
         * Panel Nord
         */
        JPanel pano1 = new JPanel();
        pano1.setPreferredSize(new Dimension(100,50));
        pano1.setBackground(new Color(230, 77, 98));
        JLabel labelTitle = new JLabel("▞ Réseau ▚");
        labelTitle.setFont(new Font("Hyeon", Font.BOLD, 32));
        pano1.add(labelTitle);
        panelGlobal.add(pano1,BorderLayout.NORTH);

        /**
         * Panel global
         */
        JPanel pano2 = new JPanel();
        pano2.setLayout(new BoxLayout(pano2, BoxLayout.Y_AXIS));
        pano2.setBackground(new Color(230, 77, 98));

        /**
         * Consignes
         */
        JPanel pano2_1 = new JPanel();
        pano2_1.setBackground(new Color(175, 0, 24));
        pano2_1.setBorder(new LineBorder(new Color(230, 77, 98)));
        JLabel label2_1 = new JLabel("Pour jouer en réseau avec un deuxième joueur vous devez:\n");
        label2_1.setFont(new Font("Hyeon",Font.BOLD, 15));
        label2_1.setForeground(Color.BLACK);

        JLabel label2_2 = new JLabel("⇨ Lancer le serveur           \n");
        label2_2.setFont(new Font("Hyeon",Font.BOLD, 15));
        label2_2.setForeground(Color.BLACK);

        JLabel label2_3 = new JLabel("⇨ Donner le nom du serveur dans le champ texte \n");
        label2_3.setFont(new Font("Hyeon",Font.BOLD, 15));
        label2_3.setForeground(Color.BLACK);

        JLabel label2_4 = new JLabel("⇨ Le jeu se lance automatiquement une fois connecté \n");
        label2_4.setFont(new Font("Hyeon",Font.BOLD, 15));
        label2_4.setForeground(Color.BLACK);

        pano2_1.add(label2_1);
        pano2_1.add(label2_2);
        pano2_1.add(label2_3);
        pano2_1.add(label2_4);

        /**
         * Configuration serveur
         */
        JPanel pano2_2 = new JPanel();
        pano2_2.setBackground(new Color(175, 0, 24));
        pano2_2.setBorder(new LineBorder(new Color(230, 77, 98)));
        jButValider = new JButton("Valider");
        jButValider.setPreferredSize(new Dimension(100,50));
        jButValider.setBackground(new Color(230, 77, 98));
        jButValider.setForeground(Color.BLACK);
        jButValider.setFocusPainted(false);
        jButValider.setFont(new Font("Hyeon", Font.BOLD, 20));
        jButValider.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        // create a label to display text
        labelValider = new JLabel("Entrez le Serveur ici :                    ");
        labelValider.setFont(new Font("Hyeon",Font.BOLD, 20));
        labelValider.setForeground(Color.BLACK);

        int portTetris = 6667;

        String nomMachine = JOptionPane.showInputDialog
                (null, "Nom de la machine hôte ?");
        //A IMPLÉMENTER
        //setContentPane(new FenetreJoueurClient(joueur));
//        Socket socket = new Socket(nomMachine, portTetris);
//        JoueurClient joueur = new JoueurClient(socket);

        textValider = new JTextField(16);
        textValider.setBackground(new Color(230, 77, 98));
        textValider.setBorder(new LineBorder(Color.BLACK));
        pano2_2.add(labelValider);
        pano2_2.add(textValider);
        pano2_2.add(jButValider);

        /**
         * Bouton retour accueil
         */
        JPanel pano2_4 = new JPanel();
        pano2_4.setBackground(new Color(175, 0, 24));
        pano2_4.setBorder(new LineBorder(new Color(230, 77, 98)));
        jButRetour = new JButton("↺ Retour");
        jButRetour.setPreferredSize(new Dimension(150,50));
        jButRetour.setBackground(new Color(230, 77, 98));
        jButRetour.setForeground(Color.BLACK);
        jButRetour.setFocusPainted(false);
        jButRetour.setFont(new Font("Hyeon", Font.BOLD, 20));
        jButRetour.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        pano2_4.add(jButRetour);

        ctrlBouton = new ControlBouton(plateau,this);
        jButJouer.addActionListener(ctrlBouton);
        jButReseau.addActionListener(ctrlBouton);
        jButParam.addActionListener(ctrlBouton);
        jButExit.addActionListener(ctrlBouton);
        jButRetour.addActionListener(ctrlBouton);

        pano2.add(pano2_1);
        pano2.add(pano2_2);
        //pano2.add(pano2_3);
        pano2.add(pano2_4);

        panelGlobal.add(pano2,BorderLayout.CENTER);
        setContentPane(panelGlobal);
    }
    /**
     * Defini le chemin pour obtenir les images à shuffle
     * selon la size de la fenetre
     * @return
     */
    public ImageIcon[] listeImages(){
        String chemin="";
        chemin += "images";
        ImageIcon [] listeImages=new ImageIcon[10];
        try{
            File folder=new File(chemin);
            int cpt=0;
            for (final File fileEntry : folder.listFiles()) {
                listeImages[cpt]=new ImageIcon(chemin+'/'+fileEntry.getName()) ;
                listeImages[cpt]=new ImageIcon(listeImages[cpt].getImage().getScaledInstance(50,50, BufferedImage.SCALE_SMOOTH));
                cpt++ ;
            }
        }catch(Exception e){ e.printStackTrace(); }
        return listeImages;
    }
    /**
     * Renvoi l'image sous forme d'icone
     * @param img
     * @return
     */
    public ImageIcon getImageButton(BufferedImage img){
        ImageIcon icon = new ImageIcon(img);
        icon = new ImageIcon(icon.getImage().getScaledInstance(50,50,BufferedImage.SCALE_SMOOTH));
        return icon;

    }

    //A IMPLÉMENTER PAR RAPPORT AU MODÈLE
    public void augmenterScore(){
//       if(controlPlateau.isLigneComplete())
        //setScoreLabel(String.valueOf(controlPlateau.nbrLignesCompletes()));
    }

    public void display(){ setVisible(true); }


    public JMenuItem getVersion1(){return item1;}
    public JMenuItem getVersion2(){return item2;}
    public JMenuItem getPause(){return item3;}
    public JMenuItem getMuteSound(){return item4;}
    public JButton getPlay(){return jButJouer;}
    public JButton getReseau(){return jButReseau;}
    public JButton getParam(){return jButParam;}
    public JButton getExit(){return jButExit;}
    public JButton getRetour(){return jButRetour;}
    public JLabel getScoreLabel(){ return scoreLabel; }
    public void setScoreLabel(String txt){ this.scoreLabel.setText(txt); }
    public String getTextReseau(){ return textValider.getText(); }


}
