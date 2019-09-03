package cn.stylefeng.guns.business.modular.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author tolyzty
 * @date 2019-07-31 09:06:38
 */

public class EnumTypeVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String desc;

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
