package com.miage.altea.tp.pokemon_battle_api.bo;

public class Pokemon {

    private int id;

    private int pokemonType;

    private PokemonType type;

    private int level;

    private int maxHp;

    private int attack;

    private int defense;

    private int speed;

    private int hp;

    private boolean ko = false;

    private boolean alive = true;

    public Pokemon() {
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isKo() { return ko;}

    public void setKo(boolean ko) { this.ko = ko; }

    public boolean isAlive() { return alive; }

    public void setAlive(boolean alive) { this.alive = alive;}

    public int getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(int pokemonType) {
        this.pokemonType = pokemonType;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }
}
