package org.example.Application.Interfaces;

import org.example.Entity.Nodes.INode;
import org.example.Entity.KVPair;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface IShardingRepository {
    public void set(KVPair pair, INode node) throws IOException, ExecutionException, InterruptedException;
    public String get(String key, INode node) throws IOException, ExecutionException, InterruptedException;
}
