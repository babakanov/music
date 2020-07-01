<#import "parts/common.ftl" as c>

<@c.page>
    <h3>My tasks  <a href="/task/newTask" class="btn btn-success btn-lg active" role="button" aria-pressed="true">New task</a></h3>

    <div class="row">
        <div class="col-4">
            <div class="list-group" id="list-tab" role="tablist">
                <a class="list-group-item list-group-item-action active" id="list-home-list" data-toggle="list"
                   href="#list-home" role="tab" aria-controls="home">New</a>
                <a class="list-group-item list-group-item-action" id="list-profile-list" data-toggle="list"
                   href="#list-profile" role="tab" aria-controls="profile">In Process</a>
                <a class="list-group-item list-group-item-action" id="list-messages-list" data-toggle="list"
                   href="#list-messages" role="tab" aria-controls="messages">Done</a>
            </div>
        </div>
        <div class="col-8">
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="list-home" role="tabpanel" aria-labelledby="list-home-list">
                    <div class="card-columns">
                        <#if newTask??>
                            <#list newTask as newTask>
                                <div class="card border-success mb-3" style="max-width: 18rem;">
                                    <div class="card-header">${newTask.getImportance()?string('Critical', 'Not critical')}</div>
                                    <div class="card-body">
                                        <h5 class="card-title">${newTask.getName()}</h5>
                                        <p class="card-text">${newTask.getDescription()}</p>
                                        <a href="/task/${newTask.id}" class="card-link">Edit</a>
                                        <a href="/deleteTask/${newTask.id}" class="card-link">Delete</a>
                                    </div>
                                </div>
                            <#else>
                                No Tasks
                            </#list>
                        </#if>
                    </div>
                </div>

                <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list">
                    <div class="card-columns">
                        <#if ipTask??>
                            <#list ipTask as ipTask>
                                <div class="card border-warning mb-3" style="max-width: 18rem;">
                                    <div class="card-header">${ipTask.getImportance()?string('Critical', 'Not critical')}</div>
                                    <div class="card-body">
                                        <h5 class="card-title">${ipTask.getName()}</h5>
                                        <p class="card-text">${ipTask.getDescription()}</p>
                                        <a href="/task/${ipTask.id}" class="card-link">Edit</a>
                                        <a href="/deleteTask/${ipTask.id}" class="card-link">Delete</a>
                                    </div>
                                </div>

                            <#else>
                                No Tasks
                            </#list>
                        </#if>
                    </div>
                </div>


                <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">
                    <div class="card-columns">
                        <#if doneTask??>
                            <#list doneTask as doneTask>
                                <div class="card border-danger mb-3" style="max-width: 18rem;">
                                    <div class="card-header">${doneTask.getImportance()?string('Critical', 'Not critical')}</div>
                                    <div class="card-body">
                                        <h5 class="card-title">${doneTask.getName()}</h5>
                                        <p class="card-text">${doneTask.getDescription()}</p>
                                        <a href="/task/${doneTask.id}" class="card-link">Edit</a>
                                        <a href="/deleteTask/${doneTask.id}" class="card-link">Delete</a>
                                    </div>
                                </div>
                            <#else>
                                No Tasks
                            </#list>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>
