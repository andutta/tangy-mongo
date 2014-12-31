package hello.consul;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Created by anshumandutta on 12/30/14.
 */
@Component
@EnableScheduling
public class Ping {


    public AgentClient agentClient;

    public AgentClient getAgentClient() {
        return agentClient;
    }

    @Autowired
    public void setAgentClient(AgentClient agentClient) {
        this.agentClient = agentClient;
    }

    @Scheduled(fixedRate = 3000)
    public void pingConsul() throws NotRegisteredException{
        agentClient.pass();
    }

    @PreDestroy
    public void deRegisterFromConsul() {
        agentClient.deregister();
    }


}
