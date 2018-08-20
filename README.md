# geb-spock-amazon-ui-automation
Using Geb / Spock, automating UI flows of Amazon. __Please run automation tests responsibly!__

## Amazon login and logout example:
- Note: All paths relative to directory: amazon-ui-automation
- Update the `src\test\resources\LoginFlowConfig.groovy` file with the amazon account email / password / your account holder's first name.
- Go to the amazon-ui-automation directory and run the automated spec from the command line:

    `cd amazon-ui-automation`

    `gradlew chromeTest -Denv=test`

The above runs the amazon ui automated tests in Chrome. Using the configuration i.e., url, email / password / first name etc supplied under the "test" environment.
