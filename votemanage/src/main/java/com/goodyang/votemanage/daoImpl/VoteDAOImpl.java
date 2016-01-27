package com.goodyang.votemanage.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goodyang.votemanage.bean.Vote;
import com.goodyang.votemanage.dao.VoteDAO;
import com.goodyang.votemanage.util.DBConnection;
import com.goodyang.votemanage.util.Page;

public class VoteDAOImpl implements VoteDAO {

	@Override
	public void addVote(Vote vote) {
		Connection conn = DBConnection.getConnection();	//获得连接对象
		String addSQL = "insert into " +
				"vote(voteName,channelID) values(?,?)";
		PreparedStatement pstmt = null;					//声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL);		//获得预处理对象并赋值
			pstmt.setString(1, vote.getVoteName());		//设置投票名称
			pstmt.setInt(2, vote.getChannelID());		//设置频道ID
			pstmt.executeUpdate();								//执行添加
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(pstmt);							//关闭预处理对象
			DBConnection.close(conn);							//关闭连接对象
		}
	}

	@Override
	public void updateVote(Vote vote) {
		
	}

	@Override
	public void deleteVote(int voteID) {
		Connection conn = DBConnection.getConnection();	//获得连接对象
		String deleteSQL = "delete from vote where voteID=?";
		PreparedStatement pstmt = null;					//声明预处理对象
		try {
			pstmt = conn.prepareStatement(deleteSQL);		//获得预处理对象并赋值
			pstmt.setInt(1, voteID);						//设置投票编号
			pstmt.executeUpdate();								//执行删除
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(pstmt);							//关闭预处理对象
			DBConnection.close(conn);							//关闭连接对象
		}
	}

	@Override
	public List<Vote> findAllVote(Page page) {
		Connection conn = DBConnection.getConnection();		//获得连接对象
		String findByIDSQL = "select * from vote limit ?,?";		//查询SQL语句
		PreparedStatement pstmt = null;	//声明预处理对象
		ResultSet rs = null;
		List<Vote> votes = new ArrayList<Vote>();
		try {
			pstmt = conn.prepareStatement(findByIDSQL);		//获得预处理对象并赋值
			pstmt.setInt(1, page.getBeginIndex());
			pstmt.setInt(2, page.getEveryPage());
			rs = pstmt.executeQuery();						//执行查询
			while(rs.next()) {
				Vote vote = new Vote();
				vote.setVoteID(rs.getInt(1));
				vote.setVoteName(rs.getString(2));
				vote.setChannelID(rs.getInt(3));
				votes.add(vote);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(rs);								//关闭结果集对象
			DBConnection.close(pstmt);							//关闭预处理对象
			DBConnection.close(conn);							//关闭连接对象
		}
		return votes;
	}

	@Override
	public List<Vote> findVoteByChannel(Page page, int channelID) {
		Connection conn = DBConnection.getConnection();		//获得连接对象
		String findByIDSQL = "select * from vote where channelID=? limit ?,?";		//查询SQL语句
		PreparedStatement pstmt = null;	//声明预处理对象
		ResultSet rs = null;
		List<Vote> votes = new ArrayList<Vote>();
		try {
			pstmt = conn.prepareStatement(findByIDSQL);		//获得预处理对象并赋值
			pstmt.setInt(1, channelID);
			pstmt.setInt(2, page.getBeginIndex());
			pstmt.setInt(3, page.getEveryPage());
			rs = pstmt.executeQuery();						//执行查询
			while(rs.next()) {
				Vote vote = new Vote();
				vote.setVoteID(rs.getInt(1));
				vote.setVoteName(rs.getString(2));
				vote.setChannelID(rs.getInt(3));
				votes.add(vote);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(rs);								//关闭结果集对象
			DBConnection.close(pstmt);							//关闭预处理对象
			DBConnection.close(conn);							//关闭连接对象
		}
		return votes;
	}

	@Override
	public Vote findVoteById(int voteID) {
		Connection conn = DBConnection.getConnection();	//获得连接对象
		String querySQL  = "select * from vote where voteID = ?";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		Vote vote = null;
		try {
			pstmt = conn.prepareStatement(querySQL);		//获得预处理对象并赋值
			pstmt.setInt(1, voteID);
			rs = pstmt.executeQuery();					//执行查询
			if(rs.next()) {
				vote = new Vote();
				vote.setVoteID(rs.getInt(1));
				vote.setVoteName(rs.getString(2));
				vote.setChannelID(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(rs);								//关闭结果集对象
			DBConnection.close(pstmt);							//关闭预处理对象
			DBConnection.close(conn);							//关闭连接对象
		}
		return vote;
	}

	@Override
	public Vote findVoteByName(String voteName) {
		Connection conn = DBConnection.getConnection();	//获得连接对象
		String querySQL  = "select * from vote where voteName = ?";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		Vote vote = null;
		try {
			pstmt = conn.prepareStatement(querySQL);		//获得预处理对象并赋值
			pstmt.setString(1, voteName);
			rs = pstmt.executeQuery();					//执行查询
			if(rs.next()) {
				vote = new Vote();
				vote.setVoteID(rs.getInt(1));
				vote.setVoteName(rs.getString(2));
				vote.setChannelID(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(rs);								//关闭结果集对象
			DBConnection.close(pstmt);							//关闭预处理对象
			DBConnection.close(conn);							//关闭连接对象
		}
		return vote;
	}

	@Override
	public int findAllCount() {
		Connection conn = DBConnection.getConnection();	//获得连接对象
		String findSQL = "select count(*) from vote";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			rs = pstmt.executeQuery();					//执行查询
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(rs);						//关闭结果集对象
			DBConnection.close(pstmt);					//关闭预处理对象
			DBConnection.close(conn);					//关闭连接对象
		}
		return count;
	}

	@Override
	public int findCountByChannel(int channelID) {
		Connection conn = DBConnection.getConnection();	//获得连接对象
		String findSQL = "select count(*) from vote where channelID=?";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			pstmt.setInt(1, channelID);
			rs = pstmt.executeQuery();					//执行查询
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(rs);						//关闭结果集对象
			DBConnection.close(pstmt);					//关闭预处理对象
			DBConnection.close(conn);					//关闭连接对象
		}
		return count;
	}
	
}
