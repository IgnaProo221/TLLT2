package player;

import Listeners.MaestriaExp;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

import java.util.UUID;

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
        this.immunity = getOrAddData(p, "inmunity", PersistentDataType.INTEGER, 0);
        this.extraHealth = getOrAddData(p, "maestry_health", PersistentDataType.INTEGER, 0);
        this.negativeHealth = getOrAddData(p, "negative_health", PersistentDataType.INTEGER, 0);
    }

    public void saveData(Player p) {
        setData(p, "temperatura", PersistentDataType.INTEGER, this.temperature);
        setData(p, "maestrialvl", PersistentDataType.INTEGER, this.masteryLevel);
        setData(p, "maestriaexp", PersistentDataType.INTEGER, this.masteryExp);
        setData(p, "inmunity", PersistentDataType.INTEGER, this.immunity);
        setData(p, "maestry_health", PersistentDataType.INTEGER, this.extraHealth);
        setData(p, "negative_health", PersistentDataType.INTEGER, this.negativeHealth);
    }

    public void tick() {

    }

    private <T, Z> Z setData(Player p, String id, PersistentDataType<T, Z> type, Z value) {
        PersistentDataContainer data = p.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(TLL2.getInstance(), id);
        data.set(key, type, value);

        return value;
    }

    private <T, Z> Z  getOrAddData(Player p, String id, PersistentDataType<T, Z> type, Z defValue) {
        PersistentDataContainer data = p.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(TLL2.getInstance(), id);

        if (!data.has(key, type)) {
            data.set(key, type, defValue);
        }

        return data.get(key, type);
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
        if (masteryLevel > 30) return this.masteryLevel;
        this.masteryLevel = masteryLevel;
        this.masteryExp = 0;

        return this.masteryLevel;
    }

    public void setMasteryExp(int masteryExp) {
        this.masteryExp = Math.max(masteryExp, 0);
        checkMasteryLevel();
    }

    private void checkMasteryLevel() {
        if (this.masteryExp >= MaestriaExp.getInstance().maxExpNecesary(this.masteryLevel)) {
            this.levelUpMastery();
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
