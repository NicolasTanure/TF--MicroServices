package com.example.asscache.Service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.asscache.FeignClient.AssinaturaClient;
import com.example.asscache.Model.AssinaturaModel;
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
        AssinaturaModel isValid;
        isValid = assinaturaRepository.getAssinatura(codass);
        if(isValid == null){
            isValid = assinaturaClient.isAssinaturaValida(codass);
            assinaturaRepository.addAssinatura(codass, isValid);
        }
        return checkAssinaturaValida(codass);
    }

    public boolean checkAssinaturaValida(Long assinaturaId) {
        AssinaturaModel assinaturaOpt = assinaturaRepository.getAssinatura(assinaturaId);
        if(assinaturaOpt.getFimVigencia().isAfter(LocalDate.now())){
            return true;
        }
        return false;
    }

   
    
}
