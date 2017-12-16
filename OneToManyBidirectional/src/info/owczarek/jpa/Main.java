package info.owczarek.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import info.owczarek.jpa.domain.Employee;
import info.owczarek.jpa.domain.Phone;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatebase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Employee employee = new Employee();
		employee.setFirstName("Jan");
		employee.setLastName("Nowak");
		employee.setSalary(1123.3);

		Phone phone1 = new Phone();
		phone1.setType("mobile");
		phone1.setNumber("343343434");
		phone1.setEmployee(employee);

		Phone phone2 = new Phone();
		phone2.setType("home");
		phone2.setNumber("758848938");
		phone2.setEmployee(employee);

		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.persist(phone1);
		entityManager.persist(phone2);
		entityManager.getTransaction().commit();
		
		entityManager.refresh(employee);
		
		entityManager.close();
		entityManagerFactory.close();
	}
}
