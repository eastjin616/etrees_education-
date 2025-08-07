<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>
</head>
<script type="text/javascript">

	$j.ajax({
		url:"/board/checkBoxShow.do",
		method : "GET",
		dataType: "json",
		success: function(data){
			console.log(data);
			
			let html ="";
			
			$j.each(data, function(i,item){
				html += "<input type='checkbox' class='typeCheck' name='type' value='" + item.codeId + "'> " + item.codeName;
			});
			$j("#checkboxContainer").html(html);
		},
		error : function(err){
			console.log("����")
		}
	})
	
	$j(document).ready(function() {
		$j("#checkAll").click(function() {
			if ($j(this).is(":checked")) {
				$j("input[name='type']").prop("checked", true);
			} else {
				$j("input[name='type']").prop("checked", false);
			}
		});
	});
	
	$j(document).on("click", "input[name='type']", function() {
		$j("input[name='type']").click(function(){
			var total = $j("input[name='type']").length;
			var checked = $j("input[name='type']:checked").length;
			
			if(total != checked) {
				$j("#checkAll").prop("checked",false);
			}else{
				$j("#checkAll").prop("checked",true);
			}
		})
	});

	$j(document).ready(function() {	
		$j("#whichOne").click(function(){
			console.log("��ȸ �ǳ�");
			let chooseType = [];
			
			$j("input[name=type]:checked").each(function(){
				chooseType.push($j(this).val());
			})
			
			console.log("���õ� ��:", chooseType);  // �� ���� �� ����!
			
			$j.ajax({
				url: "/board/checkBoxResult.do",
				method: "GET",
				data: { types: chooseType },
				dataType: "json",
				success: function(data){
					console.log("���� ������:", data);
					
					let html = "";
					$j.each(data, function(i, item) {
						html += "<tr>";
						html += "<td>" + item.boardTypeName + "</td>";
						html += "<td>" + item.boardNum + "</td>";
						html += "<td><a href='/board/" + item.boardType + "/" + item.boardNum + "/boardView.do'>" + item.boardTitle + "</a></td>";
						html += "</tr>";
					});
					
					$j("#boardTable tr:gt(0)").remove(); // ��� �� ���� �� ����
					$j("#boardTable").append(html);      // ���ο� �� �߰�
				}
				
			})
			
		})
	})

	
	
</script>


<body>
<table  align="center">
	<tr>
		<td align="right">
			total : ${totalCnt}
		</td>
	</tr>
	<tr>
		<td>
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center">
							${list.boardTypeName}
						</td>
						<td>
							${list.boardNum}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
						</td>
					</tr>	
				</c:forEach>
			</table>
		</td>
	</tr>
	
	<tr>
		<td align="right">
			<a href ="/board/boardWrite.do">�۾���</a>
		</td>
	</tr>
	
	<tr>
		<td>
			<form id="filterForm"><input type="checkbox" id="checkAll"> ��ü
				<div id="checkboxContainer"></div>
				<button type="button" id="whichOne">��ȸ</button>
			</form>
		</td>
	</tr>
</table>	
</body>
</html>