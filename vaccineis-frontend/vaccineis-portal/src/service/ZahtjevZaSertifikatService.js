
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

    getZahtevPdf(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-pdf/${uuid}`,
            responseType: 'blob'
        }).then((response) => {
            var fileURL = window.URL.createObjectURL(new Blob([response.data]));
            var fileLink = document.createElement('a');

            fileLink.href = fileURL;
            fileLink.setAttribute('download', 'file.pdf');
            document.body.appendChild(fileLink);

            fileLink.click();
        });
    }

    getZahtevHtml(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-html/${uuid}`,
            responseType: 'blob'
        }).then((response) => {
            var fileURL = window.URL.createObjectURL(new Blob([response.data]));
            var fileLink = document.createElement('a');

            fileLink.href = fileURL;
            fileLink.setAttribute('download', 'file.html');
            document.body.appendChild(fileLink);

            fileLink.click();
        });
    }

    getZahtevRdf(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-rdf/${uuid}`,
            responseType: 'blob'
        }).then((response) => {
            var fileURL = window.URL.createObjectURL(new Blob([response.data]));
            var fileLink = document.createElement('a');

            fileLink.href = fileURL;
            fileLink.setAttribute('download', 'file.xml');
            document.body.appendChild(fileLink);

            fileLink.click();
        });
    }

    getZahtevJson(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-json/${uuid}`,
            responseType: 'blob'
        }).then((response) => {
            var fileURL = window.URL.createObjectURL(new Blob([response.data]));
            var fileLink = document.createElement('a');

            fileLink.href = fileURL;
            fileLink.setAttribute('download', 'file.json');
            document.body.appendChild(fileLink);

            fileLink.click();
        });
    }


    viewZahtevPdf(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-pdf/${uuid}`,
            responseType: 'blob'
        }).then((response) => {
            var fileURL = URL.createObjectURL(response.data);
            window.open(fileURL);
        });
    }
}

export default new ZahtjevZaSertifikatService();