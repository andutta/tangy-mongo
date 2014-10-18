package hello;

import com.mongodb.MongoClient;
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
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
           return new SimpleMongoDbFactory(new MongoClient(), "localhost");

    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
