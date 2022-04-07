package com.example.oems.repository;

import com.example.oems.entity.Selection;
import com.example.oems.entity.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class TaskRepositoryTest {

    private static TaskRepository repository;

    @BeforeAll
    static void beforeAll() {
        repository = TaskRepository.getInstance();
    }

    @Test
    void testInsert() {
        for (int i = 0; i < 20; i++) {
            repository.saveTask(
                    new Task(
                            "This is a task.",
                            new Selection("yes", "no", "hmmm", "ohhh"),
                            1
                    )
            );
        }
    }

    @Test
    void testPage() {
        List<Task> list = repository.getAll(3, 2);
        list.forEach(System.out::println);
    }
}