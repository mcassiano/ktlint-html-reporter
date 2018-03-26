package me.cassiano.ktlint.reporter.html

import com.github.shyiko.ktlint.core.LintError
import com.github.shyiko.ktlint.core.Reporter
import java.io.PrintStream
import java.util.concurrent.ConcurrentHashMap

class HtmlReporter(private val out: PrintStream) : Reporter {

    private val acc = ConcurrentHashMap<String, MutableList<LintError>>()

    override fun onLintError(file: String, err: LintError, corrected: Boolean) {
        if (!corrected) {
            acc.getOrPut(file) { mutableListOf() }.add(err)
        }
    }

    override fun afterAll() {
        html {
            head {
                cssLink("https://fonts.googleapis.com/css?family=Source+Code+Pro")
                text("<style>\n")
                text("body {\n")
                text("    font-family: 'Source Code Pro', monospace;\n")
                text("}\n")
                text("h3 {\n")
                text("    font-size: 12pt;\n")
                text("}")
                text("</style>\n")
            }
            body {
                acc.forEach { file: String, errors: MutableList<LintError> ->
                    h3 { text(file) }
                    ul {
                        errors.forEach { (line, col, ruleId, detail) ->
                            item("($line, $col): $detail  ($ruleId)")
                        }
                    }
                }
            }
        }
    }

    private fun html(body: () -> Unit) {
        out.println("<html>")
        body()
        out.println("</html>")
    }

    private fun head(body: () -> Unit) {
        out.println("<head>")
        body()
        out.println("</head>")
    }

    private fun body(body: () -> Unit) {
        out.println("<body>")
        body()
        out.println("</body>")
    }

    private fun h3(body: () -> Unit) {
        out.print("<h3>")
        body()
        out.println("</h3>")
    }

    private fun text(value: String) {
        out.print(value)
    }

    private fun ul(body: () -> Unit) {
        out.println("<ul>")
        body()
        out.println("</ul>")
    }

    private fun item(value: String) {
        out.print("<li>")
        text(value)
        out.println("</li>")
    }

    private fun cssLink(link: String) {
        out.print("<link href=\"")
        out.print(link)
        out.println("\" rel=\"stylesheet\" />")
    }
}