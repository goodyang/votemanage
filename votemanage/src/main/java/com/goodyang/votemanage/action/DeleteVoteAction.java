package com.goodyang.votemanage.action;

import java.util.List;

import com.goodyang.votemanage.bean.VoteOption;
import com.goodyang.votemanage.dao.VoteDAO;
import com.goodyang.votemanage.dao.VoteOptionDAO;
import com.goodyang.votemanage.daoFactory.VoteDAOFactory;
import com.goodyang.votemanage.daoFactory.VoteOptionDAOFactory;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DeleteVoteAction extends ActionSupport{
	private int voteID;
	
	public int getVoteID() {
		return voteID;
	}
	
	public void setVoteID(int voteID) {
		this.voteID = voteID;
	}
	
	@Override
	public String execute() throws Exception {
		VoteDAO voteDAO = VoteDAOFactory.getVoteDAOInstance();
		VoteOptionDAO voteOptionDAO = VoteOptionDAOFactory.getVoteOptionDAOInstance();
		
		List<VoteOption> voteOptions = voteOptionDAO.findVoteOptionByVoteID(voteID);
		for(VoteOption voteOption : voteOptions) {
			voteOptionDAO.deleteVoteOption(voteOption.getVoteOptionID());
		}
		
		voteDAO.deleteVote(voteID);
		
		return ActionSupport.SUCCESS;
	}
	
}
