package com.ssafy.web.dto;

import lombok.Data;

@Data
public class PagingData {
	private int currentPage;
	private int blockSize;
	private int totalCnt;
	private int MaxPage;
	private int offset;
	
	public PagingData(int currentPage, int blockSize){
		this.currentPage =currentPage;
		this.blockSize =blockSize;
		basicOffset();
	}
	
	public void basicOffset() {
		offset = (currentPage-1) * blockSize;
	}
	
	public void basicMaxPage() {
		MaxPage = (int)Math.ceil(totalCnt/blockSize);
	}
}
