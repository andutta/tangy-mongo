package hello;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.ShutdownEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * Created by anshumandutta on 10/18/14.
 */
@Configuration
public class SpringMongoContext extends AbstractMongoConfiguration{

    @Value("${db.server}")
    private String dbServer;



    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(dbServer);
    }



}
