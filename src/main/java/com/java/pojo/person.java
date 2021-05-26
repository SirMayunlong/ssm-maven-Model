package com.java.pojo;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

public class person {

    private int id;

    private String name;

    private Address address;

    @Past//表示将页面传送过来的参数进行雁阵 Past：过去的一个时间。如果不是则报错  (注：controller中参数必须加上@Valid 标签)
    @DateTimeFormat(pattern = "yyyy-MM-dd")//将页面传送过来的数据指定格式
    private Date brithday;

    @Email////表示将页面传送过来的参数进行雁阵 Email：修复和xxxx@xxx.xxx   (注：controller中参数必须加上@Valid 标签)
    private String email;

    public person() {
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    public person(int id, String name, Address address, Date brithday) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.brithday = brithday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
