package doctorpad

import javafx.geometry.Side

mainWindow = application(title: app.getMessage('application.title', ' DoctorPad'),
    sizeToScene: true, centerOnScreen: true) {
    scene(fill: WHITE, width: 800, height: 600) {
        vbox {
            menuBar {
                menu(text: app.getMessage('application.menu.File.name', ' File')) {
                    menuItem(newAction)
                    menuItem(openAction)
                    separatorMenuItem()
                    menuItem(saveAction)
                    menuItem(saveAsAction)
                    separatorMenuItem()
                    menuItem(convertAction)
                }
            }

            splitPane(orientation: HORIZONTAL, prefHeight: 300, vgrow: 'always') {
                dividerPosition(index: 0, position: 0.5)
                dividerPosition(index: 1, position: 1.0)
                textArea(prefRowCount: 20, prefColumnCount: 80, text: bind(model.inputProperty()))

                tabPane(side: Side.BOTTOM) {
                    tab(app.getMessage('application.tab.Preview.title', ' Preview')) {
                        webView(id: 'webview')
                    }
                    tab(app.getMessage('application.tab.Source.title', ' Source')) {
                        textArea(prefRowCount: 20, prefColumnCount: 80, editable: false,
                            text: bind(model.outputProperty()))
                    }
                }
            }
        }
    }
}