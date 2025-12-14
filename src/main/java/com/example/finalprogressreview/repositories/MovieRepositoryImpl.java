package com.example.finalprogressreview.repositories;

import com.example.finalprogressreview.models.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public MovieRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Movie> get() {
        try (Session session = this.sessionFactory.openSession()) {
            Query<Movie> query = session.createQuery("from Movie ", Movie.class);
            return query.list();
        }
    }

    @Override
    public Movie get(int id) {
        try (Session session = this.sessionFactory.openSession()) {
            Movie movie = session.find(Movie.class, id);
            return movie;
        }
    }

    @Override
    public Movie getByTitle(String title) {
        try (Session session = this.sessionFactory.openSession()) {
            Query<Movie> query = session.createQuery("from Movie where title = :title", Movie.class);
            query.setParameter("title", title);

            List<Movie> list = query.list();
            if (list.size() == 0) {
                return null;
            }
            return list.get(0);
        }
    }

    @Override
    public void create(Movie movie) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(movie);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Movie movie) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(movie);
            session.getTransaction().commit();
        }

    }

    @Override
    public void delete(int id) {
        Movie movieToDelete = get(id);
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(movieToDelete);
            session.getTransaction().commit();
        }
    }
}
