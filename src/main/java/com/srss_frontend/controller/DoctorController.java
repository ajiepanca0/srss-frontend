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
import com.srss_frontend.doctor.model.Doctor;
import com.srss_frontend.doctor.model.DoctorResponse;
import com.srss_frontend.doctor.process.DoctorProcess;
import com.srss_frontend.exception.ServiceException;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorProcess doctorProcess;

	@GetMapping("/")
	public String getAllDoctor(Model model, RedirectAttributes redirectAttributes) {

		DoctorResponse doctorResponse = new DoctorResponse();

		try {
			
			doctorResponse = doctorProcess.getAllDoctor();
			model.addAttribute("doctors", doctorResponse.getDoctor());	

		} catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("doctors", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("doctors", null);
		}

		return "doctor/showAll";
	}

	
	@GetMapping("/add")
	public String addDoctor() {
		return "doctor/add";
	}
	
	
	@PostMapping("/add")
	public String addDoctor(@ModelAttribute Doctor doctor, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = doctorProcess.saveDoctor(doctor);

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

		return "redirect:/doctor/";
	}

	
	
	@GetMapping("/detail")
	public String detailDoctor(@RequestParam Long id, Model model) {

		Doctor doctor = new Doctor();

		try {
			
			doctor = doctorProcess.getDoctorById(id).getDoctor().get(0);
			
			
			if (doctor == null) {
				return "redirect:/doctor/";
			}
			
			model.addAttribute("doctor", doctor);

			
		}
		
		catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("doctor", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("doctor", null);
		}

		return "doctor/detail";
	}

	
	
	@GetMapping("/update")
	public String updateDoctor(@RequestParam Long id, Model model) {

		Doctor doctor = new Doctor();

		try {
			
			doctor = doctorProcess.getDoctorById(id).getDoctor().get(0);
			
			if (doctor == null) {
				return "redirect:/doctor/getAll";
			}
			
			model.addAttribute("doctor", doctor);

		} 
		
		catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("doctor", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("doctor", null);
		}

		
		return "doctor/edit";
	}

	@PostMapping("/update")
	public String updateDoctor(@ModelAttribute Doctor doctor, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = doctorProcess.updateDoctor(doctor);


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

		return "redirect:/doctor/";
	}

	
	
	@PostMapping("/delete")
	public String deletePatientById(@ModelAttribute Doctor doctor, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = doctorProcess.deleteDoctorById(doctor.getIdDokter());
		
				
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

		return "redirect:/doctor/";
	}

}
