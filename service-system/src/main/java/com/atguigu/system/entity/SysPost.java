package com.atguigu.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 岗位信息表
 * </p>
 *
 * @author atguigu
 * @since 2022-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysPost implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态（1正常 0停用）
     */
    private Boolean status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 删除标记（0:可用 1:已删除）
     */
    private Integer isDeleted;


}
