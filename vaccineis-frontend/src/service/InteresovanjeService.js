import axios from "axios";

const API_URL = "http://localhost:8082/api/interesovanje"

class InteresovanjeService {
    getInteresovanjePdf() {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-pdf`,
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

    getInteresovanjeHtml() {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-html`,
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

    viewInteresovanjePdf() {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-pdf`,
            responseType: 'blob'
        }).then((response) => {
            var fileURL = URL.createObjectURL(response.data);
            window.open(fileURL);
        });
    }
}

export default new InteresovanjeService();