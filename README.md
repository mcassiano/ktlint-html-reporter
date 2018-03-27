# ktlint HTML Reporter

This is a simple HTML reporter for [ktlint](https://github.com/shyiko/ktlint). I wrote it while setting up quality tools for [Yosef](https://github.com/concretesolutions/yosef-android).

### Usage:

Since ktlint is able to load reporters from external sources, you only to configure it like this:

```groovy
task ktlint(type: JavaExec, group: "verification") {
    // ...
    args "--reporter=html,artifact=com.github.mcassiano:ktlint-html-reporter:<latest-version>,output=${buildDir}/ktlint.html"
}

```

Note that you need to upgrade to ktlint 0.20.0 before using this.