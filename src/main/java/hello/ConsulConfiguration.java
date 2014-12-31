package hello;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by anshumandutta on 12/30/14.
 */
@Configuration
public class ConsulConfiguration {


    @Value("${service.name}")
    private String serviceName;

    @Value("${server.port}")
    private Integer port;

    @Value("${consul.host}")
    private String consulHost;

    @Value("${consul.port}")
    private Integer consulPort;

    @Bean
    public String getServiceName() {
        return serviceName;
    }

    @Bean
    public AgentClient getConsulClient() {
        try {

            Consul consul = Consul.newClient(consulHost, consulPort); // connect to Consul on localhost
            AgentClient agentClient = consul.agentClient();

            String serviceId = "1";

            agentClient.register(port, 3L, serviceName, serviceId); // registers with a TTL of 3 seconds
            agentClient.pass(); // check in with Consul
            return agentClient;

        } catch (NotRegisteredException ne){
            ne.printStackTrace();
        }
        return null;
    }
}
