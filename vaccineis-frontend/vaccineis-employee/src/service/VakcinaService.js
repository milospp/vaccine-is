import axios from "axios";

const API_URL = "http://localhost:8081/api/vakcine"

class VakcinaService {

    addKolicina(data) {
        return axios({
            method: 'PUT',
            url: `${API_URL}/dodajKolicinu`,
            headers: {
                'Authorization': 'Bearer ' + "token", // token? ********************************
            },
            data: data
        });
    }

}

export default new VakcinaService();