package com.goodyang.votemanage.action;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.goodyang.votemanage.bean.VoteOption;
import com.goodyang.votemanage.dao.VoteOptionDAO;
import com.goodyang.votemanage.daoFactory.VoteOptionDAOFactory;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DoVoteAction extends ActionSupport{
	private int voteOptionID;
	private String otherOption;
	private int voteID;
	private int channelID;
	
	public int getVoteOptionID() {
		return voteOptionID;
	}
	public void setVoteOptionID(int voteOptionID) {
		this.voteOptionID = voteOptionID;
	}
	public String getOtherOption() {
		return otherOption;
	}
	public void setOtherOption(String otherOption) {
		this.otherOption = otherOption;
	}
	public int getVoteID() {
		return voteID;
	}
	public void setVoteID(int voteID) {
		this.voteID = voteID;
	}
	public int getChannelID() {
		return channelID;
	}
	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}
	
	@Override
	public String execute() throws Exception {
		VoteOptionDAO voteOptionDAO = VoteOptionDAOFactory.getVoteOptionDAOInstance();
		Cookie cookies[] = ServletActionContext.getRequest().getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getValue().equals(Integer.toString(voteID))) {
				this.addActionError("您今天已经投过票了， 请明天再来!");
				return ActionSupport.INPUT;
			}
		}
		
		if(voteOptionID == 0) {
			VoteOption voteOption = new VoteOption();
			voteOption.setVoteID(voteID);
			voteOption.setVoteOptionName(otherOption);
			voteOption.setTicketNum(1);
			voteOptionDAO.addVoteOption(voteOption);
		}else {
			VoteOption voteOption = voteOptionDAO.findVoteOptionById(voteOptionID);
			voteOption.setTicketNum(voteOption.getTicketNum()+1);
			voteOptionDAO.updateVoteOption(voteOption);
		}
		
		Cookie cookie = new Cookie("hasVote"+voteID, String.valueOf(voteID));
		ServletActionContext.getResponse().addCookie(cookie);
		
		return ActionSupport.SUCCESS;
	}
}
