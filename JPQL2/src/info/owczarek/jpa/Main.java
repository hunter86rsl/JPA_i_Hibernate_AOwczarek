package info.owczarek.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
		// TypedQuery<Employee> query = entityManager.createQuery("select e from
		// Employee e where e.salary > :minSalary", Employee.class);
		// query.setParameter("minSalary", 5000.0);
		// }
		// 2
		// {
		// TypedQuery<Employee> query = entityManager
		// .createQuery("select e from Employee e where e.salary > ?1 and
		// e.salary < ?2", Employee.class);
		// query.setParameter(1, 2000.0);
		// query.setParameter(2, 3000.0);
		// }
		// 3
		TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where e.lastName in :names",
				Employee.class);
		List<String> names = new ArrayList<>();
		names.add("Mateusiak");
		names.add("Bednarek");
		query.setParameter("names", names);

		//

		for (Employee employee : query.getResultList()) {
			System.out.println(employee.getFirstName());
			System.out.println(employee.getLastName());
			System.out.println(employee.getSalary());
			System.out.println();
		}

		entityManager.close();
		entityManagerFactory.close();
	}

	private static void addEmployees() {
		addEmployee("Karol", "Mateusiak", 2633);
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
