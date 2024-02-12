package org.example.Entity;

import org.example.Entity.Nodes.INode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class HashModSharding implements IShardingStrategy {
    @Override
    public int getIndex(int size, String key) {
        var index = key.hashCode() % size;
        return index;
    }

    private int getHashCode(String key)
    {
        var res = 0;
        for (int i=0; i<key.length();++i) {
            res+=key.charAt(i)*i;
        }
        return res;
    }
}
