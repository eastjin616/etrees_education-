package com.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CereerVo;
import com.spring.board.vo.CertificateVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.EducationVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.RecruitVo;

public interface RecruitDao {

   int newRecruit(Map<String, Object> param);

   RecruitVo findByNameAndPhone(Map<String, Object> param );
   
   int updateRecruit(Map<String, Object> param);

   // 조회
   List<EducationVo>  selectEducations(Long seq);
   List<CereerVo>     selectCareers(Long seq);
   List<CertificateVo> selectCertificates(Long seq);

   // 삭제
   int deleteEducationBySeq(Long seq);
   int deleteCareerBySeq(Long seq);
   int deleteCertificateBySeq(Long seq);

   // 일괄 삽입
   int insertEducationList(@Param("seq") Long seq, @Param("list") List<EducationVo> list);
   int insertCareerList(@Param("seq") Long seq, @Param("list") List<CereerVo> list);
   int insertCertificateList(@Param("seq") Long seq, @Param("list") List<CertificateVo> list);
}