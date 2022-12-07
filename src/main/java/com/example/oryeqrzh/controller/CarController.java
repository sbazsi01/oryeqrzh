package com.example.oryeqrzh.controller;

import javax.validation.Valid;

import com.example.oryeqrzh.exceptions.CarAlreadyExistException;
import com.example.oryeqrzh.model.Car;
import com.example.oryeqrzh.repository.CarRepository;
import com.example.oryeqrzh.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
//tablak: cars, telephelyek,
//auth,roles,email kuldes
//

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    EmailService emailService;

    @RequestMapping("/email/{email}")
    public String sendEmail(@PathVariable(value = "email") String email) throws Exception {
        String regex= "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            return "Hibas email";
        }
        emailService.sendMessage(email);
        return "redirect:/";
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("pageTitle","Cars");
        model.addAttribute("cars",
                carRepository.findAll());

        return "car";
    }

    @GetMapping("/{id}")
    public String getCarById(@PathVariable("id") long id,Model model){
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()){
            model.addAttribute("pageTitle","Cars");
            model.addAttribute("cars",
                    car.get());
        }
        return "car";
    }

    @GetMapping("/showcreateform")
    public String showCreateForm(Model model){
        model.addAttribute("car",new Car());
        return "add-car";
    }
    @PostMapping("/addcar")
    public String addCar( Car car, BindingResult result,Model model) throws Exception{
        if(result.hasErrors()){
            return "add-car";
        }
            Car a2 = carRepository.findByModel(car.getModel());
            if (a2 != null){
                return "add-car";
            }
            Car a1 = new Car();
            a1.setBrand(car.getBrand());
            a1.setModel(car.getModel());
            a1.setHp(car.getHp());
            try {
                carRepository.save(a1);

            } catch (Exception e){
                return "add-car";
            }
            return  "redirect:/";
    }
    @GetMapping("/showformupdate/{id}")
    public String showUpdateForm(@PathVariable long id, Model model){
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()){
            model.addAttribute("car",car.get());
            return "update-car";
        }
        return "not-found";
    }
    @PostMapping("/updatecar/{id}")
    public String updateCar(@PathVariable long id,@Valid Car car,
                             BindingResult result) throws Exception{
        if(result.hasErrors()){
            return "update-car";
        }
        Optional<Car> car1 = carRepository.findById(id);
        if (car1.isPresent()){
            Car _car = car1.get();
            _car.setBrand(car.getBrand());
            _car.setModel(car.getModel());
            _car.setHp(car.getHp());
            try {
                carRepository.save(_car);

            } catch (Exception e){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
            }
            return "redirect:/";
        }
        return  "not-found";
    }
    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") long id){
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()){
            try {
             carRepository.deleteById(id);

            } catch (Exception e){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
            }

        }
        return "redirect:/";

    }
}
