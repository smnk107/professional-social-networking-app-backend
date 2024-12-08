package com.smnk107.linkedIn.connections_service.controller;

import com.smnk107.linkedIn.connections_service.entity.CreateConnectionDto;
import com.smnk107.linkedIn.connections_service.entity.Person;
import com.smnk107.linkedIn.connections_service.entity.UserDto;
import com.smnk107.linkedIn.connections_service.repository.ConnectionRepository;
import com.smnk107.linkedIn.connections_service.services.ConnectionServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class ConnectionsController {

    private final ConnectionRepository connectionRepository;
    private final ModelMapper modelMapper;
    private final ConnectionServices connectionServices;
    @GetMapping("/getAllPersons")
    ResponseEntity<List<Person>> getAllPersons()
    {
        return connectionServices.findAll();
    }

    @PostMapping("/createNewPerson")
    public ResponseEntity<Person> createNewPerson(@RequestBody UserDto userDto)
    {
        System.out.println("creating now !!!");
        return connectionServices.createNewPerson(userDto);
    }

    @PostMapping("/createConnection")
    public ResponseEntity<String> createConnection(@RequestBody CreateConnectionDto createConnectionDto)
    {
        return ResponseEntity.ok("");
    }

    @PostMapping("/send/{requestedUserId}")
    ResponseEntity<Boolean> sendConnectionRequest(@PathVariable Long requestedUserId)
    {
        return connectionServices.sendConnectionRequest(requestedUserId);


    }

    @PostMapping("/accept/{senderId}")
    ResponseEntity<Boolean> acceptConnectionRequest(@PathVariable Long senderId)
    {
        return connectionServices.acceptConnectionRequest(senderId);

    }


    @PostMapping("/reject/{senderId}")
    ResponseEntity<Boolean>  rejectConnectionRequest(@PathVariable Long senderId)
    {
        return connectionServices.rejectConnectionRequest(senderId);

    }

    @GetMapping("/firstDegreeConnections/{userId}")
    public ResponseEntity<List<Person>> getFirstDegreeConnections(@PathVariable Long userId)
    {
        //Long userId = UserContextHolder.getCurrentUserid();
        List<Person> list = connectionServices.getFirstDegreeConnections(userId);
        return  ResponseEntity.ok(list);
    }

}
