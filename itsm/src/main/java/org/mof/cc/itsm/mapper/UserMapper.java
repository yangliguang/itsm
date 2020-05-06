package org.mof.cc.itsm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mof.cc.itsm.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
