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
import com.srss_frontend.exception.ServiceException;
import com.srss_frontend.room.model.Room;
import com.srss_frontend.room.model.RoomResponse;
import com.srss_frontend.room.process.RoomProcess;


@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomProcess roomProcess;

	@GetMapping("/")
	public String getAllRoom(Model model, RedirectAttributes redirectAttributes) {

		RoomResponse roomResponse = new RoomResponse();

		try {
			
			roomResponse = roomProcess.getAllRoom();
			model.addAttribute("rooms", roomResponse.getRoom());			

		} catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("rooms", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("rooms", null);
		}

		return "room/showAll";
	}

	
	@GetMapping("/add")
	public String addRoom() {
		return "room/add";
	}
	
	
	@PostMapping("/add")
	public String addRoom(@ModelAttribute Room room, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = roomProcess.saveRoom(room);

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

		return "redirect:/room/";
	}

	
	
	@GetMapping("/detail")
	public String detailRoom(@RequestParam Long id, Model model) {

		Room room = new Room();

		try {
			
			room = roomProcess.getRoomById(id).getRoom().get(0);
			
			
			if (room == null) {
				return "redirect:/room/";
			}
			
			model.addAttribute("room", room);

			
		}
		
		catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("room", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("room", null);
		}

		return "room/detail";
	}

	
	
	@GetMapping("/update")
	public String updateRoom(@RequestParam Long id, Model model) {

		Room room = new Room();

		try {
			
			room = roomProcess.getRoomById(id).getRoom().get(0);
			
			if (room == null) {
				return "redirect:/room/getAll";
			}
			
			model.addAttribute("room", room);

		} 
		
		catch (ServiceException se) {

			System.out.print("ServiceException : " + se.getMessage());

			model.addAttribute("room", null);

		}

		catch (Exception e) {

			System.out.print("Exception : " + e.getMessage());
			model.addAttribute("room", null);
		}

		
		return "room/edit";
	}

	@PostMapping("/update")
	public String updateRoom(@ModelAttribute Room room, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = roomProcess.updateRoom(room);


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

		return "redirect:/room/";
	}

	
	
	@PostMapping("/delete")
	public String deleteRoomById(@ModelAttribute Room room, RedirectAttributes redirectAttributes) {

		Status status = new Status();

		try {

			status = roomProcess.deleteRoomById(room.getIdRoom());
		
				
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

		return "redirect:/room/";
	}

}
