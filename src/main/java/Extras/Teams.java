package Extras;

import org.apache.commons.lang.IllegalClassException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Teams {
    static List<Team> teams = new ArrayList<>();

    public static Team TEST_TEAM = new Team("Nombre del Team", "Lider", Arrays.asList(
            "Miembro1",
            "Miembro2",
            "Miembro3",
            "Miembro4"));

    public Teams() {
        TEST_TEAM.joinMember("lepepo", false);

        //Si creas un team añadelo a este ArrayList.
        teams.add(TEST_TEAM);
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

        a.add("TEST_TEAM");

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
