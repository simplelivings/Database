package com.faraway.mpdemo1010.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    //    @TableId(type= IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //乐观锁版本号
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    //逻辑删除标识
    @TableLogic
    private Integer deleted;
}
