package Extras;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Teams {
    static List<Team> teams = new ArrayList<>();

    Team STAFF_TEAM;

    public Teams() {

        try {
            STAFF_TEAM = new Team("Staff Chat", "Mutant17", Arrays.asList("Itz_Antonio_PvP", "Carrotw"
                    , "JohanBigCum", "FoquitaLover", "cPepos", "ItzFel17", "cBaguette17", "wHermes17", "Pepe3012", "xAlexPlayx", "LechugaMC17", "Blackstamp", "Gus_Gus19" // y por alguna razón aqui da error también?
                    , "THESMOL_T", "SkarbyPalace", "Tom_555", "NovaKingdom"));

            Bukkit.getLogger().info("Staff Team creado.");

        } catch (Exception x) {
            x.printStackTrace();

            Bukkit.getLogger().info("Se ha causado un error al crear el team STAFF_TEAM. Error: " + x);
        }
        //Si creas un team añadelo a este ArrayList.

        teams.add(STAFF_TEAM);
    }

    public static boolean isInTeam(Player p) {
        return Teams.get(Teams.getTeamName(p)) != null;
    }

    public static String getTeamName(Player p) {
        Team d = forPlayer(p);

        return d.getName();
    }

    public static Team get (String teamName) {
        Team b = null;
        for (Team a : teams) {
            if (a.getName().equalsIgnoreCase(teamName)) {
               return a;
            }
        }

        return b;
    }
    
    public static List<String> allTeams() {
        //Y tambien a este array
        List<String> a = new ArrayList<>();

        a.add("STAFF_CHAT");

        return a;
    }

    public static Team forPlayer(Player p) {
        Team t = null;
        for (Team a : teams) {
            for (Player b : a.getMembers()) {
                if (b.getName().equalsIgnoreCase(p.getName())) {
                    return a;
                }
            }
        }
        return t;
    }

    public static class Team {
        String teamName;
        Player owner;

        List<String> memberss = new ArrayList<>();

        public Team (String teamName, String owner, List<String> members) {
            this.teamName = teamName;

            this.owner = Bukkit.getPlayer(owner);
            memberss.addAll(members);
            this.memberss.add(owner);
        }
        
        
        public List<Player> getMembers() {
            List<Player> members = new ArrayList<>();
            
            for (String asd : memberss) {

                if (Bukkit.getPlayer(asd) != null) {

                    Player a = Bukkit.getPlayer(asd);

                    members.add(a);
                }
            }

            return members;
        }

        public Player getOwner() {
            return owner;
        }

        public String getName() {
            return teamName;
        }

        public int getSize() {
            return memberss.size() + 1;
        }

        public void joinMember(String name, boolean convertOwner) {
            for (Player d : getMembers()) {
                if (d == Bukkit.getPlayer(name)) {
                    return;
                }
            }
            if (convertOwner) {
                owner = Bukkit.getPlayer(name);
            } else {
                memberss.add(name);
            }
        }

        public void setOwner(String newOwner) {
            if (isInTeam(Bukkit.getPlayer(newOwner)) && Teams.get(Teams.getTeamName(Bukkit.getPlayer(newOwner))) == Teams.get(teamName)) {
                this.owner = Bukkit.getPlayer(newOwner);
            } else {
                throw new NullPointerException("El jugador no se encuentra en el team");
            }
        }

        public void changeName(String newName) {
            this.teamName = newName;
        }

        public void loosePlayer(String playerName) {
            Player p = Bukkit.getPlayer(playerName);

            if (Teams.forPlayer(p) != null) {

                Team team = Teams.get(Teams.getTeamName(p));

                Random random = new Random();

                int d = memberss.size();

                if (team.getOwner() == p) {

                    setOwner(memberss.get(random.nextInt(d)));

                }

                memberss.remove(p.getName());
            }
        }

    }
}
