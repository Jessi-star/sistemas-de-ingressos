package com.compass.ms_event_manager.client;

import com.compass.ms_event_manager.dto.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {
    @GetMapping("/{cep}/json")
    Address getAddressByCep(@PathVariable("cep") String cep);
}
