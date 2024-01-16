package com.codacy.web;

import com.codacy.scoobydoo.LoggingHelper;
import com.codacy.scoobydoo.configuration.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class SideBar extends BaseCodacyWebPage {

    private static class Locator {

        // Organization Dashboard
        private static final By ORGANIZATION_CODING_STANDARDS = By.id("coding-standards-navigate");
        private static final By ORGANIZATION_INTEGRATIONS     = By.id("provider-integration-navigate");
        private static final By ORGANIZATION_OVERVIEW         = By.id("overview-navigate");
        private static final By ORGANIZATION_REPOSITORIES     = By.id("repositories-navigate");

        // Repository Dashboard
        private static final By PROJECT_CODE_PATTERNS         = By.id("patterns-navigate");
        private static final By PROJECT_COMMITS               = By.id("commits-navigate");
        private static final By PROJECT_COMMITS_COVERAGE      = By.id("cov-commits-navigate");
        private static final By PROJECT_DASHBOARD             = By.id("dashboard-navigate");
        private static final By PROJECT_FILES                 = By.id("files-navigate");
        private static final By PROJECT_ISSUES                = By.id("issues-navigate");
        private static final By PROJECT_PULL_REQUESTS         = By.id("pull-requests-navigate");

        // Common
        private static final By SECURITY                      = By.id("security-navigate");
        private static final By PROVIDER_INTEGRATIONS         = By.id("provider-integration-navigate");
        // These group elements need to have two versions, one for SLA, other for the old pages, at the same time.
        // Due to that, each one needs two locators, but only one of each will appear.
        // TODO: when all pages are migrated, only one version of the locators should exist.
        private static final By COVERAGE_GROUP_OLD            = By.id("coverage-navigate");
        private static final By COVERAGE_GROUP_SLA            = By.id("coverage-group-navigate");
        private static final By QUALITY_GROUP_OLD             = By.id("quality-navigate");
        private static final By QUALITY_GROUP_SLA             = By.id("quality-group-navigate");
        private static final By SETTINGS_GROUP_OLD            = By.id("group-settings-navigate");
        private static final By SETTINGS_GROUP_SLA            = By.id("settings-navigate");

        private static final By COVERAGE_FILES                 = By.id("cov-files-navigate");
        private static final By COVERAGE_PROJECT_COMMITS       = By.id("cov-commits-navigate");
        private static final By COVERAGE_PROJECT_DASHBOARD     = By.id("cov-dashboard-navigate");
        private static final By COVERAGE_PROJECT_PULL_REQUESTS = By.id("cov-pull-requests-navigate");
        private static final By COVERAGE_SIDEBAR_PULL_REQUESTS = By.id("cov-pull-requests-navigate");
    }

    private static final Duration SETTINGS_TIMEOUT = Duration.ofSeconds(15);

    public SideBar(RemoteWebDriver driver, Configuration config) {
        super(driver, config);
    }

    public boolean isProjectCodePatternsPresent() {
        return doesElementExists(Locator.PROJECT_CODE_PATTERNS);
    }

    public void selectOrganizationOverview() {
        LoggingHelper.info("Go to Organizations Overview Page.");
        getElement(Locator.ORGANIZATION_OVERVIEW).click();
    }

    public void selectOrganizationRepositories() {
        LoggingHelper.info("Go to Repositories Page.");
        getElement(Locator.ORGANIZATION_REPOSITORIES).click();
    }

    public void selectProjectCodePatterns() {
        LoggingHelper.info("Go to Patterns Page.");
        waitForPageToLoad();
        clickOnQualitySubsection(Locator.PROJECT_CODE_PATTERNS);
    }

    public void selectProjectCommits() {
        LoggingHelper.info("Go to Commits Page.");
        clickOnQualitySubsection(Locator.PROJECT_COMMITS);
        waitForPageToLoad();
    }

    public void selectProjectCommitsCoverage() {
        LoggingHelper.info("Go to Commits Page on Coverage.");
        clickOnCoverageSubsection(Locator.PROJECT_COMMITS_COVERAGE);
        waitForPageToLoad();
    }

    public void selectProjectDashboard() {
        LoggingHelper.info("Go to Dashboard Page.");
        clickOnQualitySubsection(Locator.PROJECT_DASHBOARD);
    }

    public void selectProjectFiles() {
        LoggingHelper.info("Go to Files Page.");
        getElement(Locator.PROJECT_FILES).click();
    }

    public void selectProjectIssues() {
        LoggingHelper.info("Go to Issues Page.");
        clickOnQualitySubsection(Locator.PROJECT_ISSUES);
    }

    public void selectProjectPullRequests() {
        LoggingHelper.info("Go to PR Page.");
        getElement(Locator.PROJECT_PULL_REQUESTS).click();
    }

    public void selectSidebarCoveragePullRequests() {
        LoggingHelper.info("Go to Coverage PR Page.");
        getElement(Locator.COVERAGE_SIDEBAR_PULL_REQUESTS).click();
    }

    public void selectProjectSecurity() {
        LoggingHelper.info("Go to Security Page.");
        getElement(Locator.SECURITY).click();
    }

    public void selectSettings() {
        LoggingHelper.info("Go to Settings Page.");
        waitForPageToLoad();
        clickOnGroupButton(Locator.SETTINGS_GROUP_OLD, Locator.SETTINGS_GROUP_SLA);
    }

    public void selectProviderIntegrations() {
        LoggingHelper.info("Go to provider integrations page");
        waitForPageToLoad();
        getElement(Locator.PROVIDER_INTEGRATIONS, SETTINGS_TIMEOUT, config.getTimeout().DEFAULT_POLLING).click();
    }

    public void validateOrganizationSideBar() {
        LoggingHelper.info("Validate organization side bar.");
        getElement(Locator.ORGANIZATION_OVERVIEW)                      .assertIsDisplayed();
        getElement(Locator.ORGANIZATION_REPOSITORIES)    .assertIsDisplayed();
        getElement(Locator.ORGANIZATION_CODING_STANDARDS).assertIsDisplayed();
        getElement(Locator.ORGANIZATION_INTEGRATIONS)    .assertIsDisplayed();
        getElement(Locator.SECURITY)                     .assertIsDisplayed();
        getElement(Locator.SETTINGS_GROUP_SLA)           .assertIsDisplayed();
    }

    public void validateSideBar() {
        LoggingHelper.info("Validate side bar.");
        waitForElementToBeVisible(Locator.ORGANIZATION_OVERVIEW);
        getElement(Locator.ORGANIZATION_OVERVIEW)              .assertIsDisplayed();
        getElement(Locator.PROJECT_DASHBOARD)    .assertIsDisplayed();
        getElement(Locator.PROJECT_COMMITS)      .assertIsDisplayed();
        getElement(Locator.PROJECT_FILES)        .assertIsDisplayed();
        getElement(Locator.PROJECT_ISSUES)       .assertIsDisplayed();
        getElement(Locator.PROJECT_PULL_REQUESTS).assertIsDisplayed();
        getElement(Locator.SECURITY)             .assertIsDisplayed();
        getElement(Locator.PROJECT_CODE_PATTERNS).assertIsDisplayed();
        validateSideBarGroupsButtons();
    }

    public void validateSideBarCoverage() {
        LoggingHelper.info("Validate side bar.");
        waitForElementToBeVisible(Locator.ORGANIZATION_OVERVIEW);
        getElement(Locator.ORGANIZATION_OVERVIEW)                       .assertIsDisplayed();
        getElement(Locator.COVERAGE_PROJECT_DASHBOARD)    .assertIsDisplayed();
        getElement(Locator.COVERAGE_PROJECT_COMMITS)      .assertIsDisplayed();
        getElement(Locator.COVERAGE_FILES)                .assertIsDisplayed();
        getElement(Locator.COVERAGE_PROJECT_PULL_REQUESTS).assertIsDisplayed();
        validateSideBarGroupsButtons();
    }

    public void validateSideBarForSettings() {
        validateSideBarGroupsButtons();
    }

    public SideBar assertDashboardIsDisplayed() {
        getElement(Locator.PROJECT_DASHBOARD).assertIsDisplayed();
        return this;
    }

    public void clickOnCoverageGroup() {
        clickOnGroupButton(Locator.COVERAGE_GROUP_OLD, Locator.COVERAGE_GROUP_SLA);
    }



    private void assertGroupButtonIsDisplayed(By oldLocator, By slaLocator, String groupName) {
        assertTrue(
                isGroupButtonDisplayed(oldLocator, slaLocator),
                groupName + " section is not displayed.");
    }

    private void clickOnCoverageSubsection(By subsectionLocator) {
        if(!isElementDisplayed(subsectionLocator)) {
            clickOnCoverageGroup();
        }
        getElement(subsectionLocator).click();
    }

    private void clickOnGroupButton(By oldGroupLocator, By slaGroupLocator) {
        // While all pages are not migrated, this hack needs to be done.
        webUtils.waitForFunction(x -> isGroupButtonDisplayed(oldGroupLocator, slaGroupLocator),
                config.getTimeout().FIND_ELEMENT_WAIT_TIMEOUT, config.getTimeout().DEFAULT_POLLING);

        if(isElementDisplayed(oldGroupLocator)) {
            getElement(oldGroupLocator).click();
        } else {
            getElement(slaGroupLocator).click();
        }
    }

    private void clickOnQualityGroup() {
        clickOnGroupButton(Locator.QUALITY_GROUP_OLD, Locator.QUALITY_GROUP_SLA);
    }

    private void clickOnQualitySubsection(By subsectionLocator) {
        if(!isElementDisplayed(subsectionLocator)) {
            clickOnQualityGroup();
        }
        getElement(subsectionLocator).click();
    }

    private boolean isGroupButtonDisplayed(By oldLocator, By slaLocator) {
        return isElementDisplayed(oldLocator) || isElementDisplayed(slaLocator);
    }

    private void validateSideBarGroupsButtons() {
        assertGroupButtonIsDisplayed(Locator.SETTINGS_GROUP_OLD, Locator.SETTINGS_GROUP_SLA, "Settings");
        assertGroupButtonIsDisplayed(Locator.QUALITY_GROUP_OLD, Locator.QUALITY_GROUP_SLA, "Quality");
        assertGroupButtonIsDisplayed(Locator.COVERAGE_GROUP_OLD, Locator.COVERAGE_GROUP_SLA, "Coverage");
    }
}
