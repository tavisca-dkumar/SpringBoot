package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Repository repo;



    @Test
    public void getAllTodo() throws Exception
    {
        ArrayList<Todo> mockTodoList=new ArrayList<Todo>(){{
            add(new Todo("10-09-2019","do brush",1));
            add(new Todo("10-09-2019","do bath",2) );
        }};
        Mockito.when(repo.todoItems()).thenReturn(mockTodoList);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/todo").accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse res=result.getResponse();
        JSONAssert.assertEquals("[{'todo':'do brush','date':'10-09-2019','todoId':1},{'todo':'do bath','date':'10-09-2019','todoId':2}]",result.getResponse().getContentAsString(),false);
        Assert.assertEquals(HttpStatus.OK.value(),res.getStatus());
    }

    @Test
    public void canGetNoContentForEmptyTodoList() throws Exception
    {

        Mockito.when(repo.isEmpty()).thenReturn(true);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/todo");
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse res=result.getResponse();
        Assert.assertEquals(HttpStatus.NO_CONTENT.value(),res.getStatus());
    }

    @Test
    public void canGetTodoById() throws Exception
    {
        Todo mockTodo=new Todo("10-09-2019","do brush",1);
        Mockito.when(repo.isPresent(1)).thenReturn(true);
        Mockito.when(repo.getTodoItem(1)).thenReturn(mockTodo);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/todo/1");
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse res=result.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(),res.getStatus());
        JSONAssert.assertEquals("{'todo':'do brush','date':'10-09-2019','todoId':1}",result.getResponse().getContentAsString(),false);
    }
    @Test
    public void canGetNotFoundForUnknownId()throws Exception
    {

        Mockito.when(repo.isPresent(1)).thenReturn(false);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/todo/1");
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse res=result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(),res.getStatus());
    }
    @Test
    public void canAddTodoItem()throws Exception
    {
        ObjectMapper objectMapper=new ObjectMapper();
        Todo mockTodo=new Todo("10-09-2019","do brush",1);
        Mockito.when(repo.addTodoItem(Mockito.any(Todo.class))).thenReturn(true);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/todo").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockTodo)).contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse res=result.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(),res.getStatus());
    }
    @Test
    public void canUpdateTodoItem() throws Exception
    {
        ObjectMapper objectMapper=new ObjectMapper();
        Todo mockTodo=new Todo("10-09-2019","do brush",1);
        Mockito.when(repo.updateTodoItem(Mockito.any(Todo.class))).thenReturn(true);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.put("/todo").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockTodo)).contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse res=result.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(),res.getStatus());
    }
    @Test
    public void canUpdateTodoItemShowNotFoundForUnknownItem() throws Exception
    {
        ObjectMapper objectMapper=new ObjectMapper();
        Todo mockTodo=new Todo("10-09-2019","do brush",1);
        Mockito.when(repo.updateTodoItem(Mockito.any(Todo.class))).thenReturn(false);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.put("/todo").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockTodo)).contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse res=result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(),res.getStatus());
    }
    @Test
    public void canDeleteTodoItem() throws Exception
    {
        Todo mockTodo=new Todo("10-09-2019","do brush",1);
        Mockito.when(repo.deleteTodoItem(1)).thenReturn(true);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.delete("/todo/1");
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse res=result.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(),res.getStatus());
    }
    @Test
    public void canHandleDeleteunknownTodoItem() throws Exception
    {
        Todo mockTodo=new Todo("10-09-2019","do brush",1);
        Mockito.when(repo.deleteTodoItem(1)).thenReturn(false);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.delete("/todo/1");
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse res=result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(),res.getStatus());
    }

}
