package com.miage.altea.tp.pokemon_battle_api.repository;

import com.miage.altea.tp.pokemon_battle_api.bo.Battle;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BattleRepositoryImpl implements BattleRepository{

    private Map<String,Battle> battles = new HashMap<>();

    public void createBattle(Battle battle){
        battles.put(battle.getUuid(),battle);
    }

    public Battle getBattleByUUID(String uuid){ return battles.get(uuid); }

    @Override
    public List<Battle> getAllBattles() {
        return new LinkedList<>(battles.values());
    }

}
