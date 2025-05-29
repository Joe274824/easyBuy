package com.easyBuy.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Joe Xuanqiao Zhang
 * @since 29-05-2025
 */
@Getter
@Setter
@TableName("auth_user_details")
@ApiModel(value = "AuthUserDetails对象", description = "")
public class AuthUserDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    private LocalDateTime createdOn;

    private String email;

    private Boolean enabled;

    private String firstName;

    private String lastName;

    private String password;

    private String phoneNumber;

    private LocalDateTime updatedOn;

    private String provider;

    private String verificationCode;

    @TableLogic
    private Boolean isDeleted;
}
