<template>
  <div></div>
</template>

<script>
import axios from "axios";

const API_URL = "http://localhost:8082/api/interesovanje"

export default {
  name: "ViewDocument",

  mounted() {
    console.log('aaaaaaaaa')
    return axios({
      method: 'GET',
      url: `${API_URL}/get-pdf`,
      responseType: 'blob'
    }).then((response) => {
      console.log(response.data);
      console.log(URL.createObjectURL(response.data));
      window.location.href = URL.createObjectURL(response.data);
      // window.open(fileURL);
    });
  }
}
</script>

<style scoped>

</style>