package com.amazon.spapi;


import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class TestProxy {

    public static void main(String[] args) throws IOException {

        final int proxyPort = 22828; //your proxy port
        final String proxyHost = "120.77.176.248";
        final String username = "edazone";
        final String password = "Edazone#kN2020";


        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));


        OkHttpClient client = new OkHttpClient().newBuilder().proxy(proxy).proxyAuthenticator(new Authenticator() {
            @NotNull
            @Override
            public Request authenticate(@Nullable Route route, Response response) throws IOException {
                String credential = Credentials.basic(username, password);
                return response.request().newBuilder()
                        .header("Proxy-Authorization", credential)
                        .build();
            }

        }).build();
        Response response = client.newCall((new Request.Builder()).url("https://api.myip.com").build()).execute();

        System.out.println(response.body().string());


    }


}
