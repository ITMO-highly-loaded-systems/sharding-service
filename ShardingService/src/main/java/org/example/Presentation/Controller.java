package org.example.Presentation;


import org.example.Application.Interfaces.IService;
import org.example.Entity.KVPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/keys")
public class Controller {

    IService service;

    @Autowired
    Controller(IService service) {
        this.service = service;
    }

    @GetMapping("/get/{key}")
    public String get(@PathVariable String key) throws IOException, ExecutionException, InterruptedException {
        return service.get(key);
    }

    @RequestMapping("/set/{key}/{value}")
    public String set(@PathVariable String key, @PathVariable String value) throws IOException, ExecutionException, InterruptedException {
        KVPair pair = new KVPair(key, value);
        service.set(pair);
        return value;
    }

}
