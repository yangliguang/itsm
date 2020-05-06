package org.mof.cc.itsm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mof.cc.itsm.entity.DemoUser;

@Mapper
public interface DemoUserDao {

	@Select("select * from t_user")
	public List<DemoUser> findAll();

	@Select("select * from t_user where username = #{username}")
	public DemoUser findbyUsername(DemoUser u);

	@Insert("insert into t_user('username','fullname','password','isused','create_time','modify_time') values(#{username},#{fullname},#{password},1,now(),now())")
	public int insert(DemoUser u);

	@Delete("delete from t_user where id = #{id}")
	public int delete(DemoUser u);

	@Update("update t_user set password = #{password}")
	public int update(DemoUser u);

}
