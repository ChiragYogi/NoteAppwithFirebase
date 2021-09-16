package com.example.noteappwithfirebase.utilles

object validate {

    fun validatingInput(title: String,info: String): Boolean{
        return !(title.isEmpty() || info.isEmpty())
    }
}