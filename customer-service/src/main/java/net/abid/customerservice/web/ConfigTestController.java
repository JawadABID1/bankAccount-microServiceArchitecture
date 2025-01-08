package net.abid.customerservice.web;


import net.abid.customerservice.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestController {
//    the first way to retrieve a config settings (injection one by one)
    @Value("${customer.params.p1}")
    private String localP1;
    @Value("${customer.params.p2}")
    private String localP2;
    @Value("${global.params.p1}")
    private String globalP1;
    @Value("${global.params.p2}")
    private String globalP2;

//    the second way to retrieve config settings(dependence by injection )
    @Autowired
    private GlobalConfig   globalConfig;


    @GetMapping("/configTest")
    public Map<String, String> configTest(){
        return Map.of("localP1", localP1, "localP2", localP2,
                "globalP1", globalP1, "globalP2", globalP2);
    }
    @GetMapping("/globalConfig")
    public GlobalConfig globalConfig(){
        return globalConfig;
    }

}
