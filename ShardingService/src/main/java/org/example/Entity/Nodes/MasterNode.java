package org.example.Entity.Nodes;

import java.util.ArrayList;

public class MasterNode implements INode {

    private ArrayList<ReplicaNode> replicas;
    private String ip;

    public MasterNode(String ip, ArrayList<ReplicaNode> replicas) {
        this.ip = ip;
        this.replicas = replicas;
    }

    public ArrayList<ReplicaNode> getReplicas() {
        return replicas;
    }

    public String getIp() {
        return ip;
    }
}
