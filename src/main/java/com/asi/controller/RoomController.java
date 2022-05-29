package com.asi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import com.asi.model.Room;
import com.asi.model.User;
import com.asi.service.UserService;
import com.asi.utils.SSEHandler;

@RestController
public class RoomController {

    @Autowired
    UserService userService;

    Map<Integer, Room> availableRooms = new HashMap<Integer, Room>();
    SSEHandler emittersAvailableRooms = new SSEHandler();

    @PostConstruct
    public void init() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            while(true) {
                System.out.println("Exec");

                for (Room room : availableRooms.values()) {
                    SSEHandler handler = room.emitterRoom;
    
                    for (Map.Entry<String, SseEmitter> connection : handler.emitters.entrySet()) {
                        SseEmitter emitter = connection.getValue();
                        try {
                            emitter.send("Bonjour le monde de la room: " + room.getIdRoom());
                            System.out.println("Bonjour le monde de la room: " + room.getIdRoom());
                        } catch (IOException e) {
                            emitter.completeWithError(e);
                            e.printStackTrace();
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

    @RequestMapping(value = "/api/rooms/create", method = RequestMethod.GET)
    public SseEmitter create() {
        Integer size = availableRooms.size();
        Room newRoom = new Room(size);
        availableRooms.put(size, newRoom);
        System.out.println("Create room: " + size);
        dispatchAvailableRooms();

        // handle room connection
        return newRoom.emitterRoom.addClient();
    }

    @GetMapping(value = "/api/rooms/join/{idRoom}")
    public SseEmitter join(@PathVariable int idRoom) {
        System.out.println(idRoom);
        if (availableRooms.containsKey(idRoom)) {
            SSEHandler roomHandler = availableRooms.get(idRoom).getHandlerRoom();
            return roomHandler.addClient();
        } else {
            return null;
        }
    }

    @RequestMapping("/api/rooms")
    public SseEmitter streamAvailableRooms() {
        SseEmitter emitter = emittersAvailableRooms.addClient();
        try {
            emitter.send(availableRooms.values());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return emitter;
    }

    private void dispatchAvailableRooms() {
        Collection<Room> message = availableRooms.values();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            for (Map.Entry<String, SseEmitter> connection : emittersAvailableRooms.emitters.entrySet()) {
                SseEmitter emitter = connection.getValue();
                try {
                    emitter.send(message);
                } catch (IOException e) {
                    emitter.completeWithError(e);
                    e.printStackTrace();
                }
            }
        });
        executor.shutdown();
    }
}
