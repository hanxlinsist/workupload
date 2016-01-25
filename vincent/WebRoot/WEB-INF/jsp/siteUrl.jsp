<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String siteUrl = request.getAttribute("siteUrl").toString();
	response.sendRedirect(request.getContextPath() + "/" + siteUrl + ".action");
 %>