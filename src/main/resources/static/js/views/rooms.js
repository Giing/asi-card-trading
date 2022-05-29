import roomService from "../services/roomService.js";
import HTMLBindableElement from "../components/abstract/HTMLBindableElement.js";
import RoomList from "../components/roomList.js";

class RoomsView extends HTMLBindableElement {
    constructor() {
        super();
    }

    async render() {
        this.innerHTML = `
            <div class="ui grid">
                <div class="ten wide column" id="rooms">
                </div>
                <button class="ui red button" onclick="${this.bind(roomService.createRoom ,"createRoom")}">
                    Add room
                </button>
            </div>
        `;

        this.roomList = new RoomList([], (room) => this.selectRoom(room));
        this.querySelector("#rooms").appendChild(this.roomList);


        roomService.addAvailableRoomsEventListener("renderList", (rooms) => this.roomList.setRooms(rooms));
    }

    selectRoom(room) {
        console.log(room);
        roomService.joinRoom(room);
    }
}

customElements.define('rooms-view', RoomsView);