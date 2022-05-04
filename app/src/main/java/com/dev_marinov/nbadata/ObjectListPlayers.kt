package com.dev_marinov.nbadata

class ObjectListPlayers {

    var firstName: String? = null
    var lastName: String? = null
    var position: String? = null
    var team: String? = null
    var city: String? = null
    var conference: String? = null
    var division: String? = null

    constructor(
        firstName: String?,
        lastName: String?,
        position: String?,
        team: String?,
        city: String?,
        conference: String?,
        division: String?
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.position = position
        this.team = team
        this.city = city
        this.conference = conference
        this.division = division
    }
}