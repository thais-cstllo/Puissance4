public class Player {
    private String name;
    private int id;
    private String aColor;

    public Player(String name, int id, String pColor) {
        this.name = name;
        this.id = id;
        this.aColor = pColor;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getColor(){ return aColor; }

    public void setIdColor(String pColor){ this.aColor = pColor; }
}
