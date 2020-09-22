package io.x.das.service.impl;

import io.x.das.entity.DeviceDo;
import io.x.das.service.DasService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DasServiceImpl implements DasService {

    private Map<Long, DeviceDo> devMap;

    public DasServiceImpl() {
        devMap = new ConcurrentHashMap<>();

        for (int i = 1; i <= 10; i++) {
            DeviceDo dev = new DeviceDo();
            dev.setDevId(i);
            dev.setDevName(String.format("Dev%03d", i));
            devMap.put(dev.getDevId(), dev);
        }
    }

    @Override
    public DeviceDo getDevice(long devId) {
        return devMap.get(devId);
    }

    @Override
    public List<DeviceDo> getDevices() {
        return new ArrayList(devMap.values());
    }
}
