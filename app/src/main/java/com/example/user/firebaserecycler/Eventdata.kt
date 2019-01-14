package com.example.user.firebaserecycler

class Eventdata {


    lateinit var name: String
    lateinit var venue:String
    lateinit var time:String
    constructor()
    constructor(name: String, venue: String, time: String) {
        this.name = name
        this.venue = venue
        this.time = time
    }
}