package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private final String URL = "jdbc:mysql://localhost:3306/sdvid";
	private String user = "student";
	private String pass = "student";

	@Override
	public Film findFilmById(int filmId) {
		Connection conn;
		PreparedStatement stmt;
		ResultSet rs;
		String sqltxt;
		Film film = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			sqltxt = "SELECT * FROM film WHERE id = ?";
			stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			rs = stmt.executeQuery();

			// Film(int id, String title, String description, int release_year, int
			// language_id, int rental_duration,
			// double rental_rate, int length, double replacement_cost, String rating,
			// String special_features)
			if (rs.next()) {
				film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getInt("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"));
				
						film.setActors(findActorsByFilmId(film.getId()));

			}
			conn.close();
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Connection conn;
		PreparedStatement stmt;
		ResultSet rs;
		String sqltxt;
		Actor actor = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			sqltxt = "SELECT * FROM actor WHERE actor_id = ?";
			stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, actorId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				// public Actor(int id, String first_name, String last_name)
				actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}
			conn.close();
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		Connection conn;
		PreparedStatement stmt;
		ResultSet rs;
		String sqltxt;
		List<Actor> actors = new ArrayList<Actor>();
		Actor actor;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			sqltxt = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor.id WHERE film_id = ?";
			stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
				actors.add(actor);
			}

			conn.close();
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		Connection conn;
		PreparedStatement stmt;
		ResultSet rs;
		String sqltxt;
		List<Film> films = new ArrayList<Film>();
		Film film;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			sqltxt = "SELECT * FROM film WHERE film_id = ?";
			stmt = conn.prepareStatement(sqltxt);
			stmt.setString(1, keyword);
			rs = stmt.executeQuery();

			if (rs.next()) {
				film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getInt("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"));

				film.setActors(findActorsByFilmId(film.getId()));

				films.add(film);
			}

			conn.close();
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return films;
	}

}
