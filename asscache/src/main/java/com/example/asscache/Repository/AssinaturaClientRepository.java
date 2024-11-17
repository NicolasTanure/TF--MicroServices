package com.example.asscache.Repository;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.asscache.Model.AssinaturaModel;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class AssinaturaClientRepository {

    private HashMap<Long, AssinaturaModel> assinaturas;
    
    @Autowired
    public AssinaturaClientRepository(){
        assinaturas = new HashMap<>();
    }

    public void addAssinatura(Long codass, AssinaturaModel isValid){
        log.info("Assinatura " + codass + " Adicionada");
        assinaturas.put(codass, isValid);
    }

    public void removeAssinatura(Long codass){
        log.info("Assinatura " + codass + " Removida");
        assinaturas.remove(codass);
    }

    public AssinaturaModel getAssinatura(Long codass){
        return assinaturas.get(codass);
    }
    
}
