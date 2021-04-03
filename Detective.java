public class Detective extends Player{
    public Detective(String name, Role role) {
        super(name, role);
    }
    public void detective_asking(Player[] players,String[] z){
        for (int j = 0; j < players.length; j++) {
            if (z[1].equals(players[j].name)) {
                if (players[j].isMafia && players[j].IsAlive) {
                    System.out.println("Yes");
                    break;
                } else if (!players[j].IsAlive) {
                    System.out.println("suspect is dead");
                    break;
                } else if (!players[j].isMafia && players[j].IsAlive) {
                    System.out.println("No");
                    break;
                }
            } else if (!z[1].equals(players[j].name) && j == players.length - 1) {
                System.out.println("user not found");
            }
        }

    }
}
