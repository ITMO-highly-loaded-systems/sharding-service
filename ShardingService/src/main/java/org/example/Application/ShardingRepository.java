package org.example.Application;

import org.example.Application.Interfaces.IRepository;
import org.example.Application.Interfaces.IShardingRepository;
import org.example.Entity.Nodes.INode;
import org.example.Entity.IShardingStrategy;
import org.example.Entity.KVPair;
import org.example.Entity.Nodes.MasterNode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


@Component
public class ShardingRepository implements IRepository {
    final
    ArrayList<MasterNode> masterNodes;
    final
    IShardingStrategy shardingStrategy;
    final
    IShardingRepository repository;

    public ShardingRepository(ArrayList<MasterNode> masterNodes, IShardingStrategy shardingStrategy, IShardingRepository repository) {
        this.masterNodes = masterNodes;
        this.shardingStrategy = shardingStrategy;
        this.repository = repository;
    }

    @Override
    public void set(KVPair pair) throws IOException, ExecutionException, InterruptedException {
        var index = shardingStrategy.getIndex(masterNodes.size(), pair.key);
        repository.set(pair, masterNodes.get(index));
    }

    @Override
    public String get(String key) throws IOException, ExecutionException, InterruptedException {
        var index = shardingStrategy.getIndex(masterNodes.size(), key);
        var master = masterNodes.get(index);
        var nodes = new ArrayList<INode>();
        for (var replica: master.getReplicas()) {
            nodes.add(replica);
        }
        nodes.add(master);
        index = shardingStrategy.getIndex(nodes.size(), key);
        return repository.get(key, nodes.get(index));
    }
}
