package com.bootcamp.induk.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.domain.PageHandler;
import com.bootcamp.induk.domain.SearchCondition;
import com.bootcamp.induk.service.interfaces.BoardService;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/write")
	public String write(Model m) {
		m.addAttribute("mode", "new");
		return "board/regBoard";	// 읽기와 쓰기에 사용, 쓰기에 사용할 때는 mode == new
	}
	
	@PostMapping("/write")
	public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) {
		String writer = (String) session.getAttribute("id");
		boardDto.setWriter(writer);
		
		try {			
			if(boardService.writeBoard(boardDto) != 1)
				throw new Exception("Write failed");
			
			rattr.addFlashAttribute("msg", "write_success");
			
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("boardDto", boardDto); // m.addAttribute(boardDto);
			m.addAttribute("mode", "new");
			m.addAttribute("msg", "write_err");
			return "board/regBoard";
		}
	}
	
	@GetMapping("/read")
	public String read(Integer bno, SearchCondition sc, RedirectAttributes rattr, Model m) {
		try {
			int totalCnt = boardService.getSearchResultCnt(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			m.addAttribute("ph", pageHandler);

			m.addAttribute("boardDto", boardService.readBoard(bno));
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "read_err");
		}
		
		return "board/board";
	}
	
	@GetMapping("/list")
	public String list(@ModelAttribute SearchCondition sc, Model m, HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/login/login?toURL=" + request.getRequestURL();	// 로그인을 안 했으면 로그인 화면으로 이동
		
		try {
			int totalCnt = boardService.getSearchResultCnt(sc);
			m.addAttribute("totalCnt", totalCnt);
			
			PageHandler pageHandler = new PageHandler(totalCnt, sc);

			List<BoardDto> list = boardService.getSearchResultPage(sc);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);
			
			Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
			m.addAttribute("startOfToday", startOfToday.toEpochMilli());
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("msg", "list_err");
			m.addAttribute("totalCnt", 0);
		}
		return "board/boardList";	// 로그인을 한 상태이면, 게시판 화면으로 이동
	}
	
	@PostMapping("/modify")
	public String modify(BoardDto boardDto, SearchCondition sc, Model m,
						 HttpSession session, RedirectAttributes rattr) throws Exception {

		String writer = (String) session.getAttribute("id");
		boardDto.setWriter(writer);
		
		try {			
			if(boardService.modifyBoard(boardDto) != 1)
				throw new Exception("Modify failed");

			rattr.addFlashAttribute("msg", "modify_success");
			
			return "redirect:/board/list" + sc.getQueryString();
		} catch (Exception e) {
			e.printStackTrace();

			int totalCnt = boardService.getSearchResultCnt(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			m.addAttribute("ph", pageHandler);

			m.addAttribute(boardDto);
			m.addAttribute("msg", "modify_err");
			
			return "board/board";	// 등록하려던 내용을 보여줘야 함
		}
	}
	
	@PostMapping("/remove")
	public String remove(Integer bno, SearchCondition sc, Model m, HttpSession session, RedirectAttributes rattr) {
		String writer = (String) session.getAttribute("id");
		String msg = "delete_success";
		
		try {			
			if(boardService.removeBoard(bno, writer) != 1)
				throw new Exception("board remove error");			
		} catch (Exception e) {
			e.printStackTrace();
			msg = "delete_err";
		}
		
		rattr.addFlashAttribute("msg", msg);
		
		return "redirect:/board/list" + sc.getQueryString();
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		// 세션을 얻어서(이때 false는 session이 없어도 새로 생성하지 않는다. 반환값 null)
		HttpSession session = request.getSession(false);
		// 세션에 id가 있는지 확인하고 있으면 true 반환
		return session != null && session.getAttribute("id") != null;
	}
}
