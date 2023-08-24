package org.kainos.ea.api;

import org.kainos.ea.cli.Project;
import org.kainos.ea.client.FailedToGetProjectException;
import org.kainos.ea.client.ProjectDoesNotExistException;
import org.kainos.ea.db.ProjectDao;

public class ProjectService {

    private final ProjectDao projectDao;

    public ProjectService(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public Project getProjectById(int id) throws FailedToGetProjectException, ProjectDoesNotExistException {

        Project project = projectDao.getProjectById(id);

        if (project == null) throw new ProjectDoesNotExistException();

        return project;
    }
}
