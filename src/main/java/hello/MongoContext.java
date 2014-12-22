package hello;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by anshumandutta on 10/18/14.
 */
@Configuration
public class MongoContext {

    @Value("${db.server}")
    private String dbServer;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
           return new SimpleMongoDbFactory(new MongoClient(), dbServer);

    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
