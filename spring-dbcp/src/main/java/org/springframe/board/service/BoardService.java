package org.springframe.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframe.board.dto.BoardDTO;
import org.springframe.board.dto.PageDTO;
import org.springframe.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;


// @Controller : url : html
// @Service : 처리
// @Repository - 데이터 저장
@Service
@Log4j
@Qualifier("boardService")
public class BoardService {
	
	// 자동 DI - @Setter, @Autowired, @Inject 아무거나 써도 상관 없다
	@Inject
	private BoardMapper mapper;

	int pageLimit = 10; // 한 페이지당 보여줄 글 갯수
	int blockLimit = 5; // 하단에 보여줄 페이지 번호 갯수
	
	public List<BoardDTO> selectPageListBoard(int page) {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		int pagingstart = ( (page -1) * pageLimit) + 1;
		Map<String, Integer> pagingParams = new HashMap<>();
		// BoardMapper.xml의 #{start}, #{limit}랑 맞춰 주면은 된다
		pagingParams.put("start", pagingstart);
		pagingParams.put("limit", page * pageLimit);
		
		List<BoardDTO> pagingList = mapper.selectPageListBoard(pagingParams);
		return pagingList;
		
	}
	
	public int updatetHits(int seq) {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		// hits 올리기
//		mapper.updatetHits(seq);
		return mapper.updatetHits(seq);
	}
	
	public BoardDTO selectBoard(int seq) {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return mapper.selectBoard(seq);
	}
	
	public int insertBoard(BoardDTO boardDTO) {
		log.info(boardDTO);
		return mapper.insertBoard(boardDTO);
	}
	
    
	public List<BoardDTO> pagingBoard(int page) {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		// 한페이지당 글의 갯수 (53개)
//		int pageLimit = 3;
		// 페이지 첫번째
		int pagingstart = ((page - 1) * pageLimit) + 1;
		Map<String, Integer> pagingParams = new HashMap<>();
		// BoardMapper.xml 의 #{start}, #{limit} 랑 맞춰 주면은 된다
		pagingParams.put("start", pagingstart);
		pagingParams.put("limit", page * pageLimit );
		
		List<BoardDTO> pagingList = mapper.pagingList(pagingParams);
		
		return pagingList;
	}

	public int deleteBoard(int seq) {
		// TODO Auto-generated method stub
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return mapper.deleteBoard(seq);
	}

	public int updateBoard(BoardDTO boardDTO) {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		// TODO Auto-generated method stub
		log.info("parameter dto : " + boardDTO);
		return mapper.updateBoard(boardDTO);
	}
	
	public PageDTO paginParam(int page) {
		 // 전체 글 갯수 조회
        int boardCount = mapper.pagingCount();
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4) - 올림처리
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
	}
	
	
	
	
	
}
