import HTMLView from "../components/abstract/HTMLView.js";
import RoomList from "../components/roomList.js";

import roomService from "../services/roomService.js";

export default class RoomsView extends HTMLView {
    constructor() {
        super();
    }

    async render() {
        this.innerHTML = `
            <header-component></header-component>
            <div class="ui grid">
                <div class="ten wide column" id="rooms">
                </div>
                <button class="ui red button" onclick="${this.bind(() => this.createRoom() ,"createRoomAction")}">
                    Add room
                </button>
            </div>
        `;

        this.roomList = new RoomList([], (room) => this.selectRoom(room));
        this.querySelector("#rooms").appendChild(this.roomList);


        roomService.addAvailableRoomsEventListener("renderList", (rooms) => this.roomList.setRooms(rooms));
    }

    selectRoom(room) {
        roomService.joinRoom(room);
        // this.router.redirect("lobby");
    }

    createRoom() {
        console.log("hello 1")
        roomService.createRoom();
        // this.router.redirect("lobby");
    }
}

customElements.define('rooms-view', RoomsView);