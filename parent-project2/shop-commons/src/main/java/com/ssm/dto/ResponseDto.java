package com.ssm.dto;

import com.ssm.utils.ErrorCode;

/**
 *   {
 *     "error_code": 0,
 *     "msg":"",
 *     "data": {
 *       "uid": "1",
 *       "userAccount": "12154545",
 *       "userName": "吴系挂",
 *     }
 *   }
 */
public class ResponseDto {
    private Integer error_code;
    private String msg;
    private Object obj;

    /**
     * 成功
     * @param obj
     * @return
     */
    public static ResponseDto success(Object obj) {
        return  new ResponseDto(ErrorCode.SUCCESS,"",obj);
    }

    /**
     * 失败
     * @param error_code
     * @param msg
     * @return
     */
    public  static  ResponseDto error(Integer error_code,String msg){

        return  new ResponseDto(error_code,msg,null);
    }

    public ResponseDto(Integer error_code, String msg, Object obj) {
        this.error_code = error_code;
        this.msg = msg;
        this.obj = obj;
    }



    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "error_code=" + error_code +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                '}';
    }

    public ResponseDto() {
    }
}
