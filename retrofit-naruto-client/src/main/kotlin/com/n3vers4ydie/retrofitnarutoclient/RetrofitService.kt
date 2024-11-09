import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface DattebayoService {

    @GET("characters")
    fun getAllCharacters(@Query("limit") limit: Int): Call<CharactersResponse>

    @GET("characters/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<Character>
}