package com.kk.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kk.domain.Employee;

public class SDURObjTest {

	public static void main(String[] args) {

		Configuration cfg = null;
		SessionFactory factory = null;
		Session ses = null;
		Employee emp = null;

		Transaction tx = null;
		// activation of HB Framwork
		cfg = new Configuration();
		// read both mapping, cfg file data
		cfg = cfg.configure("/com/kk/cfgs/hibernate.cfg.xml");
		// build session factory
		factory = cfg.buildSessionFactory();
		// opensession with DB s.w
		ses = factory.openSession();
		// create obj for Domain class
		emp = new Employee();

		// Saving an Objects
		emp.setEmpId("KO343240");
		emp.setEmpName("KKkkkk");
		emp.setEmpBand("B2");
		try {
			tx = ses.beginTransaction();
			ses.save(emp);
			tx.commit();
			System.out.println("Obj is saved");

		} catch (Exception e) {
			tx.rollback();
		}

		// Deleting an Object
		emp = (Employee) ses.get(Employee.class, "KK74557");
		if (emp != null) {
			try {
				tx = ses.beginTransaction();
				ses.delete(emp);
				tx.commit();
				System.out.println("Record is deleted ");
			} catch (Exception e) {
				tx.rollback();
			}
		} else
			System.out.println("For Deletion Record is not found");

		// Updating an Object
		emp = (Employee) ses.get(Employee.class, "KO343245");
		if (emp != null) {
			emp.setEmpBand("D1");
			try {
				tx = ses.beginTransaction();
				ses.saveOrUpdate(emp);
				tx.commit();
				System.out.println("Record is updated ");
			} catch (Exception e) {
				tx.rollback();
			}
		} else
			System.out.println("For Update record is  not found");

		// Reading an Object
		emp = (Employee) ses.get(Employee.class, "NA485762");
		if (emp != null) {
			System.out.println(
					"EmpID :" + emp.getEmpId() + " EmpName :" + emp.getEmpName() + " EmpBand :" + emp.getEmpBand());
		} else
			System.out.println("Record is not found to Read");

		// close object
		ses.close();
		factory.close();
		System.out.println("Connections have been closed........");

	}

}
