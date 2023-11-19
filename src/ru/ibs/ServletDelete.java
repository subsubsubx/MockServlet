package ru.ibs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.ibs.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Model model = Model.getInstance();


    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        int id = jsonObject.get("id").getAsInt();

        if (model.getUserList().containsKey(id)) {
            model.getUserList().remove(id);
            String result = "{ \"result\": \"id " + id + " успешно удален\"}";
            printWriter.print(JsonParser.parseString(result).getAsJsonObject());
        } else {
            String result = "{ \"error\": \"id " + id + " не существует\"}";
            printWriter.print(JsonParser.parseString(result).getAsJsonObject());
        }
    }
}
