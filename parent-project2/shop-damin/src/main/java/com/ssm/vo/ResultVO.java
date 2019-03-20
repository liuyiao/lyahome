package com.ssm.vo;

/**
 *
 * 0成功
 * 失败：500
 * {
 *     "code": 0, //规定成功的状态码：0
 *     "msg": "",
 *     "count": 1000, //分页总个数
 *     "data": [{}, {}]//分页数据
 * }
 */
public class ResultVO {
    private int code;
    private String msg;
    private Long count;
    private Object data;

    /**
     * 成功
     * @param msg
     * @param count
     * @param data
     * @return
     */
    public static ResultVO success(String msg, long count, Object data){
        return new ResultVO(0,msg,count,data);

    }

    /**
     * 成功
     * @param count
     * @param data
     * @return
     */
    public static ResultVO success(long count, Object data){
        return new ResultVO(0,"success",count,data);

    }


    /**
     * 失败
     * @param msg
     * @return
     */
    public static ResultVO error(String msg){
        return new ResultVO(500,msg,null,null);

    }

    /**
     * 失败
     * @return
     */
    public static ResultVO error(){
        return new ResultVO(500,"error",null,null);

    }

    public static Object success() {
        return new ResultVO(0,"success",null,null);
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", date=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultVO(int code, String msg, Long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
