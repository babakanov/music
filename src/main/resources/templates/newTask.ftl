<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Create a new task</h3>

    ${message?ifExists}
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Task name:</label>
            <div class="col-sm-6">
                <input type="text" name="name" class="form-control" placeholder="name"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Description:</label>
            <div class="col-sm-6">
                <input type="text" name="description" class="form-control" placeholder="description"/>
            </div>
        </div>

        <div>
            <label class="col-sm-2 col-form-label">Deadline:</label>
            <div class="col-sm-6">
                <input type="date" name="deadline" class="form-control" />
            </div>
        </div>

        <div>
            <select name="importance" class="custom-select">
                <option selected="selected">Selected active</option>
                <option value="true">Critically</option>
                <option value="false">Not critical</option>
            </select>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Create</button>
    </form>
</@c.page>
