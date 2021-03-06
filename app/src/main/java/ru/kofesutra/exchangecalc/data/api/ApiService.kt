package ru.kofesutra.exchangecalc.data.api

import retrofit2.Call
import retrofit2.http.GET
import ru.kofesutra.exchangecalc.model.Data

const val BASE_URL = "https://api.nomics.com/v1/currencies/"
const val myApiKey = "c5f770409abd04d9c19f3f36f01b0ba1accf9702"

interface ApiService {

    // 2) Пишем аннотацию @GET и передаём end point (как правило то, что идёт после доменного имени)
    @GET("ticker?key=$myApiKey&ids=BTC,ETH,LTC,XRP,SOL,DOT,DASH,XMR,ZEC,BCH,LUNA,USDT") // nomics.com

// 1) Пишем функцию, название придумываем, она возвращает ответ с типом нашей модели (у нас data)
   // suspend fun getData(): Response<data>
    fun getData(): Call<List<Data>>
}