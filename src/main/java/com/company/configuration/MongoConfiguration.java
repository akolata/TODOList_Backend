package com.company.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Configuration class created to enable auditing mongo documents
 * Created by Aleksander on 01.10.2017.
 */
@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = {"com.company.repository"})
public class MongoConfiguration{
}
