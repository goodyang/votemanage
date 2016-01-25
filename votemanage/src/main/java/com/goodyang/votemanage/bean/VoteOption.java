package com.goodyang.votemanage.bean;


public class VoteOption {
	private int voteOptionID;	//ͶƱѡ��ID
	private int voteID;			//ͶƱID
	private String voteOptionName;	//ͶƱѡ�����
	private int ticketNum;
	public int getVoteOptionID() {
		return voteOptionID;
	}
	public void setVoteOptionID(int voteOptionID) {
		this.voteOptionID = voteOptionID;
	}
	public int getVoteID() {
		return voteID;
	}
	public void setVoteID(int voteID) {
		this.voteID = voteID;
	}
	public String getVoteOptionName() {
		return voteOptionName;
	}
	public void setVoteOptionName(String voteOptionName) {
		this.voteOptionName = voteOptionName;
	}
	public int getTicketNum() {
		return ticketNum;
	}
	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}
}
