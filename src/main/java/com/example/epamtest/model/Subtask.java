package com.example.epamtest.model;


import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subtasks")
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private boolean closed;

    @Column(name = "time_left")
    private Time timeLeft;

    @JoinColumn(name = "task_id")
    @ManyToOne
    private Task task;

    @JoinColumn(name = "parent_id")
    @ManyToOne
    private Subtask parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Subtask> subtaskSet = new HashSet<>();

    public Subtask() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Time getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Time timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Subtask getParent() {
        return parent;
    }

    public void setParent(Subtask parent) {
        this.parent = parent;
    }

    public Set<Subtask> getSubtaskSet() {
        return subtaskSet;
    }

    public void setSubtaskSet(Set<Subtask> subtaskSet) {
        this.subtaskSet = subtaskSet;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", closed=" + closed +
                ", timeLeft=" + timeLeft +
                '}';
    }
}
