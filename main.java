import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inp = scanner.nextLine();
        int coun = 0;
        Night night = new Night();
        Day day = new Day();
        int mafiacounter = 0;
        int villagercounter = 0;
        String[] x = inp.split(" ");
        Player[] players = new Player[x.length - 1];
        if (x[0].equals("create_game")) {
            coun++;
            for (int i = 1, j = 0; i < x.length; i++, j++) {
                players[j] = new Player();
                players[j].name = x[i];
            }
        } else {
            System.out.println("no game created");
        }
        Doctor doctor = null;
        Detective detective = null;
        int n = 0;
        while (n < players.length) {
            String y = scanner.nextLine();
            String[] z = y.split(" ");
            if (z[0].equals("assign_role")) {
                if (z[1].equals(players[n].name)) {
                    switch (z[2]) {
                        case "mafia":
                            players[n].role = Role.mafia;
                            players[n].isMafia = true;
                            mafiacounter++;
                            break;
                        case "godfather":
                            players[n].role = Role.godfather;
                            mafiacounter++;
                            break;
                        case "doctor":
                            players[n].role = Role.doctor;
                            villagercounter++;
                            doctor = new Doctor(z[1], Role.doctor);
                            break;
                        case "detective":
                            players[n].role = Role.detective;
                            villagercounter++;
                            detective = new Detective(z[1], Role.detective);
                            break;
                        case "Joker":
                            players[n].role = Role.Joker;
                            break;
                        case "villager":
                            players[n].role = Role.villager;
                            villagercounter++;
                            break;
                        case "silencer":
                            players[n].role = Role.silencer;
                            mafiacounter++;
                            players[n].isMafia = true;
                            break;
                        case "bulletproof":
                            players[n].role = Role.bulletproof;
                            villagercounter++;
                            break;
                        default:
                            System.out.println("role not found");
                    }

                } else {
                    System.out.println("user not found");
                }
            }
            n++;
        }
        String y = scanner.nextLine();
        int c = 0;
        if (y.equals("start_game")) {
            c++;
            for (int i = 0; i < players.length; i++) {
                if (players[i].role == null) {
                    System.out.println("one or more player do not have a role");
                } else
                    System.out.println(players[i].name + ": " + players[i].role);
            }
            System.out.println("Ready? Set! Go!");
        }
        if (coun == 0) {//coun>0 game created
            System.out.println("no game created");
        }
        if (c > 1) {
            System.out.println("game has already started");
        }
        System.out.println("Day " + day.numberOfDay);
        while (mafiacounter != 0 || villagercounter <= mafiacounter) {
            for (int l = 0; l < players.length; l++) {
                players[l].count = 0;
            }
            day.dayvoting(players, mafiacounter, villagercounter);
            int Index_joker = 0;
            for (int j = 0; j < players.length; j++) {
                if (players[j].role == Role.Joker) {
                    Index_joker = j;
                }
            }
            if (!players[Index_joker].IsAlive){
                return;
            }
            if (players[Index_joker].IsAlive || players[Index_joker].role != Role.Joker) {
                System.out.println("Night " + night.numberOfNight);
                night.numberOfNight++;
                for (int i = 0; i < players.length; i++) {
                    if (players[i].IsAlive && (players[i].role==Role.doctor||players[i].role==Role.detective||players[i].role==Role.mafia||players[i].role==Role.godfather||players[i].role==Role.silencer)) {
                        System.out.println(players[i].name + ": " + players[i].role);
                    }
                }
                while (scanner.hasNext()) {
                    String a = scanner.nextLine();
                    if (!a.equals("end_night") && !a.equals("get_game_state")) {
                        night.nightvoting(players, mafiacounter, villagercounter, a, doctor, detective);
                    } else if (a.equals("get_game_state")) {
                        int numberOfMafia = 0;
                        int numberOfVillager = 0;
                        for (int i = 0; i < players.length; i++) {
                            if (players[i].isMafia && players[i].IsAlive) {
                                numberOfMafia++;
                            } else if (!players[i].isMafia && players[i].role != Role.godfather && players[i].IsAlive) {
                                numberOfVillager++;
                            } else if (players[i].role == Role.godfather && players[i].IsAlive) {
                                numberOfMafia++;
                            }
                        }
                        System.out.println("Mafia = " + numberOfMafia);
                        System.out.println("Villager = " + numberOfVillager);
                    } else {
                        day.numberOfDay++;
                        System.out.println("Day " + day.numberOfDay);
                        if (players[night.nightvoting(players,mafiacounter,villagercounter,a,doctor,detective)].IsAlive){
                            System.out.println("mafia tried to kill"+players[night.nightvoting(players,mafiacounter,villagercounter,a,doctor,detective)].name);
                        }
                        else{
                            System.out.println("mafia tried to kill"+players[night.nightvoting(players,mafiacounter,villagercounter,a,doctor,detective)].name);
                            System.out.println(players[night.nightvoting(players,mafiacounter,villagercounter,a,doctor,detective)].name+"was killed");
                        }
                        for (int i=0;i< players.length;i++){
                            if (players[i].isSilenced){
                                System.out.println("Silenced"+players[i].name);
                            }
                        }
                        break;
                    }
                }
            }

        }
        if (mafiacounter==0){
            System.out.println("Villagers won!");
        }
        else if (villagercounter<=mafiacounter){
            System.out.println("Mafia won!");
        }
    }
}
//https://github.com/Zahra-Moterassed/miniproject.git