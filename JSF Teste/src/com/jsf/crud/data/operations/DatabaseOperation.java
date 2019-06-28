package com.jsf.crud.data.operations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.jsf.crud.StudentBean;

public class DatabaseOperation {

	private static ArrayList<StudentBean> studentsList = new ArrayList<StudentBean>();
	private static int i = 0;

	public static boolean getConnection() {
		// Mock method to implement further
		return true;
	}

	public static ArrayList<StudentBean> getStudentsListFromDB() {
//		studentsList = new ArrayList<StudentBean>();
//
//		for (int i = 0; i < 4; i++) {
//			StudentBean stuObj = new StudentBean();
//			stuObj.setId(i);
//			stuObj.setName("Rodrigo Amorim " + i);
//			stuObj.setEmail("rodrigo" + i + "@gmail.com");
//			stuObj.setPassword("senha");
//			stuObj.setGender("Masculino");
//			stuObj.setAddress("Rua Dr. Mario Campos " + i);
//			studentsList.add(stuObj);
//		}
		return studentsList;
	}

	public static String saveStudentDetailsInDB(StudentBean newStudentObj) {
		StudentBean stuObj = new StudentBean();
		i++;

		stuObj.setId(i);
		stuObj.setName(newStudentObj.getName());
		stuObj.setEmail(newStudentObj.getEmail());
		stuObj.setPassword(newStudentObj.getPassword());
		stuObj.setGender(newStudentObj.getGender());
		stuObj.setAddress(newStudentObj.getAddress());
		studentsList.add(stuObj);

		return "createStudent.xhtml?faces-redirect=true";
	}

	public static String deleteStudentRecordInDB(int studentId) {
		StudentBean stuObj = null;

		for (Iterator<StudentBean> iterator = studentsList.iterator(); iterator.hasNext();) {
			stuObj = iterator.next();
			if (stuObj.getId() == studentId) {
				iterator.remove();
			}
		}
		return "/studentsList.xhtml?faces-redirect=true";
	}

	public static String editStudentRecordInDB(int studentId) {
		StudentBean stuObj = null;
		/* Setting The Particular Student Details In Session */
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		for (Iterator<StudentBean> iterator = studentsList.iterator(); iterator.hasNext();) {
			stuObj = iterator.next();
			if (stuObj.getId() == studentId) {
				sessionMapObj.put("editRecordObj", stuObj);
				break;
			}
		}

		return "/editStudent.xhtml?faces-redirect=true";
	}

	public static String updateStudentDetailsInDB(StudentBean updateStudentObj) {
		StudentBean stuObj = null;

		for (Iterator<StudentBean> iterator = studentsList.iterator(); iterator.hasNext();) {
			stuObj = iterator.next();
			if (stuObj.getId() == updateStudentObj.getId()) {
				stuObj.setName(updateStudentObj.getName());
				stuObj.setGender(updateStudentObj.getGender());
				stuObj.setAddress(updateStudentObj.getAddress());
				break;
			}
		}

		return "/studentsList.xhtml?faces-redirect=true";
	}

}
