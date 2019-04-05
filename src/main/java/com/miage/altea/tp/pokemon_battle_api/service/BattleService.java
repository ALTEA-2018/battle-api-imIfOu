package com.miage.altea.tp.pokemon_battle_api.service;

import com.miage.altea.tp.pokemon_battle_api.bo.Battle;

import java.util.List;

public interface BattleService {

    Battle createBattle(String trainerOne, String trainerTwo);

    List<Battle> getAllBattles();

    Battle getBattleByUUID(String uuid);

    Battle trainerAttack(String uuid, String trainerName);
}
