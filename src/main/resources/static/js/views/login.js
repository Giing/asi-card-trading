import InputCustom from "../components/input.js";
import userService from "../services/userService.js";
import URL from "../utils/url.js";
import HTMLBindableElement from "../components/abstract/HTMLBindableElement.js";

class LoginForm extends HTMLBindableElement {
    constructor() {
        super();
        this.emailInput = new InputCustom("text", "email", "E-mail address");
        this.pwdInput = new InputCustom("password", "password", "password");
    }

    render() {
        this.innerHTML = `
            <div class="ui large form">
                <div class="ui stacked segment">
                    <span id="error" class="error"></span>
                    <div class="field">
                        <div id="emailLoginContainer" class="ui left icon input">
                            <i class="user icon"></i>
                        </div>
                    </div>
                    <div class="field">
                        <div id="pwdLoginContainer" class="ui left icon input">
                            <i class="lock icon"></i>
                        </div>
                    </div>
                    <button class="ui fluid large teal button" onclick="${this.bind(() => this.submit(), "login")}">Login</button>
                </div>

                <div class="ui error message"></div>

            </div>
      `;

        this.querySelector("#emailLoginContainer").appendChild(this.emailInput);
        this.querySelector("#pwdLoginContainer").appendChild(this.pwdInput);
    }

    setError(errorTxt) {
        const error = this.querySelector("#error");
        error.innerHTML = errorTxt;
    }

    async submit() {
        const email = "mathis.figuet@orange.fr"
        const password = "test"
        const isLogged = await userService.login({ email, password })

        if (isLogged) {
            URL.redirect('home')
        } else {
            this.setError("Une erreur est survenue lors de la connexion")
        }
    }
}

customElements.define('login-form', LoginForm);
