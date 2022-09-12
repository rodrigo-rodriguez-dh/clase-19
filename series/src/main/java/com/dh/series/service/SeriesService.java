package com.dh.series.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dh.series.model.Series;
import com.dh.series.repository.SeriesRepository;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public Series findById(String id) {
        return seriesRepository.findById(id)
            .orElse(null);
    }

    public List<Series> findAll() {
        return seriesRepository.findAll();
    }

    public Series saveSeries(Series series) {
        return seriesRepository.save(series);
    }
}
