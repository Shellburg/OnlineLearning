package cn.st.service;

import cn.st.dao.FillDao;
import cn.st.entity.Fill;
import cn.st.query.Page;

public class FillService {
   FillDao dao=new FillDao();
   
   /**
	 * 导入 填空题
	 */
	public boolean addFill(Fill fill) {
	 return	dao.addFill(fill);
	}
	
	/**
	 * 查看判断题
	 */
	public Page<Fill> queryAllFillIng(Page<Fill> page) {
		Page<Fill> pageResult=dao.queryAllFillIng(page);
		pageResult.setTotal(dao.countFill());
		return pageResult;
	}
}
