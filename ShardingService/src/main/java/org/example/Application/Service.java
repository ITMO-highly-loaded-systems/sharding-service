package org.example.Application;

import org.example.Application.Interfaces.IRepository;
import org.example.Application.Interfaces.IService;
import org.example.Entity.KVPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Component
@org.springframework.stereotype.Service
public class Service implements IService {
    private final IRepository repository;

    @Autowired
    public Service(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public void set(KVPair pair) throws IOException, ExecutionException, InterruptedException {
        repository.set(pair);

    }

    @Override
    public String get(String key) throws IOException, ExecutionException, InterruptedException {
        return repository.get(key);
    }
}
