<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입사 지원</title>

<style>
/* 기존 <style> 내용 그대로 */
body {
	font-family: Arial, sans-serif;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: flex-start;
	min-height: 100vh;
	margin: 0;
}

#title1 {
	text-align: center;
	margin-top: 40px;
}

.container {
	border: 2px solid #000;
	padding: 30px;
	margin-top: 20px;
	box-sizing: border-box;
}

.section {
	margin-top: 40px;
}

.section-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.section-header h2 {
	margin: 0;
}

.btn-group input {
	margin-left: 5px;
}

.m-auto {
	margin: auto;
}

.w-100 {
	width: 100%;
}

.text-center {
	text-align: center;
}

.w-30px {
	width: 30px;
}

.h-auto {
	height: auto;
}

.no-border {
	border: 0;
}

.p0 {
	padding: 0;
}

.pb4 {
	padding-bottom: 4px;
}

.mt-40 {
	margin-top: 40px;
}
</style>

<link rel="stylesheet" href="apply.css"><!-- 필요시 경로 수정 -->

<script>
function deleteCheckedRows(tbodyId){
  const body = document.getElementById(tbodyId);
  const checked = body.querySelectorAll('.rowCheck:checked');
  if (!checked.length) return;
  checked.forEach(chk => chk.closest('tr').remove());
}
</script>

<!-- 학력 JS -->
<script>
document.addEventListener("DOMContentLoaded", function () {
  const eduBody = document.getElementById("eduBody");
  const addEdu  = document.getElementById("addEdu");
  const delEdu  = document.getElementById("delEdu");

  function makeEduRow() {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td class="text-center"><input type="checkbox" class="rowCheck"></td>
      <td>
        <table class="w-100">
          <tr><td class="no-border p0 pb4"><input type="text" name="eduStart" placeholder="2013.02" required></td></tr>
          <tr><td class="no-border p0 text-center">~</td></tr>
          <tr><td class="no-border p0"><input type="text" name="eduEnd" placeholder="2019.10" required></td></tr>
        </table>
      </td>
      <td>
        <select name="eduDivision" class="w-100" required>
          <option value="재학">재학</option>
          <option value="중퇴">중퇴</option>
          <option value="졸업">졸업</option>
        </select>
      </td>
      <td>
        <table class="w-100">
          <tr><td class="no-border p0 pb4"><input type="text" name="schoolName" placeholder="학교명"></td></tr>
          <tr><td class="no-border p0">
            <select name="eduLocation" required>
              <option>전국</option>
            </select>
          </td></tr>
        </table>
      </td>
      <td><input type="text" name="major" placeholder="전공" required></td>
      <td><input type="text" name="grade" placeholder="학점" required></td>`;
    return tr;
  }

  addEdu.addEventListener("click", () => eduBody.appendChild(makeEduRow()));
  delEdu.addEventListener("click", () => deleteCheckedRows("eduBody"));

  if (eduBody.children.length === 0) eduBody.appendChild(makeEduRow());
});
</script>

<!-- 경력 JS -->
<script>
document.addEventListener("DOMContentLoaded", function () {
  const expBody = document.getElementById("expBody");
  const addExp  = document.getElementById("addExp");
  const delExp  = document.getElementById("delExp");

  function makeExpRow() {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td class="text-center"><input type="checkbox" class="rowCheck"></td>
      <td>
        <table class="w-100">
          <tr><td class="no-border p0 pb4"><input type="text" name="startPeriod" placeholder="2013.02"></td></tr>
          <tr><td class="no-border p0 text-center">~</td></tr>
          <tr><td class="no-border p0"><input type="text" name="endPeriod" placeholder="2019.10"></td></tr>
        </table>
      </td>
      <td><input type="text" name="compName" placeholder="회사명"></td>
      <td><input type="text" name="task" placeholder="부서/직급/직책"></td>
      <td><input type="text" name="location" placeholder="지역"></td>`;
    return tr;
  }

  addExp.addEventListener("click", () => expBody.appendChild(makeExpRow()));
  delExp.addEventListener("click", () => deleteCheckedRows("expBody"));

  if (expBody.children.length === 0) expBody.appendChild(makeExpRow());
});
</script>

