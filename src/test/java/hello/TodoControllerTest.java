package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
@RunWith(SpringRunner.class)
@WebMvcTest
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Repository repo;

    ArrayList<Todo> mockTodoList=new ArrayList<Todo>(){{
        add(new Todo("10-09-2019","do brush",1));
        add(new Todo("10-09-2019","do bath",2) );
    }};

    @Test
    public void getAllTodo() throws Exception
    {


//        given(repo.todoItems()).willReturn(mockTodoList);
//        mockMvc.perform(get("/todo"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{'date':'10-09-2019','todo':'do brush','todoId':1}]"));
//
        Mockito.when(repo.todoItems()).thenReturn(mockTodoList);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/todo").accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals("[{'todo':'do brush','date':'10-09-2019','todoId':1},{'todo':'do bath','date':'10-09-2019','todoId':2}]",result.getResponse().getContentAsString(),false);
    }

}
