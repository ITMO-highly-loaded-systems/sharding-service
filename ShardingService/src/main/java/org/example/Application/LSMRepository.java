package org.example.Application;

import org.example.Application.Interfaces.IShardingRepository;
import org.example.Entity.Nodes.INode;
import org.example.Entity.KVPair;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

public class LSMRepository implements IShardingRepository {
    private final HttpClient httpClient;
    public LSMRepository() {
        httpClient = HttpClient.newBuilder().build();
    }
    private String get(String key, String ip) throws InterruptedException, ExecutionException, IOException {
        var baseUrl = "http://" + ip;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/keys/get/" + key))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    private void set(String key, String value, String ip) throws InterruptedException, ExecutionException, IOException {
        var baseUrl = "http://" + ip;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/keys/set/" + key + "/" + value))
                .PUT(HttpRequest.BodyPublishers.ofString(""))
                .build();
        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
    @Override
    public void set(KVPair pair, INode node) throws IOException, ExecutionException, InterruptedException {
        set(pair.key, pair.getValue(), node.getIp());
    }

    @Override
    public String get(String key, INode node) throws IOException, ExecutionException, InterruptedException {
        return get(key, node.getIp());
    }
}
