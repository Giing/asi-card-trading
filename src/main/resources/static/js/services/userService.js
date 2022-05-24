import Store from "../utils/store.js"; 
import API from "../utils/axios.js";
import {updateToken} from "../utils/axios.js";

const user_key = 'user';

const get_user = () => Store.getItem(user_key);
const get_token = () => get_user()?.token;

const root = 'user';


export default {
    async register(data) {
        const reponse = {"status" : 400}// await fetch('api/user/register', data)
        return reponse.status == 200
    },
    async login(data) {
        try {
            const response = await API.post(`${root}/login`, data);
            const user = response.data;
            Store.setItem(user_key, user);
            updateToken()

            return true;
        } catch (error) {
            return false;
        }
    },
    async logout() {
        try {
            Store.removeItem(user_key);
            window.location.reload(true);
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