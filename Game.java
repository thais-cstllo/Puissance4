public class Game {
    private UserInterface aUI;
    private Engine aEngine;

    public Game(){
        this.aUI = new UserInterface();

        String pseudo1 = aUI.getPseudo();
        String pseudo2 = aUI.getPseudo1();
        //Le problème est que lorsque que l'on créé l'objet UserInterface, createUI n'est pas créé donc on ne peut pas prendre le nom des utilsateurs
        this.aEngine = new Engine(new Player(pseudo1, 1), new Player(pseudo2, 2));
        System.out.println("Pseudo récupéré pour aP1 : " + pseudo1);
        System.out.println("Pseudo récupéré pour aP2 : " + pseudo2);
        this.aUI.setEngine(this.aEngine);
        this.aEngine.setUI(this.aUI);

    }
}
