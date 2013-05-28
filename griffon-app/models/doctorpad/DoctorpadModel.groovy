package doctorpad

import groovyx.javafx.beans.FXBindable

class DoctorpadModel {
    @FXBindable String input = '''
        = Document Title
        :data-uri:
        :toc:

        == Some title

        Content...
    '''.stripIndent(8).trim()

    @FXBindable String output = ''

    String documentFileName
}
