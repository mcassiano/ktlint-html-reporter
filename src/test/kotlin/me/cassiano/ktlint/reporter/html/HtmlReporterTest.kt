package me.cassiano.ktlint.reporter.html

import com.github.shyiko.ktlint.core.LintError
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class HtmlReporterTest {

    @Test
    fun shouldRenderEmptyReportWhen_NoIssuesFound() {
        val out = ByteArrayOutputStream()
        val reporter = HtmlReporter(PrintStream(out, true))
        reporter.afterAll()

        val actual = """
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Source+Code+Pro" rel="stylesheet" />
<style>
body {
    font-family: 'Source Code Pro', monospace;
}
h3 {
    font-size: 12pt;
}</style>
</head>
<body>
<p>Congratulations, no issues found!</p></body>
</html>
""".trimStart().replace("\n", System.lineSeparator())

        val expected = String(out.toByteArray())
        assertEquals(actual, expected)
    }

    @Test
    fun shouldRenderIssuesWhen_LintProblemsFound() {
        val out = ByteArrayOutputStream()
        val reporter = HtmlReporter(PrintStream(out, true))

        reporter.onLintError(
            "/file1.kt",
            LintError(1, 1, "rule-1", "rule-1 broken"),
            false
        )

        reporter.afterAll()

        val actual = """<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Source+Code+Pro" rel="stylesheet" />
<style>
body {
    font-family: 'Source Code Pro', monospace;
}
h3 {
    font-size: 12pt;
}</style>
</head>
<body>
<h3>/file1.kt</h3>
<ul>
<li>(1, 1): rule-1 broken  (rule-1)</li>
</ul>
</body>
</html>
""".trimStart().replace("\n", System.lineSeparator())

        val expected = String(out.toByteArray())
        assertEquals(actual, expected)
    }

    @Test
    fun shouldNotRenderCorrectedIssuesWhen_LintOneIsFound() {
        val out = ByteArrayOutputStream()
        val reporter = HtmlReporter(PrintStream(out, true))

        reporter.onLintError(
            "/file1.kt",
            LintError(1, 1, "rule-1", "rule-1 broken"),
            false
        )

        reporter.onLintError(
            "/file2.kt",
            LintError(1, 1, "rule-1", "rule-1 broken"),
            true
        )

        reporter.afterAll()

        val actual = """<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Source+Code+Pro" rel="stylesheet" />
<style>
body {
    font-family: 'Source Code Pro', monospace;
}
h3 {
    font-size: 12pt;
}</style>
</head>
<body>
<h3>/file1.kt</h3>
<ul>
<li>(1, 1): rule-1 broken  (rule-1)</li>
</ul>
</body>
</html>
""".trimStart().replace("\n", System.lineSeparator())

        val expected = String(out.toByteArray())
        assertEquals(actual, expected)
    }
}