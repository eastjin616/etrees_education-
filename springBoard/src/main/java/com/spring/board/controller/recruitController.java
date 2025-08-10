package com.spring.board.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.service.recruitService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CereerVo;
import com.spring.board.vo.CertificateVo;
import com.spring.board.vo.EducationVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.RecruitVo;

@Controller
public class recruitController {
   
   @Autowired 
   recruitService recruitService;
   
   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
   
   @RequestMapping(value = "/recruit/loginScreeen.do", method = RequestMethod.GET)
   public String loginForm() {
      return "recruit/loginScreen";
   }
//============================================================================   
   @RequestMapping(value = "/recruit/login.do", method = RequestMethod.POST)
   public String login(@RequestParam String name,
                       @RequestParam String phone) throws UnsupportedEncodingException {

       Map<String, Object> param = new HashMap<>();
       param.put("name",  name.trim());
       param.put("phone", phone.trim());

       RecruitVo existing = recruitService.findByNameAndPhone(param);
       if (existing == null) {
           recruitService.newRecruit(param); // <selectKey>가 seq를 넣어도 OK(가변 Map)
       }

       String encodedName  = UriUtils.encodeQueryParam(name,  "UTF-8");
       String encodedPhone = UriUtils.encodeQueryParam(phone, "UTF-8");
       return "redirect:/recruit/main.do?name=" + encodedName + "&phone=" + encodedPhone;
   }

   @RequestMapping("/recruit/main.do")
   public String main(@RequestParam String name,
                      @RequestParam String phone,
                      Model model) {
       Map<String, Object> param = new HashMap<>();
       param.put("name",  name.trim());
       param.put("phone", phone.trim());

       RecruitVo recruit = recruitService.findByNameAndPhone(param);
       if (recruit == null) return "redirect:/recruit/loginScreeen.do";

       model.addAttribute("recruit", recruit);
       model.addAttribute("eduList", recruitService.selectEducations(recruit.getSeq()));
       model.addAttribute("carList", recruitService.selectCareers(recruit.getSeq()));
       model.addAttribute("cerList", recruitService.selectCertificates(recruit.getSeq()));
       return "recruit/jobApplicationForm";
   }


//============================================================================   

   
   @RequestMapping(value="/recruit/apply.do", method=RequestMethod.POST)
   @Transactional
   public String apply(@RequestParam String action,
                       @ModelAttribute RecruitVo recruit,
                       @RequestParam(required=false) List<String> eduStart,
                       @RequestParam(required=false) List<String> eduEnd,
                       @RequestParam(required=false) List<String> eduDivision,
                       @RequestParam(required=false) List<String> schoolName,
                       @RequestParam(required=false) List<String> eduLocation,
                       @RequestParam(required=false) List<String> major,
                       @RequestParam(required=false) List<String> grade,
                       @RequestParam(value="startPeriod", required=false) List<String> carStart,
                       @RequestParam(value="endPeriod",   required=false) List<String> carEnd,
                       @RequestParam(value="compName",    required=false) List<String> compName,
                       @RequestParam(value="task",        required=false) List<String> task,
                       @RequestParam(value="location",    required=false) List<String> carLocation,
                       @RequestParam(required=false) List<String> qualifiName,
                       @RequestParam(required=false) List<String> acquDate,
                       @RequestParam(required=false) List<String> organizeName) {

       boolean submit = "SUBMIT".equalsIgnoreCase(action);
       recruitService.updateRecruit(recruit, submit);

       Long seq = recruit.getSeq();
       recruitService.replaceEducations(seq,  packEducations(seq, eduStart, eduEnd, eduDivision, schoolName, eduLocation, major, grade));
       recruitService.replaceCareers(seq,     packCareers(seq, carStart, carEnd, compName, task, carLocation));
       recruitService.replaceCertificates(seq, packCertificates(seq, qualifiName, acquDate, organizeName));

       // 메시지 없이 로그인 화면으로
       return "redirect:/recruit/loginScreeen.do";
   }


   // ------ pack helpers ------
   private List<EducationVo> packEducations(Long seq, List<String> s, List<String> e, List<String> div,
                                            List<String> sch, List<String> loc, List<String> major,
                                            List<String> grade) {
       List<EducationVo> list = new ArrayList<>();
       if (s == null) return list;
       for (int i = 0; i < s.size(); i++) {
           EducationVo vo = new EducationVo();
           vo.setSeq(Long.valueOf(seq));
           vo.setStartPeriod(s.get(i));
           vo.setEndPeriod(e.get(i));
           vo.setDivision(div.get(i));
           vo.setSchoolName(sch.get(i));
           vo.setLocation(loc.get(i));
           vo.setMajor(major.get(i));
           vo.setGrade(grade.get(i));
           list.add(vo);
       }
       return list;
   }

   private List<CereerVo> packCareers(Long seq, List<String> s, List<String> e,
                                      List<String> comp, List<String> task, List<String> loc) {
       List<CereerVo> list = new ArrayList<>();
       if (s == null) return list;
       for (int i = 0; i < s.size(); i++) {
           CereerVo vo = new CereerVo();
           vo.setSeq(Long.valueOf(seq));
           vo.setStartPeriod(s.get(i));
           vo.setEndPeriod(e.get(i));
           vo.setCompName(comp.get(i));
           vo.setTask(task.get(i));
           vo.setLocation(loc.get(i));
           list.add(vo);
       }
       return list;
   }

   private List<CertificateVo> packCertificates(Long seq, List<String> name,
                                                List<String> acq, List<String> org) {
       List<CertificateVo> list = new ArrayList<>();
       if (name == null) return list;
       for (int i = 0; i < name.size(); i++) {
           CertificateVo vo = new CertificateVo();
           vo.setSeq(Long.valueOf(seq));
           vo.setQualifiName(name.get(i));
           vo.setAcquDate(acq.get(i));
           vo.setOrganizeName(org.get(i));
           list.add(vo);
       }
       return list;
   }

}

