
//判断对象是否为空
export const isEmptyObject = (obj) => {
    return Object.keys(obj).length === 0;
};


//验证身份证号码正确与否的方法
export const validateIDCard = (idCard) => {
    // 检查长度
    if (idCard.length !== 18) {
        return false;
    }

    // 检查前17位是否为数字，第18位是否为数字或X
    const reg = /^[1-9]\d{5}(18|19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\d{3}(\d|X|x)$/;
    if (!reg.test(idCard)) {
        return false;
    }

    // 校验码计算
    const weightFactors = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
    const checkCodes = ['1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'];
    let sum = 0;

    for (let i = 0; i < 17; i++) {
        sum += parseInt(idCard[i], 10) * weightFactors[i];
    }

    const calculatedCheckCode = checkCodes[sum % 11];
    const providedCheckCode = idCard[17].toUpperCase();

    return calculatedCheckCode === providedCheckCode;
};
