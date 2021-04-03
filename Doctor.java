public class Doctor extends Player{
    public Doctor(String name, Role role) {
        super(name, role);
    }
    public void doctor_saving(Player[] players,int maxl,int max,int othermax,int counter,String[] z){
        int savedByDoctor;
        for (int j = 0; j < players.length; j++) {
            if (z[1].equals(players[j].name)) {
                savedByDoctor = j;
                if (j == maxl && counter == 0) {
                    players[j].IsAlive = true;
                } else if (counter > 0) {
                    if (savedByDoctor == maxl) {
                        players[maxl].IsAlive = true;
                        players[othermax].IsAlive = false;
                    } else if (savedByDoctor == othermax) {
                        players[othermax].IsAlive = true;
                        players[maxl].IsAlive = false;
                    }
                }
            }
        }
    }
}
