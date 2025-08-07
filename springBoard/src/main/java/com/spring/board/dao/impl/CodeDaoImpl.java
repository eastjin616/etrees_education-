package com.spring.board.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.dao.CodeDao;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;

@Repository
public class CodeDaoImpl implements CodeDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<CodeVo> CodeListByType(String codeType) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("code.CodeListByType",codeType);
	}
	
	
}
