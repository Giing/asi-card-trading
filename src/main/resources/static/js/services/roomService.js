import Store from "../utils/store.js";
import API from "../utils/axios.js";

const baseUrl = "http://localhost:8080/api/rooms";

const getHeaders = () => ({
    headers: {
        Authorization: Store.getItem('user')?.token
    }
})

console.log(getHeaders())

const availableRoomsEvents = {}
const streamAvailableRooms = new EventSourcePolyfill(baseUrl, getHeaders());
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
    if (currentRoom) currentRoom.close();

    currentRoom = eventSource;
    currentRoom.onmessage = (event) => {
        const data = event.data;
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
        console.log("hello 2")
        API.post("/")
        setCurrentRoom(new EventSourcePolyfill(baseUrl + "/create", getHeaders()));
    },
    joinRoom(room) {
        setCurrentRoom(new EventSourcePolyfill(baseUrl + "/join/" + room.idRoom, getHeaders()));
    },
    addCurrentRoomEventListener(key, callback) {
        currentRoomEvents[key] = callback;

    },
    removeCurrentRoomEventListener(key) {
        delete currentRoomEvents[key];
    },
    isConnectedToRoom
}