package org.example.Entity;

import org.example.Entity.Nodes.INode;

import java.util.ArrayList;

public interface IShardingStrategy {
    int getIndex(int size, String index);
}
