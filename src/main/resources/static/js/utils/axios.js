import userService from "../services/userService.js";

const instance = axios.create({
  baseURL: "http://localhost:8080/",
  timeout: 3000,
  headers: {
    Authorization: ''
  }
});

// handle 401 Unhautorized resource access
instance.interceptors.response.use(undefined, function (error) {
  if(error.response.status === 401) {
    //userService.logout();
    return Promise.reject(error);
  }
});

export default instance;

export function updateBearer(){
  const token = userService.get_token();
  if(token) {
    instance.defaults.headers.Authorization = `Bearer ${token}`;
  } else {
    delete instance.defaults.headers.Authorization;
  }
};