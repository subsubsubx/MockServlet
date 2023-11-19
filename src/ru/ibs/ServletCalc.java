package ru.ibs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.ibs.logic.Calc;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/calc")
public class ServletCalc extends HttpServlet {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
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
        int a = jsonObject.get("a").getAsInt();
        int b = jsonObject.get("b").getAsInt();
        String op = jsonObject.get("math").getAsString();
        PrintWriter printWriter = response.getWriter();

        try {
            Calc calc = new Calc(a, b, op);
            String result = "{ \"result\": " + calc.getRes() + "}";
            printWriter.print(JsonParser.parseString(result).getAsJsonObject());
        } catch (ArithmeticException e) {
            String errorResult = "{ \"error\": \"Деление на ноль!\"}";
            printWriter.print(JsonParser.parseString(errorResult).getAsJsonObject());
        }
    }
}
