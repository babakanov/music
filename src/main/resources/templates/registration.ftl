<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <h3>Register for TestApi </h3>
    </div>
    ${message?ifExists}
    <@l.login "/registration" true />
</@c.page>
