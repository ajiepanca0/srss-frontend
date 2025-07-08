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

import com.srss_frontend.base.model.Status;
import com.srss_frontend.doctor.model.DoctorResponse;
import com.srss_frontend.doctor.process.DoctorProcess;
import com.srss_frontend.exception.ServiceException;
import com.srss_frontend.outpatient.model.Outpatient;
import com.srss_frontend.outpatient.model.OutpatientResponse;
import com.srss_frontend.outpatient.process.OutpatientProcess;
import com.srss_frontend.patient.model.PatientResponse;
import com.srss_frontend.patient.process.PatientProcess;
import com.srss_frontend.room.model.RoomResponse;
import com.srss_frontend.room.process.RoomProcess;

@Controller
@RequestMapping("/outpatient")
public class OutpatientController {

	@Autowired
	private OutpatientProcess outpatientProcess;
	
	@Autowired
	private PatientProcess patientProcess;
	
	@Autowired
	private DoctorProcess doctorProcess;
	
	@Autowired
	private RoomProcess roomProcess;
	
	@GetMapping("/")
	public String getAllOutpatient(Model model, RedirectAttributes redirectAttributes) {

		OutpatientResponse outpatientResponse = new OutpatientResponse();


		try {
			
			outpatientResponse = outpatientProcess.getAllOutpatient();
			model.addAttribute("outpatients", outpatientResponse.getOutpatient());			

		} catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("outpatients", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("outpatients", null);
		}

		return "outpatient/showAll";
	}

	
	@GetMapping("/add")
	public String addOutpatient(Model model, RedirectAttributes redirectAttributes) throws Exception {
		
		PatientResponse patientResponse = new PatientResponse();
		DoctorResponse doctorResponse = new DoctorResponse();


		patientResponse = patientProcess.getAllPatient();
		doctorResponse = doctorProcess.getAllDoctor();
		
		model.addAttribute("outpatient", new Outpatient());
		model.addAttribute("patients", patientResponse.getPatient());	
		model.addAttribute("doctors", doctorResponse.getDoctor());			


		return "outpatient/add";
	}
	
	
	@PostMapping("/add")
	public String addOutpatient(@ModelAttribute Outpatient outpatient, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {
			status = outpatientProcess.saveOutpatient(outpatient);

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

		return "redirect:/outpatient/";
	}

	
	
	@GetMapping("/detail")
	public String detailOutpatient(@RequestParam Long id, Model model) {

		Outpatient outpatient = new Outpatient();

		try {
			
			outpatient = outpatientProcess.getOutpatientById(id).getOutpatient().get(0);
			
			
			if (outpatient == null) {
				return "redirect:/outpatient/";
			}
						
			model.addAttribute("outpatient", outpatient);

			
		}
		
		catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("outpatient", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("outpatient", null);
		}

		return "outpatient/detail";
	}

	
	
	@GetMapping("/update")
	public String updateOutpatient(@RequestParam Long id, Model model) throws Exception {

		Outpatient outpatient = new Outpatient();
		
		PatientResponse patientResponse = new PatientResponse();
		DoctorResponse doctorResponse = new DoctorResponse();

		patientResponse = patientProcess.getAllPatient();
		doctorResponse = doctorProcess.getAllDoctor();

		try {
			
			outpatient = outpatientProcess.getOutpatientById(id).getOutpatient().get(0);
			
			if (outpatient == null) {
				return "redirect:/outpatient/getAll";
			}
			
			model.addAttribute("outpatient", outpatient);
			model.addAttribute("patients", patientResponse.getPatient());	
			model.addAttribute("doctors", doctorResponse.getDoctor());			

		} 
		
		catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("outpatient", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("outpatient", null);
		}

		
		return "outpatient/edit";
	}

	@PostMapping("/update")
	public String updateOutpatient(@ModelAttribute Outpatient outpatient, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = outpatientProcess.updateOutpatient(outpatient);
			
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

		return "redirect:/outpatient/";
	}

	
	
	@PostMapping("/delete")
	public String deleteOutpatientById(@ModelAttribute Outpatient outpatient, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = outpatientProcess.deleteOutpatientById(outpatient.getOutpatientId());
		
				
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

		return "redirect:/outpatient/";
	}

}
