package hello.servlet.web.frontcontroller.v1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerAServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerAServletV1 extends HttpServlet{
    
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    FrontControllerAServletV1(){
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("FrontControllerServletV1.service");

       String requestURI =  request.getRequestURI();

       ControllerV1 controller = controllerMap.get(requestURI);
       if(controller == null){
            response.setStatus(404);
            return;
       }
       
       controller.process(request, response);
    }
}
