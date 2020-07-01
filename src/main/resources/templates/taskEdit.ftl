<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Task editor - ${task.name}</h3>

${message?ifExists}
<form action="/task" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Task name:</label>
        <div class="col-sm-6">
            <input type="text" name="name" class="form-control" placeholder="name" value="${task.name}"/>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Description:</label>
        <div class="col-sm-6">
            <input type="text" name="description" class="form-control" placeholder="description" value="${task.description}"/>
        </div>
    </div>

    <div>
        <label class="col-sm-2 col-form-label">Selected importance:</label>
        <select name="importance" class="custom-select">
            <option value="true">Critically</option>
            <option value="false">Not critical</option>
        </select>
    </div>

    <div>
        <label class="col-sm-2 col-form-label">Selected status:</label>
        <select name="status" class="custom-select">
            <option>NEW</option>
            <option>IN_PROGRESS</option>
            <option>DONE</option>
        </select>
    </div>

    <input type="hidden" value="${task.id}" name="taskId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" class="btn btn-primary">Save</button>
</form>
</@c.page>
