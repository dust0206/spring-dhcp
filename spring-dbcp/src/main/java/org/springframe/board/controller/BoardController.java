package org.springframe.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframe.board.dto.BoardDTO;
import org.springframe.board.dto.PageDTO;
import org.springframe.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
// Type이 같으면 식별할수 있는 문자열을 지정 - ID를 지정
@Qualifier("boardService")	// 예선통과자, 여러개 중에 1개
public class BoardController {
	
	// 자동 DI
//	@Setter(onMethod_ = @Autowired)
	@Autowired
	private BoardService service;
	
	@GetMapping(value = {"/index.do", "/index"}) 
	public String index() throws Exception {
		return "board/index";
	}
	
	@GetMapping("/selectPageListBoard.do")
	public String selectPageListBoard(Model model, @RequestParam(value="page", required = false, defaultValue = "1") int page) throws Exception {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		// 데이터 처리
		List<BoardDTO> pagingList = service.selectPageListBoard(page);
		
		// 페이징 처리
		PageDTO pageDTO = service.paginParam(page);
		
		// 데이터 결과 넘기기
		model.addAttribute("pagingList", pagingList);
		model.addAttribute("paging", pageDTO);
		return "board/selectPageListBoard";
	}
	
	// board/paging?page=2
	// /board/paging
	// 처음 페이지 요청은 1페이지를 보여줌
	// required : 필수값이 아님을 표시
	// defaultValue : 
	@GetMapping("/pagingBoard.do")
	public String pagingBoard(Model model, @RequestParam(value ="page", required = false, defaultValue = "1") int page) throws Exception {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.info("Model : " + model + ", page : " + page);
		
		List<BoardDTO> pagingList = service.pagingBoard(page);
		log.info("데이터 베이스 리턴된 결과값(pagingList) : " + pagingList);
		
		PageDTO pageDTO = service.paginParam(page);
		log.info("pageDTO : " + pageDTO);
		model.addAttribute("pagingList", pagingList);
		model.addAttribute("paging", pageDTO);
		
		return "board/pagingBoard";
	}
	
	@GetMapping("/insertBoard.do")
	public String insertFormBoard() {
		return "board/insertFormBoard";
	}
	
	@PostMapping("/insertBoard.do")
	public String insertBoard(@ModelAttribute() BoardDTO  boardDTO, Model model, HttpServletRequest request ) {
		log.info(boardDTO);
		
		String referer = request.getHeader(("referer"));
		model.addAttribute("prevUrl", referer);
		
		if( !(boardDTO.getTitle().length() > 2 && boardDTO.getTitle().length() < 100) ) {
			model.addAttribute("field", "title");
			model.addAttribute("message", "2자이상 100자이하 입력");
			return "board/message";
		} else if( !(boardDTO.getUsername().length() > 2 && boardDTO.getUsername().length() < 5) ) {
			model.addAttribute("field", "username");
			model.addAttribute("message", "2자이상 5자이하 입력");
			return "board/message";
		} else if( !(boardDTO.getPasswd().length() == 4) ) {
			model.addAttribute("field", "password");
			model.addAttribute("message", "4자 입력");
			return "board/message";
		} else if( !(boardDTO.getContents().length() > 1 && boardDTO.getContents().length() < 300) ) {
			model.addAttribute("field", "contents");
			model.addAttribute("message", "1자이상 300자이하 입력");
			return "board/message";
		}
		
		service.insertBoard(boardDTO);
		
//		return "redirect:/board/selectPageListBoard.do";
		return "redirect:/board/pagingBoard.do";
	}
	
	@GetMapping("/selectBoard.do")
	public String selectBoard(@RequestParam() int seq, 
							   Model model) {
		
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		// hits 증가
		service.updatetHits(seq);
		BoardDTO boardDTO = service.selectBoard(seq);
		log.info("boardDTO : " + boardDTO);
		boardDTO.getContents().replace("\n", "<br>");
		model.addAttribute("dto", boardDTO);
		return "board/selectBoard";
	}
	
	@GetMapping("/updateBoard.do")
	public String updateFormBoard(@ModelAttribute() BoardDTO  boardDTO, Model model) {
		log.info(boardDTO);
		
		model.addAttribute("dto", service.selectBoard(boardDTO.getSeq()));
		return "board/updateFormBoard";
	}
	
	@PostMapping("/updateBoard.do") 
	public String updateBoard(BoardDTO boardDTO, Model model, HttpServletRequest request) {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		String referer = request.getHeader(("referer"));
		model.addAttribute("prevUrl", referer);
		
		log.info(boardDTO);
		// 패스워드 가져오기
		BoardDTO passDB = service.selectBoard(boardDTO.getSeq());
		
		log.info("가져온 패스워드 : " + passDB.getPasswd());
		log.info("입력한 패스워드 : " + boardDTO.getPasswd());
//		// 패스워드 비교
		if( boardDTO.getPasswd().equals(passDB.getPasswd())) {
			System.out.println("========================= OK ========================");
			service.updateBoard(boardDTO);
			return "redirect:/board/selectPageListBoard.do";
		} else {
			model.addAttribute("field", "passwd");
			model.addAttribute("message", "입력한 패스워드가 맞지 않습니다.");
			return "board/message";
		}
	}
	
	@PostMapping("deleteBoard.do")
//	public String deleteBoard(@RequestParam int seq, @RequestParam String passwd) {
	public String deleteBoard(BoardDTO boardDTO, Model model, HttpServletRequest request) throws Exception {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.info("delete param : " + boardDTO);
		
		String referer = request.getHeader("referer");
		model.addAttribute("prevUrl", referer);
		
		// 패스워드 가져오기
		BoardDTO passDB = service.selectBoard(boardDTO.getSeq());
		// 패스워드 비교
		if(passDB.getPasswd().equals(boardDTO.getPasswd())) {
			service.deleteBoard(boardDTO.getSeq());
			return "redirect:/board/selectPageListBoard.do";
		} else {
			model.addAttribute("field", "passwd");
			model.addAttribute("message", "입력한 패스워드가 맞지 않습니다.");
			return "board/message";
		}
	}
}
