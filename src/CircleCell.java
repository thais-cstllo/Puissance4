import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CircleCell extends JPanel {
    private final HashMap<Integer,String> aColors;
    private Color circleColor;
    private int aState;
    private static final int ROWS = 6;
    private static final int COLS = 7;


    public CircleCell() {
        setPreferredSize(new Dimension(80, 80)); // Taille fixe des cellules
        this.aColors = new HashMap<>();
        this.aColors.put(1,"RED");
        this.aColors.put(2,"YELLOW");
        this.aColors.put(3,"GREEN");
        this.aColors.put(4,"CYAN");
        this.aColors.put(0,"BLACK");
        this.aState = 0;
        this.circleColor = Color.BLACK;
    }
    public void setColorByInt(int colorKey) {
        String colorName = aColors.get(colorKey);
        this.setState(colorKey);
        if (colorName != null) {
            this.circleColor = stringToColor(colorName);
            repaint();
        }
    }

    public int getState() { // 0 : empty, 1 : rouge, 2 : jaune ...
        return this.aState;
    }

    public void setCircleColorByString(String colorName) {
        this.circleColor = stringToColor(colorName);
    }

    public void setState(int state) {
        this.aState = state;
    }

    public Color getColor() {
        return this.circleColor;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, getWidth(), getHeight()); // Remplir tout le JPanel en bleu

        int size = Math.min(getWidth(), getHeight()) - 10; // Ajustement pour marges
        g2d.setColor(circleColor); // Couleur du cercle
        g2d.fillOval(5, 5, size, size); // Dessiner le cercle
    }

    private Color stringToColor(String colorName) {
        switch (colorName.toUpperCase()) {
            case "RED":
                return Color.RED;
            case "YELLOW":
                return Color.YELLOW;
            case "GREEN":
                return Color.GREEN;
            case "CYAN":
                return Color.CYAN;
            case "BLACK":
                return Color.BLACK;
            default :
                return null;
        }

    }

}
