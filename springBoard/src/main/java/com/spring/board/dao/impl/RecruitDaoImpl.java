package com.spring.board.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.dao.CodeDao;
import com.spring.board.dao.RecruitDao;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CereerVo;
import com.spring.board.vo.CertificateVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.EducationVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.RecruitVo;

@Repository
public class RecruitDaoImpl implements RecruitDao{
   
   @Autowired
   private SqlSession sqlSession;

   @Override
   public int newRecruit(Map<String, Object> param) {
      // TODO Auto-generated method stub
      return sqlSession.insert("recruit.newRecruit",param);
   }

   @Override
   public RecruitVo findByNameAndPhone(Map<String, Object> param) {
      // TODO Auto-generated method stub
      return sqlSession.selectOne("recruit.findByNameAndPhone", param);
   }

   
   
   @Override
   public int updateRecruit(Map<String, Object> param) {
       return sqlSession.update("recruit.updateRecruit", param);
   }

   @Override
   public List<EducationVo> selectEducations(Long seq) {
       return sqlSession.selectList("recruit.selectEducations", seq);
   }

   @Override
   public List<CereerVo> selectCareers(Long seq) {
       return sqlSession.selectList("recruit.selectCareers", seq);
   }

   @Override
   public List<CertificateVo> selectCertificates(Long seq) {
       return sqlSession.selectList("recruit.selectCertificates", seq);
   }

   @Override
   public int deleteEducationBySeq(Long seq) {
       return sqlSession.delete("recruit.deleteEducationBySeq", seq);
   }

   @Override
   public int deleteCareerBySeq(Long seq) {
       return sqlSession.delete("recruit.deleteCareerBySeq", seq);
   }

   @Override
   public int deleteCertificateBySeq(Long seq) {
       return sqlSession.delete("recruit.deleteCertificateBySeq", seq);
   }

   @Override
   public int insertEducationList(Long seq, List<EducationVo> list) {
       Map<String, Object> p = new HashMap<>();
       p.put("seq", seq);
       p.put("list", list);
       return sqlSession.insert("recruit.insertEducationList", p);
   }

   @Override
   public int insertCareerList(Long seq, List<CereerVo> list) {
       Map<String, Object> p = new HashMap<>();
       p.put("seq", seq);
       p.put("list", list);
       return sqlSession.insert("recruit.insertCareerList", p);
   }

   @Override
   public int insertCertificateList(Long seq, List<CertificateVo> list) {
       Map<String, Object> p = new HashMap<>();
       p.put("seq", seq);
       p.put("list", list);
       return sqlSession.insert("recruit.insertCertificateList", p);
   }

}
