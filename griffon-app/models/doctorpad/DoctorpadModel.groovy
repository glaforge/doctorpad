package doctorpad

import groovyx.javafx.beans.FXBindable
import griffon.util.GriffonNameUtils

class DoctorpadModel {
    @FXBindable String input = """\
= Document Title
:data-uri:
:toc:

== Some title

Content...
"""
}
