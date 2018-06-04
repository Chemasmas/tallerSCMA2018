package chemasmas.mx.uamchat.servicio;

import chemasmas.mx.uamchat.model.Usuario;
import retrofit2.Call;

public class UAMChatImp implements UAMChat {
    @Override
    public Call<String> register(String usr, String token) {
        return null;
    }

    @Override
    public Call<Usuario> login(String usr, String token) {
        return null;
    }
}
