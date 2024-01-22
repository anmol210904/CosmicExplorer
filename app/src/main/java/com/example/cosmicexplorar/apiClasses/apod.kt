package com.example.cosmicexplorar.apiClasses

class apod() {

    var date: String = ""
    var explanation: String= ""
    var hdurl: String= ""
    var media_type: String= ""
    var service_version: String= ""
    var title: String= ""
    var url: String= ""
    constructor(
        date: String,
        explanation: String,
        hdurl: String,
        media_type: String,
        service_version: String,
        title: String,
        url: String
    ) : this() {
        this.date = date
        this.explanation = explanation
        this. hdurl = hdurl
        this.media_type = media_type
        this.service_version = service_version
        this.title = title
        this. url = url
    }
}
