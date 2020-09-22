package io.x.rms.controller;

import io.x.das.entity.DeviceDo;
import io.x.rms.service.RmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rms")
public class RmsController {

    @Autowired
    private RmsService rmsService;

    @GetMapping("/user/{uid}/device/{did}")
    DeviceDo getUserDevice(@PathVariable long uid, @PathVariable long did) {
        return rmsService.getUserDevice(uid, did);
    }

    @GetMapping("/user/{uid}/device")
    List<DeviceDo> getDevices(@PathVariable long uid) {
        return rmsService.getUserDevices(uid);
    }

}
