<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="pages" type="cn.st.query.Page" required="true"%>

<input type="hidden" id="currentPage" name="currentPage"  />

<div id="pageControl">
	<c:if test="${page.currentPage == 1}">
		<font color="#000">首页</font>
	</c:if>
	<c:if test="${page.currentPage > 1}">
		<a onclick="clickPage(1)">首页</a>
	</c:if>
	
	<c:if test="${page.currentPage == 1}">
		<font color="#000">上一页</font>
	</c:if>
	
	<c:if test="${page.currentPage > 1}">
		<a onclick="clickPage(${page.currentPage - 1})">上一页</a>
	</c:if>
	
	<c:forEach begin="1" end="${page.totalPage }" var="num">
		<c:choose>
			<c:when test="${page.currentPage == num}">
				<font color="#000">${num}</font>
			</c:when>
			<c:otherwise>
				<a onclick="clickPage(${num})">${num}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${page.currentPage == page.totalPage}">
		<font color="#000">下一页</font>
	</c:if>
	<c:if test="${page.currentPage < page.totalPage}">
		<a onclick="clickPage(${page.currentPage + 1})">下一页</a>
	</c:if>
	
	<c:if test="${page.currentPage == page.totalPage}">
		<font color="#000">末页</font>
	</c:if>
	<c:if test="${page.currentPage < page.totalPage}">
		<a onclick="clickPage(${page.totalPage})">末页</a>
	</c:if>
</div>

<script type="text/javascript" >
	function clickPage(c) {
		//alert(c);
		document.getElementById("currentPage").value=c;
		document.getElementById("form").submit();
	}
	
	function queryPage() {
		document.getElementById("currentPage").value=1;
		document.getElementById("form").submit();
	}
</script>
<style>
	#pageControl a:HOVER {
		cursor: pointer;
	}
</style>