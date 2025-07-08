package com.srss_frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.srss_frontend.base.model.Status;
import com.srss_frontend.doctor.model.DoctorResponse;
import com.srss_frontend.doctor.process.DoctorProcess;
import com.srss_frontend.exception.ServiceException;
import com.srss_frontend.inpatient.model.Inpatient;
import com.srss_frontend.inpatient.model.InpatientResponse;
import com.srss_frontend.inpatient.process.InpatientProcess;
import com.srss_frontend.patient.model.PatientResponse;
import com.srss_frontend.patient.process.PatientProcess;
import com.srss_frontend.room.model.RoomResponse;
import com.srss_frontend.room.process.RoomProcess;

@Controller
@RequestMapping("/inpatient")
public class InpatientController {

	@Autowired
	private InpatientProcess inpatientProcess;
	
	@Autowired
	private PatientProcess patientProcess;
	
	@Autowired
	private DoctorProcess doctorProcess;
	
	@Autowired
	private RoomProcess roomProcess;
	
	@GetMapping("/")
	public String getAllInpatient(Model model, RedirectAttributes redirectAttributes) {

		InpatientResponse inpatientResponse = new InpatientResponse();


		try {
			
			inpatientResponse = inpatientProcess.getAllInpatient();
			model.addAttribute("inpatients", inpatientResponse.getInpatient());			

		} catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("inpatients", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("inpatients", null);
		}

		return "inpatient/showAll";
	}

	
	@GetMapping("/add")
	public String addInpatient(Model model, RedirectAttributes redirectAttributes) throws Exception {
		
		PatientResponse patientResponse = new PatientResponse();
		DoctorResponse doctorResponse = new DoctorResponse();
		RoomResponse roomResponse = new RoomResponse();


		patientResponse = patientProcess.getAllPatient();
		doctorResponse = doctorProcess.getAllDoctor();
		roomResponse = roomProcess.getAllRoom();
		
		model.addAttribute("inpatient", new Inpatient());
		model.addAttribute("patients", patientResponse.getPatient());	
		model.addAttribute("doctors", doctorResponse.getDoctor());			
		model.addAttribute("rooms", roomResponse.getRoom());			


		return "inpatient/add";
	}
	
	
	@PostMapping("/add")
	public String addInpatient(@ModelAttribute Inpatient inpatient, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {
			
			System.out.println("Doctor Id: "+inpatient.getDoctor().getDoctorId());
			System.out.println("Doctor Name : "+inpatient.getDoctor().getDoctorName());

			
			status = inpatientProcess.saveInpatient(inpatient);

			redirectAttributes.addFlashAttribute("toastMessage", status.getResponseMessage());
			redirectAttributes.addFlashAttribute("toastType", "success");

		} catch (ServiceException se) {

			redirectAttributes.addFlashAttribute("toastMessage", se.getMessage());
			redirectAttributes.addFlashAttribute("toastType", "failed");

		} catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());

			redirectAttributes.addFlashAttribute("toastMessage", "We are having server's problem. Sorry for this inconvenience");
			redirectAttributes.addFlashAttribute("toastType", "failed");

		}

		return "redirect:/inpatient/";
	}

	
	
	@GetMapping("/detail")
	public String detailInpatient(@RequestParam Long id, Model model) {

		Inpatient inpatient = new Inpatient();

		try {
			
			inpatient = inpatientProcess.getInpatientById(id).getInpatient().get(0);
			
			
			if (inpatient == null) {
				return "redirect:/inpatient/";
			}
						
			model.addAttribute("inpatient", inpatient);

			
		}
		
		catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("inpatient", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("inpatient", null);
		}

		return "inpatient/detail";
	}

	
	
	@GetMapping("/update")
	public String updateInpatient(@RequestParam Long id, Model model) throws Exception {

		Inpatient inpatient = new Inpatient();
		
		PatientResponse patientResponse = new PatientResponse();
		DoctorResponse doctorResponse = new DoctorResponse();
		RoomResponse roomResponse = new RoomResponse();

		patientResponse = patientProcess.getAllPatient();
		doctorResponse = doctorProcess.getAllDoctor();
		roomResponse = roomProcess.getAllRoom();

		try {
			
			inpatient = inpatientProcess.getInpatientById(id).getInpatient().get(0);
			
			if (inpatient == null) {
				return "redirect:/inpatient/getAll";
			}
			
			model.addAttribute("inpatient", inpatient);
			model.addAttribute("patients", patientResponse.getPatient());	
			model.addAttribute("doctors", doctorResponse.getDoctor());			
			model.addAttribute("rooms", roomResponse.getRoom());		

		} 
		
		catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("inpatient", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("inpatient", null);
		}

		
		return "inpatient/edit";
	}

	@PostMapping("/update")
	public String updateInpatient(@ModelAttribute Inpatient inpatient, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = inpatientProcess.updateInpatient(inpatient);
			
			redirectAttributes.addFlashAttribute("toastMessage", status.getResponseMessage());
			redirectAttributes.addFlashAttribute("toastType", "success");

		} catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			redirectAttributes.addFlashAttribute("toastMessage", se.getMessage());
			redirectAttributes.addFlashAttribute("toastType", "failed");

		} catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());

			redirectAttributes.addFlashAttribute("toastMessage", "We are having server's problem. Sorry for this inconvenience");
			redirectAttributes.addFlashAttribute("toastType", "failed");

		}

		return "redirect:/inpatient/";
	}

	
	
	@PostMapping("/delete")
	public String deleteInpatientById(@ModelAttribute Inpatient inpatient, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = inpatientProcess.deleteInpatientById(inpatient.getInpatientId());
		
				
			redirectAttributes.addFlashAttribute("toastMessage", status.getResponseMessage());
			redirectAttributes.addFlashAttribute("toastType", "success");

			
		} catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			redirectAttributes.addFlashAttribute("toastMessage", se.getMessage());
			redirectAttributes.addFlashAttribute("toastType", "failed");

		} catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());

			redirectAttributes.addFlashAttribute("toastMessage", "We are having server's problem. Sorry for this inconvenience");
			redirectAttributes.addFlashAttribute("toastType", "failed");

		}

		return "redirect:/inpatient/";
	}

}
