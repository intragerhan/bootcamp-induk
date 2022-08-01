package com.bootcamp.induk.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.induk.domain.ReplyDto;
import com.bootcamp.induk.service.ReplyService;

//@Controller
//@ResponseBody
@RestController
public class ReplyController {
	@Autowired
	ReplyService service;
	
	// 댓글을 수정하는 메서드
//	@ResponseBody
	@PatchMapping("/comments/{cno}")	// /ch4/comments/70 PATCH
	public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody ReplyDto dto) {
		String replier = "asdf";
		
		dto.setReplier(replier);
		dto.setCno(cno);
		System.out.println("dto = " + dto);
		
		try {
			if(service.modify(dto) != 1)
				throw new Exception("Modify failed");
			
			return new ResponseEntity<>("Modify_success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Modify_err", HttpStatus.BAD_REQUEST);
		}
	}
	
	// 댓글을 등록하는 메서드
//	@ResponseBody
	@PostMapping("/comments")	// /ch4/comments?bno=871	POST
	public ResponseEntity<String> write(@RequestBody ReplyDto dto, Integer bno, HttpSession session) {
//		String commenter = (String) session.getAttribute("id");
		String replier = "asdf";
		dto.setReplier(replier);
		dto.setBno(bno);
		System.out.println("dto = " + dto);
		
		try {
			if(service.write(dto) != 1)
				throw new Exception("Write failed");
			
			return new ResponseEntity<>("Write_success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Write_err", HttpStatus.BAD_REQUEST);
		}
	}
	
	// 지정된 댓글을 삭제하는 메서드
//	@ResponseBody
	@DeleteMapping("/comments/{cno}")	// DELETE /comments/1?bno=871 <-- 삭제할 댓글 번호
	public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
//		String commenter = (String) session.getAttribute("id");
		String replier = "asdf";
		try {
			int rowCnt = service.remove(cno, bno, replier);
		
			if(rowCnt != 1)
				throw new Exception("Delete Failed");
			
			return new ResponseEntity<>("Delete_success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Delete_err", HttpStatus.BAD_REQUEST);
		}
	}
	
	// 지정된 게시물의 모든 댓글을 가져오는 메서드
//	@ResponseBody 
	@GetMapping("/comments")	// /comments?bno=874	GET
	public ResponseEntity<List<ReplyDto>> list(Integer bno) {
		List<ReplyDto> list = null;
		try {
			list = service.getList(bno);
			System.out.println("list = " + list);
			return new ResponseEntity<List<ReplyDto>>(list, HttpStatus.OK);	// 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ReplyDto>>(HttpStatus.BAD_REQUEST);	// 400
		}
		
	}
}
