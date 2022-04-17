package com.example.oems.repository;

import com.example.oems.Result;
import com.example.oems.dao.TaskDao;
import com.example.oems.db.AppDataBase;
import com.example.oems.entity.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class TaskRepository {
    private static Connection connection = null;
    private final TaskDao dao;

    private TaskRepository() {
        connection = AppDataBase.getConnection();
        dao = new TaskDao(connection);
    }

    public static TaskRepository getInstance() {
        return new TaskRepository();
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveTask(Task... tasks) {
        Arrays.stream(tasks).forEach(dao::insert);
    }

    public void deleteTask(Task... tasks) {
        Arrays.stream(tasks).forEach(dao::delete);
    }

    public void deleteTask(int... taskIds) {
        Arrays.stream(taskIds).forEach(dao::delete);
    }


    /**
     * Only for administrator
     *
     * @param count 每页个数
     * @param page  当前页数 从0开始
     */
    public List<Task> getAll(int count, int page) {
        List<Task> list = dao.getAll(false);
        if (list.isEmpty()) return list;
        int start = count * page;
        int end = start + count;
        if (end > list.size() - 1) end = list.size() - 1;
        if (start > end) start = end;
        if (start >= 0) return list.subList(start, end);
        else return list;
    }

    public Result<Task> getById(int id, boolean answerIgnored) {
        Task task = dao.getById(id, answerIgnored);
        if (task == null) return new Result<>("Task does not exist");
        else return new Result<>(task);
    }

    public Result<List<Task>> random(int limit, boolean answerIgnored) {
        List<Task> list = dao.random(limit, answerIgnored);
        if (list.isEmpty()) return new Result<>("Task bank maintenance");
        else return new Result<>(list);
    }
}
