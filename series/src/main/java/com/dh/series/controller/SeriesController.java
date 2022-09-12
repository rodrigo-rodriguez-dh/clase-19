package com.dh.series.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dh.series.model.Series;
import com.dh.series.service.SeriesService;

@RestController
@RequestMapping("/series")
public class SeriesController {

    private final SeriesService service;

    @Autowired
    public SeriesController(SeriesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Series> seriesList = service.findAll();
        return seriesList.isEmpty()
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : ResponseEntity.ok(seriesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Series series = service.findById(id);
        return Objects.isNull(series)
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : ResponseEntity.ok(series);
    }

    @PostMapping
    public ResponseEntity<?> saveSeries(@RequestBody Series series) {
        return ResponseEntity.ok(service.saveSeries(series));
    }
}
