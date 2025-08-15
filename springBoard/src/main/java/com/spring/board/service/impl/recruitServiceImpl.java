package com.spring.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.board.dao.RecruitDao;
import com.spring.board.service.recruitService;
import com.spring.board.vo.CereerVo;
import com.spring.board.vo.CertificateVo;
import com.spring.board.vo.EducationVo;
import com.spring.board.vo.RecruitVo;

@Service
public class recruitServiceImpl implements recruitService{
   
   @Autowired
   RecruitDao recruitDao;

   @Override
   public int newRecruit(Map<String, Object> param) {
      
      return recruitDao.newRecruit(param);
   }

   @Override
   public RecruitVo findByNameAndPhone(Map<String, Object> param) {

      return recruitDao.findByNameAndPhone(param);
   }
   

//   =========================================================
   
   @Override
   public void updateRecruit(RecruitVo r, boolean submit) {
       Map<String, Object> p = new HashMap<>();
       p.put("seq",       r.getSeq());
       p.put("name",      r.getName());
       p.put("birth",     r.getBirth());
       p.put("gender",    r.getGender());
       p.put("phone",     r.getPhone());
       p.put("email",     r.getEmail());
       p.put("address",   r.getAddress());
       p.put("hopeArea",  r.getHopeArea());
       p.put("jobType",   r.getJobType());
       p.put("submit",    submit ? "Y" : "N");
       
       System.out.println(submit + "여기는 섭스 임풀인데유");
       
       if(submit == true) {
    	   recruitDao.submitRecruit(p);
       }else {
    	   recruitDao.updateRecruit(p);
       }
       
   }

   @Override
   public List<EducationVo> selectEducations(Long seq) {
       return recruitDao.selectEducations(seq);
   }

   @Override
   public List<CereerVo> selectCareers(Long seq) {
       return recruitDao.selectCareers(seq);
   }

   @Override
   public List<CertificateVo> selectCertificates(Long seq) {
       return recruitDao.selectCertificates(seq);
   }
   
   


   @Override
   @Transactional
   public void replaceEducations(Long seq, List<EducationVo> list) {
       recruitDao.deleteEducationBySeq(seq);
       if (list != null && !list.isEmpty())
           recruitDao.insertEducationList(seq, list);
   }

   @Override
   @Transactional
   public void replaceCareers(Long seq, List<CereerVo> list) {
       recruitDao.deleteCareerBySeq(seq);
       if (list != null && !list.isEmpty())
           recruitDao.insertCareerList(seq, list);
   }

   @Override
   @Transactional
   public void replaceCertificates(Long seq, List<CertificateVo> list) {
       recruitDao.deleteCertificateBySeq(seq);
       if (list != null && !list.isEmpty())
           recruitDao.insertCertificateList(seq, list);
   }
   
   
}
