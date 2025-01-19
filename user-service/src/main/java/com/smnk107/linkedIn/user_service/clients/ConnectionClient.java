package com.smnk107.linkedIn.user_service.clients;

import com.smnk107.linkedIn.connections_service.entity.Person;
import com.smnk107.linkedIn.connections_service.entity.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "connections-service",path = "/connections")
public interface ConnectionClient {

    @PostMapping("/core/createNewPerson")
    public ResponseEntity<Person> createNewPerson(@RequestBody UserDto userDto);
}
