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
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Model model = Model.getInstance();

    /*  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
          request.setCharacterEncoding("UTF-8");
          response.setContentType("text/html;charset=utf-8");
          PrintWriter printWriter = response.getWriter();

          int id = Integer.parseInt(request.getParameter("id"));
          if (id == 0) {
              printWriter.print("<html>" +
                      "<h3>Пользователи:</h3><br/>" +
                      "ID: " +
                      "<ul>");
              for (Map.Entry<Integer, User> entry : model.getUserList().entrySet()) {
                  printWriter.print("<li>" + entry.getKey() + "</li>" +
                          "<ul>" +
                          "<li>Имя: " + entry.getValue().getName() + "</li>" +
                          "<li>Фамилия: " + entry.getValue().getSurname() + "</li>" +
                          "<li>ЗП: " + entry.getValue().getSalary() + "</li>" +
                          "</ul>");
              }
              printWriter.print("</ul>" +
                      "<a href=\"index.jsp\">Домой</a>" +
                      "</html>");
          } else if (id > 0) {
              if (id > model.getUserList().size()) {
                  printWriter.print("<html>" +
                          "Пользователя с ID: " + id + " не существует<br/>" +
                          "<a href=\"index.jsp\">Домой</a>" +
                          "</html>");
              } else {
                  printWriter.print("<html>" +
                          "<h3>Пользователь:</h3><br/>" +
                          "ID: " + id);
                  printWriter.print("<ul>" +
                          "<li>Имя: " + model.getUserList().get(id).getName() + "</li>" +
                          "<li>Фамилия: " + model.getUserList().get(id).getSurname() + "</li>" +
                          "<li>ЗП: " + model.getUserList().get(id).getSalary() + "</li>" +
                          "</ul>" + "<a href=\"index.jsp\">Домой</a>" +
                          "</html>");

              }
          } else {
              printWriter.print("<html>" +
                      "ID должен быть больше нуля<br/>" +
                      "<a href=\"index.jsp\">Домой</a>" +
                      "</html>");
          }
      }
  */


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        //     System.out.println(request.getContentType());
        String contentType = request.getContentType();
          if (contentType != null) {
            response.setContentType("application/json");
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

            if (id == 0) {
                printWriter.print(gson.toJson(model.getUserList()));
            } else if (id > 0) {
                if (id > model.getUserList().size()) {
                    String res = "{\"error\" : \"Пользователь с id " + id + " не существует\"}";
                    printWriter.print(JsonParser.parseString(res).getAsJsonObject());
                } else {
                    printWriter.print(gson.toJson(model.getUserList().get(id)));
                }
            } else {
                String res = "{\"error\" : \"id пользователя должен быть больше нуля\"}";
                printWriter.print(JsonParser.parseString(res).getAsJsonObject());
            }
        } else {
            response.setContentType("text/html");
            int id = Integer.parseInt(request.getParameter("id"));
            if (id == 0) {
                printWriter.print("<html>" +
                        "<h3>Пользователи:</h3><br/>" +
                        "ID: " +
                        "<ul>");
                for (Map.Entry<Integer, User> entry : model.getUserList().entrySet()) {
                    printWriter.print("<li>" + entry.getKey() + "</li>" +
                            "<ul>" +
                            "<li>Имя: " + entry.getValue().getName() + "</li>" +
                            "<li>Фамилия: " + entry.getValue().getSurname() + "</li>" +
                            "<li>ЗП: " + entry.getValue().getSalary() + "</li>" +
                            "</ul>");
                }
                printWriter.print("</ul>" +
                        "<a href=\"index.jsp\">Домой</a>" +
                        "</html>");
            } else if (id > 0) {
                if (id > model.getUserList().size()) {
                    printWriter.print("<html>" +
                            "Пользователя с ID: " + id + " не существует<br/>" +
                            "<a href=\"index.jsp\">Домой</a>" +
                            "</html>");
                } else {
                    printWriter.print("<html>" +
                            "<h3>Пользователь:</h3><br/>" +
                            "ID: " + id);
                    printWriter.print("<ul>" +
                            "<li>Имя: " + model.getUserList().get(id).getName() + "</li>" +
                            "<li>Фамилия: " + model.getUserList().get(id).getSurname() + "</li>" +
                            "<li>ЗП: " + model.getUserList().get(id).getSalary() + "</li>" +
                            "</ul>" + "<a href=\"index.jsp\">Домой</a>" +
                            "</html>");

                }
            } else {
                printWriter.print("<html>" +
                        "ID должен быть больше нуля<br/>" +
                        "<a href=\"index.jsp\">Домой</a>" +
                        "</html>");
            }
        }
    }
}
