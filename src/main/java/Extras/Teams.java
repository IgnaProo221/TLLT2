package Extras;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Teams {
    static List<Team> teams = new ArrayList<>();

    public static Team TEST_TEAM = new Team("Nombre del Team", "Lider", Arrays.asList("Miembro1", "Test2"));
    public static Team STAFF_TEAM = new Team("Staff Chat","Mutant17", Arrays.asList("Itz_Antonio_PvP", "Carrotw"
    ,"JohanBigCum","FoquitaLover","cPepos","ItzFel17","cBaguette17","wHermes17","Pepe3012","xAlexPlayx","LechugaMC17","Blackstamp","Gus_Gus19"
            ,"THESMOL_T","SkarbyPalace","Tom_555","NovaKingdom"));

    public Teams() {
        TEST_TEAM.joinMember("lepepo", false);
        STAFF_TEAM.joinMember("Mutant17",true);
        STAFF_TEAM.joinMember("Itz_Antonio_PvP",false);
        STAFF_TEAM.joinMember("Carrotw",false);
        STAFF_TEAM.joinMember("JohanBigCum",false);
        STAFF_TEAM.joinMember("FoquitaLover",false);
        STAFF_TEAM.joinMember("cPepos",false);
        STAFF_TEAM.joinMember("ItzFel17",false);
        STAFF_TEAM.joinMember("cBaguette17",false);
        STAFF_TEAM.joinMember("wHermes17",false);
        STAFF_TEAM.joinMember("Pepe3012",false);
        STAFF_TEAM.joinMember("xAlexPlayx",false);
        STAFF_TEAM.joinMember("LechugaMC17",false);
        STAFF_TEAM.joinMember("Blackstamp",false);
        STAFF_TEAM.joinMember("Gus_Gus19",false);
        STAFF_TEAM.joinMember("THESMOL_T",false);
        STAFF_TEAM.joinMember("SkarbyPalace",false);
        STAFF_TEAM.joinMember("Tom_555",false);
        STAFF_TEAM.joinMember("NovaKingdom",false);


        teams.add(TEST_TEAM);
        teams.add(STAFF_TEAM);
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
        List<String> a = new ArrayList<>();
        a.add("TEST_TEAM");
        a.add("STAFF_TEAM");
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

        List<String> memberss;

        public Team (String teamName, String owner, List<String> members) {
            this.teamName = teamName;

            this.owner = Bukkit.getPlayer(owner);
            this.memberss = members;
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
            if (convertOwner) {
                owner = Bukkit.getPlayer(name);
            } else {
                memberss.add(name);
            }
        }

        public void changeName(String newName) {
            this.teamName = newName;
        }

    }
}