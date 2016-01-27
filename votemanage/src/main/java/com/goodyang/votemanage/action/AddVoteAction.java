package com.goodyang.votemanage.action;

import com.goodyang.votemanage.bean.Vote;
import com.goodyang.votemanage.bean.VoteOption;
import com.goodyang.votemanage.dao.VoteDAO;
import com.goodyang.votemanage.dao.VoteOptionDAO;
import com.goodyang.votemanage.daoFactory.VoteDAOFactory;
import com.goodyang.votemanage.daoFactory.VoteOptionDAOFactory;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AddVoteAction extends ActionSupport {
	private int channel;
	private String voteName;
	private String[] voteOption;
	
	public int getChannel() {
		return channel;
	}
	
	public void setChannel(int channel) {
		this.channel = channel;
	}
	
	public String getVoteName() {
		return voteName;
	}
	
	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}
	
	public String[] getVoteOption() {
		return voteOption;
	}
	
	public void setVoteOption(String[] voteOption) {
		this.voteOption = voteOption;
	}
	
	@Override
	public String execute() throws Exception {
		VoteDAO voteDAO = VoteDAOFactory.getVoteDAOInstance();
		VoteOptionDAO voteOptionDAO = 
				VoteOptionDAOFactory.getVoteOptionDAOInstance();
		Vote vote = new Vote();
		vote.setChannelID(channel);
		vote.setVoteName(voteName);
		voteDAO.addVote(vote);
		
		int voteID = voteDAO.findVoteByName(voteName).getVoteID();
		
		VoteOption vp = null;
		for(String voteOptionName : voteOption) {
			vp = new VoteOption();
			vp.setVoteID(voteID);
			vp.setVoteOptionName(voteOptionName);
			voteOptionDAO.addVoteOption(vp);
		}
		
		return Action.SUCCESS;
	}
}







