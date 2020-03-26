package cn.st.service;

import java.util.List;

import cn.st.dao.VedioTypeDao;
import cn.st.entity.VedioType;

public class VedioTypeService {

	VedioTypeDao dao=new VedioTypeDao();
	
	/**
	 * 根据id连接数据（作为外键连接） 用类名作方法返回，即返回该类的实体类对象
	 * @param type_id
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:28:15
	 */
	public VedioType findById(int type_id) {
		return dao.findById(type_id);
	}
	/**
	 * 查询 全部的视频类型信息 （绑定下拉框 获取值）
	 * @return
	 *  @author 潘盛武
	 * @date 2017年11月27日22:28:15
	 */
	public List<VedioType> queryAllVedioTypeInf(){
		return dao.queryAllVedioTypeInf();
	}
	
	
}
