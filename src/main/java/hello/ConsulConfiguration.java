package hello;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PreDestroy;

/**
 * Created by anshumandutta on 12/30/14.
 */
@Configuration
@EnableScheduling
public class ConsulConfiguration {


    @Value("${service.name}")
    private String serviceName;

    @Value("${server.port}")
    private Integer port;

    @Value("${consul.host}")
    private String consulHost;

    @Value("${consul.port}")
    private Integer consulPort;

    @Value("${host.name}")
    private String hostName;

    @Bean
    public String getServiceName() {
        return serviceName;
    }

    private AgentClient  agentClient;

    @Bean
    public AgentClient getConsulClient() {
        try {

            Consul consul = Consul.newClient(consulHost, consulPort); // connect to Consul on localhost
            agentClient = consul.agentClient();

            agentClient.register(port, 3L, serviceName, getServiceId()); // registers with a TTL of 3 seconds
            //agentClient.pass(); // check in with Consul
            return agentClient;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Scheduled(fixedRate = 3000)
    public void pingConsul() {
        try {
            agentClient.pass();
        } catch (NotRegisteredException nre) {
            agentClient.register(port, 3L, serviceName, getServiceId()); // registers with a TTL of 3 seconds
        }
    }

    @PreDestroy
    public void deRegisterFromConsul() {
        agentClient.deregister();
    }

    private String getServiceId(){
        return hostName + ":" + port;
    }
}
