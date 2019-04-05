package com.miage.altea.tp.pokemon_battle_api.repository;

import com.miage.altea.tp.pokemon_battle_api.bo.Battle;

import java.util.List;
import java.util.Optional;

public interface BattleRepository {

    void createBattle(Battle battle);
    Battle getBattleByUUID(String uuid);
    List<Battle> getAllBattles();
}
