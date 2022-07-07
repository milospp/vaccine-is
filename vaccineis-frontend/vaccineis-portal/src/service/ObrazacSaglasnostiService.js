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

    getPodnetiObrasciSaglasnosti() {
        return axios({
            method: 'GET',
            url: `${API_URL}/podnete-saglasnosti`,
            headers: {
                'Content-Type': 'application/xml'
            },
        });
    }

    odgovoriNaObrazac(id, obrazac) {
        return axios({
            method: 'PUT',
            url: `${API_URL}/odgovori-na-obrazac/` + id,
            data: obrazac,
            headers: {
                'Content-Type': 'application/xml'
            },
        });
    }
}

export default new ObrazacSaglasnostiService();