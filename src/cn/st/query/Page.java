package cn.st.query;
import java.util.ArrayList;
import java.util.List;


/**
 * 泛型类  声明时不指定具体类  当用的时候传入的参数是什么类型就是什么类型 
 * @author PSW
 *
 * @param <T>
 */
public class Page<T> {
	
	//页面大小每页15条数据
	public int pageSize = 15;
	//当前页数
	public int currentPage;
	//总数
	public long total;
	//结果集
	/**
	 * Page<T>为泛型则List<T>传入的也是泛型
	 * 也就是说Page<T>中的T要是传入的是对象的话则List中的泛型也传入的是对象
	 */
	public List<T> result = new ArrayList<T>();
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
	
	
	/**
	 * 获取页数
	 * @return
	 */
	public long getTotalPage() {
		//三目运算符
		return this.total%this.pageSize != 0 ? this.total/this.pageSize + 1 : this.total/this.pageSize;
	}
	
}
