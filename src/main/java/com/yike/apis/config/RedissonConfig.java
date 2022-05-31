package com.yike.apis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 *  程序化配置 Redisson
 */
@Configuration
public class RedissonConfig {
    @Value("${redisson.address}")
    public String address;

    @Value("${redisson.password}")
    public String password;

    //All use of Redisson is through the RedissonClient object
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(){
        //1、Create a configuration

        Config config = new Config();
        //Cluster Mode Configuration
//        config.useClusterServers().addNodeAddress(address);
        //Single-node mode
        // Pay attention to：Redis url should start with redis:// or rediss:// (for SSL connection)
        config.useSingleServer().setAddress(address);
        //Setting connection Password
        config.useSingleServer().setPassword(password);
        config.useSingleServer().setConnectionMinimumIdleSize(10);
        config.useSingleServer().setPingConnectionInterval(3000);//**This must be set to redisson to resolve the previous bug timeout problem key *****
        config.useSingleServer().setTimeout(3000);
        config.useSingleServer().setRetryInterval(3000);
        config.useSingleServer().setDatabase(3);
        //2、Create the RedissonClient instance from Config
        RedissonClient redissonClient = Redisson.create(config);

        return redissonClient;
    }

}
