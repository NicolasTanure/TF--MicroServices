package com.example.asscache.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.asscache.Model.AssinaturaModel;

@FeignClient(name = "TF")
public interface AssinaturaClient {

    @GetMapping("servcad/buscar/{id}")
    AssinaturaModel isAssinaturaValida(@PathVariable("id") Long codass);
}
