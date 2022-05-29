import roomService from "../services/roomService.js";
import HTMLBindableElement from "../components/abstract/HTMLBindableElement.js";

class LobbyView extends HTMLBindableElement {
    constructor() {
        super();
    }

    async render() {
        this.innerHTML = `
            <div class="ui grid">
                <div class="ten wide column" id="eventRoom">
                </div>
            </div>
        `;

        this.eventRoom = this.querySelector("#eventRoom");
        roomService.addCurrentRoomEventListener((event) => {
            console.log(event)
            this.eventRoom.innerHTML = event;
        })
    }
}

customElements.define('lobby-view', LobbyView);