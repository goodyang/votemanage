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
public class ShowVoteAction extends ActionSupport {
	private int currentPage;
	
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
		
		int totalCount = voteDAO.findAllCount();
		Page page = PageUtil.createPage(10, totalCount, currentPage);
		List<Vote> votes = voteDAO.findAllVote(page);
		List<VoteResult> voteResultList = new ArrayList<VoteResult>();
		for(Vote vote : votes) {
			List<VoteOption> voteOptions = 
					voteOptionDAO.findVoteOptionByVoteID(vote.getVoteID());
			VoteResult voteResult = new VoteResult();
			voteResult.setVote(vote);
			voteResult.setVoteOptions(voteOptions);
			voteResultList.add(voteResult);
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("voteResultList", voteResultList);
		request.setAttribute("page", page);
		
		return ActionSupport.SUCCESS;
	}
}
