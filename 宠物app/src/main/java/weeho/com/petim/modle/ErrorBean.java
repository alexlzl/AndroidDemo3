package weeho.com.petim.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangkui on 2017/4/20.
 */

public class ErrorBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /*错误代码*/
    private String code;
    /*错误信息*/
    private String info;


    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getInfo() {
        return info;
    }
}
