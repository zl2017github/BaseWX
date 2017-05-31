package cn.com.et.service.message.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.et.dao.DaoSupport;
import cn.com.et.entity.message.Message;
import cn.com.et.service.message.MessageService;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public Integer save(Message message) throws Exception {
		dao.saveReturnId("MessageMapper.save", message);
		return message.getId();
	}

	
}
