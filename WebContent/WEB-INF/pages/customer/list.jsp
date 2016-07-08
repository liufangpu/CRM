<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>客户基本信息管理</title>
<script type="text/javascript">
	$(function(){
		
		$("img[id^='delete-']").click(function(){
			
			var id = $(this).attr("id");
				id = id.split("-")[1];
			var url = "${ctp}/customer/";
			var param = {"id":id,"time":new Date()};
			var callback = function(data){
				if (data=="1") {
					
					$("#delete-"+id).hide();
					$("#state-"+id).text("删除");
				}
			};
			$.post(url,param,callback);
			
			return false;
		});
		
	});
</script>


</head>
<body>

	<div class="page_title">客户基本信息管理</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">查询</button>
	</div>
	
	<form action="${ctp }/customer/list2" method="POST" >
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>客户名称</th>
				<td>
					<input type="text" name="search_LIKE_name"/>
				</td>
				<th>地区</th>
				<td>
				
				 <select name="search_EQ_region">
							<option value="">全部</option>
						<c:forEach items="${regionList }" var="dict">
							<option value="${dict.item }">${dict.item }</option>
						</c:forEach>
					</select> 
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<th>客户经理</th>
				<td><input type="text" name="search_LIKE_managerName" /></td>
				
				<th>客户等级</th>
				<td>
					 <select name="search_EQ_level">
							<option value="">全部</option>
						<c:forEach items="${levelList }" var="level">
							<option value="${level.item }">${level.item }</option>
						</c:forEach>	
					</select> 
				</td>
				
				<th>状态</th>
				<td>
					<select name="search_EQ_state">
						<option value="">全部</option>				
						<option value="正常">正常</option>				
						<option value="流失">流失</option>				
						<option value="删除">删除</option>				
					</select>
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
			<c:if test="${empty page.content }">
				暂时没有数据
			</c:if>
			<c:if test="${ not empty page.content }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				
				<tr>
					<th>客户编号</th>
					<th>客户名称</th>
					<th>地区</th>
					<th>客户经理</th>
					<th>客户等级</th>
					<th>状态</th>
					<th colspan="2">操作</th>
				</tr>
				<c:forEach items="${ page.content }" var="cust">			
					<tr>
						<td class="list_data_text">${cust.no }</td>
						<td class="list_data_ltext">${cust.name }&nbsp;</td>
						<td class="list_data_text">${cust.region }&nbsp;</td>
						<td class="list_data_text">${cust.manager.name}&nbsp;</td>
						<td class="list_data_text">${cust.level }&nbsp;</td>
                        <td  id="state-${cust.id }" class="list_data_text">${cust.state }&nbsp;</td>

							<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/customer/${cust.id}'"
								title="编辑" src="${ctp}/static/images/bt_edit.gif" class="op_button" alt="" /> 
							<img onclick="window.location.href='${ctp}/contact/list/${cust.id}'"
								title="联系人" src="${ctp}/static/images/bt_linkman.gif" class="op_button" alt="联系人信息" /> 
							<img onclick="window.location.href='${ctp}/activity/list2?customerId=${cust.id}'"
								title="交往记录" src="${ctp}/static/images/bt_acti.gif" class="op_button" alt="交往记录" /> 
							<img onclick="window.location.href='${ctp}/order/list2?customerId=${cust.id}'"
								title="历史订单" src="${ctp}/static/images/bt_orders.gif" class="op_button" alt="历史订单" /> 
							<img id="delete-${cust.id }" title="删除" src="${ctp }/static/images/bt_del.gif" class="op_button" alt="删除" />
						
						</td>					
					</tr>
				</c:forEach>	
			</table>
			</c:if>
	</form>
	<atguigu:page page="${page }" toUrl="customer"  queryString="${queryString }"/>

</body>
</html>
