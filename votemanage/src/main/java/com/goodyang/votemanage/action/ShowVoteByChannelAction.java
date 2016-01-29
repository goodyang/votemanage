package com.goodyang.votemanage.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.goodyang.votemanage.bean.Vote;
import com.goodyang.votemanage.bean.VoteOption;
import com.goodyang.votemanage.bean.VoteResult;
import com.goodyang.votemanage.dao.VoteDAO;
import com.goodyang.votemanage.dao.VoteOptionDAO;
import com.goodyang.votemanage.daoFactory.VoteDAOFactory;
import com.goodyang.votemanage.daoFactory.VoteOptionDAOFactory;
import com.goodyang.votemanage.util.Page;
import com.goodyang.votemanage.util.PageUtil;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ShowVoteByChannelAction extends ActionSupport {
	private int channelID;
	private int currentPage;
	
	public int getChannelID() {
		return channelID;
	}
	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	@Override
	public String execute() throws Exception {
		VoteDAO voteDAO = VoteDAOFactory.getVoteDAOInstance();
		VoteOptionDAO voteOptionDAO = VoteOptionDAOFactory.getVoteOptionDAOInstance();
		
		int totalCount = voteDAO.findCountByChannel(channelID);
		Page page = PageUtil.createPage(3, totalCount, currentPage);
		List<Vote> votes = voteDAO.findVoteByChannel(page, channelID);
		
		List<VoteResult> voteResultList = new ArrayList<VoteResult>();
		for(Vote vote : votes) {
			VoteResult voteResult = new VoteResult();
			voteResult.setVote(vote);
			List<VoteOption> voteOptions = voteOptionDAO.findVoteOptionByVoteID(vote.getVoteID());
			voteResult.setVoteOptions(voteOptions);
			voteResultList.add(voteResult);
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("voteResultList", voteResultList);
		request.setAttribute("page", page);
		
		return ActionSupport.SUCCESS;
	}
}
