package com.goodyang.votemanage.dao;

import java.util.List;

import com.goodyang.votemanage.bean.Vote;
import com.goodyang.votemanage.util.Page;

public interface VoteDAO {
	public void addVote(Vote vote);
	public void updateVote(Vote vote);
	public void deleteVote(int voteID);
	public List<Vote> findAllVote(Page page);
	public List<Vote> findVoteByChannel(Page page, int channelID);
	public Vote findVoteById(int voteID);
	public Vote findVoteByName(String voteName);
	public int findAllCount();
	public int findCountByChannel(int channelID);
}
