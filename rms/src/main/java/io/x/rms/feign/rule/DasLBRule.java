package io.x.rms.feign.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import feign.Request;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DasLBRule extends ZoneAvoidanceRule {

    ServerRedisSimulater simulater = new ServerRedisSimulater();

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        super.initWithNiwsConfig(iClientConfig);
    }

    @Override
    public Server choose(Object key) {
        System.out.println("!!!choose!!!");
        System.out.println(key);

        if (key instanceof Request) {
            Request request = (Request) key;
            Server server = getServerFromRequest(request);
            if (server != null) {
                return server;
            }
        }

        return super.choose(key);
    }

    private long getIdFromRequestUrl(String url) {

        long key = 0L;

        // http://localhost:8020/rms/user/1/device/2
        String[] buf = url.split("/");
        if (buf == null) {
            return key;
        }
        for (int i = 0; i < buf.length; i++) {
            if ("device".equals(buf[i]) && i + 1 < buf.length) {
                key = Integer.parseInt(buf[i + 1]);
                break;
            }
        }

        return key;
    }

    private Server getServerFromRequest(Request request) {
        return chooseById(getIdFromRequestUrl(request.url()));
    }

    private Server chooseById(long id) {

        int machineId = (int) (id % 2);
        String serverId = simulater.index2id(machineId);
        System.out.println(String.format("server id: %s, machine id: %d", serverId, machineId));

        List<Server> servers = getLoadBalancer().getReachableServers();
        System.out.println(servers);
        Optional<Server> server = servers.stream().filter(
                s -> (serverId.equals(s.getId()))).findFirst();
        if (server.isPresent()) {
            System.out.println(String.format("Server(%s) is selected", server.get().getId()));
            return server.get();
        }

        return null;
    }

    class ServerRedisSimulater {
        private Map<Integer, String> index2IdMap;
        private Map<String, Integer> id2IndexMap;

        ServerRedisSimulater() {
            index2IdMap = new ConcurrentHashMap<>();
            id2IndexMap = new ConcurrentHashMap<>();
            bind(0, "192.168.2.56:8031");
            bind(1, "192.168.2.56:8032");
        }

        void bind(int index, String id) {
            index2IdMap.put(index, id);
            id2IndexMap.put(id, index);
        }

        int id2index(String id) {
            return id2IndexMap.get(id);
        }

        String index2id(int index) {
            return index2IdMap.get(index);
        }
    }

}
