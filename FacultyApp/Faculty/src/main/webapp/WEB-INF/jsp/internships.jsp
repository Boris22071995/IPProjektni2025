<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>


<div class="container-fluid p-2">

  <h5 class="mb-3">Available Internships</h5>

  <div class="table-responsive">
    <table class="table table-sm table-bordered align-middle">

      <thead class="table-warning">
        <tr>
          <th>Title</th>
          <th>Company</th>
          <th>Description</th>
          <th>Requirements</th>
          <th>Restriction</th>
          <th>Technologies</th>
          <th>Duration</th>
        </tr>
      </thead>

      <tbody>
        <c:forEach items="${internships}" var="i">
          <tr>
            <td>${i.title}</td>
            <td>${i.companyName}</td>
			<td>${i.description}</td>
			<td>${i.requirements}</td>
			<td>${i.restriction}</td>
            <td>
              <c:forEach items="${i.technologies}" var="t">
                <span class="badge bg-secondary me-1">${t}</span>
              </c:forEach>
            </td>

            <td>
              ${i.startDate} – ${i.endDate}
            </td>
          </tr>
        </c:forEach>
      </tbody>

    </table>
  </div>
</div>
