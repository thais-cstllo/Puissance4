
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInterface extends JFrame implements ActionListener {
    private JFrame frame;
    private JLabel label;
    private JButton[] buttons;
    private Engine aEngine;
    private JPanel gridPanel;
    private Grid aGrid;
    private CircleCell[][] displayedCells; // Stocke les cellules affichées

    private String pseudo;
    private String pseudo1;
    private String Color1;
    private String Color2;

    public void setEngine(Engine pEngine) {
        this.aEngine = pEngine;
    }

    public UserInterface(Engine pEngine) {
        this.aEngine = pEngine;
        this.aGrid = this.aEngine.getGrid();  // Récupérer la grille de Engine
        this.displayedCells = this.aGrid.getGrid(); // Lier les cellules affichées à celles du modèle
        this.createUI();
    }

    public void createUI() {
        this.frame = new JFrame("Puissance4");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);

        //Affichage de la première fenettre pour choisir son pseudo et sa couleur
        Windows windows = new Windows(frame);
        windows.setVisible(true);
        if (!windows.isValide()) {
            System.exit(0); // Quitter si l'utilisateur annule
        }
        frame.setVisible(true);
        pseudo = windows.getPseudo();
        String couleur = windows.getCouleur();
        this.Color1 = couleur;
        JOptionPane.showMessageDialog(null, "Pseudo : " + pseudo + "\nCouleur choisie : " + couleur,
                "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        //Affichage de la deuxième fenettre fenetre pour choisir son pseudo et sa couleur
        Windows windows1 = new Windows(frame);
        windows1.setVisible(true);
        if (!windows1.isValide()) {
            System.exit(0); // Quitter si l'utilisateur annule
        }

        pseudo1 = windows1.getPseudo();
        String couleur1 = windows1.getCouleur();
        this.Color2 = couleur1;
        if (couleur.equals(couleur1)) {
            JOptionPane.showMessageDialog(null, "Cette couleur est déjà prise, choisissez-en une autre !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Pseudo 1 : " + pseudo);
        System.out.println("Pseudo 2 : " + pseudo1);

        JOptionPane.showMessageDialog(null, "Pseudo : " + pseudo1 + "\nCouleur choisie : " + couleur1,
                "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        JLayeredPane layeredPane = new JLayeredPane();
        frame.setContentPane(layeredPane);

        // Label
        label = new JLabel("Clique sur l'un des boutons bleus pour jouer au Puissance 4 !", SwingConstants.CENTER);
        label.setBounds(0, 20, 770, 40);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.BLUE);
        layeredPane.add(label, new Integer(2));

        // **Grille affichée**
        gridPanel = new JPanel(new GridLayout(6, 7, 10, 5));
        gridPanel.setBounds(35, 70, 700, 600);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                gridPanel.add(displayedCells[i][j]); // Utiliser les cellules de Grid
            }
        }
        gridPanel.setBackground(Color.BLUE);
        layeredPane.add(gridPanel, new Integer(1));

        // **Boutons**
        JPanel buttonPanel = new JPanel(new GridLayout(1, 7, 10, 5));
        buttonPanel.setBounds(35, 680, 700, 80);
        buttonPanel.setBackground(Color.BLACK);

        buttons = new JButton[7];

        for (int i = 0; i < 7; i++) {
            buttons[i] = new JButton("");
            buttons[i].setBackground(Color.BLUE);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }

        layeredPane.add(buttonPanel, new Integer(0));
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource().equals(buttons[i])) {
                this.aEngine.move(i);
                refreshGrid(); // Mettre à jour l'affichage
                break;
            }
        }
    }

    public void saisiPseudoCouleur(){
        // Utilité ? Dois je effacer ?
    }

    public void afficheVictoire(String pNom){
        this.frame = new JFrame("Puissance4");
        frame.setSize(470, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        frame.setContentPane(layeredPane);

        frame.setBackground(Color.BLACK);

        ImageIcon originalIcon = new ImageIcon("src\\victoire.jpg");
        Image image = originalIcon.getImage().getScaledInstance(470, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);

        // Création du JLabel contenant l'image
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 470, 300); // Position (x=100, y=50) et taille (300x200)
        imageLabel.setIcon(resizedIcon);

        // Ajout de l'image au panneau
        layeredPane.add(imageLabel, new Integer(1));

        JLabel label1 = new JLabel("Le joueur "+pNom+" a gagné ! ", SwingConstants.CENTER);
        label1.setBounds(0, 350, 470, 40);
        label1.setFont(new Font("Arial", Font.BOLD, 24));
        label1.setForeground(Color.BLUE);

        layeredPane.add(label1, new Integer(2));

        frame.setVisible(true);
    }

    // **Méthode pour rafraîchir la grille après chaque coup**
    public void refreshGrid() {
        gridPanel.repaint(); // Redessine la grille
    }

    public void enable(final boolean pOnOff){
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setEnabled(pOnOff);
        }
    }

    public void afficheEx(){
        this.frame = new JFrame("Puissance4");
        frame.setSize(470, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        frame.setContentPane(layeredPane);

        frame.setBackground(Color.BLACK);

        JLabel label1 = new JLabel("Match null !", SwingConstants.CENTER);
        label1.setBounds(0, 350, 470, 40);
        label1.setFont(new Font("Arial", Font.BOLD, 24));
        label1.setForeground(Color.BLUE);

        layeredPane.add(label1, 2);

        frame.setVisible(true);
    }


    public void disableSpecificButton(final int pButtonIndex){
        buttons[pButtonIndex].setEnabled(false);
    }

    public String getPseudo() {
        return pseudo;
    }
    public String getPseudo1() {
        return pseudo1;
    }

    public String getColor1() {
        return Color1;
    }
    public String getColor2() {
        return Color2;
    }
}
