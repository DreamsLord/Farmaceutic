import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Interface {

	static Scanner in = new Scanner(System.in);
	static ListDrags listDrags = new ListDrags();

	public static String getUserInput() {
		return in.nextLine();
	}

	public static void main(String[] args) {
		String userInput;
		do {
			System.out.println("+++++++++++++++++++++++++++++++++");
			System.out.println("++++ Program Bazy lekow    ++++");
			System.out.println("Menu:");
			System.out.println("1.Pokaz leki w Bazie Danych");
			System.out.println("2.Dodaj Lek");
			System.out.println("[x]Zakoncz");
			userInput = getUserInput();
			if (userInput.equals("1")) {
				showDrags();

			} else if (userInput.equals("2")) {
				addDrag();
			}
		} while (!userInput.equalsIgnoreCase("x"));

	}

	private static void showDrags() {
		Drag drag;
		System.out.println();
		System.out.println("#########################################################");
		System.out.println("######                Baza Lekow                ######");
		System.out.println("#########################################################");

		for (int i = 0; i < listDrags.getDrags().size(); i++) {
			drag = listDrags.getDrags().get(i);
			System.out.println(i + ": " + drag.getName());

		}
		System.out.println();
		Pattern wzorzecNumeru = Pattern.compile("[0-9]+");
		String loadNumber;
		do {
			System.out.println("Wybierz Lek");
			loadNumber = getUserInput();
		} while (!wzorzecNumeru.matcher(loadNumber).matches());

		Integer numberDrag = Integer.parseInt(loadNumber);

		if (numberDrag > listDrags.getDrags().size()) {
			System.out.println("Nie ma w bazie wybranego numeru leku, sproboj ponownie lub dodaj lek");

		} else {

			Drag electedDrag = listDrags.getDrags().get(numberDrag);

			System.out.println("nazwa: " + electedDrag.getName());
			System.out.println("Firma : " + electedDrag.getCompany());
			System.out.println("Numer Serii : " + electedDrag.getNumerSerii());
			System.out.println("Data wprowadzenia: " + electedDrag.getDataWprowadzenia().toString());
			System.out.println("waga: " + electedDrag.getWeight());
			System.out.println("Firma : " + electedDrag.getCompany());

		}
	}

	private static void addDrag() {
		System.out.println();
		System.out.println("#########################################################");
		System.out.println("######                 DODAJ  LEK               ######");
		System.out.println("#########################################################");

		Drag drag = new Drag();

		System.out.println("Podaj nazw� Leku");
		drag.setName(getUserInput());
		System.out.println("Podaj nazw� firmy");
		drag.setCompany(getUserInput());
		System.out.println("Podaj numer Serii");
		drag.setNumerSerii(getUserInput());

		Pattern wzorzecWagi = Pattern.compile("[0-9]+(\\.[0-9]+)?");
		String loadWeight;
		do {
			System.out.println("Podaj wage leku");

			loadWeight = getUserInput();
			if (wzorzecWagi.matcher(loadWeight).matches()) {
				try {
					drag.setWeight(Float.valueOf(loadWeight));

				} catch (NumberFormatException nfe) {
					System.out.println("Z�y format wagi, poprawny np: 8.00");
				}
			} else
				System.out.println("Z�y format wagi, poprawny np: 10.00");
		} while (drag.getWeight() == null);

		
		Pattern wzorzecDaty = Pattern.compile("[1-2][90][0-9]{2}.[0-1]?[0-9].[0-3]?[0-9]");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataUrodzeniaWczytana;
		do{
			System.out.println("Podaj Date wprowadzenia");
			dataUrodzeniaWczytana=getUserInput();
			if(wzorzecDaty.matcher(dataUrodzeniaWczytana).matches()){
				
			
				try{
					drag.setDataWprowadzenia(sdf.parse(dataUrodzeniaWczytana));
				}catch(ParseException pe){
					
				
					System.out.println("z�y format daty, poprawny yyyy-MM-dd");
				}
				}else
					System.out.println("z�y format daty, poprawny yyyy-mm-dd");
				
			
		}while(drag.getDataWprowadzenia()==null);
		System.out.println("To wszystkie dane, Dzi�kuje");
		listDrags.addDrag(drag);
	}
}
