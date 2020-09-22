package io.x.das.controller;

import io.x.das.entity.DeviceDo;
import io.x.das.service.DasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/das")
public class DasController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DasController.class);

    @Autowired
    private DasService dasService;

    @Value("${das.name}")
    String dasName;

    @GetMapping("/device/{id}")
    DeviceDo getDevice(@PathVariable long id) {
        LOGGER.info("[{}]getDevice", dasName);
        return dasService.getDevice(id);
    }

    @GetMapping("/device")
    List<DeviceDo> getDevices() {
        return dasService.getDevices();
    }


}
