package info.owczarek.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import info.owczarek.jpa.domain.Address;
import info.owczarek.jpa.domain.Employee;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatebase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Employee employee = new Employee();
		Address address= new Address();
		employee.setFirstName("Jan");
		employee.setLastName("Nowak");
		employee.setSalary(111.1);
		employee.setAddress(address);
		address.setLocality("Warszawa");
		address.setZipCode("33-432");
		address.setStreet("Koszykowa");
		address.setStreetNumber(222);
		
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
	}
}
