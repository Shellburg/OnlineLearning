package cn.st.service;

import cn.st.dao.JudgeDao;
import cn.st.entity.Judge;
import cn.st.query.Page;

public class JudgeService {
   
	JudgeDao dao=new JudgeDao();
	
	/**
	 * 导入判断题
	 */
	public boolean addJudge(Judge judge) {
		return dao.addJudge(judge);
	}
	public Page<Judge> queryAllJudgeIng(Page<Judge> page) {
		Page<Judge> pageRerult=dao.queryAllJudgeIng(page);
		pageRerult.setTotal(dao.countJudge());
		return pageRerult;
		
	}
	
}
