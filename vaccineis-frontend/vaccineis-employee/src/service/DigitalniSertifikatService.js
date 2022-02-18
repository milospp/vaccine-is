import axios from "axios";

const API_URL = "http://localhost:8081/api/authentication"

class DigitalniSertifikatService {

    getZahteviWithStatusPredat(token) {
        return axios({
            method: 'GET',
            url: `${API_URL}/authority`,
            headers: {
                'Authorization': 'Bearer ' + token,
            }
        });
    }

}

export default new DigitalniSertifikatService();