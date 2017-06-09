package com.shuh.osplearning.retrofit.services.factory;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by pc-135 on 2017/6/9.
 */
public class HomeGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Gson gson;
    private TypeAdapter<T> adapter;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public HomeGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType mediaType = value.contentType();
        Charset charset= mediaType == null ? UTF_8 : mediaType.charset(UTF_8);
        JSONArray jsonArray = jsonObject.optJSONArray("list");
        InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(jsonArray.toString().getBytes()), charset);
        JsonReader jsonReader = gson.newJsonReader(reader);
//        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
