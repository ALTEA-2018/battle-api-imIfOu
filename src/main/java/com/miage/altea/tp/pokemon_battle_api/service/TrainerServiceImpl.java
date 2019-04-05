package com.miage.altea.tp.pokemon_battle_api.service;

import com.miage.altea.tp.pokemon_battle_api.bo.Pokemon;
import com.miage.altea.tp.pokemon_battle_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_battle_api.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService{

    @Autowired
    private PokemonTypeService pokemonTypeService;

    private RestTemplate restTemplate;
    private String trainerServiceUrl;

    @Autowired
    @Qualifier(value = "trainerApiRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    public void setTrainerServiceUrl(String trainerServiceUrl) {
        this.trainerServiceUrl = trainerServiceUrl;
    }

    @Override
    public Trainer getTrainerByName(String name) {
        Trainer trainer = restTemplate.getForObject(trainerServiceUrl+"/trainers/"+name, Trainer.class);
        foundPokemonTypeForTrainer(trainer);
        return trainer;
    }


    private void foundPokemonTypeForTrainer(Trainer trainer){
        for(Pokemon pokemon: trainer.getTeam()){
            PokemonType pokemonType = pokemonTypeService.findPokemonTypesById(pokemon.getPokemonType());
            pokemon.setSpeed(pokemonType.getStats().getSpeed());
            pokemon.setAttack(pokemonType.getStats().getAttack());
            pokemon.setDefense(pokemonType.getStats().getDefense());
            pokemon.setHp(pokemonType.getStats().getHp());
            pokemon.setMaxHp(pokemonType.getStats().getHp());
        }
    }

}
