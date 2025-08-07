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
			console.log("오류")
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
			console.log("조회 되냐");
			let chooseType = [];
			
			$j("input[name=type]:checked").each(function(){
				chooseType.push($j(this).val());
			})
			
			console.log("선택된 값:", chooseType);  // ← 여기 꼭 찍어봐!
			
			$j.ajax({
				url: "/board/checkBoxResult.do",
				method: "GET",
				data: { types: chooseType },
				dataType: "json",
				success: function(data){
					console.log("받은 데이터:", data);
					
					let html = "";
					$j.each(data, function(i, item) {
						html += "<tr>";
						html += "<td>" + item.boardTypeName + "</td>";
						html += "<td>" + item.boardNum + "</td>";
						html += "<td><a href='/board/" + item.boardType + "/" + item.boardNum + "/boardView.do'>" + item.boardTitle + "</a></td>";
						html += "</tr>";
					});
					
					$j("#boardTable tr:gt(0)").remove(); // 헤더 외 기존 행 삭제
					$j("#boardTable").append(html);      // 새로운 행 추가
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
			<a href ="/board/boardWrite.do">글쓰기</a>
		</td>
	</tr>
	
	<tr>
		<td>
			<form id="filterForm"><input type="checkbox" id="checkAll"> 전체
				<div id="checkboxContainer"></div>
				<button type="button" id="whichOne">조회</button>
			</form>
		</td>
	</tr>
</table>	
</body>
</html>