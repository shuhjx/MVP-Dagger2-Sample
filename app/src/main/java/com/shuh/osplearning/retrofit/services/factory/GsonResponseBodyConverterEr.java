package com.shuh.osplearning.retrofit.services.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * https://github.com/MyAndroidP/Gson-adapter
 * Created by pc-135 on 2017/6/7.
 */
public class GsonResponseBodyConverterEr<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    GsonResponseBodyConverterEr(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        try {
            String response = value.string();
            JSONObject jsonObject= null;
            try {
                jsonObject = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String message = jsonObject.optString("message", "老接口");
            JsonReader jsonReader =null;
            MediaType mediaType = value.contentType();
            Charset charset=mediaType!=null?mediaType.charset(UTF_8):UTF_8;
            InputStream inputStream=new ByteArrayInputStream(response.getBytes());
            jsonReader=gson.newJsonReader(new InputStreamReader(inputStream,charset));
            if (message.equals("老接口")) {
                int code=jsonObject.optInt("result");
                if(code!=1){
                    value.close();
                    throw new RuntimeException("errorCode");
//                    throw new CustomException(code,jsonObject.optJSONObject("data").optInt(
//                            "errorCode"),jsonObject.optJSONObject("data").optString(
//                            "errorMessage"));
                }else{
                    return adapter.read(jsonReader);
                }
            }else{
                int code=jsonObject.optInt("code");
                if (code!=0){
                    value.close();
                    throw new RuntimeException(message);
//                    throw new CustomException(code,message);
                }else{
                    System.out.println(response);
                    return adapter.read(jsonReader);
                }
            }
        } finally {
            value.close();
        }

    }
}
