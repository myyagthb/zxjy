package edu.hut.aiassistant.exception;



import edu.hut.aiassistant.enums.RespEnum;
import edu.hut.aiassistant.resp.R;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
public class ControllerExceptionHandler {



    //系统异常同意处理
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public R handlerException(SystemException e){
        R r = new R();
        r.setCode(RespEnum.FAIL.getCode());
        r.setMsg(e.getE().getDesc());
        r.setContent(null);
        return r;
    }

    //校验异常同意处理
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public R handlerException(BindException e){
        R r = new R();
        r.setCode(RespEnum.FAIL.getCode());
        r.setMsg(e.getMessage());
        r.setContent(null);
        return r;
    }





    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public R handlerException(RuntimeException e) {
        return new R(RespEnum.FAIL.getCode(), "系统运行异常，请联系管理员", null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handlerException(Exception e) {
        return new R(RespEnum.FAIL.getCode(), "系统异常，请联系管理员", null);
    }
}
