package com.smnk107.linkedIn.connections_service.connections_service.repository;

import com.smnk107.linkedIn.connections_service.connections_service.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface connectionRepository extends Neo4jRepository<Person,Long> {

    @Override
    Optional<Person> findById(Long id);

    Optional<Person> findByName();

    @Query("MATCH (P1:PERSON)-[CONNECTED_TO]-(P2:PERSON) " +
            "WHERE P1.USERID = $userId " +
            "RETURN P2"
    )
    Optional<List<Person>> getFirstDegreeConnections(Long userId);
}
