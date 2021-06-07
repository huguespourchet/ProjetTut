import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Fenetre extends JFrame {
    public Plateau plateau;
    private ControlMenu ctrlMenu;
    private ControlBouton ctrlBouton;
    private ControlPlateau controlPlateau;
    private Model model;

    private JMenuBar barMenu;
    private JMenu menu1;
    private JMenu menu2;
    private JMenuItem item1;
    private JMenuItem item2;
    private JMenuItem item3;
    private JMenuItem item4;

    private JLabel scoreLabel;

    private static final int intRows = 20;
    private static final int intCols = 10;

    public Fenetre( Plateau plateau, ControlPlateau controlPlateau, Model model) throws InterruptedException {
        this.plateau = plateau;
        this.controlPlateau = controlPlateau;
        this.model = model;
        initAttribut();
        changerVersion1();
        setTitle("Tetris1");
        setLocation(200,200);
        setPreferredSize(new Dimension(500,600));
        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initAttribut(){


    }

    public void changerVersion(int version) throws InterruptedException {

        initAttribut();
        if(version == 1){
            changerVersion1();
        }
        else if(version == 2){
            changerVersion2();
        }
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void changerVersion1() throws InterruptedException {

        //Menu
        barMenu = new JMenuBar();
        menu1 = new JMenu("Menu");
        menu2 = new JMenu("Jeu");
        menu1.setBorder(new LineBorder(Color.black));
        menu2.setBorder(new LineBorder(Color.black));
        item1 = new JMenuItem("Nouvelle partie", new ImageIcon("A REMPLIR"));
        item2 = new JMenuItem("Meilleurs scores", new ImageIcon("A REMPLIR"));
        item3 = new JMenuItem("Pause", new ImageIcon("A REMPLIR"));
        item4 = new JMenuItem("Mute Sound", new ImageIcon("A REMPLIR"));
        menu1.add(item1);
        menu1.add(item2);
        menu2.add(item3);
        menu2.add(item4);
        barMenu.add(menu1);
        barMenu.add(menu2);
        setJMenuBar(barMenu);

        //Controle du Menu
        ctrlMenu = new ControlMenu(this);
        item1.addActionListener(ctrlMenu);
        item2.addActionListener(ctrlMenu);
        item3.addActionListener(ctrlMenu);
        item4.addActionListener(ctrlMenu);


        JPanel panelGlobal = new JPanel(new BorderLayout());


        JPanel pano1 = new JPanel();
        pano1.setPreferredSize(new Dimension(100,50));
        JLabel labelTitle = new JLabel("TETRIS");
        labelTitle.setFont(new Font("Serif", Font.BOLD, 24));
        pano1.add(labelTitle);
        panelGlobal.add(pano1,BorderLayout.NORTH);

        JPanel pano2 = new JPanel();
        pano2.setLayout(new BoxLayout(pano2, BoxLayout.Y_AXIS));


        JPanel pano2_1 = new JPanel();
        JLabel labelBox = new JLabel("Box");
        pano2_1.add(labelBox);

        pano2_1.setPreferredSize(new Dimension(100,50));
        pano2_1.setBorder(new LineBorder(Color.black));


        JPanel pano2_2 = new JPanel();
        JLabel labelLevel = new JLabel("Level");
        pano2_2.add(labelLevel);
        pano2_2.setPreferredSize(new Dimension(100,50));
        pano2_2.setBorder(new LineBorder(Color.black));

        pano2.add(pano2_1);
        pano2.add(Box.createHorizontalStrut(10));
        pano2.add(pano2_2);
        panelGlobal.add(pano2, BorderLayout.WEST);

//        JPanel pano3 = new JPanel(new GridLayout(intRows,intCols));
        JPanel pano3 = new JPanel();
        pano3.setLayout(new OverlayLayout(pano3));
        pano3.setPreferredSize(new Dimension(300,300)); //ne marche pas ?

        pano3.setBorder(new LineBorder(Color.pink));

//        for(int row = 0; row < intRows ; row++){
//            for(int col = 0; col < intCols ; col++){
//                JPanel panel = new JPanel(new GridBagLayout());
//                panel.setPreferredSize(new Dimension(30,15));  //ne marche pas ?
//                panel.setBorder(new LineBorder(Color.black));
//                pano3.add(panel);
//            }
//        }


        pano3.add(this.plateau);
        this.plateau.play(this);
        panelGlobal.add(pano3,BorderLayout.CENTER);

        //coté droit
        JPanel pano4 = new JPanel();
        pano4.setLayout(new BoxLayout(pano4, BoxLayout.Y_AXIS));

        //carré Next
        JPanel pano4_1 = new JPanel(new GridLayout(2,1));
        JLabel labelNext = new JLabel("Next");
        labelNext.setHorizontalAlignment(0);

        JPanel pano4_11 = new JPanel();
        //var nextPiece = plateau.getPieceCourrante();
        //pano4_11.add(nextPiece);
        pano4_11.setBorder(new LineBorder(Color.orange));
        pano4_11.setLayout(new OverlayLayout(pano4_11));

        JLabel labelNextPiece = new JLabel("test1");
//        JLabel piece = new JLabel((Icon) plateau.pieceCourrante);
//
//        pano4_11.add(piece);

        pano4_1.add(labelNext);
        pano4_1.add(pano4_11);
        pano4_1.setPreferredSize(new Dimension(100,50));
        pano4_1.setBorder(new LineBorder(Color.black));


        //carré Score
        JPanel pano4_2 = new JPanel();
        JLabel labelScore = new JLabel("Score");
        pano4_2.add(labelScore);
        pano4_2.setPreferredSize(new Dimension(100,50));
        pano4_2.setBorder(new LineBorder(Color.black));


        //carré Best
        JPanel pano4_3 = new JPanel();
        JLabel labelBest = new JLabel("Best");
        pano4_3.add(labelBest);
        pano4_3.setPreferredSize(new Dimension(100,50));
        pano4_3.setBorder(new LineBorder(Color.black));


        pano4.add(pano4_1);
        pano4.add(Box.createHorizontalStrut(3));
        pano4.add(pano4_2);
        pano4.add(Box.createHorizontalStrut(3));
        pano4.add(pano4_3);
        panelGlobal.add(pano4,BorderLayout.EAST);


        JPanel pano5 = new JPanel();
        pano5.setPreferredSize(new Dimension(100,50));
        panelGlobal.add(pano5,BorderLayout.SOUTH);

        setContentPane(panelGlobal);
    }
    public void changerVersion2(){

        //Menu
        barMenu = new JMenuBar();
        menu1 = new JMenu("Menu");
        menu2 = new JMenu("Jeu");
        menu1.setBorder(new LineBorder(Color.black));
        menu2.setBorder(new LineBorder(Color.black));
        item1 = new JMenuItem("Nouvelle partie", new ImageIcon("A REMPLIR"));
        item2 = new JMenuItem("Meilleurs scores", new ImageIcon("A REMPLIR"));
        item3 = new JMenuItem("Pause", new ImageIcon("A REMPLIR"));
        item4 = new JMenuItem("Mute Sound", new ImageIcon("A REMPLIR"));
        menu1.add(item1);
        menu1.add(item2);
        menu2.add(item3);
        menu2.add(item4);
        barMenu.add(menu1);
        barMenu.add(menu2);
        setJMenuBar(barMenu);

        //Controle du Menu
        ctrlMenu = new ControlMenu(this);
        item1.addActionListener(ctrlMenu);
        item2.addActionListener(ctrlMenu);
        item3.addActionListener(ctrlMenu);
        item4.addActionListener(ctrlMenu);


        JPanel panelTest = new JPanel();
        JLabel labelTest = new JLabel("heeeelooo");
        panelTest.add(labelTest);


        setContentPane(panelTest);
    }
    public void display(){ setVisible(true); }


    public JMenuItem getVersion1(){return item1;}
    public JMenuItem getVersion2(){return item2;}
    public JMenuItem getPause(){return item3;}
    public JMenuItem getMuteSound(){return item4;}

}
