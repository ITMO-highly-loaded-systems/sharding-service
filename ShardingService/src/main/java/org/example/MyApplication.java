package org.example;

import org.example.Application.Interfaces.IShardingRepository;
import org.example.Application.LSMRepository;
import org.example.Application.RedisRepository;
import org.example.Entity.Nodes.MasterNode;
import org.example.Entity.Nodes.ReplicaNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


@EnableScheduling
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class);
    }

    @Bean
    public ArrayList<MasterNode> createNodes() throws IOException {
        FileReader reader = new FileReader("src/main/resources/application.properties");
        Properties mainProp = new Properties();
        mainProp.load(reader);
        String configFile = mainProp.getProperty("configFile");
        FileReader reader1 = new FileReader(configFile);
        Properties nodesProp = new Properties();
        nodesProp.load(reader1);

        var res = new ArrayList<MasterNode>();
        int mastersCount = Integer.parseInt(nodesProp.getProperty("mastersCount"));
        for (int i = 0; i < mastersCount; ++i) {
            String pref = "master." + i;
            String Ip = nodesProp.getProperty(pref);
            int replicasCount = Integer.parseInt(nodesProp.getProperty(pref+".replicasCount"));
            var list = new ArrayList<ReplicaNode>();
            pref+=".replica";
            for (int j = 0; j<replicasCount;++j)
            {
                String replicasIp = nodesProp.getProperty(pref + "." + j);
                list.add(new ReplicaNode(replicasIp));
            }
            res.add(new MasterNode(Ip, list));
        }
        reader1.close();
        reader.close();
        return res;
    }


    @Bean
    public IShardingRepository createRepository() throws IOException {
        FileReader reader = new FileReader("src/main/resources/application.properties");
        Properties mainProp = new Properties();
        mainProp.load(reader);
        String type = mainProp.getProperty("type");
        IShardingRepository res;
        switch (type)
        {
            case "redis":
            {
                res = new RedisRepository();
                break;
            }
            default:
            {
                res = new LSMRepository();
            }
        }
        reader.close();
        return res;
    }

}
