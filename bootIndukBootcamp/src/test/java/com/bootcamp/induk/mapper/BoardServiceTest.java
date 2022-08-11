package com.bootcamp.induk.mapper;

import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.service.interfaces.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    @Commit
    void test1() throws Exception {
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("title");
        boardDto.setContents("1234");
        boardDto.setWriter("asdf");

        int insertCnt = boardService.writeBoard(boardDto);
        assertThat(insertCnt).isEqualTo(1);
    }
}
