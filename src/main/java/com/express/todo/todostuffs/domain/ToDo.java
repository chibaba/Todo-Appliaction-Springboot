package com.express.todo.todostuffs.domain;


import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ToDo {
    @NotNull
    private String id;
    @NotNull
    private String description;
    private LocalDateTime created;
    private LocalDateTime modifieed;
    private boolean completed;

    public ToDo() {
        LocalDateTime date = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.created = date;
        this.modifieed = date;

         }
    public ToDo(String description) {
        this();
        this.description = description;
    }
}
