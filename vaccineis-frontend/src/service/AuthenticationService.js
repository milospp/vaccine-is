import axios from "axios";

const API_URL = "url"

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

}

export default new AuthenticationService();