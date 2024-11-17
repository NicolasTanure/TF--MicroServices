package com.example.asscache.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TF")
public interface AssinaturaClient {

    @GetMapping("servcad/assinvalida/{codass}")
    boolean isAssinaturaValida(@PathVariable("codass") Long codass);
}
