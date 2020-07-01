<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <h3>
            Dear guest, first log in.
        </h3>
    </div>
<@l.login "/login" false/>
</@c.page>
