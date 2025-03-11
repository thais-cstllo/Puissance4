import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Windows  extends JDialog implements ActionListener{
    private JTextField pseudoField;
    private JComboBox<String> couleurBox;
    private boolean valide = false;
    private JButton valider;
    private JButton annuler;

    public Windows(JFrame parent) { //Frame principale
        super(parent, "Paramètres du joueur", true);
        setLayout(new GridLayout(3, 2, 10, 10));

        //Ajout du pseudo
        add(new JLabel("Pseudo :"));
        pseudoField = new JTextField();
        add(pseudoField);

        //Fenêtre de couleurs
        add(new JLabel("Color :"));
        String[] couleurs = {"Red", "Yellow", "Cyan", "Green"};
        couleurBox = new JComboBox<>(couleurs);
        add(couleurBox);

        //Bouttons
        valider = new JButton("Valider");
        annuler = new JButton("Annuler");
        valider.addActionListener(this);
        annuler.addActionListener(this);

        add(valider);
        add(annuler);

        setSize(300, 150);
        setLocationRelativeTo(parent);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == valider) {
            if (pseudoField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un pseudo !", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                valide = true;
                setVisible(false);
            }
        } else if (e.getSource() == annuler) {
            valide = false;
            setVisible(false);
        }
    }

    public boolean isValide() {
        return valide;
    }

    public String getPseudo() {
        return pseudoField.getText().trim();
    }

    public String getCouleur() {
        return (String) couleurBox.getSelectedItem();
    }
}
