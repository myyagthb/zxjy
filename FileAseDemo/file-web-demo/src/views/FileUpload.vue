<template>

</template>

<script setup>


// 解密逻辑 file-二进制-解密-blob-file
import {encryptFile} from "../../public/js/util";

const desFileList = () =>{
  this.files.map((item) => {
    let readers = new FileReader()
    readers.readAsArrayBuffer(item.url) // item.url 为加密后的串
    readers.onload = () => {
      var fileContent = new Uint8Array(readers.result)
      // console.log(fileContent, 'fileContent加密前');
      var encryptedContent = decryptFile(fileContent);
      console.log(encryptedContent, '解密后3333');
      let blob = atobToblob(encryptedContent, item.url.type)
      console.log(blob,'blob');
      let blobToFile = new File([blob], item.url.name, {
        type: item.url.type,
        lastModified: Date.now()
      })
      item.url = blobToFile
      item.name = item.url.name
      item.tag=1
      // blobDownload(blob,blobToFile.name)
    }
  })
  console.log(this.files,'this.files');
  setTimeout(() => {
    this.$refs.files.setFileList(this.files)
  }, 500);
}


export function blobDownload(blob, fileName) {
  // 判断浏览器类型
  if (window.navigator && window.navigator.msSaveOrOpenBlob) {
    // 如果是 IE 浏览器，使用 msSaveOrOpenBlob 方法
    window.navigator.msSaveOrOpenBlob(blob, fileName);
  } else {
    var myUrl = URL.createObjectURL(blob); //创建图片的临时url
    // downloadFile(myUrl,name)
    var a = document.createElement("a") //新建一个a链接
    a.setAttribute("href", myUrl) // a链接的url为图片的url
    a.setAttribute("download", fileName)
    a.setAttribute("target", "_blank")
    let clickEvent = document.createEvent("MouseEvents");
    clickEvent.initEvent("click", true, true);
    a.dispatchEvent(clickEvent);
  }
}


// 上传文件
const handleFileUpload =(file) =>{
  this.upload.isUploading = true
  let reader = new FileReader()
  reader.readAsArrayBuffer(file.file) // 以二进制数据读取文件
  reader.onload = () => {
    var fileContent =  new Uint8Array(reader.result) // 转成Uint8Array类型
    var encryptedContent = encryptFile(fileContent); // 加密
    console.log(encryptedContent, '加密后串');
    let blobType = file.file.type
    let fileName = file.file.name
    let blob = this.atobToblob(encryptedContent, blobType) // 将加密后串转blob
    let blobToFile = new File([blob], fileName, {
      type: blobType,
      lastModified: Date.now()
    }); // 得到加密文件
    let formData = new FormData();
    formData.append("file", blobToFile); // 传给后端即可

  }


}
const atobToblob = (intArray,type) =>{
  // 创建一个 Blob 对象
  var blob = new Blob([intArray], { type: type });
  return blob;
}



</script>



<style scoped>

</style>