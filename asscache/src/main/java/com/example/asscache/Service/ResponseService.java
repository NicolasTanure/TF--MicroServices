package com.example.asscache.Service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.asscache.Repository.AssinaturaClientRepository;

@Component
public class ResponseService {
    public static final String QUEUENAME = "conversions.v1.conversion-request.save-history";
    private AssinaturaClientRepository assinaturaRepository;

    @Autowired
    public ResponseService(AssinaturaClientRepository assinaturaRepository){
        this.assinaturaRepository = assinaturaRepository;
    }
    
    @RabbitListener(queues = QUEUENAME)
    public void updateCache(Long codass){
        assinaturaRepository.removeAssinatura(codass);
    }
}
