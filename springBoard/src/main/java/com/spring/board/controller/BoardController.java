package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.spring.board.service.codeService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	@Autowired
	private codeService codeService;
	
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
	public String boardWrite(Model model) throws Exception{
		
		List<CodeVo> codeList = codeService.codeListByType("menu");
	    model.addAttribute("codeList", codeList);
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardInsert(boardVo);
		
		System.out.println("boardType: " + boardVo.getBoardType()); // null이면 문제!
		
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

	//======================check 박스=========================

	@RequestMapping("/board/checkBoxShow.do")
	@ResponseBody
	public List<CodeVo> checkBoxShow() {
	    return codeService.codeListByType("menu");
	}
	
	//======================TYPE list조회=========================
	
//	@RequestMapping("/board/checkBoxResult.do")
//	@ResponseBody
//	public List<BoardVo> checkBoxResult(@RequestParam("types[]") List<String> types) {
//	    
//		System.out.println("여기까지 왔니???");
//		
//		return boardService.checkBoxResult(types);
//	}
	
	@RequestMapping("/board/checkBoxResult.do")
	@ResponseBody
	public List<BoardVo> checkBoxResult(
	    @RequestParam(value = "types[]", required = false) List<String> types,
	    @RequestParam(value = "pageNo", required = false) String pageNoStr
	) {
	    int pageNo = 1;
	    try {
	        if (pageNoStr != null && !pageNoStr.isBlank()) {
	            pageNo = Integer.parseInt(pageNoStr.trim());
	        }
	    } catch (NumberFormatException e) {
	        pageNo = 1;
	    }

	    PageVo pageVo = new PageVo();
	    pageVo.setPageNo(pageNo);
	    pageVo.setBoardTypeList(types);
	    
	    System.out.println("최종 boardTypeList: " + pageVo.getBoardTypeList());
 
	    return boardService.checkBoxResult(pageVo);
	}










	

}
	






