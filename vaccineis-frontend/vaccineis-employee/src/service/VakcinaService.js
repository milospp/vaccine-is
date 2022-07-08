import axios from "axios";

const API_URL = "http://localhost:8081/api/vakcine"

class VakcinaService {

    addKolicina(data) {
        return axios({
            method: 'PUT',
            url: `${API_URL}/dodajKolicinu`,
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        });
    }

    loadVakcine() {
        return axios({
            method: 'GET',
            url: `${API_URL}`,
            headers: {
                'Content-Type': 'application/json'
            },
        });
    }

    getBrojVakcina(dateStart, dateEnd) {
        return axios({
            method: 'GET',
            url: `${API_URL}/broj-vakcina?dateStart=` + dateStart + "&dateEnd=" + dateEnd,
            headers: {
                'Content-Type': 'application/xml'
            },
        });
    }

}

export default new VakcinaService();