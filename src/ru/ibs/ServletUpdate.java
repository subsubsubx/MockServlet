package ru.ibs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.ibs.logic.Model;
import ru.ibs.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/update")
public class ServletUpdate extends HttpServlet {

    private Model model = Model.getInstance();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader br = request.getReader();
            while ((line = br.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
        JsonObject jsonObject = gson.fromJson(String.valueOf(jb), JsonObject.class);
        Integer id = jsonObject.get("id").getAsInt();
        String name = jsonObject.get("name").getAsString();
        String surname = jsonObject.get("surname").getAsString();
        double salary = jsonObject.get("salary").getAsDouble();
        if (model.getUserList().containsKey(id)) {
            model.getUserList().put(id, new User(name, surname, salary));
            printWriter.print(gson.toJson(model.getUserList().get(id)));
        } else {
            String error = "{\"error\": \"запись с id " + id + " отсутствует\"}";
            printWriter.print(JsonParser.parseString(error).getAsJsonObject());
        }
    }
}

