package com.dh.springbootintegration.jpa.entity;

import com.dh.springbootintegration.jpa.enums.UseSex;
import com.dh.springbootintegration.jpa.enums.UseState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户模型")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("用户ID")
    private int useId;
    @Column
    @ApiModelProperty("用户姓名")
    private String useName;
    @Column
    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty("用户性别")
    private UseSex useSex;
    @Column
    @ApiModelProperty("用户年龄")
    private int useAge;
    @Column
    @ApiModelProperty("用户身份证号")
    private String useIdNo;
    @Column
    @ApiModelProperty("用户手机号")
    private String usePhoneNum;
    @Column
    @ApiModelProperty("用户邮箱")
    private String useEmail;
    @Column
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @Column
    @ApiModelProperty("修改时间")
    private LocalDateTime modifyTime;
    @Column
    @ApiModelProperty("用户状态")
    @Enumerated(EnumType.ORDINAL)
    private UseState useState;
}
