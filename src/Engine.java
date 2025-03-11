
public class Engine {
    private Player aP1;
    private Player aP2;
    private Grid aGrid;
    private Player aMainPlayer;
    private UserInterface aUI;


    public Engine() {
        this.aGrid = new Grid();
        this.aMainPlayer = this.aP1;
    }

    public void setUI(UserInterface pUI) {
        this.aUI = pUI;
    }

    public void setP2(Player aP2) {
        this.aP2 = aP2;
    }

    public void setP1(Player p1) {
        this.aP1 = p1;
    }

    public void setDefaultPlayer(){
        this.aMainPlayer = this.aP1;
    }

    public Grid getGrid() {return this.aGrid;}

    public void move(final int pCol) {
        String vNom = this.aMainPlayer.getName();
        System.out.println("Player : " + vNom + this.aMainPlayer.getId()+ this.aMainPlayer.getColor());
        this.aGrid.changeCellColor(pCol,this.aMainPlayer); // Insère le pion dans la colonne choisie
        if(this.checkVictory(this.aMainPlayer)) {
            System.out.printf("le joueur " + vNom+ " a gagné ! ");  // Vérifie si le joueur a gagné
            this.aUI.afficheVictoire(vNom);
            this.endGame();
            return;
        }
        if(isColFull(pCol)) { this.aUI.disableSpecificButton(pCol); }
        if(isGridFull()) { this.aUI.afficheEx(); this.endGame(); }
        this.alternate(); // Passe au joueur suivant
    }

    public void setMainPlayer(final Player pMainPlayer) {
        this.aMainPlayer = pMainPlayer;
    }

    /**
     * Methode à utiliser après le jeu du joueur pour faire changer le joueur principal
     */
    public void alternate() {
        if (this.aMainPlayer.equals(this.aP1)) {
            this.setMainPlayer(this.aP2);
        }else {
            this.setMainPlayer(this.aP1);
        }
        System.out.println(this.aMainPlayer.getName());
    }
/*
    public Player alternate2() {
        if (this.aMainPlayer == this.aP1.getId()){
            this.setMainPlayer(this.aP2.getId());
            return this.aP2;
        }else {
            this.setMainPlayer(this.aP1.getId());
            return this.aP1;
        }
    }*/

    public boolean checkAlignementHorizontal(final Player p) {
        int rows = this.aGrid.ROWS;
        int cols = this.aGrid.COLS;
        int playerId = p.getId();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols-3; j++) {
                if (this.aGrid.getGrid()[i][j].getState() == playerId &&
                        this.aGrid.getGrid()[i][j + 1].getState() == playerId &&
                        this.aGrid.getGrid()[i][j + 2].getState() == playerId &&
                        this.aGrid.getGrid()[i][j + 3].getState() == playerId) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean checkAlignementVertical(final Player p){
        int rows = this.aGrid.ROWS;
        int cols = this.aGrid.COLS;
        int playerId = p.getId();

        for(int i = 0; i<cols; i++ ){
            for(int j = 0; j<rows-3; j++){
                if(this.aGrid.getGrid()[j][i].getState() == playerId &&
                        this.aGrid.getGrid()[j+1][i].getState() == playerId &&
                        this.aGrid.getGrid()[j+2][i].getState() == playerId &&
                        this.aGrid.getGrid()[j+3][i].getState() == playerId){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkAlignementDiagonalM(final Player p) {
        int rows = this.aGrid.ROWS;
        int cols = this.aGrid.COLS;
        int playerId = p.getId();

        for (int i = 3; i < rows; i++) {
            for (int j = 0; j < cols - 3; j++) {
                if (this.aGrid.getGrid()[i][j].getState() == playerId &&
                        this.aGrid.getGrid()[i - 1][j + 1].getState() == playerId &&
                        this.aGrid.getGrid()[i - 2][j + 2].getState() == playerId &&
                        this.aGrid.getGrid()[i - 3][j + 3].getState() == playerId)
                    return true;

            }
        }
        return false;
    }

    public boolean checkAlignementDiagonalD(final Player p) {
        int rows = this.aGrid.ROWS;
        int cols = this.aGrid.COLS;
        int playerId = p.getId();

        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < cols - 3; j++) {
                if (this.aGrid.getGrid()[i][j].getState() == playerId &&
                        this.aGrid.getGrid()[i + 1][j + 1].getState() == playerId &&
                        this.aGrid.getGrid()[i + 2][j + 2].getState() == playerId &&
                        this.aGrid.getGrid()[i + 3][j + 3].getState() == playerId)
                    return true;
            }
        }
        return false;
    }

    public boolean checkVictory(final Player p){
        boolean result = false;
        if(checkAlignementHorizontal(p)){
            result = true;
        }else if(checkAlignementVertical(p)){
            result = true;
        }else if(checkAlignementDiagonalM(p)){
            result = true;
        }else if(checkAlignementDiagonalD(p)){
            result = true;
        }
        return result;
    }

    public void endGame(){
        this.aUI.enable(false);
    }

    public boolean isColFull(final int pCol) {
        return this.aGrid.getGrid()[0][pCol].getState() !=  0;
    }

    public boolean isGridFull(){
        for(int col = 0 ; col < this.aGrid.COLS ; col++){
            if(!(isColFull(col))) return false;
        }
        return true;
    }

}
