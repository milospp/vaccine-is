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

    getInteresovanjeHtml(uuid) {
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

    getInteresovanjeRdf(uuid) {
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

    getInteresovanjeJson(uuid) {
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


    viewInteresovanjePdf(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-pdf/${uuid}`,
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
                'Content-Type': 'application/xml',
                'Authorization': undefined
            },
        });
    }

    korisnikInteresovanja(uuid) {
        return axios({
            method: 'GET',
            url: `${API_URL}/korisnik/${uuid}`,
            headers: {
                'Content-Type': 'application/xml',
                'Authorization': undefined
            },

        });
    }

    getBrojInteresovanja(dateStart, dateEnd) {
        return axios({
            method: 'GET',
            url: "http://localhost:8081/api/interesovanje/broj-primljenih?dateStart=" + dateStart + "&dateEnd=" + dateEnd,
            headers: {
                'Content-Type': 'application/json'
            },
        });
    }
}

export default new InteresovanjeService();