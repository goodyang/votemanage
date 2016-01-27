package com.goodyang.votemanage.dao;

import java.util.List;

import com.goodyang.votemanage.bean.VoteOption;

public interface VoteOptionDAO {
	public void addVoteOption(VoteOption voteOption);
	public void updateVoteOption(VoteOption voteOption);
	public void deleteVoteOption(int voteOptionID);
	public List<VoteOption> findVoteOptionByVoteID(int voteID);
	public VoteOption findVoteOptionById(int voteOptionID);
}
