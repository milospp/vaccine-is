import axios from "axios";

const API_URL = "http://localhost:8082/api/digitalni-zeleni-sertifikat-zahtev"

class DigitalniZeleniSertifikatService {

    createZahtev(data) {
        return axios({
            method: 'POST',
            url: `${API_URL}`,
            headers: {
                'Content-Type': 'application/xml'
            },
            data: data,
        });
    }
}

export default new DigitalniZeleniSertifikatService();