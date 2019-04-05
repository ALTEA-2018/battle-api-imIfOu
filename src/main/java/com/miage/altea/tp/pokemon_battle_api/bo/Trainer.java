package com.miage.altea.tp.pokemon_battle_api.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Trainer {

    private String name;


    private List<Pokemon> team;

    private boolean nextTurn;

    public Trainer() {}

    public Trainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("team")
    public List<Pokemon> getTeam() {
        return team;
    }

    @JsonProperty("pokemons")
    public void setTeam(List<Pokemon> team) { this.team = team; }

    public boolean isNextTurn() {return nextTurn; }

    public void setNextTurn(boolean nextTurn) { this.nextTurn = nextTurn;}
}