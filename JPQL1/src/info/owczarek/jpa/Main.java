package info.owczarek.jpa;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import info.owczarek.jpa.domain.Employee;

public class Main {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatebase");
		entityManager = entityManagerFactory.createEntityManager();

		addEmployees();
//	1		
//		{
//			TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where e.lastName = 'Pajak'", Employee.class);
//			Employee employee = query.getSingleResult();
//			System.out.println("First name: " + employee.getFirstName());
//			System.out.println("Last name: " + employee.getLastName());
//			System.out.println("Salary: " + employee.getSalary());
//		}
//	2		
//		{
//			TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where e.salary > 3000 order by e.salary", Employee.class);
//			List<Employee> employees = query.getResultList();
//			for (Employee employee : employees) {
//				System.out.println("First name: " + employee.getFirstName());
//				System.out.println("Last name: " + employee.getLastName());
//				System.out.println("Salary: " + employee.getSalary());
//				System.out.println();
//			}
//		}
//	3		
		Query query = entityManager.createQuery("select concat(e.firstName, ' ' , e.lastName), e.salary * 0.2 from Employee e");
		Iterator<?> iterator = query.getResultList().iterator();
		while (iterator.hasNext()) {
			Object[] item = (Object[]) iterator.next();
			String name = (String) item[0];
			double tax = (double) item[1];
			System.out.println(name+" has to pay "+ tax);
		}
//		
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
