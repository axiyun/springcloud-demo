package io.x.rms.service.impl;

import com.netflix.config.ConfigurationManager;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import io.x.das.entity.DeviceDo;
import io.x.rms.feign.DasFeign;
import io.x.rms.feign.rule.RibbonClient;
import io.x.rms.service.RmsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RmsServiceImpl implements RmsService {

    private DasFeign dasFeign;

    public RmsServiceImpl() {
        try {
            ConfigurationManager.loadPropertiesFromResources("das-eureka.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }

        dasFeign = Feign.builder()
                .client(RibbonClient.create())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(DasFeign.class, "http://das");
    }

    @Override
    public DeviceDo getUserDevice(long userId, long devId) {

        return dasFeign.getDevice(devId);
    }

    @Override
    public List<DeviceDo> getUserDevices(long userId) {
        return dasFeign.getDevices();
    }
}
