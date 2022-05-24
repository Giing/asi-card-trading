import Store from "../utils/store.js"; 
import API from "../utils/axios.js";

const user_key = 'user';

const get_user = () => Store.getItem(user_key);
const get_token = () => get_user()?.token;


export default {
    async register(data) {
        const reponse = {"status" : 400}// await fetch('api/user/register', data)
        return reponse.status == 200
    },
    async login(data) {
        try {
            const response = {"id_user": "0","user_name": data.login, "money": 9000, "token": "zaeazeazeazeaez"} // await fetch('api/user/login', data)
            Store.setItem(user_key, response);
            return true;
        } catch (error) {
            return false;
        }
    },
    async logout() {
        try {
            const response = {"id_user": "0","user_name": "karim", "money": 9000, "token": "zaeazeazeazeaez"} // await fetch('api/user/logout', {login})
            Store.removeItem(user_key);
            window.location.reload(true)
            return true;
        } catch (error) {
            return false;
        }
    },
    get_user,
    get_token,
    isUserLoggedIn() {
        return get_user() != null;
    }
}