package com.goodyang.votemanage.daoFactory;

import com.goodyang.votemanage.dao.VoteOptionDAO;
import com.goodyang.votemanage.daoImpl.VoteOptionDAOImpl;

public class VoteOptionDAOFactory {
	public static VoteOptionDAO getVoteOptionDAOInstance() {
		return new VoteOptionDAOImpl();
	}
}
