package ru.ibs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.ibs.logic.Model;
import ru.ibs.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final AtomicInteger counter = new AtomicInteger(4);
    private final Model model = Model.getInstance();

/*    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();


        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        double salary = Double.parseDouble(request.getParameter("salary"));
        User newUser = new User(name, surname, salary);
        model.addUser(counter.getAndIncrement(), newUser);
        printWriter.print("<html>" +
                "<h3>Пользователь " + name + " " + surname + ", ЗП: " + salary + " успешно создан</h3>"
                + "<a href=\"addUser.html\">Создать еще одного пользователя</a>" + "<br/>"
                + "<a href=\"index.jsp\">Домой</a>"
                + "</html>");
    }*/

/*    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        String name = jsonObject.get("name").getAsString();
        String surname = jsonObject.get("surname").getAsString();
        double salary = jsonObject.get("salary").getAsDouble();

        User user = new User(name, surname, salary);
        model.addUser(counter.getAndIncrement(), user);

        PrintWriter printWriter = response.getWriter();
        printWriter.print(gson.toJson(model.getUserList().get(model.getUserList().size())));*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();

        if (request.getContentType().equals("application/json")) {
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
            String name = jsonObject.get("name").getAsString();
            String surname = jsonObject.get("surname").getAsString();
            double salary = jsonObject.get("salary").getAsDouble();

            User user = new User(name, surname, salary);
            model.addUser(counter.getAndIncrement(), user);

            printWriter.print(gson.toJson(model.getUserList().get(model.getUserList().size())));
        } else {
            response.setContentType("text/html");

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            double salary = Double.parseDouble(request.getParameter("salary"));

            User newUser = new User(name, surname, salary);
            model.addUser(counter.getAndIncrement(), newUser);
            printWriter.print("<html>" +
                    "<h3>Пользователь " + name + " " + surname + ", ЗП: " + salary + " успешно создан</h3>"
                    + "<a href=\"addUser.html\">Создать еще одного пользователя</a>" + "<br/>"
                    + "<a href=\"index.jsp\">Домой</a>"
                    + "</html>");

        }
    }
}
