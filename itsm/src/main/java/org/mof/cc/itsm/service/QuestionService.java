package org.mof.cc.itsm.service;

import org.mof.cc.itsm.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-29
 */
public interface QuestionService extends IService<Question> {
	public Question queryQuestionById(int id);
	
	public void updateQuestion(Question question);
	
	public void saveQuestion(Question question);
	
	public void deleteQuestion(int id);
	
}
