package com.java.controller;

import com.google.gson.JsonObject;
import com.java.pojo.Address;
import com.java.pojo.person;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@ControllerAdvice//控制器处理异常的类
//@SessionAttributes(value="student,...")//表示 在session中存放student对象
//@SessionAttributes(types={Student.class,Address.class})//表示 在session中存放student,address类型的数据
@RequestMapping("testController")
public class testController {

    /**
     * 请求转发 forward 地址栏不变 一次请求两次响应 request作用域为一个
     * 重定向 redirect 地址栏改变 两次请求两次响应 request作用域为两个 注意取数取不到
     * @return
     */
    @RequestMapping(value = "test")
    public String test(){
//        return "forward：pages/front/success.jsp";//此种方式不会被视图解析器解析
//        return "redirect：pages/front/success.jsp";//此种方式不会被视图解析器解析
        return "front/success";
    }

    /**
     * param表示请求连接路径是“http:id:端口号/testController/testController”且必须要有参数name是123，age是18才能通过,同理header请求头中也要符合要求
     * @return
     */
    @RequestMapping(value = "testController",params = {"name=123,age=18"},headers = {"..."})
    public String testController(){
        return "front/success";
    }

    /**
     * 获取连接中传递过来的参数值。 如<a href=""..传值
     * 注：传参用/ 不是？ result方式传值
     * 方法一 通过PathVariable标签可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中
     * 例如：
     *       @RequestMapping(value=”hello/{id}/{name}”)
     *       请求路径：http://localhost:8080/testController/hello/1/james
     * @return
     */
    @RequestMapping(value="testParam1/{id}/{name}")
    public String testParam1(@PathVariable("id") Integer id,@PathVariable("name") String name){
        System.out.println("参数id值："+id);
        System.out.println("参数name值："+name);
        return "front/success";
    }

    /**
     * 获取连接中传递过来的参数值。  如<a href=""..传值
     * 注：传参用？ 不是/ 不同方式传值
     * 方法二 通过RequestParam标签 （等价于 request.getParamter("")）
     * required = false  表示 该参数不是必传的
     * defaultValue = "23" 表示默认值为 23
     * @return
     */
    @RequestMapping(value="testParam2")
    public String testParam2(@RequestParam("id") Integer id, @RequestParam(value="name",required = false,defaultValue = "23") String name){
        System.out.println("参数id值："+id);
        System.out.println("参数name值："+name);
        return "front/success";
    }

    /**
     * mvc支持（注：界面参数属性必须和实体类中一致，支持级联属性。如下shudent中address）
     * 获取界面传递参数（实体类）
     *  界面中 参数name=“id”,value=“传递的参数值”
     *  界面中 参数name=“name”,value=“传递的参数值”
     *  界面中 参数name=“address.homeAddress”,value=“传递的参数值”
     *  界面中 参数name=“address.schooleAddress”,value=“传递的参数值”
     * @return
     */
    @RequestMapping(value = "testBean")
    public String testBean(person student){
        System.out.println(student);
        return "front/success";
    }

    /**
     * 获取请求头信息
     * 通过@RequestHeader标签获取请求头中属性信息
     * @return
     */
    @RequestMapping("testHeader")
    public String testHeader(@RequestHeader("Expires") String expires){
        System.out.println("请求头中 Expires属性值："+expires);
        return "front/success";
    }

    /**
     * cookie
     * 前置知识：
     *  服务端在接受客户端第一次请求时，会分配给客户端一个session（改session包含一个sessionId），
     *  并且服务端会在第一次响应客户端时，且改sessionId赋值给JSESSIONID,并传给客户端cookie中
     * @return
     */
    public String testCookie(@CookieValue("") String cookie){
        System.out.println("cookis："+cookie);
        return "front/success";
    }

    /**
     * result风格
     * *（浏览器默认 header 请求头）
     *  （浏览器默认）POST     （sql-增）
     *  （浏览器默认的）GET     （sql-查）
     *  DELETE      (sql-删 SJP中使用时，需要:1.配置hidden过滤器 2.在POST请求的基础上增加一个隐藏域。如：<input type="hidden" name="_method" value="DELETE"/>)
     *  PUT         (sql-改 SJP中使用时，需要在POST请求的基础上增加一个隐藏域。如：<input type="hidden" name="_method" value="PUT"/>)
     * @return
     */
    @RequestMapping(value = "testRestful",method = RequestMethod.DELETE)
    public String testResultful(){

        return "front/successs";
    }

    /**
     * 控制层跳转回界面且传递参数
     * ModelAndView、ModelMap、Map、Model 一般数据放在了request作用域中
     *
     * @SessionAttributes、@ModelAttribute
     *
     */
    @RequestMapping(value = "testModelAndView")
     public ModelAndView testModelAndView(){
        ModelAndView modelAndView =new ModelAndView("front/successs");
        //传递参数值
        modelAndView.addObject("name","ModelAndView");
        modelAndView.addObject("age","18");
        //或 直接传递对象
        person student = new person();
        student.setId(1);
        student.setName("modelAndView");
        Address address = new Address();
        student.setAddress(address);
        modelAndView.addObject("student",student);
        return modelAndView;
     }

