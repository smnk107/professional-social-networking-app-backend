package com.smnk107.linkedIn.connections_service.services;

import com.smnk107.linkedIn.connections_service.auth.UserContextHolder;
import com.smnk107.linkedIn.connections_service.entity.Person;
import com.smnk107.linkedIn.connections_service.entity.UserDto;
import com.smnk107.linkedIn.connections_service.event.AcceptRequestEvent;
import com.smnk107.linkedIn.connections_service.event.RejectRequestEvent;
import com.smnk107.linkedIn.connections_service.event.SendRequestEvent;
import com.smnk107.linkedIn.connections_service.repository.ConnectionRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class ConnectionServices {

    private final ConnectionRepository connectionRepository;
    private final ModelMapper modelMapper;

    private final KafkaTemplate<Long, AcceptRequestEvent> acceptRequestEventKafkaTemplate;
    private final KafkaTemplate<Long, RejectRequestEvent> rejectRequestEventKafkaTemplate;
    private final KafkaTemplate<Long, SendRequestEvent> sendRequestEventKafkaTemplate;

    public ResponseEntity<Person> createNewPerson(UserDto userDto)
    {
        try {
            Person person = Person.builder()
                    .userId(userDto.userId)
                    .name(userDto.name)
                    .build();//modelMapper.map(userDto,Person.class);
            return ResponseEntity.ok(connectionRepository.save(person));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ResponseEntity.ok(null);
    }

    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok(connectionRepository.findAll());
    }

    public List<Person> getFirstDegreeConnections(Long userId) {
        return connectionRepository.getFirstDegreeConnections(userId);
    }

    public ResponseEntity<Boolean> rejectConnectionRequest(Long senderId) {

        Long userId = UserContextHolder.getCurrentUserid();

        Boolean isRequestSent = connectionRepository.isRequested(senderId,userId);
        if(!isRequestSent)
        {
            throw new RuntimeException("there is no request to accept");
        }

        Boolean isConnected = connectionRepository.isConnected(userId,senderId);
        if(isConnected)
        {
            throw  new RuntimeException("already connected !");
        }

        Boolean rejected = connectionRepository.deleteConnectionRequest(senderId,userId);
        log.info("Connection rqst deleted for rejection");
        //Boolean accepted = connectionRepository.rejectConnectionRequest(userId,senderId);

        if(rejected)
        {
            log.info("Request rejected !!");
        }

        RejectRequestEvent rejectRequestEvent = RejectRequestEvent.builder()
                .rejectorId(userId)
                .senderId(senderId)
                .build();

        rejectRequestEventKafkaTemplate.send("request-rejected-topic",rejectRequestEvent);

        return ResponseEntity.ok(rejected);
    }

    public ResponseEntity<Boolean> acceptConnectionRequest(Long senderId) {
        Long userId = UserContextHolder.getCurrentUserid();

        Boolean isRequestSent = connectionRepository.isRequested(senderId,userId);
        if(!isRequestSent)
        {
            throw new RuntimeException("there is no request to accept");
        }

        Boolean isConnected = connectionRepository.isConnected(userId,senderId);
        if(isConnected)
        {
            throw  new RuntimeException("already connected !");
        }

        connectionRepository.deleteConnectionRequest(senderId,userId);
        log.info("Connection rqst deleted for accept");
        Boolean accepted = connectionRepository.acceptConnectionRequest(userId,senderId);

        if(accepted)
        {
            log.info("Request Accepted !!");
        }

        AcceptRequestEvent acceptRequestEvent = AcceptRequestEvent.builder()
                .acceptorId(userId)
                .senderId(senderId)
                .build();

        acceptRequestEventKafkaTemplate.send("request-accepted-topic",acceptRequestEvent);

        return ResponseEntity.ok(accepted);
    }

    public ResponseEntity<Boolean> sendConnectionRequest(Long requestedUserId) {
        Long userId = UserContextHolder.getCurrentUserid();

        Boolean isRequested = connectionRepository.isRequested(userId,requestedUserId);

        if(isRequested)
        {
            log.info("The user is already requested for connection");
            throw new RuntimeException("The user is already requested for connection");
        }

        Boolean isConnected = connectionRepository.isConnected(userId,requestedUserId);

        if(isConnected)
        {
            log.info("The user is already connected");
            throw new RuntimeException("The user is already connected");
        }

        log.info("Requested sent !!");
        Boolean sent = connectionRepository.addConnectionRequest(userId,requestedUserId);

        SendRequestEvent sendRequestEvent = SendRequestEvent.builder()
                .ReceiverId(requestedUserId)
                .senderId(userId)
                .build();

        sendRequestEventKafkaTemplate.send("request-sent-topic",sendRequestEvent);


        return ResponseEntity.ok(sent);
    }
}
