<#import "parts/common.ftl" as c>

<@c.page>
    <h3>User editor - ${user.username}</h3>

<form action="/user" method="post">

    <div>
        <select name="active" class="custom-select">
            <option selected="selected">Yes</option>
            <option value="false">No</option>
        </select>
    </div>

    <#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
    </div>
    </#list>

    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" class="btn btn-primary">Save</button>
</form>
</@c.page>
