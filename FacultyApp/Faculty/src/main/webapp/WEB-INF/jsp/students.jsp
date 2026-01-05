<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>


<script>
function fillStudent(fn, ln, idx, year, username) {
  document.getElementById('firstName').value = fn;
  document.getElementById('lastName').value = ln;
  document.getElementById('indexNumber').value = idx;
  document.getElementById('yearOfStudy').value = year;
  document.getElementById('username').value = username;
  document.getElementById('password').value = '';
}
</script>



<div class="container-fluid p-2">

  <div class="d-flex justify-content-between align-items-center mb-2">
    <h5 class="m-0">Students</h5>

    <div>
      <button class="btn btn-sm btn-secondary me-1"
              data-bs-toggle="modal"
              data-bs-target="#uploadCsvModal">
        CSV
      </button>

       <button class="btn btn-sm btn-primary"
          data-bs-toggle="modal"
          data-bs-target="#addStudentModal">
    + Add
  </button>
    </div>
  </div>

  <!-- TABLE -->
  <table class="table table-sm table-bordered align-middle">
  <thead class="table-warning">
    <tr>
      <th>Last name</th>
      <th>First name</th>
      <th>Index</th>
      <th>Year</th>
    </tr>
  </thead>

  <tbody>
    <c:forEach items="${students}" var="s">
      <tr data-bs-toggle="modal"
          data-bs-target="#studentModal"
          onclick="fillStudent(
            '${s.firstName}',
            '${s.lastName}',
            '${s.indexNumber}',
            '${s.yearOfStudy}',
            '${s.username}'
          )">

        <td>${s.lastName}</td>
        <td>${s.firstName}</td>
        <td>${s.indexNumber}</td>
        <td>${s.yearOfStudy}</td>

      </tr>
    </c:forEach>
  </tbody>
</table>


</div>
<div class="modal fade" id="addStudentModal" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <form method="post"
            action="${pageContext.request.contextPath}/students">

        <div class="modal-header">
          <h5 class="modal-title">Add Student</h5>
          <button type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body">

          <h6>Student data</h6>

          <div class="mb-2">
            <label class="form-label">First name</label>
            <input type="text"
                   name="firstName"
                   class="form-control form-control-sm"
                   required>
          </div>

          <div class="mb-2">
            <label class="form-label">Last name</label>
            <input type="text"
                   name="lastName"
                   class="form-control form-control-sm"
                   required>
          </div>

          <div class="mb-2">
            <label class="form-label">Index number</label>
            <input type="text"
                   name="indexNumber"
                   class="form-control form-control-sm"
                   required>
          </div>

          <div class="mb-3">
            <label class="form-label">Year of study</label>
            <input type="number"
                   name="yearOfStudy"
                   min="1"
                   max="6"
                   class="form-control form-control-sm"
                   required>
          </div>

          <hr>

          <h6>Login data</h6>

          <div class="mb-2">
            <label class="form-label">Username</label>
            <input type="text"
                   name="username"
                   class="form-control form-control-sm"
                   required>
          </div>

          <div class="mb-2">
            <label class="form-label">Password</label>
            <input type="password"
                   name="password"
                   class="form-control form-control-sm"
                   required>
          </div>

        </div>

        <div class="modal-footer">
          <button type="submit"
                  class="btn btn-primary btn-sm">
            Save
          </button>
          <button type="button"
                  class="btn btn-secondary btn-sm"
                  data-bs-dismiss="modal">
            Cancel
          </button>
        </div>

      </form>

    </div>
  </div>
</div>

<div class="modal fade" id="studentModal" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <form method="post"
            action="${pageContext.request.contextPath}/students">

      

        <div class="modal-header">
          <h5 class="modal-title">Edit student</h5>
          <button type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body">

          <div class="mb-2">
            <label class="form-label">First name</label>
            <input id="firstName"
                   name="firstName"
                   class="form-control form-control-sm"
                   required>
          </div>

          <div class="mb-2">
            <label class="form-label">Last name</label>
            <input id="lastName"
                   name="lastName"
                   class="form-control form-control-sm"
                   required>
          </div>

          <div class="mb-2">
            <label class="form-label">Index number</label>
            <input id="indexNumber"
                   name="indexNumber"
                   class="form-control form-control-sm"
                   readonly>
          </div>

          <div class="mb-3">
            <label class="form-label">Year of study</label>
            <input id="yearOfStudy"
                   name="yearOfStudy"
                   type="number"
                   class="form-control form-control-sm"
                   required>
          </div>

          <hr>

          <div class="mb-2">
            <label class="form-label">Username</label>
            <input id="username"
                   name="username"
                   class="form-control form-control-sm"
                   required>
          </div>

          <div class="mb-2">
            <label class="form-label">New password (optional)</label>
            <input id="password"
                   name="password"
                   type="password"
                   class="form-control form-control-sm">
          </div>

        </div>

        <div class="modal-footer">
          <button type="submit"
                  class="btn btn-primary btn-sm" value="edit">
            Save
          </button>

          <button type="submit"
                  name="action"
                  value="delete"
                  class="btn btn-danger btn-sm"
                  onclick="return confirm('Delete student?')">
            Delete
          </button>
        </div>

      </form>

    </div>
  </div>
</div>
<div class="modal fade" id="uploadCsvModal" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <form method="post"
            action="${pageContext.request.contextPath}/students"
            enctype="multipart/form-data">

        <input type="hidden" name="action" value="csv">

        <div class="modal-header">
          <h5 class="modal-title">Upload CSV</h5>
          <button type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body">
          <label class="form-label">CSV file</label>
          <input type="file"
                 name="csvFile"
                 accept=".csv"
                 class="form-control form-control-sm"
                 required>
        </div>

        <div class="modal-footer">
          <button type="submit"
                  class="btn btn-primary btn-sm">
            Upload
          </button>
          <button type="button"
                  class="btn btn-secondary btn-sm"
                  data-bs-dismiss="modal">
            Cancel
          </button>
        </div>

      </form>

    </div>
  </div>
</div>

