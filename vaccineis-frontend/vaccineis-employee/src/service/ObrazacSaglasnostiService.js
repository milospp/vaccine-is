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

    getObrazacById(id) {
        return axios({
            method: 'GET',
            url: `${API_URL}/` + id,
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

    getSaglasnostPdf(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-pdf/${uuid}`,
            responseType: 'blob',
            headers: {
                'Authorization': undefined
            },
        }).then((response) => {
            var fileURL = window.URL.createObjectURL(new Blob([response.data]));
            var fileLink = document.createElement('a');

            fileLink.href = fileURL;
            fileLink.setAttribute('download', 'file.pdf');
            document.body.appendChild(fileLink);

            fileLink.click();
        });
    }

    getSaglasnostHtml(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-html/${uuid}`,
            responseType: 'blob',
            headers: {
                'Authorization': undefined
            },
        }).then((response) => {
            var fileURL = window.URL.createObjectURL(new Blob([response.data]));
            var fileLink = document.createElement('a');

            fileLink.href = fileURL;
            fileLink.setAttribute('download', 'file.html');
            document.body.appendChild(fileLink);

            fileLink.click();
        });
    }

    getSaglasnostRdf(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-rdf/${uuid}`,
            responseType: 'blob',
            headers: {
                'Authorization': undefined
            },
        }).then((response) => {
            var fileURL = window.URL.createObjectURL(new Blob([response.data]));
            var fileLink = document.createElement('a');

            fileLink.href = fileURL;
            fileLink.setAttribute('download', 'file.xml');
            document.body.appendChild(fileLink);

            fileLink.click();
        });
    }

    getSaglasnostJson(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-json/${uuid}`,
            responseType: 'blob',
            headers: {
                'Authorization': undefined
            },
        }).then((response) => {
            var fileURL = window.URL.createObjectURL(new Blob([response.data]));
            var fileLink = document.createElement('a');

            fileLink.href = fileURL;
            fileLink.setAttribute('download', 'file.json');
            document.body.appendChild(fileLink);

            fileLink.click();
        });
    }


    viewSaglasnostPdf(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-pdf/${uuid}`,
            responseType: 'blob',
            headers: {
                'Authorization': undefined
            },
        }).then((response) => {
            var fileURL = URL.createObjectURL(response.data);
            window.open(fileURL);
        });
    }
    
    korisnikObrasci(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/korisnik/${uuid}`,
            headers: {
                'Content-Type': 'application/xml',
                'Authorization': undefined
            },

        });
    }
}

export default new ObrazacSaglasnostiService();