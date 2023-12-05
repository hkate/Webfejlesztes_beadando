package com.example.webfejlesztes_beadando.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired private CarRepository repo;

    public List<Car> listAll(){
        return (List<Car>) repo.findAll();
    }

    public void save(Car car) {
        repo.save(car);
    }

    public Car get(Integer id) throws CarNotFoundException {
        Optional<Car> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new CarNotFoundException("Could not find any cars with ID" + id);
    }

    public void delete(Integer id) throws CarNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new CarNotFoundException("Could not find any cars with ID" + id);
        }
        repo.deleteById(id);
    }
}