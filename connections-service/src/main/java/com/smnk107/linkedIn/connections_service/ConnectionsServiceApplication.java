package com.smnk107.linkedIn.connections_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jAuditing
@EnableNeo4jRepositories
public class ConnectionsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectionsServiceApplication.class, args);
	}

}
