<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container-fluid p-2">

  <h5 class="mb-3">Student work tracking</h5>

  <!-- STUDENT LIST -->
  <div class="mb-3">
    <label class="form-label">Select student</label>
    <form method="get" action="${pageContext.request.contextPath}/dashboard">
      <input type="hidden" name="page" value="worklog"/>

      <select name="studentId"
              class="form-select form-select-sm"
              onchange="this.form.submit()">

        <option value="">-- choose --</option>

        <c:forEach items="${students}" var="s">
          <option value="${s.id}"
            <c:if test="${s.id == selectedStudentId}">selected</c:if>>
            ${s.name} (${s.index})
          </option>
        </c:forEach>

      </select>
    </form>
  </div>

  <c:if test="${not empty selectedStudentId}">

    <!-- WORK LOG -->
    <div class="card mb-3">
      <div class="card-header bg-warning">
        Work log
      </div>
      <ul class="list-group list-group-flush">
        <c:forEach items="${workLogs}" var="w">
          <li class="list-group-item">
            <strong>Week ${w.week}:</strong><br/>
            ${w.activities}
          </li>
        </c:forEach>
      </ul>
    </div>

    <!-- COMPANY FEEDBACK -->
    <div class="card mb-3">
      <div class="card-header bg-info text-white">
        Company feedback
      </div>
      <div class="card-body">
        <p>${companyFeedback.comment}</p>
        <small class="text-muted">
          ${companyFeedback.createdAt}
        </small>
      </div>
    </div>

    <!-- FACULTY GRADE -->
    <div class="card mb-3">
      <div class="card-header bg-success text-white">
        Faculty grade
      </div>
      <div class="card-body">
        <h4>Grade: ${facultyGrade.grade}</h4>
        <p>${facultyGrade.comment}</p>
      </div>
    </div>

  </c:if>

</div>
