package com.mybatis.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @ApiModelProperty(value = "账号")
  private String username;

  private String password;

  @ApiModelProperty(value = "姓名")
  private String nickname;

  @ApiModelProperty(value = "邮箱")
  private String email;

  @ApiModelProperty(value = "电话")
  private String phone;

  @ApiModelProperty(value = "状态")
  private Integer status;

  private Date createdAt;

  private Integer createdBy;

  private Date updatedAt;

  private Integer updatedBy;
}
