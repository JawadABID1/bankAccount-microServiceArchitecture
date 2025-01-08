package net.abid.customerservice.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties(prefix = "global.params")
@RefreshScope
public class GlobalConfig {
    private String p1;
    private String p2;


    public String getP1(){
        return this.p1;
    }
    public void setP1(String p1) {
        this.p1 = p1;
    }

    public void setP2(String p2){
        this.p2 = p2;
    }
    public String getP2() {
        return this.p2;
    }
}
