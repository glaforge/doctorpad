package doctorpad

import griffon.transform.Threading
import javafx.stage.FileChooser
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

    def convertAction = {
        updatePreview()
    }

    def newAction = {
        model.documentFileName = null
        model.input = ''
    }

    @Threading(Threading.Policy.SKIP)
    def openAction = {
        FileChooser fileChooser = new FileChooser()
        fileChooser.title = app.getMessage('application.dialog.Open.title', 'Open document')
        String documentFileName = fileChooser.showOpenDialog(view.mainWindow)?.absolutePath
        if (documentFileName) {
            execOutsideUI {
                model.documentFileName = documentFileName
                String documentText = new File(documentFileName).text
                model.input = documentText
                updatePreview()
            }
        }
    }

    def saveAction = {
        if (model.documentFileName) {
            new File(model.documentFileName).write(model.input)
        } else {
            saveAsAction()
        }
    }

    @Threading(Threading.Policy.SKIP)
    def saveAsAction = {
        FileChooser fileChooser = new FileChooser()
        fileChooser.title = app.getMessage('application.dialog.Save.title', 'Save document')
        String documentFileName = fileChooser.showSaveDialog(view.mainWindow)?.absolutePath
        if (documentFileName) {
            model.documentFileName = documentFileName
            new File(model.documentFileName).write(model.input)
        }
    }

    private void updatePreview() {
        String output = instance.render(model.input, options)
        execInsideUIAsync {
            model.output = output
            view.webview.engine.loadContent(output)
        }
    }
}
