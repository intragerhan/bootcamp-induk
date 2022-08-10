package com.bootcamp.induk.mapper;

import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.domain.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BoardMapper {

    int insertBoard(BoardDto boardDto) throws Exception;
    List<BoardDto> selectBoardList() throws Exception;

    BoardDto selectBoard(Integer bno) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;

    int updateBoard(BoardDto boardDto) throws Exception;
    int deleteBoard(Integer bno, String writer) throws Exception;
    int deleteBoardList() throws Exception;

    int getCount() throws Exception;
    List<BoardDto> selectPage(Map map) throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;
    List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;

	int updateCommentCnt(Integer bno, int cnt);
}
