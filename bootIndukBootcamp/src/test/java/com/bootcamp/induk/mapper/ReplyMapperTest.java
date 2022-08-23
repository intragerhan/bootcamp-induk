package com.bootcamp.induk.mapper;

import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.domain.ReplyDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMybatis
public class ReplyMapperTest {

    @Autowired
    private ReplyMapper replyMapper;

    @DisplayName("댓글 삽입")
    @Test
    public void insert() throws Exception {
        //given
        ReplyDto replyDto = new ReplyDto(355, 0, "hello", "asdf");
        //when
        int result = replyMapper.insertReply(replyDto);
        //then
        assertThat(result).isEqualTo(1);
    }

    @DisplayName("더미 댓글 삽입")
    @Test
    public void insertDummy() throws Exception {
        for(int i = 1; i <= 20; i++) {
            //given
            ReplyDto replyDto = new ReplyDto(355, 14, "reply-contents" + i, "asdf");
            //then
            replyMapper.insertReply(replyDto);
        }
        //then
        assertTrue(replyMapper.getCount(355) >= 20);
    }
}
