package org.springframe.board.mapper;
// org.zerock.board.mapper - mapper.java
// org.springframe.mapper.board.BoardMapper.xml

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframe.board.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class BoardMapper {
	
	@Autowired
	private  SqlSessionTemplate sql;

	public BoardMapper(SqlSessionTemplate sql) {
		super();
		this.sql = sql;
	}
	
	public List<BoardDTO> selectPageListBoard(Map<String, Integer> pagingParams) {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		
//		List<BoardDTO> list =  sql.selectList("Board.selectPageListBoard");
//		log.info(list);
		return sql.selectList("Board.selectPageListBoard", pagingParams);
	}

	public BoardDTO selectBoard(int seq) {
		return sql.selectOne("Board.selectBoard", seq);
	}
	
	public int updatetHits(int seq) {
		log.info(seq);
		return sql.update("Board.updatetHits", seq);
	}
	
	public int insertBoard(BoardDTO boardDTO) {
		return sql.insert("Board.insertBoard", boardDTO);
	}

	public List<BoardDTO> pagingList(Map<String, Integer> pagingParams) {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		// TODO Auto-generated method stub
		return sql.selectList("Board.pagingList", pagingParams);
	}

	public int deleteBoard(int seq) {
		// TODO Auto-generated method stub
		return sql.delete("Board.deleteBoard", seq);
		
	}

	public int updateBoard(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		
		return sql.update("Board.updateBoard", boardDTO);
	}

	public int pagingCount() {
		return sql.selectOne("Board.pagingCount") ;
	}

}
