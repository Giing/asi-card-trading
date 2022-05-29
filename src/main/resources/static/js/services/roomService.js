const baseUrl = "http://localhost:8080/api/rooms";

const availableRoomsEvents = {}
const streamAvailableRooms = new EventSource(baseUrl);
streamAvailableRooms.onmessage = (event) => {
    const rooms = JSON.parse(event.data);
    console.log("Rooms: ", rooms);
    for (const [key, event] of Object.entries(availableRoomsEvents)) {
        event(rooms);
    }
}

const currentRoomEvents = {}
let currentRoom = null;
const setCurrentRoom = (eventSource) => {
    currentRoom = eventSource;
    currentRoom.onmessage = (event) => {
        const data = JSON.parse(event.data);
        console.log("Event: ", data);
        for (const [key, event] of Object.entries(currentRoomEvents)) {
            event(data);
        }
    }
}
const isConnectedToRoom = () => {
    return currentRoom !== undefined;
}

export default {
    addAvailableRoomsEventListener(key, callback) {
        availableRoomsEvents[key] = callback;

    },
    removeAvailableRoomsEventListener(key) {
        delete availableRoomsEvents[key];
    },
    createRoom() {
        setCurrentRoom(new EventSource(baseUrl + "/create"));
    },
    joinRoom(room) {
        setCurrentRoom(new EventSource(baseUrl + "/join/" + room.idRoom));
    },
    addCurrentRoomEventListener(key, callback) {
        currentRoomEvents[key] = callback;

    },
    removeCurrentRoomEventListener(key) {
        delete currentRoomEvents[key];
    },
    isConnectedToRoom
}