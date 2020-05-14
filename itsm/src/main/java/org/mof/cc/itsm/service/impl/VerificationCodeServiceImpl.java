package org.mof.cc.itsm.service.impl;

import java.util.Random;

import javax.annotation.Resource;

import org.mof.cc.itsm.service.VerificationCodeService;
import org.mof.cc.itsm.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月1日 下午7:35:57
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService{
	
	private static final Logger log = LoggerFactory.getLogger(VerificationCodeService.class);

	@Resource
	private RedisUtil redisUtil;

	@Override
	public String saveCode(int id) {
		String vefificationCode = generateRandomCode();
		redisUtil.set(id + "", vefificationCode);
		redisUtil.expire(id + "", 30);
		log.info("生成验证码{}：{}", id, vefificationCode);
		return "success";
	}

	@Override
	public String getCode(int id) {
		return redisUtil.get(id + "");
	}
	
	/*
	 * 生成随机六位验证码
	 */
	public String generateRandomCode() {
		Random r = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		//六位数
		for(int i = 0; i < 6; i++) {
			stringBuffer.append(r.nextInt(10));
		}
		return stringBuffer.toString();
	}

}
