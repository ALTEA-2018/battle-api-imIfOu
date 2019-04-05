package com.miage.altea.tp.pokemon_battle_api.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BattleException extends RuntimeException {

    public BattleException(){
        super();
    }

    public BattleException(String message){
        super(message);
    }
}