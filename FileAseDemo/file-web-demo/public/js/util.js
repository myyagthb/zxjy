import CryptoJS from 'crypto-js';
import CryptoU8array from "./crypto-en"
var key = CryptoJS.enc.Utf8.parse("1111111111111111"); //十六位十六进制数作为秘钥
var iv = CryptoJS.enc.Utf8.parse('1111111111111111');//十六位十六进制数作为秘钥偏移量
// 使用 AES 进行文件加密
export function encryptFile(word) {
    const messageWordArray = CryptoU8array.u8array.parse(word);
    var encrypted = CryptoJS.AES.encrypt(messageWordArray, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });
    let encryptedBytes = encrypted.ciphertext
    return CryptoU8array.u8array.stringify(encryptedBytes)
}
// 使用 AES 进行文件解密
export function decryptFile(word) {
    const messageWordArray = CryptoU8array.u8array.parse(word);
    var dcBase64String = messageWordArray.toString(CryptoJS.enc.Base64);
    var decrypt = CryptoJS.AES.decrypt(dcBase64String, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });
    // var decryptedStr = decrypt.toString(CryptoJS.enc.Utf8);
    return CryptoU8array.u8array.stringify(decrypt)
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