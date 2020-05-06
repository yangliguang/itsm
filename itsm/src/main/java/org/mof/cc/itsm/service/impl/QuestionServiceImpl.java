package org.mof.cc.itsm.service.impl;

import org.mof.cc.itsm.entity.Question;
import org.mof.cc.itsm.mapper.QuestionMapper;
import org.mof.cc.itsm.service.QuestionService;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangliguang
 * @since 2020-04-29
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
	public Question queryQuestionById(int id) {
		return super.getById(id);
	}
	
	public void updateQuestion(Question question) {
		UpdateWrapper<Question> questionUpdateWrapper = new UpdateWrapper<>();
		System.out.println(question);
		questionUpdateWrapper.eq("id", question.getId());
		System.out.println(questionUpdateWrapper);
		super.update(question, questionUpdateWrapper);
		return;
	}
	
	public void saveQuestion(Question question) {
		super.save(question);
	}
	
	public void deleteQuestion(int id) {
		super.removeById(id);
	}
}
