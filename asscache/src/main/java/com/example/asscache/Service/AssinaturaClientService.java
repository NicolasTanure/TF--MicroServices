package com.example.asscache.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.asscache.FeignClient.AssinaturaClient;
import com.example.asscache.Repository.AssinaturaClientRepository;

@Service
public class AssinaturaClientService {

    private final AssinaturaClient assinaturaClient;
    private final AssinaturaClientRepository assinaturaRepository;

    @Autowired
    public AssinaturaClientService(AssinaturaClient assinaturaClient, AssinaturaClientRepository assinaturaRepository) {
        this.assinaturaClient = assinaturaClient;
        this.assinaturaRepository = assinaturaRepository;
    }


    public boolean isAssinaturaValida(Long codass){
        Boolean isValid;
        isValid = assinaturaRepository.getAssinatura(codass);
        if(isValid == null){
            isValid = assinaturaClient.isAssinaturaValida(codass);
            assinaturaRepository.addAssinatura(codass, isValid);
        }
        return isValid;
    }
    
}
