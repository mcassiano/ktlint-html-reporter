package me.cassiano.ktlint.reporter.html

import com.pinterest.ktlint.core.Reporter
import com.pinterest.ktlint.core.ReporterProvider
import java.io.PrintStream

class HtmlReporterProvider : ReporterProvider {
    override val id: String = "html"
    override fun get(out: PrintStream, opt: Map<String, String>): Reporter = HtmlReporter(out)
}
