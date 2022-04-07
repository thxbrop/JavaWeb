package com.example.oems.dao;

import com.example.oems.entity.Selection;
import com.example.oems.entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao implements BaseDao<Task> {
    private final Connection connection;

    public TaskDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Task task) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO task (description, selections, correct) VALUES (?,?,?)")) {
            statement.setString(1, task.getDescription());
            statement.setString(2, task.getSelections().toJson());
            statement.setInt(3, task.getCorrect());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Task task) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM task WHERE id = ?")) {
            statement.setInt(1, task.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    @Override
    public Task getById(int id) {
        return getById(id, false);
    }

    public Task getById(int id, boolean answerIgnored) {
        Task task = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM task WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (answerIgnored) {
                    task = new Task(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            Selection.parseJson(resultSet.getString("selections"))
                    );
                } else {
                    task = new Task(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            Selection.parseJson(resultSet.getString("selections")),
                            resultSet.getInt("correct")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Deprecated
    @Override
    public List<Task> getAll() {
        return getAll(false);
    }

    public List<Task> getAll(boolean answerIgnored) {
        List<Task> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM task")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) list.add(toEntity(resultSet, answerIgnored));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Task> random(int limit, boolean answerIgnored) {
        List<Task> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM task ORDER BY RAND() LIMIT ?")) {
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) list.add(toEntity(resultSet, answerIgnored));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Task toEntity(ResultSet resultSet, boolean answerIgnored) throws SQLException {
        Task task;
        if (answerIgnored) {
            task = new Task(
                    resultSet.getInt("id"),
                    resultSet.getString("description"),
                    Selection.parseJson(resultSet.getString("selections"))
            );
        } else {
            task = new Task(
                    resultSet.getInt("id"),
                    resultSet.getString("description"),
                    Selection.parseJson(resultSet.getString("selections")),
                    resultSet.getInt("correct")
            );
        }
        return task;
    }
}
