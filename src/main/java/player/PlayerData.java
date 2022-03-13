package player;

import Listeners.MaestriaExp;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

import java.util.UUID;

import static Utilities.Format.PREFIX;
import static Utilities.Format.format;

public class PlayerData {
    private String playerName;
    private UUID uuid;

    private int temperature, masteryLevel, masteryExp, immunity, extraHealth, negativeHealth;

    public PlayerData(String playerName, UUID id) {
        this.playerName = playerName;
        this.uuid = (id == null ? Bukkit.getOfflinePlayer(playerName).getUniqueId() : id);

        Player p = Bukkit.getPlayer(uuid);
        if (p != null) loadData(p);
    }

    private void loadData(Player p) {
        this.temperature = getOrAddData(p, "temperatura", PersistentDataType.INTEGER, 30);
        this.masteryLevel = getOrAddData(p, "maestrialvl", PersistentDataType.INTEGER, 1);
        this.masteryExp = getOrAddData(p, "maestriaexp", PersistentDataType.INTEGER, 0);
        //this.immunity = getOrAddData(p, "inmunity", PersistentDataType.INTEGER, 0);
        this.extraHealth = getOrAddData(p, "maestry_health", PersistentDataType.INTEGER, 0);
        this.negativeHealth = getOrAddData(p, "negative_health", PersistentDataType.INTEGER, 0);

        this.immunity = 0;

        printDebug(p);
    }

    public void saveData(Player p) {
        setData(p, "temperatura", PersistentDataType.INTEGER, this.temperature);
        setData(p, "maestrialvl", PersistentDataType.INTEGER, this.masteryLevel);
        setData(p, "maestriaexp", PersistentDataType.INTEGER, this.masteryExp);
        //setData(p, "inmunity", PersistentDataType.INTEGER, this.immunity);
        setData(p, "maestry_health", PersistentDataType.INTEGER, this.extraHealth);
        setData(p, "negative_health", PersistentDataType.INTEGER, this.negativeHealth);

        printDebug(p);
    }

    private void printDebug(Player p) {
        Bukkit.broadcastMessage("-------[ DEBUG DATA " + getName() + " ]-------");
        Bukkit.broadcastMessage("Temperatura: " + temperature + " | " + getData(p, "temperatura", PersistentDataType.INTEGER));
        Bukkit.broadcastMessage("Maestria Level: " + masteryLevel + " | " + getData(p, "maestrialvl", PersistentDataType.INTEGER));
        Bukkit.broadcastMessage("Maestria Exp: " + masteryExp + " | " + getData(p, "maestriaexp", PersistentDataType.INTEGER));
        Bukkit.broadcastMessage(" ");
        Bukkit.broadcastMessage(" ");
        Bukkit.broadcastMessage(" ");
    }

    public void tick() {

    }

    private <T, Z> Z setData(Player p, String id, PersistentDataType<T, Z> type, Z value) {
        PersistentDataContainer data = p.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(TLL2.getInstance(), id);
        data.set(key, type, value);

        return value;
    }

    private <T, Z> Z getOrAddData(Player p, String id, PersistentDataType<T, Z> type, Z defValue) {
        PersistentDataContainer data = p.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(TLL2.getInstance(), id);

        if (!data.has(key, type)) {
            data.set(key, type, defValue);
        }

        return data.get(key, type);
    }

    private <T, Z> Z getData(Player p, String id, PersistentDataType<T, Z> type) {
        PersistentDataContainer data = p.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(TLL2.getInstance(), id);

        return data.get(key, type);
    }

    private NamespacedKey key(String id) {
        return NamespacedKey.minecraft(id);
    }

    public String getName() {
        return playerName;
    }

