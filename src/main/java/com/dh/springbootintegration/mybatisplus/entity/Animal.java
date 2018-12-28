package com.dh.springbootintegration.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.dh.springbootintegration.mybatisplus.enums.AnimalSex;
import com.dh.springbootintegration.mybatisplus.enums.AnimalType;
import lombok.*;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "ANIMAL")
public class Animal {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "NAME",exist = true)
    private String name;
    @TableField(value = "TYPE",exist = true)
    @EnumValue
    private AnimalType type;
    @TableField(value = "SEX",exist = true)
    private AnimalSex sex;
    @TableField(value = "MASTER",exist = true)
    private String master;
    @TableField(value = "IS_DEL",exist = true)
    @TableLogic
    private int isDel;
}
