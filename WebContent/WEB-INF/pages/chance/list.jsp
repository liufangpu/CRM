<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>销售机会管理</title>
	<script type="text/javascript">
		$(function(){
			$("#new").click(function(){
				window.location.href="${ctp}/chance/";
				return false;
			});
			
			$("img[id^='delete']").click(function(){
				
				var label=$(this).prev(":hidden").val();
				var id=$(this).next(":hidden").val();
				var flag=confirm("确定删除"+label+"吗？");
				if (flag) {
					window.location.href="${ctp}/chance/delete?id="+id;
				}
			
				return false;
			});
			
		});
	</script>
</head>

<body class="main">
	<form id="command" action="${ctp}/chance/list2" method="post" >
		<div class="page_title">
			销售机会管理
		</div>
		<div class="button_bar">
			<button class="common_button" id="new" >
				新建
			</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					客户名称
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_contact" />
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${empty page.content }">
			没有数据
		</c:if>
		
		<c:if test="${not empty page.content }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						客户名称
					</th>
					<th>
						概要
					</th>
					<th>
						联系人
					</th>
					<th>
						联系人电话
					</th>
					<th>
						创建时间
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach items="${page.content }" var="item">
					<tr>
						<td class="list_data_number">${item.id }</td>
						<td class="list_data_text">${item.custName }</td>
						<td class="list_data_text">${item.title }</td>
						<td class="list_data_text">${item.contact }</td>
						<td class="list_data_text">${item.contactTel }</td>
						<td class="list_data_text">
							<fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/chance/dispatch?id=${item.id }'" 
								title="指派" src="${ctp}/static/images/bt_linkman.gif" class="op_button" />
							<img onclick="window.location.href='${ctp}/chance/${item.id}'" 
								title="编辑" src="${ctp}/static/images/bt_edit.gif"
								class="op_button" />
								<input type="hidden" value="${item.custName }"/>
						<%-- 	<img id="delete"  onclick="window.location.href='${ctp}/chance/delete?id=${ item.id}'" 
								title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" /> --%>
								<img id="delete${item.id }"   
								title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
								<input type="hidden" value="${item.id }">
						</td>
					</tr>
				</c:forEach>
			</table>

			<div style="text-align:right; padding:6px 6px 0 0;">
			
				共 ${page.totalElements } 条记录 
				&nbsp;&nbsp;
				
				当前第 ${page.pageNo } 页/共 ${page.totalPage } 页
				&nbsp;&nbsp;
				
				<c:if test="${page.hasPrev }">
					<a href="${ctp }/chance/list?pageNo=1">首页</a>
					&nbsp;&nbsp;
					<a href="${ctp }/chance/list?pageNo=${page.prev}">上一页</a>
					&nbsp;&nbsp;
				</c:if>		
				
				<c:if test="${page.hasNext }">
					<a href="${ctp }/chance/list?pageNo=${page.next}">下一页</a>
					&nbsp;&nbsp;
					<a href="${ctp }/chance/list?pageNo=${page.totalPage}">末页</a>
					&nbsp;&nbsp;
				</c:if>				
				
				转到 <input id="pageNo" size='1'/> 页
				&nbsp;&nbsp;
			
			</div>
			
			<script type="text/javascript" src="${ctp}/static/jquery/jquery-1.9.1.min.js"></script>
			<script type="text/javascript">
			
				$(function(){
					
					$("#pageNo").change(function(){
						var pageNo = $(this).val();
						var reg = /^\d+$/;
						if(!reg.test(pageNo)){
							$(this).val("");
							alert("输入的页码不合法");
							return false;
						}
						
						var pageNo2 = parseInt(pageNo);
						if(pageNo2 < 1 || pageNo2 > parseInt("${page.totalPage}")){
							$(this).val("");
							alert("输入的页码不合法");
							return;
						}
						
						//查询条件需要放入到 class='condition' 的隐藏域中. 
						window.location.href = window.location.pathname + "?pageNo=" + pageNo2;
						
					});
				});
			</script>
			
		</c:if>
		
	</form>
	
</body>
</html>
