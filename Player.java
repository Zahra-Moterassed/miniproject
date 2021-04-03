public class Player {
    String name;
    Role role;
    boolean IsAlive=true;
    int count=0;
    boolean isSilenced=false;
    boolean isMafia=false;
    boolean isCalled=false;
    boolean isOnceKilled=false;
    public Player(){
    }
    public Player(String name,Role role){
        this.name=name;
        this.role=role;
    }
}
