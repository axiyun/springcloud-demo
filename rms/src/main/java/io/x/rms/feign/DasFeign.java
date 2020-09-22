package io.x.rms.feign;

import io.x.das.entity.DeviceDo;
import io.x.rms.feign.rule.DasLoadBalancerConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="das", configuration= DasLoadBalancerConfiguration.class)
public interface DasFeign {

    @RequestMapping(value="/das/device", method = RequestMethod.GET)
    List<DeviceDo> getDevices();

    @RequestMapping(value="/das/device/{id}", method = RequestMethod.GET)
    DeviceDo getDevice(@PathVariable long id);

}
