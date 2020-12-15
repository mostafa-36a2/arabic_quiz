package com.alnamaa.arabic_quiz;

import android.os.AsyncTask;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Connectivity {
    public static String lastReporetedError;


    //-----------------
    public interface ResponseHandler{
        void handleResponse(String response);
    }
    public static void CommunicationWithAPI(String mURL,
                                            final String jsonInputString,
                                            final ResponseHandler responseHandler
    ){

        MyLogger.printAndStore("CommunicationWithAPI called, "+mURL+" input "+jsonInputString);

        AsyncTask<String, String, String> myTask ;

        myTask = new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String data) {
                super.onPostExecute(data);
                if(responseHandler!=null)
                {
                    responseHandler.handleResponse(data);
                }
            }

            @Override
            protected void onProgressUpdate(String... values) {
            }

            @Override
            protected String doInBackground(String... strings) {
                lastReporetedError = "";
                if (Looper.myLooper() == null)
                    Looper.prepare();

                String mURL=strings[0];
                HttpURLConnection httpURLConnection;

                URL url ;
                try {
                    url = new URL(mURL);
                } catch (MalformedURLException e) {
                    reportError(mURL,"MalformedURLException in new URL(mURL);",e);
                    return null;
                }

                try {
                    httpURLConnection = (HttpURLConnection)url.openConnection();
                } catch (IOException e) {
                    reportError(mURL,"IOException in openConnection", e);
                    return null;
                }

                if(httpURLConnection==null)
                {
                    ToastMaker.showError(Errors.HTTPURLCONNECTION_IS_NULL);
                    return "error httpURLConnection is null";
                }

                try {
                    httpURLConnection.setRequestMethod("GET");
                } catch (ProtocolException e) {
                    reportError(mURL,"ProtocolException  in httpURLConnection.setRequestMethod(\"POST\");", e);
                    return null;
                }
                httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setConnectTimeout(1000 * 2);
                httpURLConnection.setReadTimeout(1000 * 5);
                httpURLConnection.setDoOutput(true);
                if(jsonInputString!=null)
                {

                    OutputStream os = null;
                    try {
                        os = httpURLConnection.getOutputStream();
                    } catch (IOException e) {
                        if(httpURLConnection.getErrorStream()!=null)
                        {
                            lastReporetedError = "Fail in httpURLConnection.getOutputStream, but error stream exists";
                            reportError(mURL,httpURLConnection.getErrorStream());
                        }else {
                            lastReporetedError = "يبدو أنك غير متصل بالإنترنت";
                            reportError(mURL, "IOException os = httpURLConnection.getOutputStream();(even error stream is null)", e);
                            Settings.toggleHTTPStatus();
                        }
                        return null;
                    }

                    byte[] input = new byte[0];
                    try {
                        input = jsonInputString.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        reportError(mURL,"UnsupportedEncodingException  input = jsonInputString.getBytes(\"UTF-8\");", e);
                        return null;
                    }
                    try {
                        os.write(input, 0, input.length);
                    } catch (IOException e) {
                        reportError(mURL,"IOException  os.write(input, 0, input.length);", e);
                        return null;
                    }
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        reportError(mURL,"IOException  os.close();", e);
                        return null;
                    }
                }

                try {
                    httpURLConnection.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                    reportError(mURL,"IOException httpURLConnection.connect();", e);
                    return null;
                }

                int responseCode ;
                try {
                    responseCode = httpURLConnection.getResponseCode();
                } catch (IOException e) {
                    reportError(mURL,"IOException responseCode = httpURLConnection.getResponseCode();", e);
                    return null;
                }

                InputStreamReader inputStreamReader = null;
                try {
                    inputStreamReader = new InputStreamReader(
                            httpURLConnection.getInputStream(), "UTF-8");
                } catch (IOException e) {
                    reportError(mURL,"IOException responseCode =new InputStreamReader(\n" +
                            " httpURLConnection.getInputStream();", e);
                    return null;
                }

                StringBuilder response = new StringBuilder();

                BufferedReader br = new BufferedReader(inputStreamReader);
                String responseLine ;
                while (true) {
                    try {
                        if ((responseLine = br.readLine()) == null) break;
                    } catch (IOException e) {
                        reportError(mURL,"IOException br.readLine();", e);
                        return null;
                    }
                    response.append(responseLine.trim());
                }
                try {
                    br.close();
                } catch (IOException e) {
                    reportError(mURL,"IOException br.close();;", e);
                    return null;
                }

                String responseMessage = null;
                try {
                    responseMessage = httpURLConnection.getResponseMessage();
                } catch (IOException e) {
                    reportError(mURL,"IOException httpURLConnection.getResponseMessage();", e);
                    return null;
                }

                httpURLConnection.disconnect();

                MyLogger.printAndStore("responseCode:"+responseCode+"\n" +
                        "responseMessage : " + responseMessage);

                return response.toString();
            }
        };
        myTask.execute(mURL);
    }

    private static void reportError(String mURL, InputStream errorStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader( errorStream, "UTF-8");
        } catch (IOException e) {
            reportError(mURL,"reportErrorStream:IOException responseCode =new InputStreamReader(\n" +
                    " httpURLConnection.getInputStream();", e);
            return;
        }

        StringBuilder response = new StringBuilder();

        BufferedReader br = new BufferedReader(inputStreamReader);
        String responseLine ;
        while (true) {
            try {
                if ((responseLine = br.readLine()) == null) break;
            } catch (IOException e) {
                reportError(mURL,"reportErrorStream:IOException br.readLine();", e);
                return;
            }
            response.append(responseLine.trim());
        }
        try {
            br.close();
        } catch (IOException e) {
            reportError(mURL,"reportErrorStream:IOException br.close();;", e);
        }
    }

    private static void reportError(String mURL, String error_name, Exception e) {
        MyLogger.printAndStore("Error in "+mURL+" "+error_name);
        lastReporetedError = error_name;
    }

    //-----------------
}
