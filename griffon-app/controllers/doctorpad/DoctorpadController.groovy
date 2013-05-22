package doctorpad

import org.asciidoctor.Asciidoctor
import org.asciidoctor.Attributes
import org.asciidoctor.OptionsBuilder
import org.asciidoctor.SafeMode

class DoctorpadController {
    def model
    def view

    static {
        attr = new Attributes()
        attr.linkCss = false
    }

    private Asciidoctor instance = Asciidoctor.Factory.create()
    private static Attributes attr
    private Map options = OptionsBuilder.options()
            .compact(false)
            .headerFooter(true)
            .safe(SafeMode.UNSAFE)
            .backend("html5")
            .attributes(attr)
            .asMap()

    def convertAction = { evt = null ->
        def output = instance.render(model.input, options)
        println output
        execInsideUIAsync {
            view.webview.engine.loadContent(output)
        }
    }
}
