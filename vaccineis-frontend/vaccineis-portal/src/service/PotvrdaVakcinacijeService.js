import axios from "axios";

const API_URL = "http://localhost:8082/api/potvrda"

class PotvrdaService {

    mojePotvrde() {
        return axios({
            method: 'GET',
            url: `${API_URL}/moje-potvrde`,
            headers: {
                'Content-Type': 'application/xml'
            },
        });
    }
}

export default new PotvrdaService();