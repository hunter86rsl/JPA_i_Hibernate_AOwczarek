package info.owczarek.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import info.owczarek.jpa.domain.Employee;

public class Main {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatebase");
		entityManager = entityManagerFactory.createEntityManager();

		addEmployees();

		// 1
		// {
		// Query query = entityManager.createQuery(
		// "select avg(e.salary), min(e.salary), max(e.salary), sum(e.salary),
		// count(e) from Employee e");
		// Object[] result = (Object[]) query.getSingleResult();
		// System.out.println("Œrednia: " + result[0]);
		// System.out.println("Najmni¿sza: " + result[1]);
		// System.out.println("Najwy¿sza: " + result[2]);
		// System.out.println("Suma: " + result[3]);
		// System.out.println("Pracowników: " + result[4]);
		// }

		// 2
		{
			Query query = entityManager.createQuery(
					"select substring(e.firstName, 1, 3), trim(e.lastName), lower(e.firstName), upper(e.firstName), length(e.firstName) from Employee e where e.firstName = 'Karol'");

			Object[] result = (Object[]) query.getSingleResult();
			
			System.out.println("Pierwsze trzy litery imienia: " + result[0]) ;
			System.out.println("Nazwisko: |" +  result[1] + "|");
			System.out.println("Imie malymi literami: " + result[2]);
			System.out.println("Imie duzymi literami: " + result[3]);
			System.out.println("Dlugosc imienia: "+result[4]);
			
			
		}
		//

		entityManager.close();
		entityManagerFactory.close();
	}

	private static void addEmployees() {
		addEmployee("Karol", "     Mateusiak     ", 2633);
		addEmployee("Marika", "Bednarek", 2345);
		addEmployee("Jan", "Mateusiak", 7346);
		addEmployee("Daria", "Kowalska", 2352);
		addEmployee("Monika", "Ucisnska", 4263);
		addEmployee("Ernest", "Pajak", 2634);
		addEmployee("Kamil", "Stepien", 2345);
		addEmployee("Przemek", "Maciejewski", 5433);
		addEmployee("Robert", "Wozniak", 3324);
		addEmployee("Agnieszka", "Nowak", 2000);
		addEmployee("Angelika", "Bednarska", 1000);
	}

	private static void addEmployee(String firstName, String lastName, double salary) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setSalary(salary);

		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
	}
}
