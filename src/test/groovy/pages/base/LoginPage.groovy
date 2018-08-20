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

package pages.base

import pages.base.AmazonPage

abstract class LoginPage extends AmazonPage {

    static url = "ap/signin"

    private static final FORM_TITLE = "Sign in".toUpperCase()

    static content = {
        emailField(required: false)  { $('#ap_email') }  //Find the email Field by Id
        loginFormContainer() {emailField.closest('.a-box')} //Then find the closest (login form) container
        loginFormTitle() {loginFormContainer.find('h1').first()}
        continueFieldButton(required: false) { loginFormContainer.find('#continue.a-button-input')}
        passwordField(required: false)  { loginFormContainer.find('#ap_password') }
        signInButton(required: false)  {loginFormContainer.find('#signInSubmit')}
    }

    static at = {
        loginFormTitle.text().toUpperCase().contains(FORM_TITLE)
        browser.getCurrentUrl().toLowerCase().startsWith(AmazonPage.config.urlWithForwardSlash + "ap/signin")
    }

    def enterEmail(email){
        emailField.value(email)
    }

    def enterPassword(password){
        passwordField.value(password)
    }

    def clickOnContinueButton(){
        continueFieldButton.click()
    }

    def clickOnSignInButton(){
        signInButton.click()
    }
}