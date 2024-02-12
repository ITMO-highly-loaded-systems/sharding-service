package org.example.Entity.Nodes;

public class ReplicaNode implements INode{
    public ReplicaNode(String ip) {
        this.ip = ip;
    }

    private String ip;
    @Override
    public String getIp() {
        return ip;
    }
}
