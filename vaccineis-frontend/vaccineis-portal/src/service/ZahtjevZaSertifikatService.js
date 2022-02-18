
import axios from "axios";

const API_URL = "http://localhost:8082/api/digitalni-zeleni-sertifikat-zahtev"

class ZahtjevZaSertifikatService {

    mojiZahtjevi() {
        return axios({
            method: 'GET',
            url: `${API_URL}/moji-zahtjevi`,
            headers: {
                'Content-Type': 'application/xml'
            },
        });
    }
}

export default new ZahtjevZaSertifikatService();