package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();

	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		menu();
		int menuChoice = input.nextInt();

		switch (menuChoice) {
		case 1:
			option1(input);
			break;
		case 2:
			option2(input);
			break;
		case 3:
			System.out.println("Goodbye");
			break;

		default:
			System.out.println("Enter a valid number");
			break;
		}

	}

	private void menu() {
		System.out.println("1: Look up a film by its id");
		System.out.println("2: Look up a film by a search keyword");
		System.out.println("3: Exit the application");
		System.out.println();
		System.out.println("Enter a number");
	}

	public Film option1(Scanner input) {
		System.out.println("Enter film id: ");
		int filmId = input.nextInt();
		Film film = db.findFilmById(filmId);
		if (film != null) {
			System.out.println(film);
		} else {
			System.out.println("Film id not found");
		}
		return film;
	}
	
	public List<Film> option2(Scanner input) {
		System.out.println("Enter film keyword: ");
		String keyword = input.next();
		List<Film> films = db.findFilmByKeyword(keyword);
		if (films != null) {
			System.out.println(films);
		} else {
			System.out.println("Film keyword not found");
		}
		return films;
	}

}
