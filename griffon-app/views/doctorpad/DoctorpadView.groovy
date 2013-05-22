package doctorpad

application(title: 'Doctorpad', sizeToScene: true, centerOnScreen: true) {
    scene(fill: black, width: 400, height: 300) {
        vbox() {
            splitPane(orientation: HORIZONTAL, prefHeight: 300, vgrow: "always") {
                dividerPosition(index: 0, position: 0.5)
                dividerPosition(index: 1, position: 1.0)
                textArea(prefRowCount: 20, prefColumnCount: 80, text: bind(model.inputProperty()))
                webView(id: "webview")
            }
            button(convertAction, maxWidth: Double.MAX_VALUE)
        }
    }
}
