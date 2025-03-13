package com.motorsports.car;

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
    @Autowired
    private CarService service;

    @GetMapping("/cars")
    public String showCarList(Model model) {
        List<Car> listCars = service.listALL();
        model.addAttribute("listCars", listCars); // список автомобилей в модель
        return "cars";
    }

    @GetMapping("/cars/add")
    public String showAddForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("pageTitle", "Добавить авто");
        return "add_auto";
    }

    @PostMapping("/cars/save")
    public String saveCar(Car car, RedirectAttributes ra) {
        service.save(car);
        ra.addFlashAttribute("message", "Успешно!");
        return "redirect:/cars";
    }


    @GetMapping("/cars/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model, RedirectAttributes ra) {
        try {
            Car product = service.get(id);
            model.addAttribute("car", product);
            model.addAttribute("pageTitle", "Редактирование информации об авто \n" +
                    "ID:"+ id);
            return "add_auto";
        } catch (CarFoundErrorException e) {
            ra.addFlashAttribute("message", "Успешно!");
            return "redirect:/cars";
        }
    }

    @GetMapping("/cars/delete/{id}")
    public String deleteSelectedCar(@PathVariable("id") int id, Model model, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "Авто с ID: "  + id + " было удалено из базы!");
        } catch (CarFoundErrorException e) {
            ra.addFlashAttribute("message", "Авто с таким ID: " + id + "не было обнаружено!");
        }
        return "redirect:/cars";
    }

}