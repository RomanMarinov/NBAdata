package com.dev_marinov.nbadata

class ObjectListGames {

    var date: String? = null
    var teamHome: String? = null
    var cityHome: String? = null
    var conferenceHome: String? = null
    var divisionHome: String? = null
    var scoreHome: String? = null
    var periodHome: String? = null
    var seasonHome: String? = null
    var statusHome: String? = null

    var teamVisitor: String? = null
    var conferenceVisitor: String? = null
    var cityVisitor: String? = null
    var divisionVisitor: String? = null
    var scoreVisitor: String? = null

    constructor(
        date: String?,
        teamHome: String?,
        cityHome: String?,
        conferenceHome: String?,
        divisionHome: String?,
        scoreHome: String?,
        periodHome: String?,
        seasonHome: String?,
        statusHome: String?,
        teamVisitor: String?,
        conferenceVisitor: String?,
        cityVisitor: String?,
        divisionVisitor: String?,
        scoreVisitor: String?
    ) {
        this.date = date
        this.teamHome = teamHome
        this.cityHome = cityHome
        this.conferenceHome = conferenceHome
        this.divisionHome = divisionHome
        this.scoreHome = scoreHome
        this.periodHome = periodHome
        this.seasonHome = seasonHome
        this.statusHome = statusHome

        this.teamVisitor = teamVisitor
        this.conferenceVisitor = conferenceVisitor
        this.cityVisitor = cityVisitor
        this.divisionVisitor = divisionVisitor
        this.scoreVisitor = scoreVisitor
    }

}