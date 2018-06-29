package com.treasure.hunt.service;

public interface ActivityStatisticsService {

    void updateStatistics(Long activityId, String updateType, String updateStatus);
}
