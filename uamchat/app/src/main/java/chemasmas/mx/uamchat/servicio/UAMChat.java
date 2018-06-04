package chemasmas.mx.uamchat.servicio;

import chemasmas.mx.uamchat.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Field;

public interface UAMChat {

    Call<String> register(@Field("nombre")String usr, @Field("token")String token);

    Call<Usuario> login(@Field("nombre")String usr, @Field("token")String token);


}
