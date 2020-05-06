package org.mof.cc.itsm.service;
/**
 * <p>
 * 
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月1日 下午7:33:51
 */
public interface VerificationCodeService {

	//存验证码
	public String saveCode(int id);
	//取验证码
	public String getCode(int id);
}
