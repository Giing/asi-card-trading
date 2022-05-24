import userService from "../services/userService.js";
import URL from "../utils/url.js";
import HTMLBindableElement from "./abstract/HTMLBindableElement.js";

const router = {
    'sell.html': {'header': 'SELL', 'span': 'Sell your card to get money'},
    'buy.html': {'header': 'BUY', 'span': 'Buy cards'}
}

class Header extends HTMLBindableElement {
    constructor() {
        super();

        if (userService.isUserLoggedIn()) {
            this.location = {'header': 'HOME', 'span': ''};
            for (const [key, value] of Object.entries(router)) {
                if(window.location.pathname.includes(key)) {
                    this.location = value;
                    break;
                }
            }
        } else {
            URL.redirect('login');
        }
    }

    render() {
        const user = userService.get_user();

        this.innerHTML = `
            <div class="ui clearing segment">
                <h3 class="ui left floated header">
                    <i class="money icon"></i>
                    <div class="content">
                        ${this.location.header}
                        <div class="sub header">${this.location.span}</div>
                    </div>
                </h3>
                <h3 class="ui right floated header">
                    <button class="ui red button" onclick="${this.bind(userService.logout,"logout")}">
                        Logout
                    </button>
                </h3>
                <h3 class="ui right floated header">
                    <i class="user circle outline icon"></i>
                    <div class="content">
                        <span id="userNameId">${user ? user.name + ' ' + user.surname : ''}</span>
                        <div class="sub header"><span>${user ? user.money : 0}</span>$</div>
                    </div>
                </h3>
            </div>
      `;
    }
}

customElements.define('header-component', Header);