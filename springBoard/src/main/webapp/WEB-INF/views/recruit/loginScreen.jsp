<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
<form action="/recruit/login.do" method="post" accept-charset="UTF-8">
    <table border="1">
        <tr>
            <td>이름</td>
            <td colspan="2">
                <input type="text" name="name" required>
            </td>
        </tr>
        <tr>
            <td>휴대폰번호</td>
            <td colspan="2">
                <input type="text" name="phone" required>
            </td>
        </tr>
        <tr style="text-align: center;">
            <td colspan="3">
                <input type="submit" value="입사지원">
            </td>
        </tr>
    </table>
</form>


</body>
</html>