import java.awt.*;
import java.util.Arrays;

public class Grid {
    private CircleCell[][] grid;
    public final int ROWS = 6;
    public final int COLS = 7;

    public Grid() {
        grid = new CircleCell[ROWS][COLS];
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                grid[i][j] = new CircleCell();         // initialisation de la grille ( toutes les cases sont vides )
            }
        }
    }

    public void changeCellColor(int col, Player pPlayer) {
        if (col < 0 || col >= COLS) return; // Vérification que la colonne est valide
        String color = pPlayer.getColor();
        int id = pPlayer.getId();
        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][col].getColor().equals(Color.BLACK)) {
                grid[row][col].setCircleColorByString(color);
                grid[row][col].setState(id);
                return;
            }
        }
    }

    public CircleCell[][] getGrid() {
        return grid;
    }
/*
    public void insert(final int pCol,final int player_id){
        if( pCol < 0 || pCol >= COLS ) throw new IndexOutOfBoundsException("Numéro de colonne invalide");
        int r = 0;
        int row = ROWS-1;
        while(row >= 0) {
            if(this.grid[row][pCol].getState() == 0) r = row;
            row--;
        }
        grid[r][pCol].setState(player_id);
    }*/



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = ROWS-1; i >= 0; i--) {
            for(int j = 0; j < COLS; j++) {
                sb.append(this.grid[i][j].getColor()).append(" | ");
                if(j == COLS-1) sb.append("\n");
            }
        }
        return sb.toString();
    }


}
