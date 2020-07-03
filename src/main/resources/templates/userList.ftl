<#import "parts/common.ftl" as c>

<@c.page>
<h3>List of users</h3>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th>Active</th>
        <th>Email</th>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td>${user.active?string('Yes', 'No') }</td>
            <td>${user.email}</td>
            <td><a href="/user/${user.id}">Edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>
