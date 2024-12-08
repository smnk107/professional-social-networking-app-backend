package com.smnk107.notification_service.clients;

import com.smnk107.notification_service.clientEntities.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "connections-service",path = "/connections")
public interface ConnectionClient {

    @GetMapping("/core/firstDegreeConnections/{userId}")
    public ResponseEntity<List<Person>> getFirstDegreeConnections(@PathVariable Long userId);
}
