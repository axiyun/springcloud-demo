package io.x.rms.feign.rule;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.openfeign.ribbon.FeignLoadBalancer;

public class DasFeignLoadBalanceRule extends ZoneAwareLoadBalancer {

    FeignLoadBalancer k;
    @Override
    public Server choose(Object key) {

        return super.choose(key);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        super.setLoadBalancer(lb);
    }
}