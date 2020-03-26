<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="url" rtexprvalue="true" type="java.lang.String" required="true"%>

<a href='${url}'>
	<img src="${resourcePath}/image/edit.gif" border="0">
</a>

