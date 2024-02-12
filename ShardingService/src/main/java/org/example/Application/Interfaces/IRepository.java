package org.example.Application.Interfaces;

import org.example.Entity.KVPair;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface IRepository {
    public void set(KVPair pair) throws IOException, ExecutionException, InterruptedException;
    public String get(String key) throws IOException, ExecutionException, InterruptedException;
}
