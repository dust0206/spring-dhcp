package org.springframe.board.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PageDTO {
	private int page; 	// 현재 페이지
	private int maxPage;	// 전체 필요한 페이지 갯수
	private int startPage;	// 현재 페이지  기준 시작 페이지 값
	private int endPage; 	// 현재 페이지 기준 마지막 페이지 값
}
