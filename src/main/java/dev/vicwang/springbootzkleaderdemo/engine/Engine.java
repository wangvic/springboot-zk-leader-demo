package dev.vicwang.springbootzkleaderdemo.engine;

import dev.vicwang.springbootzkleaderdemo.zookeeper.ZookeeperClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Engine {
    private ZookeeperClient zkClient;
    private boolean isLeader;

    @Autowired
    public Engine(ZookeeperClient zkClient) {
        this.zkClient = zkClient;
        log.info("Engine initialised");
    }

    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent event) {
        try {
            zkClient.start();
            isLeader = zkClient.isLeader();
            log.info("LeaderStatus is {}", isLeader);
            zkClient.subscribe(this::electLeader, "Engine");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void electLeader(boolean isLeader, String traceId) {
        log.warn("current leader status {}, new leader status {}", this.isLeader, isLeader);

        if (this.isLeader == isLeader) {
            log.info("do nothing");
        }

        this.isLeader = isLeader;
        if (isLeader) {
            log.info("became leader");
        } else {
            log.info("became backup");
        }
    }
}
