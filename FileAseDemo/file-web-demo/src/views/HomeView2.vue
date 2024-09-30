<template>
  <div>
    <input type="file" @change="uploadFile" />
    <button @click="downloadFile">下载文件</button>
  </div>
</template>

<script>
import axios from 'axios'
import CryptoJS from 'crypto-js'

export default {
  data() {
    return {
      file: null,
      key: 'your_aes_key_here' // 替换为您自己的 AES 密钥
    }
  },
  methods: {
    uploadFile(event) {
      this.file = event.target.files[0]
    },
    async downloadFile() {
      try {
        const response = await axios.get('/api/download', {
          responseType: 'arraybuffer'
        })
        const decryptedData = this.decryptData(response.data)
        this.downloadBlob(decryptedData, this.file.name)
      } catch (error) {
        console.error('Error downloading file:', error)
      }
    },
    encryptData(data) {
      const iv = CryptoJS.lib.WordArray.random(16)
      const encrypted = CryptoJS.AES.encrypt(data, CryptoJS.enc.Utf8.parse(this.key), {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
      })
      return CryptoJS.lib.WordArray.create([...iv, ...encrypted.ciphertext]).toString(CryptoJS.enc.Base64)
    },
    decryptData(data) {
      const binaryData = atob(data)
      const iv = CryptoJS.enc.Hex.parse(binaryData.slice(0, 32))
      const encryptedData = CryptoJS.enc.Hex.parse(binaryData.slice(32))
      const decrypted = CryptoJS.AES.decrypt({ ciphertext: encryptedData }, CryptoJS.enc.Utf8.parse(this.key), {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
      })
      return decrypted.toString(CryptoJS.enc.UTF8)
    },
    downloadBlob(data, filename) {
      const blob = new Blob([data], { type: 'application/octet-stream' })
      const url = window.URL.createObjectURL(blob)
      const link = document.createElement('a')
      link.href = url
      link.setAttribute('download', filename)
      document.body.appendChild(link)
      link.click()
      document.body.removeChild(link)
    }
  }
}
</script>