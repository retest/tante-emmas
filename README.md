# Tante Emmas Online Shop

This is an example legacy code project, borrowed from [testrecorder](http://testrecorder.amygdalum.net/), to demonstrate various Golden Master Testing tools.

## Setup

Make sure ChromeDriver is [installed](http://chromedriver.chromium.org/downloads/) and in your `PATH`. If not, go to `ChromeDriverFactory` and uncomment the following line:

```java
System.setProperty( "webdriver.chrome.driver", "path/to/chromedriver" );
```

Afterwards, run `mvn test` to execute all tests.

## Travis Setup for Your Forked Repository

1. Register at [Travis](https://travis-ci.com/).
2. Click on your name to go to `Profile`.
3. Enable Github Apps integration so Travis can access your repositories (you can do this for `tante-emmas` only).
4. Navigate to `tante-emmas` in Travis.
5. Under `More options` select `Trigger Build` for the branch master.

## Apply Changes with retest

To accept recheck differences [retest](https://update.retest.de/) must be downloaded. Set the retest workspace system property as java arguments inside the `.bat` file:

```java
-Dde.retest.workDirectory=${path-to-tante-emmas}/src/test/resources/retest
```