    public UUID getID() {
        return uuid;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getImmunity() {
        return immunity;
    }

    public void setImmunity(int immunity) {
        this.immunity = immunity;
    }

    public int getExtraHealth() {
        return extraHealth;
    }

    public void setExtraHealth(int extraHealth) {
        this.extraHealth = extraHealth;
    }

    public int getNegativeHealth() {
        return negativeHealth;
    }

    public void setNegativeHealth(int negativeHealth) {
        this.negativeHealth = negativeHealth;
    }

    //
    public int getMasteryLevel() {
        return masteryLevel;
    }
    public int getMasteryExp() {
        return masteryExp;
    }

    public int levelUpMastery() {
        return this.levelUpMastery(1);
    }

    public int levelUpMastery(int levelAdd) {
        return this.setMasteryLevel(this.masteryLevel+(Math.max(levelAdd, 1)));
    }

    public int setMasteryLevel(int masteryLevel) {
        if (masteryLevel >= 30) return this.masteryLevel = 30;
        if (masteryLevel <= 0) return this.masteryLevel = 1;
        this.masteryLevel = masteryLevel;
        this.masteryExp = 0;

        return this.masteryLevel;
    }

    public void setMasteryExp(int masteryExp) {
        this.masteryExp = Math.max(masteryExp, 0);
        checkMasteryLevel(Bukkit.getPlayer(this.uuid), 0);
    }

    public void checkMasteryLevel(Player p, int xp) {
        if (p == null || xp <= 0) return;

        this.masteryExp+=xp;
        if (!hasReachedLevel30() && this.masteryExp >= MaestriaExp.getInstance().maxExpNecesary(this.masteryLevel)) {
            int level = getMasteryLevel();
            int newLevel = levelUpMastery();

            p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 10.0F, 2.0F);
            p.playSound(p.getLocation(),Sound.ENTITY_PLAYER_LEVELUP, 10.0F,-1.0F);

            p.sendTitle(format("&b¡NUEVO NIVEL!"), format("&8" + level + "&c -> &7" + newLevel));

            Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendMessage(format("&3MAESTRIA &8> &c&l" + p.getName() + "&7 ha aumentado su nivel. &e" + level + "&8 >> &6" + newLevel));
            });
            int ehp = getExtraHealth();

            if(getMasteryLevel() == 1) {
                p.sendMessage(MaestriaExp.hp_plus);
                setExtraHealth(ehp + 2);
            }else if(getMasteryLevel() == 2) {
                p.sendMessage(MaestriaExp.hp_plus);
                setExtraHealth(ehp + 2);
            }else if(getMasteryLevel() == 3) {
                p.sendMessage(MaestriaExp.att_plus);
                p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + 0.60);
            }else if(getMasteryLevel() == 4) {
                p.sendMessage(MaestriaExp.def_plus);
                p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue() + 0.50);
            }else if(getMasteryLevel() == 5) {
                p.sendMessage(MaestriaExp.hp_plus);
                setExtraHealth(ehp + 2);
            }else if(getMasteryLevel() == 6) {
                p.sendMessage(MaestriaExp.att_plus);
                p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + 0.60);
            }else if(getMasteryLevel() == 7) {
                p.sendMessage(MaestriaExp.def_plus);
                p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue() +0.50);
            }else if(getMasteryLevel() == 8) {
                p.sendMessage(MaestriaExp.hp_plus);
                setExtraHealth(ehp + 2);
            }else if(getMasteryLevel() == 9) {
                p.sendMessage(MaestriaExp.att_plus);
                p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + 0.60);
            }else if(getMasteryLevel() == 10) {
                p.sendMessage(format("&3MAESTRIA &8> Has recibido un Buff de Vision Nocturna Permanente!"));
                p.sendMessage(format("&3MAESTRIA &8> &e&l¡PELIGRO! Ahora picar puede llamar la atencion de criaturas hostiles"));
            }else if(getMasteryLevel() == 11) {
                p.sendMessage(MaestriaExp.hp_plus);
                setExtraHealth(ehp + 2);
            }else if(getMasteryLevel() == 12) {
                p.sendMessage(MaestriaExp.att_plus);
                p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + 0.60);
            }else if(getMasteryLevel() == 13) {
                p.sendMessage(MaestriaExp.def_plus);
                p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue() + 0.50);
            }else if(getMasteryLevel() == 14) {
                p.sendMessage(MaestriaExp.hp_plus);
                setExtraHealth(ehp + 2);
            }else if(getMasteryLevel() == 15) {
                p.sendMessage(format("&3MAESTRIA &8> Llegaste al Nivel 15, &el¡Felicidades! &c&lAhora afronta tu destino sin mas recompensas"));
            }else if(getMasteryLevel() == 20) {
                p.sendMessage(format("&3MAESTRIA &8> Has recibido un Buff de Haste I Permanente!"));
                p.sendMessage(format("&3MAESTRIA &8> &e&l¡PELIGRO! Ahora hay muchas mas criaturas dispuestas a acabar contigo!"));
            }else if(getMasteryLevel() == 30) {
                p.sendMessage(format("&3MAESTRIA &8> Has recibido un Buff de Haste II Permanente!"));
                p.sendMessage(format("&3MAESTRIA &8> &e&l¡PELIGRO! El Cosmos esta enfadado contigo!"));
                p.sendMessage(PREFIX,format("&e&lFelicidades por llegar al Nivel 30, no hay mas camino para tu trabajo de Mineria, ¡Buen Trabajo!"));
            }
        }
    }

    public boolean hasReachedLevel30() {
        return this.masteryLevel >= 30;
    }

    public boolean hasReachedLevel20() {
        return this.masteryLevel >= 20 && !hasReachedLevel30();
    }

    public boolean hasReachedLevel10() {
        return this.masteryLevel >= 10 && !hasReachedLevel20();
    }
}