    /**
     * 控制层跳转回界面且传递参数
     * ModelAndView、ModelMap、Map、Model 一般数据放在了request作用域中
     *
     * @SessionAttributes、@ModelAttribute
     *
     */
    @RequestMapping(value = "testModelMap")
    public String testModelMap(ModelMap modelMap){

        modelMap.addAttribute("name","modelMap");
        modelMap.addAttribute("age","18");

        modelMap.put("student",new person());
        return "front/successs";
    }

    /**
     * 控制层跳转回界面且传递参数
     * ModelAndView、ModelMap、Map、Model 一般数据放在了request作用域中
     *
     * @SessionAttributes、@ModelAttribute
     *
     */
    @RequestMapping(value = "testMap")
    public String testMap(Map<Object,Object> map){

        map.put("name","map");
        map.put("age","18");

        map.put("student",new person());
        return "front/successs";
    }

    /**
     * 控制层跳转回界面且传递参数
     * ModelAndView、ModelMap、Map、Model 一般数据放在了request作用域中
     *
     * @SessionAttributes、@ModelAttribute
     *
     */
    @RequestMapping(value = "testModel")
    public String testModel(Model model){

        model.addAttribute("name","model");
        model.addAttribute("age","18");

        model.addAttribute("student",new person());
        return "front/successs";
    }

    //===========================================================
    /**
     * @ModelAttribute
     * 在此类中任何调用前，都会先执行此方法
     * @return
     */
    @ModelAttribute
    public void queryModelAttribute(Map<String ,Object> map){
        //相关逻辑
        map.put("student",new person(1,"",new Address(),new Date()));//约定：map的key 就是方法参数 类型的首字母小写
        map.put("stu",new person(1,"",new Address(),new Date()));//当不一样时，后面调用参数需要增加@ModelAttribute标签
    }

    /**
     * Student中的对象 与上queryModelAttribute方法中map的key首字母小写一样，及形参会自动获取
     * @param student
     * @return
     */
    @RequestMapping(value = "testModelAttribute")
    public String testModelAttribute(person student, @ModelAttribute(value = "stu") person student2){
        System.out.println("studen的id为："+1);
        System.out.println("studen2的id为："+1);
        return "front/successs";
    }
    //===========================================================

    /**
     * i18n 国际化操作（1。配置文件中配置 2.界面使用jstl中的fmt标签）
     * 通过控制器响应进入界面才会进行国际化操作
     * @return
     */
    @RequestMapping(value = "testI18n")
    public String testI18n(){
        return "front/successs";
    }

    /**
     * 格式化数据测试
     * @param student 中birthday属性指定格式
     * @param bindingResult 如果student格式化数据出错，会把错误信息传入bindingResult 注：必须放在student后一个，且紧挨着（可以把错误信息发送前台）
     *   标签    @Valid与实体类属性校验关联，该校验汇执行
     * @return
     */
    @RequestMapping(value = "testDateTimeFormat")
    public String testDateTimeFormat(@Valid person student, BindingResult bindingResult){
        //循环打印错误信息
        int errorCount = bindingResult.getErrorCount();
        if(errorCount>0){
            for (FieldError fieldError:bindingResult.getFieldErrors()) {
                System.out.println(fieldError.getDefaultMessage());
            }
        }
        return "front/successs";
    }

    /**
     * ajax
     * @return
     */
    @ResponseBody//表示把方法返回值转变为json，并响应前端AJAX回调结果
    @RequestMapping(value = "testAjax")
    public String testAjax(){
        Map<String, String> map = new HashMap<>();
        map.put("name","小明");
        return map.toString();
    }

    /**
     * 上传文件
     * 注：前端请求时: method="post" enctype="multipart/form-data"
     * @return
     */
    @RequestMapping(value = "testUpload")
    public String testUpload(@RequestParam("desc") String desc,@RequestParam("file") MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();//获得输入流
        OutputStream outputStream = new FileOutputStream("C:\\A.txt");//输出流。文件输出
        byte[] bytes = new byte[1024];
        int len = -1;
        while((len=inputStream.read(bytes))!=-1){//输入流不断地读取，下面输出流进行输出
            outputStream.write(bytes,0,len);
        }
        outputStream.close();
        inputStream.close();
        return "front/successs";
    }

    /**
     * 异常捕获
     * @ExceptionHandler 可以捕获次类中抛出的ArithmeticException异常
     * 与@ControllerAdvice//控制器处理异常的类 结合使用
     * @param e
     * @return
     */
    @ExceptionHandler({ArithmeticException.class})
    public String testArithmeticException(ArithmeticException e){
        return "error/404";
    }

    /**
     * 自定义异常 当遇到异常 ALREADY_REPORTED(208, "Already Reported"),错误信息为 "自定义异常处理"
     * @return
     */
    @ResponseStatus(value = HttpStatus.ALREADY_REPORTED,reason = "自定义异常处理")
    public String testResponseStatus(){
        return "";
    }

}
