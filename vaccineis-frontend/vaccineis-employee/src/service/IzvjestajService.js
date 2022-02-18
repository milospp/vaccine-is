import axios from "axios";

const API_URL = "http://localhost:8081/api/izvestaj"

class IzvjestajService {

    createIzvjestaj(data) {
        return axios({
            method: 'POST',
            url: `${API_URL}`,
            headers: {
                'Content-Type': 'application/xml'
            },
            data: data,
        });
    }

    getIzvjestajPdf() {
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

    getIzvjestajHtml() {
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

    // eslint-disable-next-line no-unused-vars
    viewIzvjestajPdf() {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-pdf`,
            responseType: 'blob'
        });
    }

    generisiNoviIzvjestaj(startDate, endDate) {
        return axios({
            method: 'GET',
            url: `${API_URL}/get-izvjestaj/startDate=` + startDate + `/endDate=`+endDate,
            responseType: 'blob'
        });
    }
}

export default new IzvjestajService();