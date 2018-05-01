# ktlint HTML Reporter

This is a simple HTML reporter for [ktlint](https://github.com/shyiko/ktlint). I wrote it while setting up quality tools for [Yosef](https://github.com/concretesolutions/yosef-android).

### Usage:

Since ktlint is able to load reporters from external sources, you can simply configure it like:

```groovy
task ktlint(type: JavaExec, group: "verification") {
    // ...
    args "--reporter=html,artifact=me.cassiano:ktlint-html-reporter:<latest-version>,output=${buildDir}/ktlint.html"
}

```

The reporter is also available on jCenter, so you don't need to include JitPack if you need to use it directly as a dependency:

```compile "me.cassiano:ktlint-html-reporter:$latestVersion"```

**Note: you need to upgrade to ktlint 0.20.0 before using this.**
