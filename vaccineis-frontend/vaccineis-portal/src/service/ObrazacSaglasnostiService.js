import axios from "axios";

const API_URL = "http://localhost:8082/api/saglasnost"

class ObrazacSaglasnostiService {

    createObrazacSaglasnosti(data) {
        return axios({
            method: 'POST',
            url: `${API_URL}`,
            headers: {
                'Content-Type': 'application/xml'
            },
            data: data,
        });
    }

    mojiObrasci() {
        return axios({
            method: 'GET',
            url: `${API_URL}/moji-obrasci`,
            headers: {
                'Content-Type': 'application/xml'
            },
        });
    }
}

export default new ObrazacSaglasnostiService();