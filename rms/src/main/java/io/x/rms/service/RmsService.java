package io.x.rms.service;

import io.x.das.entity.DeviceDo;

import java.util.List;

public interface RmsService {

    DeviceDo getUserDevice(long userId, long devId);

    List<DeviceDo> getUserDevices(long userId);
}
