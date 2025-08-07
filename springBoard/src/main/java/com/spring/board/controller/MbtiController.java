package com.spring.board.controller;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;

@Controller
public class MbtiController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//====================================MBTI 질문 페이지=============================== 
	@RequestMapping(value="/mbti/mbti.do", method = RequestMethod.GET)
	public String mbtiMain(Model model, @RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		BoardVo boardVo = setParameter(pageNo);
	    List<BoardVo> boardList = boardService.selectMbtiList(boardVo);
	    
	    model.addAttribute("pageNo", pageNo);
	    model.addAttribute("boardList", boardList);
	    return "board/mbti";
	}
	
	private BoardVo setParameter(int no) { // 1, 2, 3, 4
		BoardVo boardVo = new BoardVo();
		
		int startRow = 5 * no - 4;	// 1, 6, 11, 16
		int endRow = 5 * no;		// 5, 10, 15, 20
		boardVo.setStartRow(startRow);
		boardVo.setEndRow(endRow);
		
		return boardVo;
    }

	//====================================답변 제출=============================== 
	@RequestMapping(value="/mbti/mbtiAction.do", method = RequestMethod.POST)
	public String mbtiActionBoard(HttpServletRequest request,
	                               @RequestParam("pageNo") int pageNo,
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
	        	System.out.println("옵션쩜 랭스 의 길이"+"option_".length());
	        	
	            int value =  Integer.parseInt(entry.getValue()[0]);
	            
	            System.out.println("야 이거 벨류값 뭐나오냐 파스인트하면" + value);
	            
	            String type = request.getParameter("type_" + boardNum);
	           
	            sumScore(scores, type, value);
	        }
	    }

	    // 세션에 점수 저장
	    session.setAttribute("scores", scores);

	    // 4페이지에서 최종 계산
	    if (pageNo == 4) {
	        String result =
	            pick(scores, "E", "I") +
	            pick(scores, "N", "S") +
	            pick(scores, "F", "T") +
	            pick(scores, "J", "P");

	        

	        session.setAttribute("mbtiResult", result);
	        
	        // 점수 초기화 짜피 결과 페이지는 mbtiResult만 쓰니께
	        session.removeAttribute("scores");

	        return "redirect:/board/mbtiResult.do";
	    }

	    // 4페이지 아니면 page+1로 넘어가기
	    return "redirect:/mbti/mbti.do?pageNo=" + (pageNo + 1);
	}
	
//====================================점수 계산 뤄쥑=============================== 
private void sumScore(Map<String, Integer> scores, String type, int value) {
		
		// IE
	    char f = type.charAt(0); //I
	    char b = type.charAt(1); //E
	    
	    String fKey = String.valueOf(f);
	    String bKey = String.valueOf(b);

	    int fScore = scores.get(fKey);
	    int bScore = scores.get(bKey);
	    
	    //map에 TYPE에 case에 맞는 값을 더해라 
	    switch (value) {
	    
	    	case 1: scores.put(fKey, fScore + 3); break;
	    	case 2: scores.put(fKey, fScore + 2); break;
	    	case 3: scores.put(fKey, fScore + 1); break;
	    	case 4: break;
	    	case 5: scores.put(bKey, bScore + 1); break;
	    	case 6: scores.put(bKey, bScore + 2); break;
	    	case 7: scores.put(bKey, bScore + 3); break;
	    }

	    System.out.println("타입입 " + type + ", 벨류류 " + value);
	    System.out.println("점수수 " + scores);
	}


	

	//====================================결과 페이지=============================== 
	@RequestMapping(value="/mbti/mbtiResult.do", method = RequestMethod.GET)
	public String mbtiResult(HttpSession session, Model model) {
	    System.out.println("=== 엠비티아이 리절트 실행됨 ===");
	    String result = (String) session.getAttribute("mbtiResult");
	    model.addAttribute("mbtiResult", result);
	    return "board/mbtiResult";
	}
	
	//====================================사전순 빠른걸로=============================== 
	private String pick (Map<String, Integer> scores, String x, String y) {
		int xScore = scores.get(x);
		int yScore = scores.get(y);
		
		if (xScore > yScore) {
			return x; 
		}else if (xScore < yScore) {
			return y;
		}else {			
			return (x.compareTo(y) <= 0) ? x : y;
		}
		
	}
	
}





