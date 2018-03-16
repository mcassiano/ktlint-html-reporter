package me.cassiano.ktlint.reporter.html

import com.github.shyiko.ktlint.core.Reporter
import com.github.shyiko.ktlint.core.ReporterProvider
import java.io.PrintStream

class HtmlReporterProvider : ReporterProvider {
    override val id: String = "html"
    override fun get(out: PrintStream, opt: Map<String, String>): Reporter = HtmlReporter(out)
}