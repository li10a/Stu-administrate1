<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${pageInfo.totalPage gt 1}">
<div class="text-center">
  <ul class="pagination">
    <li><a href="/admin/${param.url}?page=${pageInfo.displayPages[0] - 1 le 0 ? pageInfo.totalPage : pageInfo.displayPages[0] - 1}">&laquo;</a></li>
    <c:forEach items="${pageInfo.displayPages}" var="page">
    <li><a href="/admin/${param.url}?page=${page}"><c:if test="${page eq pageInfo.currentPage}"><b></c:if>${page}<c:if test="${page eq pageInfo.currentPage}"></b></c:if></a></li>
    </c:forEach>
    <li><a href="/admin/${param.url}?page=${pageInfo.displayPages[fn:length(pageInfo.displayPages) - 1] ge pageInfo.totalPage ? 1 : pageInfo.displayPages[fn:length(pageInfo.displayPages) - 1] + 1}">&raquo;</a></li>
  </ul>
</div>
</c:if>