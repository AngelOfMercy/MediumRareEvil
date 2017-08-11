package Game;
public class Player {

    private static int ID_GENERATION = 1;
    private int PLAYER_ID = -1;
    private String name;

    public Player(String name){
        this.PLAYER_ID = ID_GENERATION++;
        this.name = name;

    }

    public int getID(){
        return PLAYER_ID;
    }

    public String getName(){ return name; }

}
