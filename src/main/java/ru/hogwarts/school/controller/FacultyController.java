package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculties")
@Tag(name = "API для работы с факультетами")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    @Operation(summary = "Создание факультета")
    public ResponseEntity<Faculty> create(@RequestBody Faculty facultyRs) {
        Faculty faculty = facultyService.create(facultyRs);
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    @Operation(summary = "Изменение факультета")
    public ResponseEntity<Faculty> edit(@RequestBody Faculty facultyRs) {
        Faculty faculty = facultyService.update(facultyRs);
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление факультета по id")
    public ResponseEntity<Faculty> deleteById(@PathVariable("id") long id) {
        facultyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    @Operation(summary = "Получить факультет по id")
    public ResponseEntity<Faculty> getById(@PathVariable("id") long id) {
        Faculty faculty = facultyService.getById(id);
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("all")
    @Operation(summary = "Получить все факультеты")
    public ResponseEntity<Collection<Faculty>> getAll() {
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping
    @Operation(summary = "Получить все факультеты по названию или цвету")
    public ResponseEntity<Collection<Faculty>> getAllByNameOrColor(@RequestParam("nameOrColor") String nameOrColor) {
        Collection<Faculty> faculty = facultyService.getAllByNameOrColor(nameOrColor, nameOrColor);
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("{id}/students")
    @Operation(summary = "Получить студентов по id факультета")
    public ResponseEntity<Collection<Student>> getStudentsByFacultyId(@PathVariable("id") long facultyId) {
        Collection<Student> students = facultyService.getStudentsByFacultyId(facultyId);
        return ResponseEntity.ok(students);

    }
}
