import axios from "axios";

const API_URL = "http://localhost:8082/api/interesovanje"

class InteresovanjeService {

    createInteresovanje(data) {
        console.log("AAAAAAAA");
        return axios({
            method: 'POST',
            url: `${API_URL}`,
            headers: {
                'Content-Type': 'application/xml'
            },
            data: data,
        });
    }

    getInteresovanjePdf(uuid) {
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

    getInteresovanjeHtml(uuid) {
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

    mojaInteresovanja() {
        return axios({
            method: 'GET',
            url: `${API_URL}/moja-interesovanja`,
            headers: {
                'Content-Type': 'application/xml'
            },
        });
    }
}

export default new InteresovanjeService();