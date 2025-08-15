package com.spring.board.service;

import java.util.List;
import java.util.Map;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CereerVo;
import com.spring.board.vo.CertificateVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.EducationVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.RecruitVo;

public interface recruitService {

   int newRecruit(Map<String, Object> param);
   
   RecruitVo findByNameAndPhone(Map<String, Object> param );
   
   // 저장(부모 업데이트)
   void updateRecruit(RecruitVo recruit, boolean submit);

   // 자식 조회
   List<EducationVo>  selectEducations(Long seq);
   List<CereerVo>     selectCareers(Long seq);
   List<CertificateVo> selectCertificates(Long seq);

   // 삭제 후 일괄 insert
   void replaceEducations(Long seq, List<EducationVo> list);
   void replaceCareers(Long seq, List<CereerVo> list);
   void replaceCertificates(Long seq, List<CertificateVo> list);
   
   //서브밋 제출
   
   
}