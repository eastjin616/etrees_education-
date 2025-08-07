package com.spring.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.dao.CodeDao;
import com.spring.board.service.boardService;
import com.spring.board.service.codeService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;

@Service
public class codeServiceImpl implements codeService{
	
	@Autowired
	CodeDao codeDao;

	@Override
	public List<CodeVo> codeListByType(String codeType) {
		// TODO Auto-generated method stub
		return codeDao.CodeListByType(codeType);
	}
}
