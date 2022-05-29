package com.asi.model;

import com.asi.utils.SSEHandler;

public class Room {
    private Integer idRoom;
    public SSEHandler emitterRoom = new SSEHandler();

    public Room(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Room (Integer idRoom, SSEHandler emitterRoom) {
        this.idRoom = idRoom;
        this.emitterRoom = emitterRoom;
    }

    public Integer getIdRoom() {
        return this.idRoom;
    }
    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public SSEHandler getHandlerRoom() {
        return this.emitterRoom;
    }
    public void setHandlerRoom(SSEHandler emitterRoom) {
        this.emitterRoom = emitterRoom;
    }
}
