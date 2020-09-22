package io.x.das.service;

import io.x.das.entity.DeviceDo;

import java.util.List;

public interface DasService {

    DeviceDo getDevice(long devId);

    List<DeviceDo> getDevices();
}
