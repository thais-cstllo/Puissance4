public class Game {
    private UserInterface aUI;
    private Engine aEngine;

    public Game(){
        this.aEngine = new Engine();
        this.aUI = new UserInterface(this.aEngine);
        this.aEngine.setUI(this.aUI);
        String pseudo1 = aUI.getPseudo();
        String pseudo2 = aUI.getPseudo1();
        Player p1 = new Player(pseudo1, 1);
        Player p2 = new Player(pseudo2, 2);
        this.aEngine.setP1(p1);
        this.aEngine.setP2(p2);
        System.out.println("Pseudo récupéré pour aP1 : " + pseudo1);
        System.out.println("Pseudo récupéré pour aP2 : " + pseudo2);
    }
}
