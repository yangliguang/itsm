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
 * @since 2020-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_question")
public class Question extends Model<Question> {

    private static final long serialVersionUID=1L;

    /**
     * ID，自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 问题简述
     */
    private String quetion;

    /**
     * 问题描述
     */
    private String questionDetail;

    /**
     * 提问者
     */
    private String askUser;

    /**
     * 提问时间
     */
    private LocalDateTime askTime;

    /**
     * 回复
     */
    private String reply;

    /**
     * 回复人
     */
    private String replyUser;

    /**
     * 回复时间
     */
    private LocalDateTime replyTime;

    /**
     * 问题点赞数
     */
    private Integer likes;

    /**
     * 问题踩数
     */
    private Integer unlikes;

    /**
     * 是否删除，0，否，1，已删除
     */
    private Boolean isDeleted;

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
