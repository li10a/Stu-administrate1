<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
<c:if test="${not empty msg}">
alert(${msg});
</c:if>
window.parent.location.href="${url}";
</script>