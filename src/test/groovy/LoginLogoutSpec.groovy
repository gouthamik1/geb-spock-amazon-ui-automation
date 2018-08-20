/*
 * Copyright (c) 2018. https://automationschool.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */


import geb.spock.GebReportingSpec
import pages.base.AmazonPage
import pages.common.HomePage
import pages.common.LandingPage
import pages.common.LoginEnterEmailPage
import pages.common.LoginEnterPasswordPage
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class LoginLogoutSpec extends GebReportingSpec {
    @Shared cfg
    @Shared email
    @Shared password

    def setupSpec() {
        cfg = ConfigReader.getConfiguration()
        AmazonPage.config = cfg;
        email = cfg.loginFlowConfig.userEmail
        password = cfg.loginFlowConfig.password
    }

    def cleanupSpec() {
    }

    def setup() {
        baseUrl = cfg.urlWithForwardSlash
    }

    def "Sign in with valid email and password"() {
        given: "You are on amazon homepage"
        HomePage homePage = to HomePage
        report("Amazon Home Page")

        when: "You click on Account-Lists-SignIn area"
        homePage.clickOnSignin()
        report("Amazon Login Page - Enter Email")

        then: "Check you are on Login Enter Email page"
        LoginEnterEmailPage loginEnterEmailPage = at LoginEnterEmailPage
        report("Amazon Login Enter Email page")

        when: "Enter valid email"
        loginEnterEmailPage.enterEmail(email)
        report("Amazon Login Enter Email page with email")
        loginEnterEmailPage.clickOnContinueButton()

        then: "Check you are on Login Enter Password page"
        LoginEnterPasswordPage loginEnterPasswordPage = at LoginEnterPasswordPage
        report("Amazon Login Enter Password Page")

        when: "Enter valid password"
        loginEnterPasswordPage.enterPassword(password)
        report("Amazon Login Enter Password page with password")
        loginEnterPasswordPage.clickOnSignInButton()

        then: "Check you are on landing page"
        at LandingPage
        report("Amazon Landing Page for user with email - " + email)

    }

    def "SignOut"() {

        given: "You are on landing page"
        LandingPage landingPage = at LandingPage

        when: "You click on SignOut"
        landingPage.clickOnSignOut()

        then: "Check you are back on the Login Enter Email Page"
        at LoginEnterEmailPage
        report("Back at Login Enter Email Page after SignOut")

    }
}


