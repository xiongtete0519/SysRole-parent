package com.atguigu.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 组织机构
 * </p>
 *
 * @author atguigu
 * @since 2022-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门id
     */
    private Long parentId;

    /**
     * 树结构
     */
    private String treePath;

    /**
     * 排序
     */
    private Integer sortValue;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 电话
     */
    private String phone;

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
