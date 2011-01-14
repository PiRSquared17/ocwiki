<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %> 

<table class="site-counter-table">
  <tr>
    <td class="site-counter-title">Hôm nay:</td>
    <td class="site-counter-value">${module.todayViewCount}</td>
  </tr>
  <tr>
    <td class="site-counter-title">Tuần này:</td>
    <td class="site-counter-value">${module.lastWeekViewCount}</td>
  </tr>
  <tr>
    <td class="site-counter-title">Tháng này:</td>
    <td class="site-counter-value">${module.lastMonthViewCount}</td>
  </tr>
  <tr>
    <td class="site-counter-title">Tất cả:</td>
    <td class="site-counter-value">${module.allTimeViewCount}</td>
  </tr>
</table>
