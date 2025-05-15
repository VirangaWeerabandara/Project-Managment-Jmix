package com.company.projectmanagement.security;

import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.UiMinimalPolicies;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "UI: minimal access", code = UiMinimalRole.CODE)
public interface UiMinimalRole extends UiMinimalPolicies {

    String CODE = "ui-minimal";

    @ViewPolicy(viewIds = {"MainView", "TimeEntry.detail"})
    void main();

    @ViewPolicy(viewIds = "LoginView")
    @SpecificPolicy(resources = "ui.loginToUi")
    void login();

    @MenuPolicy(menuIds = "TimeEntry.detail")
    void screens();
}
