<template>
  <div class="home">
    <input type="file" @change="onFileChange" />
    <button @click="uploadFile">上传文件</button>
    <button @click="downloadFile">下载文件</button>
  </div>
</template>

<script>
import axios from 'axios';
import CryptoJS from 'crypto-js';

export default {
  data() {
    return {
      file: null,
      encryptedFile: null,
      secretKey: "abcedefghijklmnfsdfasfasdfsafsdfjour9243ur8ujeoifuodsidjfksfksdjfksdjfhoabcedefghijklmno"
    };
  },
  methods: {
    onFileChange(e) {
      this.file = e.target.files[0];
    },
    async uploadFile() {
      console.log(this.file)
      if (!this.file) return alert('请选择文件');
      const fileData = await this.encryptFile(this.file);
      const formData = new FormData();
      formData.append('file', fileData.encryptedData, fileData.name);
      try {
        const response = await axios.post('/api/upload', formData, {
          headers: { 'Content-Type': 'multipart/form-data' },
        });
        console.log('文件上传成功:', response.data);
        this.encryptedFile = response.data.encryptedFile;
      } catch (error) {
        console.error('文件上传失败:', error);
      }
    },
    async downloadFile() {
      if (!this.encryptedFile) return alert('请先上传文件');
      try {
        const response = await axios.get(`/api/download?filename=${this.file.name}`, {
          responseType: 'blob',
        });
        const decryptedFile = await this.decryptFile(response.data);
        this.downloadBlob(decryptedFile, this.file.name);
      } catch (error) {
        console.error('文件下载失败:', error);
      }
    },
    // encryptFile(file) {
    //   return new Promise((resolve, reject) => {
    //     const reader = new FileReader();
    //     reader.onload = (event) => {
    //       const data = event.target.result;
    //       console.log(data)
    //       console.log(this.secretKey)
    //       const encryptedData = CryptoJS.AES.encrypt(data, this.secretKey).toString();
    //       resolve({ name: file.name, type: file.type, encryptedData });
    //     };
    //     reader.onerror = reject;
    //     reader.readAsArrayBuffer(file);
    //   });
    // },
    // decryptFile(blob) {
    //   return new Promise((resolve, reject) => {
    //     const reader = new FileReader();
    //     reader.onload = (event) => {
    //       const encryptedData = event.target.result;
    //       const decryptedData = CryptoJS.AES.decrypt(encryptedData, this.secretKey).toString(CryptoJS.enc.Utf8);
    //       resolve(decryptedData);
    //     };
    //     reader.onerror = reject;
    //     reader.readAsText(blob);
    //   });
    // },
    downloadBlob(blob, filename) {
      const url = window.URL.createObjectURL(new Blob([blob]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', filename);
      document.body.appendChild(link);
      link.click();
      link.parentNode.removeChild(link);
    },





    encryptFile(file) {
      return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = (event) => {
          const data = event.target.result;
          const wordArray = CryptoJS.lib.WordArray.create(data);
          const encryptedData = CryptoJS.AES.encrypt(wordArray, this.secretKey).toString();
          resolve({ name: file.name, type: file.type, encryptedData });
        };
        reader.onerror = reject;
        reader.readAsArrayBuffer(file);
      });
    },
    decryptFile(blob) {
      return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = (event) => {
          const encryptedData = event.target.result;
          const decryptedData = CryptoJS.AES.decrypt(encryptedData, this.secretKey).toString(CryptoJS.enc.Utf8);
          resolve(decryptedData);
        };
        reader.onerror = reject;
        reader.readAsDataURL(blob);
      });
    },


  },
};
</script>
