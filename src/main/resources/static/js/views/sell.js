import CardList from "../components/cardList.js"
import Card from "../components/card.js"

import SaleService from "../services/saleService.js";
import CardService from "../services/cardService.js";

class SellView extends HTMLElement {
    constructor() {
        super();
    }

    async render() {
        this.cards = await CardService.getCardUser();

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
        if ( this.cards.length >=1 ) {
            this.selectedCard = new Card(this.cards[0], true);
        } else {
            this.selectedCard = new Card(undefined, true);
        }
        this.selectedCard.bindOnButtonClick(() => this.sell())
        this.querySelector("#selectedCard").appendChild(this.selectedCard);
    }

    selectCard(card) {
        this.selectedCard.setCard(card)
    }

    async sell() {
        const price = this.selectedCard.button.getValue();
        const card = this.selectedCard.card;

        await SaleService.sellCard({...card, price});
        this.render()
    }

    connectedCallback() {
        this.render();
    }
}

customElements.define('sell-view', SellView);