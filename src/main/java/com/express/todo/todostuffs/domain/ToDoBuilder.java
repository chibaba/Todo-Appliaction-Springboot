package com.express.todo.todostuffs.domain;

public class ToDoBuilder {

    private static ToDoBuilder instance = new ToDoBuilder();
    private  String id = null;
    private String description = "";


    private ToDoBuilder() {}

    public static ToDoBuilder create() {
        return  instance;
    }


}
