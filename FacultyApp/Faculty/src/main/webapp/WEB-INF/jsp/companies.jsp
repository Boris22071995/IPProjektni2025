<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>


<div class="container-fluid p-2">

  <div class="d-flex justify-content-between align-items-center mb-3">
    <h5 class="m-0">Companies</h5>
    <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#addCompanyModal">
      + Add
    </button>
  </div>

  <!-- TABLE -->
  <div class="table-responsive">
    <table class="table table-sm table-bordered align-middle">
      <thead class="table-warning">
        <tr>
          <th>Name</th>
          <th>Status</th>
          <th style="width:120px;">Action</th>
        </tr>
      </thead>
      <tbody>
  <c:forEach items="${companies}" var="c">
    <tr>
      <td>${c.name}</td>

      <td>
        <c:choose>
          <c:when test="${c.approved}">
            <span class="badge bg-success">ACTIVE</span>
          </c:when>
          <c:otherwise>
            <span class="badge bg-secondary">INACTIVE</span>
          </c:otherwise>
        </c:choose>
      </td>

      <td>
  <c:choose>
    <c:when test="${c.approved}">
      <form method="post" action="${pageContext.request.contextPath}/company">
  <input type="hidden" name="id" value="${c.id}">
  <input type="hidden" name="action" value="deactivate">
  <button class="btn btn-sm btn-danger">Deactivate</button>
</form>
    </c:when>
    <c:otherwise>
     <form method="post" action="${pageContext.request.contextPath}/company">
  <input type="hidden" name="id" value="${c.id}">
  <input type="hidden" name="action" value="activate">
  <button class="btn btn-sm btn-success">Activate</button>
</form>

    </c:otherwise>
  </c:choose>
</td>

    </tr>
  </c:forEach>
</tbody>

    </table>
    
    <div class="modal fade" id="addCompanyModal" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <form method="post" action="${pageContext.request.contextPath}/company">
        <div class="modal-header">
          <h5 class="modal-title">Add Company</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>

       <div class="modal-body">

  <h6>Company data</h6>

  <div class="mb-2">
    <label class="form-label">Company name</label>
    <input type="text" name="companyName" class="form-control form-control-sm" required>
  </div>

  <div class="mb-3">
    <label class="form-label">Description</label>
    <textarea name="companyDescription"
              class="form-control form-control-sm"
              rows="3"
              required></textarea>
  </div>

  <hr>

  <h6>Company login</h6>

  <div class="mb-2">
    <label class="form-label">Username</label>
    <input type="text" name="username" class="form-control form-control-sm" required>
  </div>

  <div class="mb-2">
    <label class="form-label">Password</label>
    <input type="password" name="password" class="form-control form-control-sm" required>
  </div>

</div>


        <div class="modal-footer">
          <button type="submit" class="btn btn-primary btn-sm">Save</button>
          <button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">Cancel</button>
        </div>

      </form>

    </div>
  </div>
</div>
    
  </div>

</div>
