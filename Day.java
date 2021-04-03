import java.util.Scanner;
public class Day {
    int numberOfDay=1;
    public int dayvoting(Player[] players,int mafiacounter,int villagercounter){
        int len=0;
        Scanner scanner=new Scanner(System.in);
        while (len< players.length) {
            String a = scanner.nextLine();
            String[] s = a.split(" ");
            for (int j = 0; j < players.length; j++) {
                if (s[0].equals(players[j])) {
                    if (!players[j].isSilenced) {
                        for (int k = 0; k < players.length; k++) {
                            if (s[1].equals(players[k].name)) {
                                players[j].count++;
                            } else if (!s[1].equals(players[k].name) && k == players.length - 1) {
                                System.out.println("user not found");
                            }
                        }
                    } else {
                        System.out.println("voter is silenced");
                    }
                }
            }
            len++;
        }
        String h=scanner.nextLine();
        int max=0;//max count
        int maxl=0;//max count's index
        int counter=0;// number of maxes
        for (int l=0;l<players.length;l++){
            if(players[l].count>=max) {
                if(players[l].count==max && max!=0){
                    counter++;
                }
                maxl = l;
                max =players[l].count;

            }
        }
        if(counter>0){
            System.out.println("nobody died");
        }
        if(players[maxl].IsAlive && counter==0) {
            if (players[maxl].isMafia){
                mafiacounter--;
            }
            else if(players[maxl].role==Role.godfather){
                mafiacounter--;
            }
            else if(!players[maxl].isMafia && players[maxl].role!=Role.godfather){
                villagercounter--;
            }
            players[maxl].IsAlive = false;
        }
        else if(!players[maxl].IsAlive){
            System.out.println("votee already dead");
        }
        if (players[maxl].role==Role.Joker){
            System.out.println("Joker won!");
        }
        else if(players[maxl].role!=Role.Joker) {
            System.out.println(players[maxl].name + " died");
        }
        return maxl;
    }
}
