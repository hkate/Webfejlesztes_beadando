package com.example.webfejlesztes_beadando.car;

import com.example.webfejlesztes_beadando.user.User;
import com.example.webfejlesztes_beadando.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CarController {
    @Autowired private CarService service;
    @Autowired private UserService userservice;

    @GetMapping("/cars")
    public String showCarList(Model model){
        List<Car> listCars = service.listAll();
        model.addAttribute("listCars", listCars);

        return "cars";
    }

    @GetMapping("/cars/new")
    public String showNewForm(Model model){
        List<User> userList = userservice.listAll();
        model.addAttribute("car", new Car());
        model.addAttribute("userList", userList);
        model.addAttribute("pageTitle","Add New Car");
        return "car_form";
    }

    @PostMapping("/cars/save")
    public String saveCar(Car car, RedirectAttributes ra){
        service.save(car);
        ra.addFlashAttribute("message", "The car has been save succesfully");
        return "redirect:/cars";
    }

    @GetMapping("/cars/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Car car = service.get(id);
            List<User> userList = userservice.listAll();
            model.addAttribute("car", car);
            model.addAttribute("userList", userList);
            model.addAttribute("pageTitle", "Edit Car (ID: " + id + ")");

            return "car_form";
        } catch (CarNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/cars";
        }
    }

    @GetMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The car ID "+ id + " has been deleted");
        } catch (CarNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/cars";
    }


}