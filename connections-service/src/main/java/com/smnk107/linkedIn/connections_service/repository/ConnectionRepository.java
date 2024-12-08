package com.smnk107.linkedIn.connections_service.repository;

import com.smnk107.linkedIn.connections_service.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends Neo4jRepository<Person,Long> {


    @Query("MATCH (n:Person) RETURN n LIMIT 10")
    List<Person> findById();

    @Query("MATCH (personA:Person) -[:CONNECTED_TO]- (personB:Person) " +
            "WHERE personA.userId = $userId " +
            "RETURN personB")
    List<Person> getFirstDegreeConnections(Long userId);

    @Query("Match (p1:Person)-[R:REQUESTED_TO]-> (p2:Person) "+
    "where p1.userId=$requester AND p2.userId=$requested "+
    "RETURN COUNT(R)>0 ")
    Boolean isRequested(Long requested,Long requester);

    @Query("Match (p1:Person)-[R:CONNECTED_TO]- (p2:Person) "+
    "where p1.userId=$requester AND p2.userId=$requested "+
    "RETURN COUNT(R)>0 ")
    Boolean isConnected(Long requested,Long requester);

    @Query("Match (p1:Person)\n" +
            "match (p2:Person)\n" +
            "where p1.userId=$sender and p2.userId=$receiver\n" +
            "merge (p1)-[r:REQUESTED_TO]->(p2)")
    Boolean addConnectionRequest(Long sender, Long receiver);

    @Query("Match (p1:Person)-[r:REQUESTED_TO]->(p2:Person)\n" +
            "where p1.userId=$sender and p2.userId=$receiver\n" +
            "delete r")
    Boolean deleteConnectionRequest(Long sender, Long receiver);

    @Query("Match (p1:Person) \n" +
            "match (p2:Person)\n" +
            "where p1.userId=$sender and p2.userId=$receiver\n" +
            "merge (p1)-[r:CONNECTED_TO]->(p2)")
    Boolean acceptConnectionRequest(Long sender, Long receiver);

//    @
//    Boolean rejectConnectionRequest(Long userId,Long senderId)


//
//    Optional<Person> findByName();
//
//    @Query("MATCH (P1:PERSON)-[CONNECTED_TO]-(P2:PERSON) " +
//            "WHERE P1.USERID = $userId " +
//            "RETURN P2"
//    )
 //   Optional<List<Person>> getFirstDegreeConnections();
}
