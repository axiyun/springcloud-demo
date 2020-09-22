package io.x.rms.service.impl;

import io.x.das.entity.DeviceDo;
import io.x.rms.feign.DasFeign;
import io.x.rms.service.RmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RmsServiceImpl implements RmsService {

    @Autowired
    private DasFeign dasFeign;

    @Override
    public DeviceDo getUserDevice(long userId, long devId) {

        return dasFeign.getDevice(devId);
    }

    @Override
    public List<DeviceDo> getUserDevices(long userId) {
        return dasFeign.getDevices();
    }
}
