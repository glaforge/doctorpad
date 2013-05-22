application {
    title = 'Doctorpad'
    startupGroups = ['doctorpad']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "doctorpad"
    'doctorpad' {
        model      = 'doctorpad.DoctorpadModel'
        view       = 'doctorpad.DoctorpadView'
        controller = 'doctorpad.DoctorpadController'
    }

}
