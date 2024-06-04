package ar.com.damianfrutos.conversordedivisas

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {
    @GET("pair/{from}/{to}/{amount}")
    suspend fun getConversion(
        @Path("from") from: String,
        @Path("to") to: String,
        @Path("amount") amount: Double
    ): Response<ConversionResponse>
}
