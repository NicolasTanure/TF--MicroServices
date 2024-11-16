package com.example.asscache.Repository;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssinaturaClientRepository {

    private HashMap<Long, Boolean> assinaturas;
    
    @Autowired
    public AssinaturaClientRepository(){
        assinaturas = new HashMap<>();
    }

    public void addAssinatura(Long codass, Boolean isValid){
        assinaturas.put(codass, isValid);
    }

    public void removeAssinatura(Long codass, Boolean isValid){
        assinaturas.remove(codass);
    }

    public Boolean getAssinatura(Long codass){
        return assinaturas.get(codass);
    }
    
}
