package com.miage.altea.tp.pokemon_battle_api.controller;

import com.miage.altea.tp.pokemon_battle_api.bo.Battle;
import com.miage.altea.tp.pokemon_battle_api.service.BattleService;
import com.miage.altea.tp.pokemon_battle_api.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/battles")
public class BattleController {

    @Autowired
    private BattleService battleService;

    @Autowired
    private TrainerService trainerService;

    @PostMapping
    public Battle createBattle(@RequestParam String trainer, @RequestParam String opponent){
       return battleService.createBattle(trainer,opponent);
    }

    @GetMapping
    public List<Battle> getAllBattles(){
        return battleService.getAllBattles();
    }

    @GetMapping("/{uuid}")
    public Battle getBattleByUUID(@PathVariable String uuid){
        return battleService.getBattleByUUID(uuid);
    }

    @PostMapping("/{uuid}/{trainerName}/attack")
    public Battle trainerAttack(@PathVariable String uuid,@PathVariable String trainerName){
        return battleService.trainerAttack(uuid,trainerName);
    }

}
