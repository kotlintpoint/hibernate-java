package sodhankit.hibernate.eager.vs.lazy;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import sodhankit.hibernate.eager.vs.lazy.entity.Course;
import sodhankit.hibernate.eager.vs.lazy.entity.Instructor;
import sodhankit.hibernate.eager.vs.lazy.entity.InstructorDetail;

public class GetCoursesLater {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// start a transaction
			session.beginTransaction();
			
			// option 2: Hibernate query with HQL
			
			// get the instructor from db
			int theId = 2;

			Query<Course> query = 
					session.createQuery("select c from Course c "
									+ "where c.instructor.id=:theInstructorId", 
							Course.class);

			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
 
			List<Course> tempCourses=query.getResultList();
			
			System.out.println("tempCourses : " + tempCourses);	
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			System.out.println("Done!");
		}
		finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}