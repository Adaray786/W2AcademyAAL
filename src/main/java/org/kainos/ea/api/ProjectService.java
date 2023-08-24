package org.kainos.ea.api;

import org.kainos.ea.cli.Project;
import org.kainos.ea.cli.ProjectRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.db.ProjectDao;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {

    private final ProjectDao projectDao;

    public ProjectService(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public List<Project> getProjects() throws FailedToGetProjectException {
        return projectDao.getProjects();
    }

    public Project getProjectById(int id) throws FailedToGetProjectException, ProjectDoesNotExistException {

        Project project = projectDao.getProjectById(id);

        if (project == null) throw new ProjectDoesNotExistException();

        return project;
    }

    public int createProject(ProjectRequest request) throws FailedToCreateProjectException {
        try {
            int id = projectDao.createProject(request);

            //TODO validate reqeust

            if (id == -1) {
                throw new FailedToCreateProjectException();
            }

            return id;
        } catch (SQLException e) {
            e.printStackTrace();

            throw new FailedToCreateProjectException();
        }
    }

    public void updateProject(int id, ProjectRequest request) throws FailedToUpdateProjectException, ProjectDoesNotExistException {
        try {
            if (projectDao.getProjectById(id) == null) {
                throw new ProjectDoesNotExistException();
            }

             projectDao.updateProject(id, request);
        } catch (SQLException | FailedToGetProjectException e) {
            e.printStackTrace();

            throw new FailedToUpdateProjectException();
        }
    }
}
