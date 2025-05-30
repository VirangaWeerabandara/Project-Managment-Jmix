package com.company.projectmanagement.app;

import com.company.projectmanagement.entity.Project;
import com.company.projectmanagement.entity.ProjectStats;
import com.company.projectmanagement.entity.Task;
import io.jmix.core.DataManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProjectStatsService {
    private final DataManager dataManager;

    public ProjectStatsService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<ProjectStats> fetchProjectStats() {
        List<Project> projects = dataManager.load(Project.class).all().list();

        List<ProjectStats> projectStats = projects.stream().map(project -> {
            ProjectStats stat = dataManager.create(ProjectStats.class);
            stat.setId(project.getId());
            stat.setProjectName(project.getName());
            stat.setTaskCount(project.getTask().size());

            Integer plannedEffort = project.getTask().stream().map(Task::getEstimation).reduce(0, Integer::sum);
            stat.setPlannedEfforts(plannedEffort);
            stat.setActualEfforts(getActualEffort(project.getId()));
            return stat;
        }).collect(Collectors.toList());
        return projectStats;
    }

    public Integer getActualEffort(UUID projectId) {
        return dataManager.loadValue("select SUM(te.timeSpent) from TimeEntry te " +
                "where te.task.project.id = :projectId", Integer.class).
                parameter("projectId", projectId)
                .one();
    }
}