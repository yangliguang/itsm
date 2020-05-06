package org.mof.cc.itsm.controller;

import org.mof.cc.itsm.entity.User;
import org.mof.cc.itsm.service.VerificationCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 验证码接口 集成Redis RESTful接口
 * </p>
 *
 * @author yangliguang
 * @since 2020年5月1日 下午7:29:40
 */
@RestController
@Api(value = "/verificationcode", tags = "验证码模块")
@RequestMapping("/api/verificationcode")
public class VerificationCodeController {

	private static final Logger log = LoggerFactory.getLogger(VerificationCodeController.class);

	@Autowired
	VerificationCodeService verificationCodeService;

	// 存验证码
	@ApiOperation(value = "保存验证码")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> saveCode(@RequestBody User user) {
		try {
			verificationCodeService.saveCode(user.getId());
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("生成验证码出错：{}", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	// 取验证码
	@ApiOperation(value = "根据用户ID获取验证码")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getCode(@PathVariable("id") int id) {
		try {
			String code = verificationCodeService.getCode(id);
			if (null == code) {
				// 资源不存在，响应404
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			// 200
			return ResponseEntity.ok(code);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询验证码出错：{}", e);
		}
		// 500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
