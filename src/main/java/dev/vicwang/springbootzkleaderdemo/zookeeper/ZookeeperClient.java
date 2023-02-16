package dev.vicwang.springbootzkleaderdemo.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ZookeeperClient {
    private CuratorFramework curator;
    private String id;
    private String zookeeperPath;
    private LeaderLatch leaderLatch;

    @Autowired
    public ZookeeperClient(
            @Value("${zookeeper.host}") String zookeeperConnectionString,
            @Value("${zookeeper.id:1}") String id,
            @Value("${zookeeper.path}") String zookeeperPath) {
        this.curator = CuratorFrameworkFactory.newClient(zookeeperConnectionString,
                new ExponentialBackoffRetry(1000, 20));
        this.id = id;
        this.zookeeperPath = zookeeperPath;
    }

    public void start() throws Exception {
        curator.start();

        if (!curator.blockUntilConnected(1, TimeUnit.SECONDS)){
            throw new ZookeeperException();
        }
        leaderLatch = new LeaderLatch(curator, zookeeperPath, id);
        leaderLatch.start();
    }

    public boolean isLeader(){
        return leaderLatch.hasLeadership();
    }

    public void subscribe(ZookeeperConsumer consumer, String traceID) {
        leaderLatch.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                consumer.accept(true, traceID);
            }

            @Override
            public void notLeader() {
                consumer.accept(false, traceID);
            }
        });
    }
}
