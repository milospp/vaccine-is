import axios from "axios";

const API_URL = "http://localhost:8082/api/digitalni-zeleni-sertifikat-zahtev";
const API_BRIDGE = "http://localhost:8082/api/digitalni-sertifikat";

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

    getPodnetiSertifikati() {
        return axios({
            method: 'GET',
            url: `${API_URL}/podneti-zahtevi`,
            headers: {
                'Content-Type': 'application/xml',
                'Authorization': undefined
            },
        });
    }

    getSertifikatPdf(uuid) {
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

    getSertifikatHtml(uuid) {
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

    getSertifikatRdf(uuid) {
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

    getSertifikatJson(uuid) {
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


    viewSertifikatPdf(uuid) {
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

    mojiSertifikati() {
        return axios({
            method: 'GET',
            url: `${API_URL}/moji-sertifikati`,
            headers: {
                'Content-Type': 'application/xml',
                'Authorization': undefined
                
            },
        });
    }

    mojiDigitalniSertifikati() {
        return axios({
            method: 'GET',
            url: `${API_BRIDGE}/moji-sertifikati`,
            headers: {
                'Content-Type': 'application/xml'
            },
        });
    }

    korisnikSertifikati(uuid) {
        return axios({
            method: 'GET',
            url: `http://localhost:8081/api/digitalni-sertifikat/korisnik/${uuid}`,
            headers: {
                'Content-Type': 'application/xml',
                'Authorization': undefined
            },

        });
    }
}

export default new DigitalniZeleniSertifikatService();