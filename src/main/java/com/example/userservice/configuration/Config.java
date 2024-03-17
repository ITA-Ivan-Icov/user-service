// MongoDBConfig.java

package com.example.userservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.userservice.repository")
public class Config extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "user-service";
    }

}
