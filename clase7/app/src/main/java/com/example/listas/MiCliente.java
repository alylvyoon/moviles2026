import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;

public class MiCliente {

    private String url = "https://function-bun-production-640d.up.railway.app/api/characters";
    private OkHttpClient client = new OkHttpClient();

    private ArrayList<String> getElements() {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()){
                Log.e("MiCliente", "Error: " + response.code());
                return new ArrayList<>();
            }
            String respuesta = response.body().string();
            Log.i("MiCliente", "Respuesta: " + respuesta);

            ArrayList<String> elementos = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(respuesta);
            JSONArray array = jsonObject.getJSONArray("characters");

            for (int i = 0; i < array.length(); i++){
                String elemento = array.getString(i);
            elementos.add(elemento);
        }

        return elementos;

        } catch (IOException e) {
            Log.e("MiCliente", "Error IO: " + e.getMessage());
            return new ArrayList<>();
        } catch (JSONException e) {
            Log.e("MiCliente", "Error JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
