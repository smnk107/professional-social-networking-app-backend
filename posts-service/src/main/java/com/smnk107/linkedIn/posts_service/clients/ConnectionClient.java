package com.smnk107.linkedIn.posts_service.clients;

import com.smnk107.linkedIn.posts_service.clientEntities.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "connections-service",path = "/connections")
public interface ConnectionClient {

    @GetMapping("/core/firstDegreeConnections/{userId}")
    public ResponseEntity<List<Person>> getFirstDegreeConnections(@PathVariable Long userId);
}
