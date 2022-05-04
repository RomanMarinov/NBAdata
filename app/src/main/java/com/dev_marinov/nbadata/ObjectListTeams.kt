package com.dev_marinov.nbadata

class ObjectListTeams {

    var fullName: String? = null
    var city: String? = null
    var conference: String? = null
    var division: String? = null

    constructor(fullName: String?, city: String?, conference: String?, division: String?) {
        this.fullName = fullName
        this.city = city
        this.conference = conference
        this.division = division
    }
}