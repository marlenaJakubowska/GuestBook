package com.codecool.guestbook;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GuestBook implements HttpHandler {

    private static List<EntryNote> entryNotes;

    public GuestBook() {
        System.out.println("test3");

        entryNotes = new ArrayList<>();

    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            postNote(httpExchange);
        }
        
        if (method.equals("GET")) {
            getNotes(httpExchange);
            
        }
        getNotes(httpExchange);
    }

    private String getTemplate() {
        final String response;
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/template.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("entryNote", entryNotes);
        response = template.render(model);
        return response;
    }


    private void send200(HttpExchange httpExchange, String response) throws IOException{
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void getNotes(HttpExchange httpExchange) throws IOException {
        String response;
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/template.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("entryNotes", entryNotes);
        response = template.render(model);
        send200(httpExchange, response);
    }

    private void postNote(HttpExchange httpExchange) throws IOException {
        Map<String, String> inputs = getInputs(httpExchange);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = formatter.format(date);
        entryNotes.add(new EntryNote(inputs.get("name"), inputs.get("message"), strDate));
    }

    private Map<String, String> getInputs(HttpExchange httpExchange) throws IOException {
        String formData = getFormData(httpExchange);
        return parseFormData(formData);
    }

    private String getFormData(HttpExchange httpExchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            // We have to decode the value because it's urlencoded. see: https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
            String key = URLDecoder.decode(keyValue[0], "UTF-8");
            String value = URLDecoder.decode(keyValue[1], "UTF-8");
            map.put(key, value);
        }
        return map;
    }

}

