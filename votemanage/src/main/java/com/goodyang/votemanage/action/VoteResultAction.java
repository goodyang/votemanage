package com.goodyang.votemanage.action;

import java.awt.Font;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.goodyang.votemanage.bean.Vote;
import com.goodyang.votemanage.bean.VoteOption;
import com.goodyang.votemanage.dao.VoteDAO;
import com.goodyang.votemanage.dao.VoteOptionDAO;
import com.goodyang.votemanage.daoFactory.VoteDAOFactory;
import com.goodyang.votemanage.daoFactory.VoteOptionDAOFactory;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class VoteResultAction extends ActionSupport {
	private JFreeChart chart;
	private int voteID;
	
	public int getVoteID() {
		return voteID;
	}
	
	public void setVoteID(int voteID) {
		this.voteID = voteID;
	}
	
	public JFreeChart getChart() {
		VoteDAO voteDAO = VoteDAOFactory.getVoteDAOInstance();
		VoteOptionDAO voteOptionDAO = VoteOptionDAOFactory.getVoteOptionDAOInstance();
		
		Vote vote = voteDAO.findVoteById(voteID);		
		String voteName = vote.getVoteName();
		
		List<VoteOption> voteOptions = voteOptionDAO.findVoteOptionByVoteID(voteID);
		
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		for(VoteOption voteOption : voteOptions) {
			dcd.setValue(voteOption.getTicketNum(), "", 
					voteOption.getVoteOptionName());
		}
		
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");  
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		ChartFactory.setChartTheme(mChartTheme); 
		
		chart = ChartFactory.createBarChart3D(voteName, 
												"投票选项", 
												"投票数", 
												dcd, 
												PlotOrientation.VERTICAL,
												false,
												true,
												false);
        
		return chart;		
	}
	
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