<!-- 자격증 JS -->
<script>
document.addEventListener("DOMContentLoaded", function () {
  const cerBody = document.getElementById("cerBody");
  const addCer  = document.getElementById("addCer");
  const delCer  = document.getElementById("delCer");

  function makeCerRow() {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td class="text-center"><input type="checkbox" class="rowCheck"></td>
      <td><input type="text" name="qualifiName" placeholder="자격증명"></td>
      <td><input type="text" name="acquDate"    placeholder="YYYY.MM"></td>
      <td><input type="text" name="organizeName" placeholder="발행처"></td>`;
    return tr;
  }

  addCer.addEventListener("click", () => cerBody.appendChild(makeCerRow()));
  delCer.addEventListener("click", () => deleteCheckedRows("cerBody"));

  if (cerBody.children.length === 0) cerBody.appendChild(makeCerRow());
});
</script>

</head>

<body>
<div class="title" id="title1"><h1>입사 지원서</h1></div>

<form id="applyForm" method="post" action="/recruit/apply.do">
  <input type="hidden" name="seq" value="${recruit.seq}"/>

  <div class="container">
    <div class="placeholder-box">
      <table border="1" class="m-auto">
        <tr>
          <td>이름</td>
          <td colspan="2"><input type="text" name="name"  value="${recruit.name}"></td>
          <td>생년월일</td>
          <td colspan="2"><input type="text" name="birth" value="${recruit.birth}" required></td>
        </tr>
        <tr>
          <td>성별</td>
          <td colspan="2">
            <select name="gender" id="gender" required>
              <option value="">선택</option>
              <option value="남자" ${recruit.gender eq '남자' ? 'selected="selected"' : ''}>남자</option>
              <option value="여자" ${recruit.gender eq '여자' ? 'selected="selected"' : ''}>여자</option>
            </select>
          </td>
          <td>연락처</td>
          <td colspan="2"><input type="text" name="phone" value="${recruit.phone}"></td>
        </tr>
        <tr>
          <td>email</td>
          <td colspan="2"><input type="text" name="email"   value="${recruit.email}"   required></td>
          <td>주소</td>
          <td colspan="2"><input type="text" name="address" value="${recruit.address}" required></td>
        </tr>
        <tr>
          <td>희망근무지</td>
          <td colspan="2">
            <select name="hopeArea" id="hopeArea" required>
              <option value="전국" ${recruit.hopeArea eq '전국' ? 'selected="selected"' : ''}>전국</option>
            </select>
          </td>
          <td>근무형태</td>
          <td colspan="2">
            <select name="jobType" id="jobType" required>
              <option value="정규직" ${recruit.jobType eq '정규직' ? 'selected="selected"' : ''}>정규직</option>
              <option value="계약직" ${recruit.jobType eq '계약직' ? 'selected="selected"' : ''}>계약직</option>
            </select>
          </td>
        </tr>
      </table>
    </div>

    <!-- 학력 -->
    <div class="section">
      <div class="section-header">
        <h2>학력</h2>
        <div class="btn-group">
          <input type="button" id="addEdu" value="추가">
          <input type="button" id="delEdu" value="삭제">
        </div>
      </div>

      <div class="placeholder-box h-auto">
        <table id="eduTable" border="1" class="m-auto w-100">
          <thead>
            <tr>
              <td class="w-30px"></td>
              <td>재학기간</td>
              <td>구분</td>
              <td>학교명(소재지)</td>
              <td>전공</td>
              <td>학점</td>
            </tr>
          </thead>
          <tbody id="eduBody">
            <c:forEach var="e" items="${eduList}">
              <tr>
                <td class="text-center"><input type="checkbox" class="rowCheck"></td>
                <td>
                  <table class="w-100">
                    <tr><td class="no-border p0 pb4"><input type="text" name="eduStart" value="${e.startPeriod}" required></td></tr>
                    <tr><td class="no-border p0 text-center">~</td></tr>
                    <tr><td class="no-border p0"><input type="text" name="eduEnd"   value="${e.endPeriod}"  required></td></tr>
                  </table>
                </td>
                <td>
                  <select name="eduDivision" class="w-100" required>
                    <option value="졸업" ${e.division eq '재학' ? 'selected="selected"' : ''}>재학</option>
                    <option value="재학" ${e.division eq '중퇴' ? 'selected="selected"' : ''}>중퇴</option>
                    <option value="휴학" ${e.division eq '졸업' ? 'selected="selected"' : ''}>졸업</option>
                  </select>
                </td>
                <td>
                  <table class="w-100">
                    <tr><td class="no-border p0 pb4"><input type="text" name="schoolName" value="${e.schoolName}"></td></tr>
                    <tr><td class="no-border p0">
                      <select name="eduLocation" required>
                        <option ${e.location eq '전국' ? 'selected="selected"' : ''}>전국</option>
                      </select>
                    </td></tr>
                  </table>
                </td>
                <td><input type="text" name="major" value="${e.major}" required></td>
                <td><input type="text" name="grade" value="${e.grade}" required></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 경력 -->
    <div class="section">
      <div class="section-header">
        <h2>경력</h2>
        <div class="btn-group">
          <input type="button" id="addExp" value="추가">
          <input type="button" id="delExp" value="삭제">
        </div>
      </div>
      <div class="placeholder-box h-auto">
        <table id="expTable" border="1" class="m-auto w-100">
          <thead>
            <tr>
              <td class="w-30px"></td>
              <td>근무기간</td>
              <td>회사명</td>
              <td>부서/직급/직책</td>
              <td>지역</td>
            </tr>
          </thead>
          <tbody id="expBody">
            <c:forEach var="c" items="${carList}">
              <tr>
                <td class="text-center"><input type="checkbox" class="rowCheck"></td>
                <td>
                  <table class="w-100">
                    <tr><td class="no-border p0 pb4"><input type="text" name="startPeriod" value="${c.startPeriod}"></td></tr>
                    <tr><td class="no-border p0 text-center">~</td></tr>
                    <tr><td class="no-border p0"><input type="text" name="endPeriod"   value="${c.endPeriod}"></td></tr>
                  </table>
                </td>
                <td><input type="text" name="compName" value="${c.compName}"></td>
                <td><input type="text" name="task"     value="${c.task}"></td>
                <td><input type="text" name="location" value="${c.location}"></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 자격증 -->
    <div class="section">
      <div class="section-header">
        <h2>자격증</h2>
        <div class="btn-group">
          <input type="button" id="addCer" value="추가">
          <input type="button" id="delCer" value="삭제">
        </div>
      </div>
      <div class="placeholder-box h-auto">
        <table id="cerTable" border="1" class="m-auto w-100">
          <thead>
            <tr>
              <td class="w-30px"></td>
              <td>자격증명</td>
              <td>취득일</td>
              <td>발행처</td>
            </tr>
          </thead>
          <tbody id="cerBody">
            <c:forEach var="x" items="${cerList}">
              <tr>
                <td class="text-center"><input type="checkbox" class="rowCheck"></td>
                <td><input type="text" name="qualifiName" value="${x.qualifiName}"></td>
                <td><input type="text" name="acquDate"    value="${x.acquDate}"></td>
                <td><input type="text" name="organizeName" value="${x.organizeName}"></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 저장 / 제출 -->
    <div class="text-center mt-40">
      <input type="submit" name="action" value="SAVE">
      <input type="submit" name="action" value="SUBMIT">
    </div>
  </div>
</form>
</body>
</html>
