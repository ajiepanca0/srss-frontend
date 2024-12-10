package com.srss_frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.srss_frontend.base.model.Status;
import com.srss_frontend.exception.ServiceException;
import com.srss_frontend.patient.model.Patient;
import com.srss_frontend.patient.model.PatientResponse;
import com.srss_frontend.process.PatientProcess;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientProcess pasienProcess;

	@GetMapping("/")
	public String getAllPatient(Model model, RedirectAttributes redirectAttributes) {

		PatientResponse patientResponse = new PatientResponse();

		try {
			
			patientResponse = pasienProcess.getAllPatient();
			model.addAttribute("patients", patientResponse.getPatient());			

		} catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("patients", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("patients", null);
		}

		return "patient/showAll";
	}

	
	@GetMapping("/add")
	public String addPatient() {
		return "patient/add";
	}
	
	
	@PostMapping("/add")
	public String addPatient(@ModelAttribute Patient patient, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = pasienProcess.savePatient(patient);

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

		return "redirect:/patient/";
	}

	
	
	@GetMapping("/detail")
	public String detailPatient(@RequestParam Long id, Model model) {

		Patient patient = new Patient();

		try {
			
			patient = pasienProcess.getPatientById(id).getPatient().get(0);
			
			
			if (patient == null) {
				return "redirect:/patient/";
			}
			
			model.addAttribute("patient", patient);

			
		}
		
		catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("patient", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("patient", null);
		}

		return "patient/detail";
	}

	
	
	@GetMapping("/update")
	public String updatePatient(@RequestParam Long id, Model model) {

		Patient patient = new Patient();

		try {
			
			patient = pasienProcess.getPatientById(id).getPatient().get(0);
			
			if (patient == null) {
				return "redirect:/patient/getAll";
			}
			
			model.addAttribute("patient", patient);

		} 
		
		catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("patient", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("patient", null);
		}

		
		return "patient/edit";
	}

	@PostMapping("/update")
	public String updatePatient(@ModelAttribute Patient patient, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = pasienProcess.updatePatient(patient);


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

		return "redirect:/patient/";
	}

	
	
	@PostMapping("/delete")
	public String deletePatientById(@ModelAttribute Patient patient, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = pasienProcess.deletePatientById(patient.getIdPasien());
		
				
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

		return "redirect:/patient/";
	}

}
