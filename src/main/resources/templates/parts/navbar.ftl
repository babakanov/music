<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">TestApi</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if !isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/task/allTasks">My tasks</a>
                </li>
            </#if>

            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">User List</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Main page</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/addUser">User Add</a>
            </li>
            </#if>

            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Profile</a>
                </li>
            </#if>
        </ul>

        <div class="navbar-text mr-4">${name}</div>
        <@l.logout />
    </div>
</nav>
