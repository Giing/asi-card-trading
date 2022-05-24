import InputCustom from "../components/input.js";
import userService from "../services/userService.js";
import URL from "../utils/url.js";
import HTMLBindableElement from "../components/abstract/HTMLBindableElement.js";

class RegisterForm extends HTMLBindableElement {
    constructor() {
        super();
        this.nameInput = new InputCustom("text","name","Name");
		this.emailInput = new InputCustom("email","email","Email");
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
                        <div id="emailRegisterContainer" class="ui left icon input">
                            <i class="user icon"></i>
                        </div>
                    </div>

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
                            <i class="lock icon"></i>
                        </div>
                    </div>
                    <div class="field">
                        <div id="RePasswordRegisterContainer" class="ui left icon input">
                            <i class="lock icon"></i>
                        </div>
                    </div>
                    <button class="ui fluid large teal submit button" onclick="${this.bind(() => this.submit(),"login")}">Register</button>
                </div>

                <div class="ui error message"></div>

            </div>
      `;

      this.querySelector("#nameRegisterContainer").appendChild(this.nameInput);
	  this.querySelector("#emailRegisterContainer").appendChild(this.emailInput);
      this.querySelector("#surnameRegisterContainer").appendChild(this.surnameInput);
      this.querySelector("#passwordRegisterContainer").appendChild(this.pwdInput);
      this.querySelector("#RePasswordRegisterContainer").appendChild(this.repwdInput);

    }
    
    setError(errorTxt) {
        const error = this.querySelector("#error");
        error.innerHTML = errorTxt;
    }

    async submit() {
        const nameUser = this.nameInput.getValue()
		const emailUser = this.emailInput.getValue()
        const surnameUser = this.surnameInput.getValue()
        const passwordUser = this.pwdInput.getValue()
        const passwordConfirm = this.repwdInput.getValue()

        const isRegistred = await userService.register({
			emailUser,
            nameUser,
            surnameUser,
            passwordUser,
            passwordConfirm
        })
        
        if (isRegistred) {
            URL.redirect('login')
        } else {
            this.setError("Une erreur est survenue lors de la création de compte")
        }
    }
}

customElements.define('register-form', RegisterForm);
