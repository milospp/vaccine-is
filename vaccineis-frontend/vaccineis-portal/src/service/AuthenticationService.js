import axios from "axios";

const API_URL = "http://localhost:8082/api/authentication"

class AuthenticationService {

    getAuthorizedUser(token) {
        return axios({
            method: 'GET',
            url: `${API_URL}/authority`,
            headers: {
                'Authorization': 'Bearer ' + token,
            }
        });
    }

    register(data) {
        return axios({
            method: 'POST',
            url: `${API_URL}/register`,
            headers: {
                'Content-Type': 'application/xml'
            },
            data: data,
        });
    }

    login(data) {
        return axios({
            method: 'POST',
            url: `${API_URL}/login`,
            headers: {
                'Content-Type': 'application/xml'
            },
            data: data
        });
    }

}

export default new AuthenticationService();