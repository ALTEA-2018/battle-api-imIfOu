package com.miage.altea.tp.pokemon_battle_api.service;

import com.miage.altea.tp.pokemon_battle_api.bo.Battle;
import com.miage.altea.tp.pokemon_battle_api.bo.Pokemon;
import com.miage.altea.tp.pokemon_battle_api.bo.Trainer;
import com.miage.altea.tp.pokemon_battle_api.bo.Turn;
import com.miage.altea.tp.pokemon_battle_api.repository.BattleRepository;
import com.miage.altea.tp.pokemon_battle_api.service.exception.BattleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BattleServiceImpl implements BattleService{

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private TrainerService trainerService;

    public Battle createBattle(String trainerOne, String trainerTwo)  {

        Trainer trainer = trainerService.getTrainerByName(trainerOne);
        Trainer opponent = trainerService.getTrainerByName(trainerTwo);

        if(trainer != null && opponent != null){

            Battle battle = new Battle();
            battle.setTrainer(trainer);
            battle.setOpponent(opponent);

            String key = trainerOne + ":" + trainerTwo;
            byte[] bytes = new byte[0];
            try {
                bytes = key.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            UUID uuid = UUID.nameUUIDFromBytes(bytes);
            battle.setUuid(uuid.toString());

            Pokemon firstPokemonTrainer = getCurrentPokemonALive(trainer).get();
            Pokemon firstPokemonOppo = getCurrentPokemonALive(opponent).get();

            if(firstPokemonTrainer.getSpeed()>=firstPokemonOppo.getSpeed()){
                trainer.setNextTurn(false);
                opponent.setNextTurn(true);
            }else{
                trainer.setNextTurn(true);
                opponent.setNextTurn(false);
            }

            battleRepository.createBattle(battle);

            return battle;
        }

        return null;
    }


    public List<Battle> getAllBattles(){
        return battleRepository.getAllBattles();
    }

    public Battle getBattleByUUID(String uuid){ return battleRepository.getBattleByUUID(uuid);}

    public Battle trainerAttack(String uuid, String trainerName){
        Battle battle = getBattleByUUID(uuid);
        Trainer trainer = trainerService.getTrainerByName(trainerName);
        if(battle == null || trainer == null){
            throw new BattleException();
        }

        trainer = getCurrentTrainer(battle,trainer);
        if(!trainer.isNextTurn()){
            throw new BattleException("Not your turn!");
        }

        if(!isGameEndind(battle)){
            Pokemon pokemonAttack = getCurrentPokemonALive(trainer).get();
            Pokemon pokemonTarget = getCurrentPokemonALive(getCurrentOpponent(battle,trainer)).get();
            int attack = attack(pokemonAttack,pokemonTarget);
            if(pokemonTarget.getHp()>attack){
                pokemonTarget.setHp(pokemonTarget.getHp()-attack);
            }else{
                pokemonTarget.setAlive(false);
                pokemonTarget.setKo(true);
            }
            trainer.setNextTurn(!trainer.isNextTurn());
            getCurrentOpponent(battle,trainer).setNextTurn(!getCurrentOpponent(battle,trainer).isNextTurn());
        }

        return battle;
    }

    private boolean isGameEndind(Battle battle){
        return battle.getTrainer().getTeam().isEmpty() || battle.getOpponent().getTeam().isEmpty();
    }

    private Trainer getCurrentTrainer(Battle battle,Trainer trainer){
        return battle.getTrainer().equals(trainer) ? battle.getTrainer() : battle.getOpponent();
    }

    private Trainer getCurrentOpponent(Battle battle,Trainer trainer){
       return battle.getTrainer().equals(trainer) ? battle.getOpponent():battle.getTrainer();
    }

    private Optional<Pokemon> getCurrentPokemonALive(Trainer trainer){
        return trainer.getTeam().stream().filter(Pokemon::isAlive).findFirst();
    }

    private int attack(Pokemon attack,Pokemon def){
        return (((2*attack.getLevel())/5)+2*(attack.getAttack()/def.getDefense()))+2;
    }
}
