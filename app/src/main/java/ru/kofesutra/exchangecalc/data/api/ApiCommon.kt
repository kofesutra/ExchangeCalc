package ru.kofesutra.exchangecalc.data.api

// Создаём переменную, чтобы связывать инстанс билдера с АПИ сервисом
// билдер создастся и будет понимать откуда брать энд пойнты

object ApiCommon {
    val retrofitService: ApiService
        get() = RetrofitInstance.getClient().create(ApiService::class.java)
}