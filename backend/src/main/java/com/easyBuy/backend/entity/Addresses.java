package com.easyBuy.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@ApiModel(value = "Addresses对象", description = "")
public class Addresses implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    private String city;

    private String phoneNumber;

    private String state;

    private String street;

    private String zipCode;

    private String userId;

    private String name;

    @TableLogic
    private Boolean isDeleted;
}
