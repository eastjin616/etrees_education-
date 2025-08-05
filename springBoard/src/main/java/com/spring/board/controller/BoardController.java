package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model,PageVo pageVo) throws Exception{
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		int page = 1;
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);;
		}
		
		boardList = boardService.SelectBoardList(pageVo);
		totalCnt = boardService.selectBoardCnt();
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		
		return "board/boardList";
	}
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception{
		
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardInsert(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
//===========================================수정===============================
	
	@RequestMapping(value = "/board/boardUpdate.do", method = RequestMethod.GET)
	public String boardUpdate(
							  @RequestParam("boardType") String boardType,
	                          @RequestParam("boardNum") int boardNum,
	                          Model model) throws Exception {

	    BoardVo boardVo = boardService.selectBoard(boardType, boardNum); //boardNum에 일치하는 값을 조회해서 들고오기
	    
	    model.addAttribute("board", boardVo);

	    return "board/boardUpdate";
	}
	
	@RequestMapping(value = "/board/boardUpdateAction.do", method = RequestMethod.GET)
	@ResponseBody
	public String boardUpdateAction(BoardVo boardVo) throws Exception {
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardUpdate(boardVo);
		result.put("success", (resultCnt > 0)?"Y":"N");
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println(boardVo.getBoardNum()+"여기는 controller");
		
		return callbackMsg;
	}
	
	//====================================삭제===============================
	
	@RequestMapping(value = "/board/boardDelete.do", method = RequestMethod.GET)
	public String deleteBoard(
	        @RequestParam("boardNum") int boardNum,
	        RedirectAttributes redirectAttributes) throws Exception {

	    int result = boardService.boardDelete(boardNum);

	    if (result > 0) {
	    	return "redirect:/board/boardList.do?pageNo=1";
	    } else {
	    	 return "/board/boardUpdateAction.do"; 
	    }
	}
	//====================================MBTI 질문 페이지=============================== 
	//====================================MBTI 질문 페이지=============================== 
	//====================================MBTI 질문 페이지=============================== 
	@RequestMapping(value="/board/mbti.do", method = RequestMethod.GET)
	public String mbtiBoard(Locale locale, Model model,
	         @RequestParam(value="page", defaultValue="1") int page,
	         PageVo pageVo) throws Exception {

	    pageVo.setPageNo(page);
	    pageVo.setPageSize(5);

	    List<BoardVo> boardList = boardService.selectBoardList1(pageVo);

	    int totalCnt = boardService.selectBoardCnt();
	    int maxPages = (int) Math.ceil((double) totalCnt / pageVo.getPageSize());

	    model.addAttribute("boardList", boardList);
	    model.addAttribute("curtPage", page);     
	    model.addAttribute("maxPages", maxPages); 

	    return "board/mbti";
	}

	//====================================답변 제출=============================== 
	@RequestMapping(value="/board/mbtiAction.do", method = RequestMethod.POST)
	public String mbtiActionBoard(HttpServletRequest request,
	                               @RequestParam("page") int page,
	                               HttpSession session) {

	    Map<String, Integer> scores = (Map<String, Integer>) session.getAttribute("scores");
	    if (scores == null) {
	        scores = new HashMap<>();
	        scores.put("E", 0); scores.put("I", 0);
	        scores.put("N", 0); scores.put("S", 0);
	        scores.put("F", 0); scores.put("T", 0);
	        scores.put("J", 0); scores.put("P", 0);
	    }

	    Map<String, String[]> params = request.getParameterMap();
	    for (Map.Entry<String, String[]> entry : params.entrySet()) {
	        if (entry.getKey().startsWith("option_")) {
	            int boardNum = Integer.parseInt(entry.getKey().substring("option_".length()));
	            int value = Integer.parseInt(entry.getValue()[0]);
	            String type = request.getParameter("type_" + boardNum);
	            calculateScore(scores, type, value);
	        }
	    }

	    // 세션에 점수 저장 후...
	    session.setAttribute("scores", scores);

	    // 4페이지에서 최종 계산
	    if (page == 4) {
	        String result = (scores.get("E") >= scores.get("I") ? "E" : "I")
	                      + (scores.get("N") >= scores.get("S") ? "N" : "S")
	                      + (scores.get("F") >= scores.get("T") ? "F" : "T")
	                      + (scores.get("J") >= scores.get("P") ? "J" : "P");

	        // 점수 초기화
	        session.removeAttribute("scores");

	        session.setAttribute("mbtiResult", result);
	        return "redirect:/board/mbtiResult.do";
	    }

	    // 4페이지 아니면 page+1로 넘어가기
	    return "redirect:/board/mbti.do?page=" + (page + 1);
	}
	
	//====================================점수 계산 메서드=============================== 
	private void calculateScore(Map<String, Integer> scores, String type, int value) {
	    int fScore = 0;
	    int bScore = 0;

	    switch (value) {
	        case 1: fScore = 3; break;
	        case 2: fScore = 2; break;
	        case 3: fScore = 1; break;
	        case 4: fScore = 0; bScore = 0; break;
	        case 5: bScore = 1; break;
	        case 6: bScore = 2; break;
	        case 7: bScore = 3; break;
	    }

	    switch (type) {
	        case "EI":
	            scores.put("E", scores.get("E") + fScore);
	            scores.put("I", scores.get("I") + bScore);
	            break;
	        case "NS":
	            scores.put("N", scores.get("N") + fScore);
	            scores.put("S", scores.get("S") + bScore);
	            break;
	        case "FT":
	            scores.put("F", scores.get("F") + fScore);
	            scores.put("T", scores.get("T") + bScore);
	            break;
	        case "JP":
	            scores.put("J", scores.get("J") + fScore);
	            scores.put("P", scores.get("P") + bScore);
	            break;
	    }
	    
	    System.out.println("타입입 " + type + ", 벨류류" + value);
	    System.out.println("점수수 " + scores);
	}


	//====================================결과 페이지=============================== 
	@RequestMapping(value="/board/mbtiResult.do", method = RequestMethod.GET)
	public String mbtiResult(HttpSession session, Model model) {
	    System.out.println("=== MBTI 리절트 실행됨 ===");
	    String result = (String) session.getAttribute("mbtiResult");
	    model.addAttribute("mbtiResult", result);
	    return "board/mbtiResult";
	}


	

}





