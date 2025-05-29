package com.easyBuy.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("auth_user_authority")
@ApiModel(value = "AuthUserAuthority对象", description = "")
public class AuthUserAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userId;

    private String authoritiesId;
}
