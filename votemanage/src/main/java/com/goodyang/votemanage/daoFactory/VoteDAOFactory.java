package com.goodyang.votemanage.daoFactory;

import com.goodyang.votemanage.dao.VoteDAO;
import com.goodyang.votemanage.daoImpl.VoteDAOImpl;

public class VoteDAOFactory {
	public static VoteDAO getVoteDAOInstance() {
		return new VoteDAOImpl();
	}
}
