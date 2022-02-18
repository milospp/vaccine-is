import axios from "axios";

const API_URL = "http://localhost:8081/api/authentication"
const API_SERT = "http://localhost:8081/api/digitalni-sertifikat";

class DigitalniSertifikatService {

    getZahteviWithStatusPredat(token) {
        return axios({
            method: 'GET',
            url: `${API_URL}/authority`,
            headers: {
                'Authorization': 'Bearer ' + token,
            }
        });
    }

    getSertifikatPdf() {
        return axios({
            method: 'GET',
            url: `${API_SERT}/get-pdf`,
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
            url: `${API_SERT}/get-html`,
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
            url: `${API_SERT}/get-pdf`,
            responseType: 'blob'
        }).then((response) => {
            var fileURL = URL.createObjectURL(response.data);
            window.open(fileURL);
        });
    }

    respondToSertikatRequest(decision, message, requestId="5511615319889") {
        let data = "<SertifikatResponseDTO><decision>"+decision+"</decision><message>"+message+"</message>" +
            "<requestId>" + requestId + "</requestId>"+
            "</SertifikatResponseDTO>"
        return axios({
            method: 'POST',
            url: `${API_SERT}/response-request`,
            headers: {
                'Content-Type': 'application/xml'
            },
            data: data
        });
    }
}

export default new DigitalniSertifikatService();