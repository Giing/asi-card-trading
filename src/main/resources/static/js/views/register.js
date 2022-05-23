import InputCustom from "../components/input.js";
import userService from "../services/userService.js";
import URL from "../utils/url.js";
import HTMLBindableElement from "../components/abstract/HTMLBindableElement.js";

class RegisterForm extends HTMLBindableElement {
    constructor() {
        super();
        this.nameInput = new InputCustom("text","name","Name");
        this.surnameInput = new InputCustom("text","surname","Surname");
        this.pwdInput = new InputCustom("password","password","password");
        this.repwdInput = new InputCustom("password","repassword","RePassword");
    }

    render() {
        this.innerHTML = `
            <div class="ui large form">
                <div class="ui stacked segment">
                    <span id="error" class="error"></span>
                    <div class="field">
                        <div id="nameRegisterContainer" class="ui left icon input">
                            <i class="user icon"></i>
                        </div>
                    </div>
                    <div class="field">
                        <div id="surnameRegisterContainer" class="ui left icon input">
                            <i class="user icon"></i>
                        </div>
                    </div>
                    <div class="field">
                        <div id="passwordRegisterContainer" class="ui left icon input">
                            <i class="user icon"></i>
                        </div>
                    </div>
                    <div class="field">
                        <div id="RePasswordRegisterContainer" class="ui left icon input">
                            <i class="lock icon"></i>
                        </div>
                    </div>
                    <button class="ui fluid large teal submit button" onclick="${this.bind(() => this.submit(),"login")}">Login</button>
                </div>

                <div class="ui error message"></div>

            </div>
      `;

      this.querySelector("#nameRegisterContainer").appendChild(this.nameInput);
      this.querySelector("#surnameRegisterContainer").appendChild(this.surnameInput);
      this.querySelector("#passwordRegisterContainer").appendChild(this.pwdInput);
      this.querySelector("#RePasswordRegisterContainer").appendChild(this.repwdInput);

    }
    
    setError(errorTxt) {
        const error = this.querySelector("#error");
        error.innerHTML = errorTxt;
    }

    async submit() {
        const name = this.nameInput.getValue()
        const surname = this.surnameInput.getValue()
        const pwd = this.pwdInput.getValue()
        const repwd = this.repwdInput.getValue()

        const isRegistred = await userService.register({
            name,
            surname,
            pwd,
            repwd
        })
        
        if (isRegistred) {
            URL.redirect('login')
        } else {
            this.setError("Une erreur est survenue lors de la création de compte")
        }
    }
}

customElements.define('register-form', RegisterForm);
