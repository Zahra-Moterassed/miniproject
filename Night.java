public class Night {
    int numberOfNight = 1;

    public int nightvoting(Player[] players, int mafiacounter, int villagercounter, String a, Doctor doctor, Detective detective) {
        String[] z = a.split(" ");
        int othermax = 0;
        int max = 0;
        int maxl = 0;
        int counter = 0;
        for (int i = 0; i < players.length; i++) {
            if (z[0].equals(players[i].name)) {
                if (players[i].role == Role.mafia || players[i].role == Role.doctor || players[i].role == Role.detective
                        || players[i].role == Role.godfather || players[i].role == Role.silencer) {
                    if (players[i].role == Role.mafia || players[i].role == Role.godfather || (players[i].role == Role.silencer
                            && players[i].isCalled)) {
                        for (int k = 0; k < players.length; k++) {
                            if (z[1].equals(players[k].name)) {
                                if (players[k].IsAlive) {
                                    players[k].count++;
                                    break;
                                } else {
                                    System.out.println("votee already dead");
                                    break;
                                }
                            } else if (!z[1].equals(players[k]) && k == players.length - 1) {
                                System.out.println("user not joined");
                            }
                        }

                        for (int l = 0; l < players.length; l++) {
                            if (players[l].count >= max) {
                                if (players[l].count == max && max != 0) {
                                    counter++;
                                    othermax = l;
                                } else if (players[l].count > max) {
                                    maxl = l;
                                    max = players[l].count;
                                }

                            }
                        }
                        if (players[maxl].IsAlive && counter == 0) {
                            if (players[maxl].role == Role.bulletproof) {
                                if (!players[maxl].isOnceKilled) {
                                    players[maxl].isOnceKilled = true;
                                } else {
                                    players[maxl].IsAlive = false;
                                }
                            } else {
                                players[maxl].IsAlive = false;
                            }
                        } else if (!players[maxl].IsAlive) {
                            System.out.println("votee already dead");
                        }
                    } else if (players[i].role == Role.doctor) {
                        doctor.doctor_saving(players, maxl, max, othermax, counter, z);
                    } else if (players[i].role == Role.silencer && !players[i].isCalled) {
                        players[i].isCalled = true;
                        for (int j = 0; j < players.length; j++) {
                            if (z[1].equals(players[j].name)) {
                                players[j].isSilenced = true;
                            }
                        }
                    } else if (players[i].role == Role.detective && !players[i].isCalled) {
                        players[i].isCalled = true;
                        detective.detective_asking(players, z);

                    } else if (players[i].isCalled && players[i].role == Role.detective) {
                        System.out.println("detective has already asked");
                    }
                }
                else {
                        System.out.println("user can not wake up during night");
                    }
                    if (!players[i].IsAlive) {
                        System.out.println("user is dead");
                    }
            }
        }
        return maxl;
    }
}