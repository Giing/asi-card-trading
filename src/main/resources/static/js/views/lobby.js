import HTMLView from "../components/abstract/HTMLView.js";

import roomService from "../services/roomService.js";

export default class LobbyView extends HTMLView {
    constructor() {
        super();
    }

    async render() {
        this.innerHTML = `
            <header-component></header-component>
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