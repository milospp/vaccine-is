import axios from "axios";

const API_URL = "http://localhost:8082/api/termin"

class TerminService {

    getNepotvrdjeniTermin(data) {
        return axios({
            method: 'GET',
            url: `${API_URL}/novi-termin`,
            headers: {
                'Content-Type': 'application/xml'
            },
            data: data,
        });
    }
}

export default new TerminService();