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

    getSertifikatPdf() {
        return axios({
            method: 'GET',
            url: `${API_BRIDGE}/get-pdf`,
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

    getSertifikatHtml() {
        return axios({
            method: 'GET',
            url: `${API_BRIDGE}/get-html`,
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

    viewSertifikatPdf() {
        return axios({
            method: 'GET',
            url: `${API_BRIDGE}/get-pdf`,
            responseType: 'blob'
        }).then((response) => {
            var fileURL = URL.createObjectURL(response.data);
            window.open(fileURL);
        });
    }
}

export default new DigitalniZeleniSertifikatService();