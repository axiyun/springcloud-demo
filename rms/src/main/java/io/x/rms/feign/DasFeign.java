package io.x.rms.feign;

import feign.Param;
import feign.RequestLine;
import io.x.das.entity.DeviceDo;

import java.util.List;

public interface DasFeign {

    @RequestLine("GET /das/device")
    List<DeviceDo> getDevices();

    @RequestLine("GET /das/device/{id}")
    DeviceDo getDevice(@Param("id") long id);

}
