<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Task delete - ${task1.name}</h3>

<form action="/deleteTask" method="post">
    <div class="card border-light mb-3" style="max-width: 18rem;">
        <div class="card-header">${task1.getImportance()?string('Critical', 'Not critical')}</div>
        <div class="card-body">
            <h5 class="card-title">${task1.name}</h5>
            <p class="card-text">${task1.description}</p>
            <h5 class="card-title">${task1.getStatus()}</h5>
        </div>
    </div>

    <input type="hidden" value="${task1.id}" name="taskId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" class="btn btn-primary">Delete</button>
</form>
</@c.page>
