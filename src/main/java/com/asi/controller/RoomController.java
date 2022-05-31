package com.asi.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import com.asi.dto.RoomDto;
import com.asi.model.Room;
import com.asi.service.UserService;
import com.asi.utils.SseHandler;

@RestController
public class RoomController {

    private final static Logger LOG = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    UserService userService;

    Map<Integer, Room> availableRooms = new HashMap<Integer, Room>();
    SseHandler emittersAvailableRooms = new SseHandler();

    @PostConstruct
    public void init() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            while(true) {
                for (Room room : availableRooms.values()) {
                    SseHandler handler = room.emitterRoom;
    
                    for (Map.Entry<String, SseEmitter> connection : handler.emitters.entrySet()) {
                        SseEmitter emitter = connection.getValue();
                        try {
                            emitter.send(room);
                        } catch (IOException e) {
                        }
                    }
                }
                randomDelay();
            }
        });
    }

    private void randomDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @RequestMapping("/api/rooms")
    @ResponseBody
    public ResponseEntity<?> getAvailableRooms() {
        return new ResponseEntity<>(availableRooms.values(), HttpStatus.OK);
    }

    @PostMapping("/api/rooms")
    public Room createRoom(@RequestBody RoomDto room) {
        Integer size = availableRooms.size();
        Room newRoom = new Room(size, room.getNameRoom());
        System.out.println("Create room: " + size);
        availableRooms.put(size, newRoom);
        dispatchAvailableRooms();
        return newRoom;
    }


    @RequestMapping("/api/rooms/sse")
    public SseEmitter streamAvailableRooms() {
        return emittersAvailableRooms.addClient();
    }

    @GetMapping(value = "/api/rooms/sse/join/{idRoom}")
    public SseEmitter join(@PathVariable int idRoom) {
        if (availableRooms.containsKey(idRoom)) {
            return availableRooms.get(idRoom).addPlayer();
        } else {
            return null;
        }
    }

    private void dispatchAvailableRooms() {
        Collection<Room> message = availableRooms.values();
        for (Map.Entry<String, SseEmitter> connection : emittersAvailableRooms.emitters.entrySet()) {
            try {
                SseEmitter emitter = connection.getValue();
                emitter.send(message);
            } catch (IOException e) {

            }
        }
    }
}
