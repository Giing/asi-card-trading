import CardList from "../components/cardList.js"
import Card from "../components/card.js"

import SaleService from "../services/saleService.js";
import userService from "../services/userService.js";

class BuyView extends HTMLElement {
    constructor() {
        super();
    }

    async render() {
        this.cards = await SaleService.getCardsToSell();

        console.log(await SaleService.test())

        this.innerHTML = `
            <div class="ui grid">
                <div class="ten wide column" id="cards">
                </div>
                <div class=" five wide column" id="selectedCard">
                </div>
            </div>
        `;

        this.cardsContainer = new CardList(this.cards, (card) => this.selectCard(card));
        this.querySelector("#cards").appendChild(this.cardsContainer);
        this.selectedCard = new Card(this.cards[0]);
        this.selectedCard.bindOnButtonClick(() => this.buy())
        this.querySelector("#selectedCard").appendChild(this.selectedCard);
    }

    selectCard(card) {
        this.selectedCard.setCard(card)
    }

    buy() {
        const card = this.selectedCard.card;
        SaleService.buyCard(card);
        this.render()
    }

    connectedCallback() {
        this.render();
    }
}

customElements.define('buy-view', BuyView);