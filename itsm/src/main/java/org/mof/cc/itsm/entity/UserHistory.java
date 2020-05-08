package org.mof.cc.itsm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangliguang
 * @since 2020-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_history")
public class UserHistory extends Model<UserHistory> {

    private static final long serialVersionUID=1L;

    /**
     * ID，自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户全称
     */
    private String fullname;

    /**
     * 密码
     */
    private String password;

    /**
     * 使用状态，0不可用，1可用
     */
    private Boolean isused;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 扩展字段1
     */
    private String bak1;

    /**
     * 扩展字段2
     */
    private String bak2;

    /**
     * 扩展字段3
     */
    private String bak3;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
