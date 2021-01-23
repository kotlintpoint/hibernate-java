package sodhankit.hibernate.one2one.bidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import sodhankit.hibernate.one2one.bidirectional.entity.Instructor;
import sodhankit.hibernate.one2one.bidirectional.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// create the objects
			
			/*Instructor tempInstructor = 
					new Instructor("Ankit", "Sodha", "ankit@kotlintpoint.com");
			
			InstructorDetail tempInstructorDetail =
					new InstructorDetail(
							"http://www.kotlintpoint.com/youtube",
							"Love to Code In Java!!!");		
			*/
			
			
			Instructor tempInstructor = 
					new Instructor("Neeraj", "Agnihotri", "neeraj@kotlintpoint.com");
			
			InstructorDetail tempInstructorDetail =
					new InstructorDetail(
							"http://www.youtube.com",
							"Love to Code in .Net!!!");		
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			//
			// Note: this will ALSO save the details object
			// because of CascadeType.ALL
			//
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);					
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}





