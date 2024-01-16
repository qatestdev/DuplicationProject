package com.codacy.web;

import com.codacy.Constant;
import com.codacy.scoobydoo.configuration.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class PullRequestWebPage extends BaseCodacyWebPage {

    private static class Locator {

        private static final By ALERT_MESSAGE                          = By.id("alert_message");
        private static final By AUTHOR_COLUMN                          = By.id("table-header-author");
        private static final By COMMITS_CONTAINER                      = By.id("commitsView");
        private static final By COMMITS_TAB                            = By.id("commits_tab");
        private static final By COMMIT_COVERAGE_TABLE                  = By.id("commits-table") ;
        private static final By COMMIT_COVERAGE_TITLE_COLUMN           = By.id("table-header-commit");
        private static final By COMMIT_DETAIL                          = By.id("qa-commit-detail");
        private static final By COMMIT_DETAIL_CONTAINER                = By.id("qa-commit-detail-container");
        private static final By COMMIT_METRICS_CONTAINER               = By.id("commit-overview-result");
        private static final By COMMIT_STATUS_CONTAINER                = By.id("commit_status_container");
        private static final By COMMIT_TABLE                           = By.id("commit-table");
        private static final By COMMIT_TIME                            = By.id("commit-time");
        private static final By COMMIT_TOTAL_COVERAGE_COLUMN           = By.id("table-header-coverage-total");
        private static final By COMPLEXITY_VALUE                       = By.id("complexity-value");
        private static final By CLOSE_PRS_TAB                          = By.id("closed_prs_tab");
        private static final By COVERAGE_COLUMN_ON_FILES_TAB           = By.cssSelector("td.coverage-value-cell");
        private static final By COVERAGE_FILES_TABLE                   = By.id("commit-files-table");
        private static final By OPEN_PRS_TAB                           = By.id("open_prs_tab");
        private static final By COVERAGE_SINGLE_COLUMN_ON_FILES_TAB    = By.cssSelector("td.coverage-cell");
        private static final By COVERAGE_STATUS_COLUMN                 = By.id("table-header-status-coverage");
        private static final By TITLE_COLUMN                           = By.id("table-header-pull-request");
        private static final By COVERAGE_VALUE                         = By.id("variation-value");
        private static final By COVERAGE_VARIATION                     = By.id("pr-coverage-variation");
        private static final By COVERAGE_VARIATION_COLUMN              = By.id("table-header-coverage-variation") ;
        private static final By COVERAGE_VARIATION_COLUMN_ON_FILES_TAB = By.cssSelector("td.coverage-variation-cell");
        private static final By DIFF_CONTAINER                         = By.id("commitDiff");
        private static final By DIFF_COVERAGE_COLUMN                   = By.id("table-header-diff-coverage") ;
        private static final By DIFF_COVERAGE_VALUE                    = By.id("diff_coverage-value");
        private static final By DIFF_LEFT_CHANGES_COMPONENT            = By.id("src/main/java/org/codacy/BasePage.java");
        private static final By DIFF_RIGHT_FILE_LIST                   = By.xpath("//p[contains(text(),'BasePage.java')]");
        private static final By DIFF_TAB                               = By.id("diff_tab");
        private static final By DUPLICATION_VALUE                      = By.id("duplication-value");
        private static final By FILES_CONTAINER                        = By.id("filesView");
        private static final By FILES_COVERAGE_TAB                     = By.id("pr_files_tab");
        private static final By FILES_TAB                              = By.id("files_tab");
        private static final By FIRST_DUPLICATED_FILENAME              = new ByChained(By.className("duplication-block"), By.tagName("p"));
        private static final By FIXED_DUPLICATION_CONTAINER            = By.id("fixedClonesView");
        private static final By FIXED_DUPLICATION_TAB                  = By.id("fixed_duplication_tab");
        private static final By FIXED_ISSUES_CONTAINER                 = By.id("fixedIssuesView");
        private static final By FIXED_ISSUES_DETAIL                    = By.id("fixed-issues");
        private static final By FIXED_ISSUES_TAB                       = By.id("fixed_issues_tab");
        private static final By ISSUES_COLUMN                          = By.id("issues_column");
        private static final By ISSUES_VALUE                           = By.id("issues-value");
        private static final By NAV_BAR_NAVIGATION                     = By.id("commit_details_tab");
        private static final By NEW_DUPLICATION_CONTAINER              = By.id("newClonesView");
        private static final By NEW_DUPLICATION_TAB                    = By.id("new_duplication_tab");
        private static final By NEW_ISSUES_CONTAINER                   = By.id("newIssuesView");
        private static final By NEW_ISSUES_DETAIL                      = By.xpath("//*[@href=\"#possible-new-issues\"]");
        private static final By NEW_ISSUES_TAB                         = By.id("new_issues_tab");
        private static final By NO_NEW_COMMITS                         = By.xpath("//*[contains(text(),'Oops! No commits!')]");
        private static final By NO_NEW_FIXED_ISSUES                    = By.xpath("//*[contains(text(),'fix any issue!')]");
        private static final By NO_NEW_ISSUES                          = By.xpath("//*[contains(text(),'Woo! You don')]");
        private static final By ORIGIN_COLUMN                          = By.id("origin_column");
        private static final By PR_DIFF_TAB                            = By.id("pr_diff_tab");
        private static final By PULL_REQUESTS_DIFF_COVERAGE            = By.id("pr-diff-variation");
        private static final By PULL_REQUESTS_TABLE_CONTAINER          = By.id("pull-requests-table");
        private static final By SELECT_PULL_REQUEST                    = By.cssSelector("#pull-requests-table tbody > tr:first-child");
        private static final By SELECT_VIEW_LOGS                       = By.xpath("//*[contains(text(),'View logs')]") ;
        private static final By STATUS_COLUMN                          = By.id("table-header-status-quality");
        private static final By NEW_ISSUES_COLUMN                      = By.id("table-header-new-issues");
        private static final By DUPLICATION_COLUMN                     = By.id("table-header-duplication");
        private static final By COMPLEXITY_COLUMN                      = By.id("table-header-complexity");

        private static final String COVERAGE_VARIATION_VALUE_WITH_VALUE                      = "//*[contains(text(),'%s')]";
        private static final String DIFF_COVERAGE_VALUE_WITH_VALUE                           = "//*[contains(text(),'%s')]";
        private static final String FILE_NAME_EXISTENCE                                      = "//strong[contains(text(),'%s')]";
        private static final String SELECT_COVERAGE_PULL_REQUEST                             = "//*[@id=\"pull-requests-table\"]/table/tbody/tr[%s]";
        private static final String SELECT_PULL_REQUEST_BY_TITLE                             = "//*[text()[contains(.,'%s')]]";

        private static By getFileHeaderDiffCoverageValueLocator(String coverageValue) {
            return By.xpath("//div[contains(text(), '" + coverageValue + "')]");
        }
    }

    private final String url = config.getData(Constant.Key.URL_APP);

    public PullRequestWebPage(RemoteWebDriver driver, Configuration configuration) {
        super(driver, configuration);
    }

    public void selectClosedPrsTab() {
        getElement(Locator.CLOSE_PRS_TAB).isDisplayed();
        getElement(Locator.CLOSE_PRS_TAB).click();
    }

    public void selectOpenPrsTab() {
        getElement(Locator.OPEN_PRS_TAB).isDisplayed();
        getElement(Locator.OPEN_PRS_TAB).click();
    }

    public void validateOpenPullRequestsTable() {
        getElementWhenVisible(Locator.PULL_REQUESTS_TABLE_CONTAINER).isDisplayed();
        getElementWhenVisible(Locator.STATUS_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.AUTHOR_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.TITLE_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.NEW_ISSUES_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.DUPLICATION_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.COMPLEXITY_COLUMN).isDisplayed();
    }

    public void validateOpenPullRequestsTableCoverage() {
        getElementWhenVisible(Locator.PULL_REQUESTS_TABLE_CONTAINER).isDisplayed();
        getElementWhenVisible(Locator.COVERAGE_STATUS_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.AUTHOR_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.TITLE_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.DIFF_COVERAGE_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.COVERAGE_VARIATION_COLUMN).isDisplayed();
    }

    public void validatePullRequestsCommitsTableCoverage() {
        getElementWhenVisible(Locator.COMMIT_COVERAGE_TABLE).isDisplayed();
        getElementWhenVisible(Locator.COVERAGE_STATUS_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.AUTHOR_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.COMMIT_COVERAGE_TITLE_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.COMMIT_TOTAL_COVERAGE_COLUMN).isDisplayed();
        getElementWhenVisible(Locator.COVERAGE_VARIATION_COLUMN).isDisplayed();
    }

    public void validateClosedPullRequestsTable() {
        validateOpenPullRequestsTable();
    }

    public void validateClosedPullRequestsTableCoverage() {
        validateOpenPullRequestsTableCoverage();
    }

    public void selectPullRequest() {
        getElementWhenClickable(Locator.SELECT_PULL_REQUEST).isDisplayed();
        getElementWhenClickable(Locator.SELECT_PULL_REQUEST).click();
    }

    public void selectCoveragePullRequest(String line) {
        getElementWhenClickable(By.xpath(String.format(Locator.SELECT_COVERAGE_PULL_REQUEST, line))).isDisplayed();
        getElementWhenClickable(By.xpath(String.format(Locator.SELECT_COVERAGE_PULL_REQUEST, line))).click();
    }

    //Todo refactor tests using "selectPullRequest" method above by "selectPullRequestByTitle"
    public void selectPullRequestByTitle(String pullRequestTitle) {
        getElementWhenClickable(By.xpath(String.format(Locator.SELECT_PULL_REQUEST_BY_TITLE, pullRequestTitle))).click();
    }

    public void validateCommitDetail() {
        getElementWhenVisible(Locator.COMMIT_DETAIL_CONTAINER).isDisplayed();
        getElementWhenVisible(Locator.COMMIT_DETAIL).isDisplayed();
        getElementWhenVisible(Locator.COMMIT_TIME).isDisplayed();
        getElementWhenVisible(Locator.COMMIT_STATUS_CONTAINER).isDisplayed();
    }

    public void validateDiffCoverageValue(String coverageValue) {
        assertElementContainsCoverageValue(Locator.DIFF_COVERAGE_VALUE, coverageValue);
    }

    public void validatePRDiffCoverageValue(String coverageValue) {
        assertElementContainsCoverageValue(Locator.PULL_REQUESTS_DIFF_COVERAGE, coverageValue);
    }

    public void validatePRFileHeaderDiffCoverageValue(String coverageValue) {
        getElement(Locator.getFileHeaderDiffCoverageValueLocator(coverageValue)).assertIsDisplayed();
    }

    public void validateCoverageVariationValue(final String coverageValue) {
        getElementWhenVisible(By.xpath(String.format(Locator.COVERAGE_VARIATION_VALUE_WITH_VALUE, coverageValue))).isDisplayed();
    }

    // Use this method when the file line has only the coverage diff value column (no coverage value column then).
    // See also: validateCoverageValuesOfFirstFile method.
    public void validateCoverageVariationValueOfFirstFile(final String coverageVariationValue) {
        assertElementContainsCoverageValue(Locator.COVERAGE_SINGLE_COLUMN_ON_FILES_TAB, coverageVariationValue);
    }

    // Use this method when the file line has BOTH coverage and coverage diff values columns.
    // See also: validateCoverageVariationValueOfFirstFile method.
    public void validateCoverageValuesOfFirstFile(String coverageValue, String coverageVariationValue) {
        assertElementContainsCoverageValue(Locator.COVERAGE_COLUMN_ON_FILES_TAB, coverageValue);
        assertElementContainsCoverageValue(Locator.COVERAGE_VARIATION_COLUMN_ON_FILES_TAB, coverageVariationValue);
    }

    public void validateCommitMetricsDetails() {
        getElementWhenVisible(Locator.COMMIT_METRICS_CONTAINER).isDisplayed();
        getElementWhenVisible(Locator.ALERT_MESSAGE).isDisplayed();
        getElementWhenVisible(Locator.ISSUES_VALUE).isDisplayed();
        getElementWhenVisible(Locator.DUPLICATION_VALUE).isDisplayed();
        getElementWhenVisible(Locator.COMPLEXITY_VALUE).isDisplayed();
        getElementWhenVisible(Locator.COVERAGE_VALUE).isDisplayed();
        getElementWhenVisible(Locator.DIFF_COVERAGE_VALUE).isDisplayed();
    }

    public void validateNewIssuesDetails() {
        getElementWhenVisible(Locator.NAV_BAR_NAVIGATION).isDisplayed();
        getElementWhenClickable(Locator.NEW_ISSUES_TAB).click();
        getElementWhenVisible(Locator.NEW_ISSUES_CONTAINER).isDisplayed();
        try {
            assertElementIsDisplayed(Locator.NEW_ISSUES_DETAIL);
        } catch (AssertionError e) {
            assertElementIsDisplayed(Locator.NO_NEW_ISSUES);
        }
    }

    public void validateFixedIssuesDetails() {
        getElementWhenClickable(Locator.FIXED_ISSUES_TAB).click();
        getElementWhenVisible(Locator.FIXED_ISSUES_CONTAINER).isDisplayed();
        try {
            assertElementIsDisplayed(Locator.FIXED_ISSUES_DETAIL);
        } catch (AssertionError e){
            assertElementIsDisplayed(Locator.NO_NEW_FIXED_ISSUES);
        }
    }

    public void validateNewDuplicationDetails() {
        getElementWhenClickable(Locator.NEW_DUPLICATION_TAB).click();
        getElementWhenVisible(Locator.NEW_DUPLICATION_CONTAINER).isDisplayed();
    }

    public void validateFixedDuplicationDetails() {
        getElementWhenClickable(Locator.FIXED_DUPLICATION_TAB).click();
        getElementWhenVisible(Locator.FIXED_DUPLICATION_CONTAINER).isDisplayed();
    }

    public void validateFilesDetails() {
        getElementWhenClickable(Locator.FILES_TAB).click();
        getElementWhenVisible(Locator.FILES_CONTAINER).isDisplayed();
    }

    public void validateCoverageFilesDetails() {
        getElementWhenClickable(Locator.FILES_COVERAGE_TAB).click();
        getElementWhenVisible(Locator.COVERAGE_FILES_TABLE).isDisplayed(); //commit-files-table
        getElementWhenVisible(Locator.PULL_REQUESTS_DIFF_COVERAGE).isDisplayed();
        getElementWhenVisible(Locator.COVERAGE_VARIATION).isDisplayed();
    }

    public void validateDiffDetails() {
        getElementWhenClickable(Locator.DIFF_TAB).click();
        getElementWhenVisible(Locator.DIFF_CONTAINER).isDisplayed();
    }

    public void validateCommitsDetails() {
        getElementWhenClickable(Locator.COMMITS_TAB).click();
        getElementWhenVisible(Locator.COMMITS_CONTAINER).isDisplayed();
        try {
            assertElementIsDisplayed(Locator.COMMIT_TABLE);
        } catch (AssertionError e) {
            assertElementIsDisplayed(Locator.NO_NEW_COMMITS);
        }
    }

    public void validateCommitsCoverageDetails() {
        getElementWhenVisible(Locator.PULL_REQUESTS_DIFF_COVERAGE).isDisplayed();
        getElementWhenVisible(Locator.COVERAGE_VARIATION).isDisplayed();

    }

    public void validateDiffCoverageDetails() {
        getElement(Locator.PULL_REQUESTS_DIFF_COVERAGE).assertIsDisplayed();
        getElement(Locator.COVERAGE_VARIATION).assertIsDisplayed();
        getElement(Locator.DIFF_RIGHT_FILE_LIST).assertIsDisplayed();
        getElement(Locator.DIFF_LEFT_CHANGES_COMPONENT).assertIsDisplayed();
    }

    public void selectNewDuplicationTab() {
        getElementWhenClickable(Locator.NEW_DUPLICATION_TAB).click();
    }

    public void validateFirstDuplicationBlock(String targetFilename) {
        waitForElementToBeVisible(Locator.FIRST_DUPLICATED_FILENAME);
        getElement(Locator.FIRST_DUPLICATED_FILENAME)
                .assertHasExactText(targetFilename);
    }

    public void validateIfFilesArePresent(String... fileNames) {
        for (String file : fileNames) {
            getElementWhenVisibleWithConfigurableTimeout(By.xpath(String.format(Locator.FILE_NAME_EXISTENCE, file)),
                    config.getTimeout().LONG_WAIT_TIMEOUT,
                    config.getTimeout().LONG_POLLING);
        }
    }

    public void selectFilesTab() {
        getElementWhenClickable(Locator.FILES_TAB).click();
    }

    public void selectViewLogs() {
        getElementWhenVisible(Locator.SELECT_VIEW_LOGS).click();
    }

    public void selectFilesCoverageTab() {
        getElementWhenClickable(Locator.FILES_COVERAGE_TAB).click();
    }

    public void navigateToPullRequestPageQualityProduct(String provider, String repositoryPath) {
        webUtils.navigateTo(url + "/" + provider + "/" + repositoryPath + "/pull-requests");
    }

    public void navigateToPullRequestPageCoverageProduct(String provider, String repositoryPath) {
        webUtils.navigateTo(url + "/" + provider + "/" + repositoryPath + "/coverage/pullRequests");
    }

    public void selectDiffCoverageTab() {
        getElement(Locator.PR_DIFF_TAB).click();
    }



    private void assertElementContainsCoverageValue(By locator, String expectedCoverageValue) {
        assertThat(getElement(locator).getText(), containsString(expectedCoverageValue));
    }
}
