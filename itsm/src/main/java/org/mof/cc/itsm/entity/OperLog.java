package org.mof.cc.itsm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_oper_log")
public class OperLog extends Model<OperLog> {

    private static final long serialVersionUID=1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模块标题
     */
    private String title;

    /**
     * 业务类型（0其它，1，增加，2删除，3修改，4查询）
     */
    private String businessType;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作类别（0其它，1后台用户，2手机端用户）
     */
    private Integer operType;

    /**
     * 操作人员
     */
    private String operName;

    /**
     * 请求URL
     */
    private String operUrl;

    /**
     * 主机地址
     */
    private String operIp;

    /**
     * 操作地点
     */
    private String operLocation;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 返回参数
     */
    private String responseJson;

    /**
     * 操作状态（1正常，0异常）
     */
    private Integer status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private LocalDateTime operTime;

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
